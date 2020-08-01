package dev.diogoro.lyncastest.exception;

public class PessoaNaoEncontradaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3734851337039322806L;

	public PessoaNaoEncontradaException() {
		super();
	}
	
	public PessoaNaoEncontradaException(String e) {
		super(e);
	}
}
