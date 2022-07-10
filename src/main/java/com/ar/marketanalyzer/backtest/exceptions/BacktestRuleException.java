package com.ar.marketanalyzer.backtest.exceptions;

public class BacktestRuleException extends RuntimeException {

    private static final long serialVersionUID = -4445680179028358071L;

    public BacktestRuleException() {
    }

    public BacktestRuleException(String message) {
        super(message);
    }

    public BacktestRuleException(Throwable cause) {
        super(cause);
    }

    public BacktestRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
