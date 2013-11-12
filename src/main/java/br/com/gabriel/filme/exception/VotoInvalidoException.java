package br.com.gabriel.filme.exception;

public class VotoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VotoInvalidoException(String message) {
		super(message);
	}

	public VotoInvalidoException(String mensagem, Throwable e) {
		super(mensagem, e);
	}

}
