package dev.diogoro.lyncastest.mappers;

import dev.diogoro.lyncastest.domain.Pessoa;
import dev.diogoro.lyncastest.domain.Pessoa.PessoaBuilder;
import dev.diogoro.lyncastest.model.PessoaDto;
import dev.diogoro.lyncastest.model.PessoaDto.PessoaDtoBuilder;
import java.sql.Date;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-31T19:01:12-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public Pessoa pessoaDtoParaPessoa(PessoaDto pessoaDto) {
        if ( pessoaDto == null ) {
            return null;
        }

        PessoaBuilder pessoa = Pessoa.builder();

        pessoa.id( pessoaDto.getId() );
        pessoa.nome( pessoaDto.getNome() );
        pessoa.sexo( pessoaDto.getSexo() );
        pessoa.email( pessoaDto.getEmail() );
        if ( pessoaDto.getDataNascimento() != null ) {
            pessoa.dataNascimento( new Date( pessoaDto.getDataNascimento().getTime() ) );
        }
        pessoa.naturalidade( pessoaDto.getNaturalidade() );
        pessoa.dataCriacao( dateMapper.asTimestamp( pessoaDto.getDataCriacao() ) );
        pessoa.dataUltimaAtualizacao( dateMapper.asTimestamp( pessoaDto.getDataUltimaAtualizacao() ) );
        pessoa.cpf( pessoaDto.getCpf() );

        return pessoa.build();
    }

    @Override
    public PessoaDto pessoaParaPessoaDto(Pessoa pessoa) {
        if ( pessoa == null ) {
            return null;
        }

        PessoaDtoBuilder pessoaDto = PessoaDto.builder();

        pessoaDto.id( pessoa.getId() );
        pessoaDto.nome( pessoa.getNome() );
        pessoaDto.sexo( pessoa.getSexo() );
        pessoaDto.email( pessoa.getEmail() );
        pessoaDto.dataNascimento( pessoa.getDataNascimento() );
        pessoaDto.naturalidade( pessoa.getNaturalidade() );
        pessoaDto.dataCriacao( dateMapper.asOffsetDateTime( pessoa.getDataCriacao() ) );
        pessoaDto.dataUltimaAtualizacao( dateMapper.asOffsetDateTime( pessoa.getDataUltimaAtualizacao() ) );
        pessoaDto.cpf( pessoa.getCpf() );

        return pessoaDto.build();
    }
}
