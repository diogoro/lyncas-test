package dev.diogoro.lyncastest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.diogoro.lyncastest.exception.CpfExistenteException;
import dev.diogoro.lyncastest.exception.PessoaNaoEncontradaException;

@ControllerAdvice
public class MvcControllerHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
		List<String> errorList = new ArrayList<>(e.getConstraintViolations().size());

		e.getConstraintViolations().forEach(error -> errorList.add(error.toString()));
		return new ResponseEntity<List>(errorList, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorList = new ArrayList<>(ex.getBindingResult().getFieldErrors().size());

		ex.getBindingResult().getFieldErrors().stream()
				.forEach(error -> errorList.add("Parameter: " + error.getField() + " " + error.getDefaultMessage()));
		return new ResponseEntity<Object>(errorList, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CpfExistenteException.class)
	public ResponseEntity<String> CpfExistenteHandler(RuntimeException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(PessoaNaoEncontradaException.class)
	public ResponseEntity<String> PessoaNaoEncontradaHandler(RuntimeException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
