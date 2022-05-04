package br.com.spring_date_starter.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.spring_date_starter.model.Telefone;

public interface TelefoneRepository extends CrudRepository<Telefone, Long>{

}
