package com.ar.marketanalyzer.backtest.repo;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRuleRepo extends JpaRepository<AbstractRule, Integer>{
	
}
