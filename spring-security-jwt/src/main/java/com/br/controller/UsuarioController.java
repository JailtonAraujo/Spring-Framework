package com.br.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Usuario;
import com.br.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> getAll (){
		
		return new ResponseEntity<List<Usuario>>(usuarioRepository.findAll(),HttpStatus.OK);
	}

}
