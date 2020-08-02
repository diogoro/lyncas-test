package dev.diogoro.lyncastest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/pessoas")
@RestController
public class PessoaController {

	private final PessoaService pessoaService;
	
	@Operation(summary = "Obter pessoa por id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Pessoa encontrada", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = PessoaDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Id invalido", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada", 
			    content = @Content) })
	@GetMapping("/{idPessoa}")
	public ResponseEntity<PessoaDto> obterPessoaPorId(@PathVariable("idPessoa") UUID idPessoa) {
		return new ResponseEntity<>(pessoaService.obterPessoaPorId(idPessoa), HttpStatus.OK);
	}

	@Operation(summary = "Cadastrar pessoa")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "Pessoa cadastrada", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = PessoaDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Dados invalidos", 
			    content = @Content), 
			  @ApiResponse(responseCode = "409", description = "Cpf ja cadastrado", 
			    content = @Content) })
	@PostMapping
	public ResponseEntity cadastrarPessoa(@RequestBody @Validated PessoaDto pessoa) {
		return new ResponseEntity(pessoaService.cadastrarPessoa(pessoa), HttpStatus.CREATED);
	}

	@Operation(summary = "Atualizar pessoa")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "204", description = "Pessoa atualizada", 
			    content = @Content),
			  @ApiResponse(responseCode = "400", description = "Dados invalidos", 
			    content = @Content), 
			  @ApiResponse(responseCode = "409", description = "Cpf ja cadastrado", 
			    content = @Content) })
	@PutMapping("/{idPessoa}")
	public ResponseEntity atualizarPessoa(@PathVariable("idPessoa") UUID idPessoa,
			@RequestBody @Validated PessoaDto pessoa) {
		return new ResponseEntity(pessoaService.atualizarPessoa(idPessoa, pessoa), HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Apagar pessoa")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "204", description = "Pessoa deletada", 
			    content = @Content),
			  @ApiResponse(responseCode = "400", description = "Dados invalidos", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada", 
			    content = @Content) })
	@DeleteMapping("/{idPessoa}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarPessoa(@PathVariable("idPessoa") UUID idPessoa) {
		pessoaService.apagarPessoa(idPessoa);
	}

	@Operation(summary = "Obter lista de pessoas")
	
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "lista de pessoas encontrada", 
			    content = { @Content(mediaType = "application/json", 
			      array = @ArraySchema(schema = @Schema(implementation = PessoaDto.class)))}),
			  @ApiResponse(responseCode = "400", description = "Id invalido", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada", 
			    content = @Content) })
	@GetMapping
	public ResponseEntity<List<PessoaDto>> obterListaPessoas() {
		return new ResponseEntity<>(pessoaService.obterListaPessoas(), HttpStatus.OK);
	}

}
