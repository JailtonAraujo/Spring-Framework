package br.com.spring_date_starter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.spring_date_starter.model.Pessoa;

public interface InterfacePessoa extends CrudRepository<Pessoa, Long>{
	
	@Query(value = "select p from Pessoa p where p.nome like ?1%")
	public List<Pessoa> buscaPorNome(String nome);
	
	@Query(value = "select new br.com.spring_date_starter.model.Pessoa (p.id, p.nome) from Pessoa p where p.nome = :nome")
	public Pessoa buscaExata(@Param("nome") String nome);
}
