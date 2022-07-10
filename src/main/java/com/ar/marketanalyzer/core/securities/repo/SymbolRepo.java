package com.ar.marketanalyzer.core.securities.repo;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepo extends JpaRepository<Symbol, Integer>{

	Symbol findBySymbol(String symbol);
}
