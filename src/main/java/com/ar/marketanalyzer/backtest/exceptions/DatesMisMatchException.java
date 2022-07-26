package com.ar.marketanalyzer.backtest.exceptions;

public class DatesMisMatchException extends RuntimeException {

    private static final long serialVersionUID = -7666257851970536053L;

    public DatesMisMatchException() {
    }

    public DatesMisMatchException(String message) {
        super(message);
    }

    public DatesMisMatchException(Throwable cause) {
        super(cause);
    }

    public DatesMisMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
