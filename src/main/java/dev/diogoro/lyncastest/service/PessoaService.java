package dev.diogoro.lyncastest.service;

import java.util.List;
import java.util.UUID;

import dev.diogoro.lyncastest.model.PessoaDto;

public interface PessoaService {

	PessoaDto obterPessoaPorId(UUID idPessoa);

	PessoaDto cadastrarPessoa(PessoaDto pessoa);

	PessoaDto atualizarPessoa(UUID idPessoa, PessoaDto pessoa);

	void apagarPessoa(UUID idPessoa);

	List<PessoaDto> obterListaPessoas();

}
