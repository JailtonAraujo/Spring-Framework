package br.com.spring_date_starter;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.spring_date_starter.dao.InterfacePessoa;
import br.com.spring_date_starter.dao.TelefoneRepository;
import br.com.spring_date_starter.model.Pessoa;
import br.com.spring_date_starter.model.Telefone;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring-config.xml"})
public class appTestjpa {
	
	@Autowired
	private InterfacePessoa interfacePessoa;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Test
	public void testInsert() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setIdade(30);
		pessoa.setNome("Ricardo dias");
		pessoa.setSobrenome("Marcos dfgfd");
		
		interfacePessoa.save(pessoa);
		
	}
	
	@Test
	public void testeConsulta() {
		
		Optional<Pessoa> pessoa = interfacePessoa.findById(1L);
		
		System.out.println(pessoa.get().getId());
		System.out.println(pessoa.get().getNome());
		System.out.println(pessoa.get().getSobrenome());
		System.out.println(pessoa.get().getIdade());
	}
	
	@Test
	public void consultaTodos() {
		
		Iterable<Pessoa> list = interfacePessoa.findAll();
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa.getId());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getSobrenome());
			System.out.println(pessoa.getIdade());
			System.out.println("====================================");
		}
	}
	
	@Test
	public void testUpdate() {
		
		Optional<Pessoa> pessoa = interfacePessoa.findById(3L);
		
		Pessoa data = pessoa.get();
		
		data.setNome("Fabio");
		
		interfacePessoa.save(data);
		
	}
	
	@Test
	public void testDelete() {
		
		interfacePessoa.deleteById(3L);
	}
	
	@Test
	public void testConsultarPorNome() {
		List<Pessoa> list = interfacePessoa.buscaPorNome("ricardo");
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa.getId());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getSobrenome());
			System.out.println(pessoa.getIdade());
			System.out.println("====================================");
		}
	}
	
	@Test
	public void testBuscaExata() {
		Pessoa pessoa = interfacePessoa.buscaExata("Jailton");
		
		System.out.println(pessoa.getId());
		System.out.println(pessoa.getNome());
		System.out.println(pessoa.getSobrenome());
		System.out.println(pessoa.getIdade());
	}
	
	@Test
	public void testDeletarPorNome() {
		interfacePessoa.deletarPorNome("Ricardo");
	}
	
	@Test
	public void testeUpdateComClausura() {
		interfacePessoa.updatePorNome("Araujo Santos", "Jailton");
	}
	
	@Test
	public void testInsertTelefone() {
		Telefone telefone = new Telefone();
		
		Optional<Pessoa> pessoa = interfacePessoa.findById(1L);
		
		telefone.setNumero("4654556");
		telefone.setPessoa(pessoa.get());
		
		telefoneRepository.save(telefone);
	}
}
