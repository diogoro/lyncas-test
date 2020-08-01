package dev.diogoro.lyncastest.controller;

import java.util.Date;

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

}
