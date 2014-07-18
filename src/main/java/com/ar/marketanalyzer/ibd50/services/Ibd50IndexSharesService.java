package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50IndexShares;

public interface Ibd50IndexSharesService {

	public Ibd50IndexShares create(Ibd50IndexShares ibd50IndexShares);
	public Ibd50IndexShares delete(int id) throws GenericIbd50NotFound;
	public List<Ibd50IndexShares> findAll();
	public Ibd50IndexShares update(Ibd50IndexShares ibd50IndexShares) throws GenericIbd50NotFound;
	public Ibd50IndexShares findById(int id) throws GenericIbd50NotFound;
}
