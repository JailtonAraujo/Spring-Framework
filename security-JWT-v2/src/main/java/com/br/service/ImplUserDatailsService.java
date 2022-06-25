package com.br.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.br.data.UserDatailsData;
import com.br.entity.Usuario;
import com.br.repository.UsuarioRepository;

@Component
public class ImplUserDatailsService implements UserDetailsService{

	private final UsuarioRepository usuarioRepository;
	
	public ImplUserDatailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("User ["+usuario.get().getUsername()+"] not found!");
		}
		
		return new UserDatailsData(usuario);
	}

}
