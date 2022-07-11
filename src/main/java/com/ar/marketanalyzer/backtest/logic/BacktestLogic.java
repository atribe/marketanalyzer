package com.ar.marketanalyzer.backtest.logic;

import com.ar.marketanalyzer.core.securities.logic.SecurityOhlcvLogic;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@PropertySource(value = "classpath:common.properties")
public class BacktestLogic {

    private final SymbolServiceInterface symbolService;
    private final SecurityOhlcvLogic securityOhlcvLogic;

    private final BacktestModelLogic modelLogic;

    @Value("index.symbols")
    private String[] indexSymbolList;
    @Value("index.names")
    private String[] indexNamesList;
    @Value("index.type")
    private String indexType;

    public void init() {
        log.trace("Start init()");
        List<Symbol> defaultSymbols = getDefaultSymbols();

        securityOhlcvLogic.updateOhlcv(defaultSymbols);

        runCurrentModels(defaultSymbols);
        log.trace("End init()");
    }

    private List<Symbol> getDefaultSymbols() {
        log.trace("start getDefaultSymbols()");

        List<Symbol> defaultSymbols = new ArrayList<>();

        for (int i = 0; i < indexSymbolList.length; i++) {
            Symbol symbol = new Symbol(indexSymbolList[i], indexNamesList[i], indexType); // create a new symbol
            symbol = symbolService.createOrFindDuplicate(symbol);                // add it to the db and return the symbol including the ID
            defaultSymbols.add(symbol);
        }

        log.trace("end getDefaultSymbols()");
        return defaultSymbols;
    }

    public void runCurrentModels(List<Symbol> defaultSymbols) {
        for (Symbol symbol : defaultSymbols) {
            runCurrentModel(symbol);
        }
    }

    public void runCurrentModel(Symbol symbol) {
        modelLogic.runCurrentModel(symbol);
    }
}
