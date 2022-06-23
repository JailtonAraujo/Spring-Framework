package com.br.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.ApplicationContextLoad;
import com.br.DTO.TokenDTO;
import com.br.entity.User;
import com.br.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final long EXPIRATION_TIME = 172800000;

	private static final String SECRET = "b2cc3dcf-817f-4c34-a58c-8ef631a3a12e";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, String userName) throws IOException {

		User user = ApplicationContextLoad.getApplicationContext().getBean(UserRepository.class)
				.findByUsername(userName).get();

		Date now = new Date();
		Date exp = new Date(now.getTime() + EXPIRATION_TIME);

		String JWT = Jwts.builder().setIssuer("myapp").setSubject(user.getId().toString()).setIssuedAt(now)
				.setExpiration(exp).signWith(SignatureAlgorithm.HS512, SECRET).compact();

		TokenDTO token = TokenDTO.builder().Autorization(JWT).type(TOKEN_PREFIX).nameUser(user.getName()).build();

		ObjectMapper mapper = new ObjectMapper();

		String tokenResponse = mapper.writeValueAsString(token);

		response.getWriter().write(tokenResponse);
	}

	public Authentication getAuthentication(HttpServletRequest request) {

		try {
			

			String token = request.getHeader(HEADER_STRING);

			if (token != null && !token.trim().isEmpty() && token.startsWith("Bearer ")) {

				String tokenClear = token.replace("Bearer", "").trim();

				String idUser = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenClear).getBody().getSubject();

				if (idUser != null) {
					Optional<User> user = ApplicationContextLoad.getApplicationContext().getBean(UserRepository.class)
							.findById(Integer.valueOf(idUser));

					if (user.isPresent()) {
						return new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword(),
								user.get().getAuthorities());
					}
				}

			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		return null;
		
	}
}
