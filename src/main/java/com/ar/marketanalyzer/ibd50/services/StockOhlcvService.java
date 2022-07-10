package com.ar.marketanalyzer.ibd50.services;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface StockOhlcvService {

    StockOhlcv create(StockOhlcv stockOhlcv);

    StockOhlcv delete(int id) throws SecuritiesNotFound;

    List<StockOhlcv> findAll();

    StockOhlcv update(StockOhlcv stockOhlcv) throws SecuritiesNotFound;

    StockOhlcv findById(int id);

    List<StockOhlcv> findByTicker(Symbol ticker) throws SecuritiesNotFound;

    void batchInsert(List<StockOhlcv> ohlcvList);

    StockOhlcv findByTickerAndDate(Symbol foundTicker, LocalDateTime date) throws SecuritiesNotFound;

    List<StockOhlcv> findByTickerAndDateAfter(Symbol ticker, LocalDateTime date) throws SecuritiesNotFound;
}
