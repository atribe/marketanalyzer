package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;

public interface Ibd50TrackingService {

	public Ibd50Tracking create(Ibd50Tracking ibd50Tracking);
	public Ibd50Tracking delete(int id) throws SymbolNotFound;
	public List<Ibd50Tracking> findAll();
	public Ibd50Tracking update(Ibd50Tracking ibd50Tracking) throws SymbolNotFound;
	public Ibd50Tracking findById(int id);
	/**
	 * Finds an Ibd50Tracking Object that meets the following criteria given by
	 * the parameters below.
	 * 
	 * @param active					boolean
	 * @param ticker					TickerSymbol object
	 * @return							Ibd50Tracking object
	 * @throws SymbolNotFound		Symbol not found in db or tracker that met the criteria not found
	 * @throws Ibd50TooManyFound		Too many trackers found, objects are not getting set to inactive properly
	 */
	public Ibd50Tracking findByActiveAndTicker(boolean active, Symbol ticker) throws SymbolNotFound, Ibd50TooManyFound;
	public List<Ibd50Tracking> findByActiveTrue();
	public Ibd50Tracking updateActivity(Ibd50Tracking tracker);
	public List<Ibd50Tracking> findByActiveFalseAndDateAfter(LocalDate date) throws SymbolNotFound;
}
