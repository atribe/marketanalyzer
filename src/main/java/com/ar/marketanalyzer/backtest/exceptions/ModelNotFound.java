package com.ar.marketanalyzer.backtest.exceptions;

public class ModelNotFound extends RuntimeException {

    private static final long serialVersionUID = -4445680179028358071L;

    public ModelNotFound() {
    }

    public ModelNotFound(String message) {
        super(message);
    }

    public ModelNotFound(Throwable cause) {
        super(cause);
    }

    public ModelNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
