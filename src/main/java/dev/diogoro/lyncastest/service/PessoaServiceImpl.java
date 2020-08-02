package dev.diogoro.lyncastest.service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import dev.diogoro.lyncastest.domain.Pessoa;
import dev.diogoro.lyncastest.exception.CpfExistenteException;
import dev.diogoro.lyncastest.exception.PessoaNaoEncontradaException;
import dev.diogoro.lyncastest.mappers.PessoaMapper;
import dev.diogoro.lyncastest.model.PessoaDto;
import dev.diogoro.lyncastest.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

	private final PessoaRepository pessoaRepository;
	private final PessoaMapper pessoaMapper;
	private final String MSG_PESSOA_NAO_ENCONTRADA = "Pessoa inexistnete";
	private final String MSG_CPF_EXISTENTE = "Cpf ja cadastrado";
	
	@Override
	public PessoaDto obterPessoaPorId(UUID idPessoa) {
		return pessoaMapper.pessoaParaPessoaDto(
				pessoaRepository.findById(idPessoa)
				.orElseThrow(() -> new PessoaNaoEncontradaException(MSG_PESSOA_NAO_ENCONTRADA)));
	}

	@Override
	public PessoaDto cadastrarPessoa(@Valid PessoaDto pessoaDto) {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		if (pessoas.stream().filter(pessoa -> pessoa.getCpf().equals(pessoaDto.getCpf())).findAny().isPresent()) {
			throw new CpfExistenteException(MSG_CPF_EXISTENTE);
		}
		return pessoaMapper.pessoaParaPessoaDto(pessoaRepository.save(pessoaMapper.pessoaDtoParaPessoa(pessoaDto)));
	}

	@Override
	public PessoaDto atualizarPessoa(UUID idPessoa, @Valid PessoaDto pessoaDto) {
		Pessoa pessoa = pessoaRepository.findById(idPessoa)
				.orElseThrow(() -> new PessoaNaoEncontradaException(MSG_PESSOA_NAO_ENCONTRADA));
		

		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setDataNascimento(new Date(pessoaDto.getDataNascimento().getTime()));
		pessoa.setEmail(pessoaDto.getEmail());
		pessoa.setNaturalidade(pessoaDto.getNaturalidade());
		pessoa.setNacionalidade(pessoaDto.getNacionalidade());
		pessoa.setSexo(pessoaDto.getSexo());

		return pessoaMapper.pessoaParaPessoaDto(pessoaRepository.save(pessoa));
	}

	@Override
	public void apagarPessoa(UUID idPessoa) {
		pessoaRepository.deleteById(idPessoa);
	}

	public List<PessoaDto> obterListaPessoas() {
		return (List<PessoaDto>) pessoaRepository.findAll().stream()
				.map(pessoa -> pessoaMapper.pessoaParaPessoaDto(pessoa)).collect(Collectors.toList());
	}

}
