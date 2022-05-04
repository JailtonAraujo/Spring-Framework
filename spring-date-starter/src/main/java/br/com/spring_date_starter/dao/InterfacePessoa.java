package br.com.spring_date_starter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring_date_starter.model.Pessoa;

public interface InterfacePessoa extends CrudRepository<Pessoa, Long>{
	
	@Query(value = "select p from Pessoa p where p.nome like ?1%")
	public List<Pessoa> buscaPorNome(String nome);
	
	@Query(value = "select new br.com.spring_date_starter.model.Pessoa (p.id, p.nome) from Pessoa p where p.nome = :nome")
	public Pessoa buscaExata(@Param("nome") String nome);
	
	@Modifying
	@Transactional
	@Query(value = "delete from Pessoa p where p.nome = ?1")
	public void deletarPorNome(String nome);
	
	@Modifying
	@Transactional
	@Query(value = "update Pessoa p set p.sobrenome = ?1 where p.nome = ?2")
	public void updatePorNome(String sobrenome, String nome );
}
