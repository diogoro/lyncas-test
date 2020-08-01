package dev.diogoro.lyncastest.domain;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pessoa {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type="org.hibernate.type.UUIDCharType")
	@Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
	private UUID id;
	
	private String nome;
	private Character sexo;
	private String email;
	private Date dataNascimento;
	private String naturalidade;
	private String nacionalidade;
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp dataCriacao;
	@UpdateTimestamp
	private Timestamp dataUltimaAtualizacao;
	@Column(unique = true)
	private String cpf;

}
