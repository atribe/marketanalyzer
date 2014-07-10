package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50TrackingService {

	public Ibd50Tracking create(Ibd50Tracking ibd50Tracking);
	public Ibd50Tracking delete(int id) throws GenericIbd50NotFound;
	public List<Ibd50Tracking> findAll();
	public Ibd50Tracking update(Ibd50Tracking ibd50Tracking) throws GenericIbd50NotFound;
	public Ibd50Tracking findById(int id);
	/**
	 * Finds an Ibd50Tracking Object that meets the following criteria given by
	 * the parameters below.
	 * 
	 * @param active					boolean
	 * @param ticker					TickerSymbol object
	 * @return							Ibd50Tracking object
	 * @throws GenericIbd50NotFound		Symbol not found in db or tracker that met the criteria not found
	 * @throws Ibd50TooManyFound		Too many trackers found, objects are not getting set to inactive properly
	 */
	public Ibd50Tracking findByActiveAndTicker(boolean active, TickerSymbol ticker) throws GenericIbd50NotFound, Ibd50TooManyFound;
	
}
