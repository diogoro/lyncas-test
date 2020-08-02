package dev.diogoro.lyncastest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.diogoro.lyncastest.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID>{

	Pessoa findByCpf(String cpf);
}
