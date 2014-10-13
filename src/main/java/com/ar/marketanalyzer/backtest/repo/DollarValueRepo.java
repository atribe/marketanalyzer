package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.DollarValue;

public interface DollarValueRepo extends JpaRepository<DollarValue, Integer>{

}
