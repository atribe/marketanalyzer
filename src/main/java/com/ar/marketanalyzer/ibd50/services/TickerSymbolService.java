package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.beans.TickerSymbol;
import com.ar.marketanalyzer.ibd50.exceptions.TickerSymbolNotFound;

public interface TickerSymbolService {

public TickerSymbol create(TickerSymbol tickerSymbol);
public TickerSymbol delete(int id) throws TickerSymbolNotFound;
public List<TickerSymbol> findAll();
public TickerSymbol update(TickerSymbol tickerSymbol) throws TickerSymbolNotFound;
public TickerSymbol findById(int id);

}
