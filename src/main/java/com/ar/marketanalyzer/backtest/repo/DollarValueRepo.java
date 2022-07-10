package com.ar.marketanalyzer.backtest.repo;

import com.ar.marketanalyzer.backtest.models.DollarValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DollarValueRepo extends JpaRepository<DollarValue, Integer>{

}
