package it.prova.gestioneutenze.web.api.exceptions;

public class PasswordNonCoincidonoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordNonCoincidonoException() {

	}

	public PasswordNonCoincidonoException(String message) {
		super(message);
	}

}
