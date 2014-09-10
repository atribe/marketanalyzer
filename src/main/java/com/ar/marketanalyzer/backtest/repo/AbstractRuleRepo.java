package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;

public interface AbstractRuleRepo extends JpaRepository<AbstractRule, Integer>{
	
}
