package br.com.spring_date_starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.spring_date_starter.dao.InterfacePessoa;
import br.com.spring_date_starter.model.Pessoa;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring-config.xml"})
public class appTestjpa {
	
	@Autowired
	private InterfacePessoa interfacePessoa;
	
	@Test
	public void testInsert() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setIdade(23);
		pessoa.setNome("Jailton");
		pessoa.setSobrenome("Araujo");
		
		interfacePessoa.save(pessoa);
		
	}

}
