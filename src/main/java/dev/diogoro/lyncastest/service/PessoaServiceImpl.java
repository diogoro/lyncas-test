package dev.diogoro.lyncastest.service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import dev.diogoro.lyncastest.controller.PessoaNaoEncontradaException;
import dev.diogoro.lyncastest.domain.Pessoa;
import dev.diogoro.lyncastest.mappers.PessoaMapper;
import dev.diogoro.lyncastest.model.PessoaDto;
import dev.diogoro.lyncastest.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {
	
	private final PessoaRepository pessoaRepository;
	private final PessoaMapper pessoaMapper;

	@Override
	public PessoaDto obterPessoaPorId(UUID idPessoa) {
		return pessoaMapper.pessoaParaPessoaDto(pessoaRepository.findById(idPessoa).orElseThrow(PessoaNaoEncontradaException::new));
	}

	@Override
	public PessoaDto cadastrarPessoa(@Valid PessoaDto pessoa) {
		return pessoaMapper.pessoaParaPessoaDto(pessoaRepository.save(pessoaMapper.pessoaDtoParaPessoa(pessoa)));
	}

	@Override
	public PessoaDto atualizarPessoa(UUID idPessoa, @Valid PessoaDto pessoaDto) {
		Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(PessoaNaoEncontradaException::new);
		
		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setDataNascimento((Date) pessoaDto.getDataNascimento());
		pessoa.setEmail(pessoaDto.getEmail());
		pessoa.setNaturalidade(pessoa.getNaturalidade());
		pessoa.setSexo(pessoaDto.getSexo());
		
		return pessoaMapper.pessoaParaPessoaDto(pessoaRepository.save(pessoa));
	}

	@Override
	public void apagarPessoa(UUID idPessoa) {
		pessoaRepository.deleteById(idPessoa);
	}

	@Override
	public List<PessoaDto> obterListaPessoas() {
		return (List<PessoaDto>) pessoaRepository.findAll().stream().map(mapper -> pessoaMapper.pessoaParaPessoaDto(mapper));
	}

}