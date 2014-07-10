package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50RankingService {

	public Ibd50Ranking create(Ibd50Ranking Ibd50Ranking);
	public Ibd50Ranking delete(int id) throws GenericIbd50NotFound;
	public List<Ibd50Ranking> findAll();
	public Ibd50Ranking update(Ibd50Ranking Ibd50Ranking) throws GenericIbd50NotFound;
	public Ibd50Ranking findById(int id);
	public List<Ibd50Ranking> findByRankAndTicker(int rank, TickerSymbol ticker) throws GenericIbd50NotFound;
}
