package dev.diogoro.lyncastest.mappers;

import org.mapstruct.Mapper;

import dev.diogoro.lyncastest.domain.Pessoa;
import dev.diogoro.lyncastest.model.PessoaDto;

@Mapper(uses = DateMapper.class)
public interface PessoaMapper {
	Pessoa pessoaDtoParaPessoa(PessoaDto pessoaDto);
	
	PessoaDto pessoaParaPessoaDto(Pessoa pessoa);
}
