package com.ar.marketanalyzer.core.securities.exceptions;

public class SecuritiesNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1488348114120853324L;

	public SecuritiesNotFound() {
        // TODO Auto-generated constructor stub
    }

    public SecuritiesNotFound(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SecuritiesNotFound(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public SecuritiesNotFound(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
