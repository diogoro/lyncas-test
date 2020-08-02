package dev.diogoro.lyncastest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.diogoro.lyncastest.BaseTest;
import dev.diogoro.lyncastest.exception.PessoaNaoEncontradaException;
import dev.diogoro.lyncastest.model.PessoaDto;

@SpringBootTest
@ActiveProfiles("testH2")
public class PessoaServiceImplTest extends BaseTest {

	@Autowired
	PessoaServiceImpl pessoaService;
	
	/**
	 * Teste usando o banco de dados H2
	 */

	@Test
	public void cadastrarPessoaTest() {
		PessoaDto pessoaTest = obterPessoaServiceTest();

		PessoaDto pessoaSalva = pessoaService.cadastrarPessoa(pessoaTest);

		PessoaDto pessoaBanco = pessoaService.obterPessoaPorId(pessoaSalva.getId());

		pessoaService.apagarPessoa(pessoaSalva.getId());

		assertThat(pessoaBanco.equals(pessoaSalva));
	}

	@Test
	public void atualizarPessoaTest() {
		PessoaDto pessoaTeste = obterPessoaServiceTest();
		PessoaDto pessoaTesteSalva = pessoaService.cadastrarPessoa(pessoaTeste);
		pessoaTeste.setNaturalidade("Catarinense");
		pessoaService.atualizarPessoa(pessoaTesteSalva.getId(), pessoaTeste);

		PessoaDto pessoaTesteAtualiazada = pessoaService.obterPessoaPorId(pessoaTesteSalva.getId());

		pessoaService.apagarPessoa(pessoaTesteSalva.getId());
		assertEquals(pessoaTesteSalva.getId(), pessoaTesteAtualiazada.getId());
		assertEquals(pessoaTeste.getNaturalidade(), pessoaTesteAtualiazada.getNaturalidade());
	}

	@Test
	public void apagarPessoaTest() {
		List<PessoaDto> lista = pessoaService.obterListaPessoas();

		assertFalse(lista.size() == 0);
		PessoaDto pessoa = lista.get(0);

		pessoaService.apagarPessoa(pessoa.getId());

		assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.obterPessoaPorId(pessoa.getId()));
	}
}
