package com.ar.marketanalyzer.ibd50.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.ibd50.models.StockOhlcv;

public interface StockOhlcvRepository extends JpaRepository<StockOhlcv, Integer>{

}
