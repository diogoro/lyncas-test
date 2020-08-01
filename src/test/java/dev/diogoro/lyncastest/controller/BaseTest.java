package dev.diogoro.lyncastest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

}
