package com.ar.marketanalyzer.core.securities.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.core.securities.models.Symbol;

public interface SymbolRepo extends JpaRepository<Symbol, Integer>{

	public Symbol findBySymbol(String symbol);
}
