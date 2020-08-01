package dev.diogoro.lyncastest.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import dev.diogoro.lyncastest.model.PessoaDto;

public interface PessoaService {

	PessoaDto obterPessoaPorId(UUID idPessoa);

	PessoaDto cadastrarPessoa(@Valid PessoaDto pessoa);

	PessoaDto atualizarPessoa(UUID idPessoa, @Valid PessoaDto pessoa);

	void apagarPessoa(UUID idPessoa);

	List<PessoaDto> obterListaPessoas();

}
