package com.ar.marketanalyzer.securities.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.securities.models.Symbol;

public interface SymbolRepo extends JpaRepository<Symbol, Integer>{

	public Symbol findBySymbol(String symbol);
}
