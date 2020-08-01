package dev.diogoro.lyncastest.bootstrap;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.diogoro.lyncastest.domain.Pessoa;
import dev.diogoro.lyncastest.repository.PessoaRepository;

@Component
public class PessoaLoader implements CommandLineRunner{
	private final PessoaRepository pessoaRepository;
	
	public PessoaLoader(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		salvarPessoasNoBanco();
	}
	
	private void salvarPessoasNoBanco() {
		if (pessoaRepository.count() == 0) {
			
			pessoaRepository.save(Pessoa.builder()
					.cpf("26748887844")
					.nome("Fulado de Tal")
					.dataNascimento(Date.valueOf("1990-04-09"))
					.email("fulano@xyz.com")
					.build());
			
			pessoaRepository.save(Pessoa.builder()
					.cpf("53956164628")
					.nome("Sicrano de Tal")
					.dataNascimento(Date.valueOf("2016-12-31"))
					.email("sicrano@xyz.com")
					.build());
		}
	}
}
