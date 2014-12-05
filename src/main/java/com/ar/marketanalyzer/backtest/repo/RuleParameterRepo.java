package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.RuleParameter;

public interface RuleParameterRepo extends JpaRepository<RuleParameter, Integer>{
	
}
