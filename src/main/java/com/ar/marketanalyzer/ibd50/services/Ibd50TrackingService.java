package com.ar.marketanalyzer.ibd50.services;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;


import java.time.LocalDate;
import java.util.List;

public interface Ibd50TrackingService {

	public Ibd50Tracking create(Ibd50Tracking ibd50Tracking);
	public Ibd50Tracking delete(int id) throws SecuritiesNotFound;
	public List<Ibd50Tracking> findAll();
	public Ibd50Tracking update(Ibd50Tracking ibd50Tracking) throws SecuritiesNotFound;
	public Ibd50Tracking findById(int id);
	/**
	 * Finds an Ibd50Tracking Object that meets the following criteria given by
	 * the parameters below.
	 * 
	 * @param active					boolean
	 * @param ticker					TickerSymbol object
	 * @return							Ibd50Tracking object
	 * @throws SecuritiesNotFound		Symbol not found in db or tracker that met the criteria not found
	 * @throws Ibd50TooManyFound		Too many trackers found, objects are not getting set to inactive properly
	 */
	public Ibd50Tracking findByActiveAndTicker(boolean active, Symbol ticker) throws SecuritiesNotFound, Ibd50TooManyFound;
	public List<Ibd50Tracking> findByActiveTrue();
	public Ibd50Tracking updateActivity(Ibd50Tracking tracker);
	public List<Ibd50Tracking> findByActiveFalseAndDateAfter(LocalDate date) throws SecuritiesNotFound;
}
