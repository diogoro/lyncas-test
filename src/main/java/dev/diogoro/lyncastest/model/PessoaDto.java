package dev.diogoro.lyncastest.model;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDto {

	@Null
	private UUID id;
	@NotBlank
	private String nome;
	private Character sexo;
	@Email
	private String email;
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	@PastOrPresent
	private Date dataNascimento;
	private String naturalidade;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	@Null
	private OffsetDateTime dataCriacao;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	@Null
	private OffsetDateTime dataUltimaAtualizacao;
	@CPF
	@NotBlank
	private String cpf; // validar cpf formato e não pode haver duas pessoas com o mesmo cpf na base
}
