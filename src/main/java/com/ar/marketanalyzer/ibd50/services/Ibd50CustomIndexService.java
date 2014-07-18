package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50CustomIndex;

public interface Ibd50CustomIndexService {

	public Ibd50CustomIndex create(Ibd50CustomIndex ibd50CustomIndex);
	public Ibd50CustomIndex delete(int id) throws GenericIbd50NotFound;
	public List<Ibd50CustomIndex> findAll();
	public Ibd50CustomIndex update(Ibd50CustomIndex ibd50CustomIndex) throws GenericIbd50NotFound;
	public Ibd50CustomIndex findById(int id) throws GenericIbd50NotFound;
}
