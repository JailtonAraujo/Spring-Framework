package br.com.spring_date_starter.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.spring_date_starter.model.Pessoa;

public interface InterfacePessoa extends CrudRepository<Pessoa, Long>{
	
	
}
