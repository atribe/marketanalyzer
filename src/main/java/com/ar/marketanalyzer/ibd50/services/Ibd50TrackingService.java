package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;

public interface Ibd50TrackingService {

	public Ibd50Tracking create(Ibd50Tracking ibd50Tracking);
	public Ibd50Tracking delete(int id) throws GenericIbd50NotFound;
	public List<Ibd50Tracking> findAll();
	public Ibd50Tracking update(Ibd50Tracking ibd50Tracking) throws GenericIbd50NotFound;
	public Ibd50Tracking findById(int id);
	
}
