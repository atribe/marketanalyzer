package com.ar.marketanalyzer.ibd50.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;

public interface Ibd50TrackingRepository extends JpaRepository<Ibd50Tracking, Integer>{
	public List<Ibd50Tracking> findByActiveAndTicker(boolean active, Symbol ticker);
	public List<Ibd50Tracking> findByActiveTrue();
	public List<Ibd50Tracking> findByActiveFalseAndLeaveDateGreaterThanEqual(Date date);
}
