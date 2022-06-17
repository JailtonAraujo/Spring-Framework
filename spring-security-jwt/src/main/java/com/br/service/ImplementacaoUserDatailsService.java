package com.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.model.Usuario;
import com.br.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDatailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	//Consultar usuario no banco//
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByLogin(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Login ou senha incorretos");
		}
		
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}

}
