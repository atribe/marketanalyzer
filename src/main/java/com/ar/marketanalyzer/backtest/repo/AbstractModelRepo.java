package com.ar.marketanalyzer.backtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;

public interface AbstractModelRepo extends JpaRepository<AbstractModel, Integer>{
	
}
