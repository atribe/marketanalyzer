package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface TickerSymbolService {

public TickerSymbol create(TickerSymbol tickerSymbol);
public TickerSymbol delete(int id) throws GenericIbd50NotFound;
public List<TickerSymbol> findAll();
public TickerSymbol update(TickerSymbol tickerSymbol) throws GenericIbd50NotFound;
public TickerSymbol findById(int id);

}
