package com.ar.marketanalyzer.backtest.repo;

import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleResultRepo extends JpaRepository<AbstractRuleResult, Integer>{
	
}
