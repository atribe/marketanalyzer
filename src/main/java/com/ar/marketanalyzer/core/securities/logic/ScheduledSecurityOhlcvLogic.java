package com.ar.marketanalyzer.core.securities.logic;

import com.ar.marketanalyzer.backtest.logic.BacktestLogic;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledSecurityOhlcvLogic {
    private final SymbolServiceInterface symbolService;
    private final SecurityOhlcvLogic securityOhlcvLogic;
    private final BacktestLogic backtestLogic;

    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void ohlcScheduledUpdate() {
        List<Symbol> symbolList = symbolService.findAll();    // Find all symbols in the DB

        securityOhlcvLogic.updateOhlcv(symbolList);                            // and then update their ohlcv

        //Then update their models
        backtestLogic.runCurrentModels(symbolList);
    }
}
