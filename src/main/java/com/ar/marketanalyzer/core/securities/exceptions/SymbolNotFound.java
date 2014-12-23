package com.ar.marketanalyzer.core.securities.exceptions;

public class SymbolNotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1488348114120853324L;

	public SymbolNotFound() {
        // TODO Auto-generated constructor stub
    }

    public SymbolNotFound(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SymbolNotFound(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public SymbolNotFound(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
