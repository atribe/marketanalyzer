package com.ar.marketanalyzer.ibd50.repositories;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface Ibd50TrackingRepository extends JpaRepository<Ibd50Tracking, Integer>{
	public List<Ibd50Tracking> findByActiveAndTicker(boolean active, Symbol ticker);
	public List<Ibd50Tracking> findByActiveTrue();
	public List<Ibd50Tracking> findByActiveFalseAndLeaveDateGreaterThanEqual(LocalDate date);
}
