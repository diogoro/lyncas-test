package dev.diogoro.lyncastest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.diogoro.lyncastest.service.SourceService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/source")
@RestController
public class SourceController {
	
	private final SourceService sourceService;

	@GetMapping
	public ResponseEntity<String> obterLinkRepositorio() {
		return new ResponseEntity<String>(sourceService.obterLinkRepositorio(), HttpStatus.OK);
	}
}
