package dev.diogoro.lyncastest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.diogoro.lyncastest.domain.Pessoa;
import dev.diogoro.lyncastest.model.PessoaDto;

public class BaseTest {
	
	protected PessoaDto obterPessoaValida() {
		return PessoaDto.builder()
				.cpf("26748887844")
				.nome("Fulado de Tal")
				.dataNascimento(new Date())
				.email("fulano@xyz.com")
				.build();
	}
	
	protected List<PessoaDto> obterListaPessoasValidas() {
		List<PessoaDto> lista = new ArrayList<>();
		lista.add(obterPessoaValida());
		lista.add(PessoaDto.builder()
				.cpf("46262014829")
				.nome("Sicrano de Tal")
				.dataNascimento(new Date())
				.email("sicrano@xyz.com")
				.build());
		return lista;
	}
	
	protected PessoaDto obterPessoaEmailInvalido() {
		return PessoaDto.builder()
				.cpf("26748887844")
				.nome("Fulado de Tal")
				.dataNascimento(new Date())
				.email("fulano.xyz.com")
				.build();
	}
	
	protected Pessoa obterPessoaTest() {
		return Pessoa.builder()
				.cpf("26748887844")
				.nome("Sicrano de Tal")
				.dataNascimento(java.sql.Date.valueOf("2009-09-07"))
				.email("sicrano@xyz.com")
				.build();
	}

}
