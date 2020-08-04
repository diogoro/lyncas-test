package dev.diogoro.lyncastest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import dev.diogoro.lyncastest.BaseTest;
import dev.diogoro.lyncastest.domain.Pessoa;

@DataJpaTest
@ActiveProfiles("testH2")
class PessoaRepositoryTest extends BaseTest{

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	PessoaRepository pessoaRepository;

	@Test
	void testFindById() {
		Pessoa pessoa = obterPessoaTest();
		
		Pessoa pessoaSalva = entityManager.persistAndFlush(pessoa);
		
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(pessoaSalva.getId());
		
		assertTrue(pessoaEncontrada.isPresent());
		
		pessoa.setId(pessoaSalva.getId());
		
		assertThat(pessoaEncontrada.get().equals(pessoa));
		
		entityManager.remove(pessoaSalva);
	}
	
	@Test
	void testSave() {
		Pessoa pessoa = obterPessoaTest();
		
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		Pessoa pessoaEncotradaNaBase = entityManager.find(Pessoa.class, pessoaSalva.getId());
		
		assertNotNull(pessoaSalva, "Nao conseguiu salvar no banco");
		
		assertNotNull(pessoaEncotradaNaBase, "Nao encontrou nada na base de dados");
		
		pessoa.setId(pessoaSalva.getId());
		
		assertThat(pessoaEncotradaNaBase.equals(pessoa));
		
		entityManager.remove(pessoaEncotradaNaBase);
	}
	
	@Test
	void delete() {
		Pessoa pessoa = obterPessoaTest();
		
		Pessoa pessoaSalva = entityManager.persistAndFlush(pessoa);
		
		pessoaRepository.deleteById(pessoaSalva.getId());
		
		Pessoa pessoaEncotradaNaBase = entityManager.find(Pessoa.class, pessoaSalva.getId());
		
		assertNull(pessoaEncotradaNaBase);
	}

}
