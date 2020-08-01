package dev.diogoro.lyncastest.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.diogoro.lyncastest.model.PessoaDto;
import dev.diogoro.lyncastest.service.PessoaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/pessoa")
@RestController
public class PessoaController {

	private final PessoaService pessoaService;

	@GetMapping("/{idPessoa}")
	public ResponseEntity<PessoaDto> obterPessoaPorId(@PathVariable("idPessoa") UUID idPessoa) {
		return new ResponseEntity<>(pessoaService.obterPessoaPorId(idPessoa), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity cadastrarPessoa(@RequestBody @Valid PessoaDto pessoa) {
		return new ResponseEntity(pessoaService.cadastrarPessoa(pessoa), HttpStatus.CREATED);
	}

	@PutMapping("/{idPessoa}")
	public ResponseEntity atualizarPessoa(@PathVariable("idPessoa") UUID idPessoa,
			@RequestBody @Valid PessoaDto pessoa) {
		return new ResponseEntity(pessoaService.atualizarPessoa(idPessoa, pessoa), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{idPessoa}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarPessoa(@PathVariable("idPessoa") UUID idPessoa) {
		pessoaService.apagarPessoa(idPessoa);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PessoaDto> obterListaPessoas() {
		return pessoaService.obterListaPessoas();
	}

}