package com.ar.marketanalyzer.ibd50.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.ibd50.beans.TickerSymbol;

public interface TickerSymbolRepository extends JpaRepository<TickerSymbol, Integer>{

}
