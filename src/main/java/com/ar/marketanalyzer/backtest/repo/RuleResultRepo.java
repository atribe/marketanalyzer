package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.RuleResult;

public interface RuleResultRepo extends JpaRepository<RuleResult, Integer>{
	
}
