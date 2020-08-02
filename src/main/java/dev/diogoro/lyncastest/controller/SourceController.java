package dev.diogoro.lyncastest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.diogoro.lyncastest.service.SourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/source")
@RestController
public class SourceController {
	
	private final SourceService sourceService;

	@Operation(summary = "Obter link do repositorio")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Link do repositorio", 
			    content = { @Content(mediaType = "text/plain") }),
			  @ApiResponse(responseCode = "400", description = "Requisicao sem sucesso", 
			    content = @Content)})
	@GetMapping
	public ResponseEntity<String> obterLinkRepositorio() {
		return new ResponseEntity<String>(sourceService.obterLinkRepositorio(), HttpStatus.OK);
	}
}
