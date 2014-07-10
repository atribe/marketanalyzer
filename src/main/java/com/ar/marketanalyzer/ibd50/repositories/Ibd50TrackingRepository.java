package com.ar.marketanalyzer.ibd50.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50TrackingRepository extends JpaRepository<Ibd50Tracking, Integer>{
	public List<Ibd50Tracking> findByActiveAndTicker(boolean active, TickerSymbol ticker);
}