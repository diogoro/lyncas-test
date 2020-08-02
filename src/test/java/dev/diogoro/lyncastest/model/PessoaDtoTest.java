package dev.diogoro.lyncastest.model;

import org.junit.jupiter.api.Test;

class PessoaDtoTest {

	@Test
	void testPessoaDtoCpf() {
		PessoaDto.builder()
		.cpf("11223344")
		.build();
	}

}
