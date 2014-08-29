package com.ar.marketanalyzer.ibd50.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.ibd50.models.Ibd50CustomIndex;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;

public interface Ibd50CustomIndexServiceInterface {

	public Ibd50CustomIndex create(Ibd50CustomIndex ibd50CustomIndex);
	public Ibd50CustomIndex delete(int id) throws SecuritiesNotFound;
	public List<Ibd50CustomIndex> findAll();
	public Ibd50CustomIndex update(Ibd50CustomIndex ibd50CustomIndex) throws SecuritiesNotFound;
	public Ibd50CustomIndex findById(int id) throws SecuritiesNotFound;
}
