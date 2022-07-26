package com.ar.marketanalyzer.backtest.repo;

import com.ar.marketanalyzer.backtest.models.RuleParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleParameterRepo extends JpaRepository<RuleParameter, Integer>{
	
}
