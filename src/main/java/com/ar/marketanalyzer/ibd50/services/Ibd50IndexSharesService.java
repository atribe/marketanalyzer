package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.models.Ibd50IndexShares;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;

public interface Ibd50IndexSharesService {

	public Ibd50IndexShares create(Ibd50IndexShares ibd50IndexShares);
	public Ibd50IndexShares delete(int id) throws SecuritiesNotFound;
	public List<Ibd50IndexShares> findAll();
	public Ibd50IndexShares update(Ibd50IndexShares ibd50IndexShares) throws SecuritiesNotFound;
	public Ibd50IndexShares findById(int id) throws SecuritiesNotFound;
}
