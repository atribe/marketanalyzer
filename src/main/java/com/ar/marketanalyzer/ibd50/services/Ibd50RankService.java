package com.ar.marketanalyzer.ibd50.services;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;

import java.util.Date;
import java.util.List;

public interface Ibd50RankService {

	public Ibd50Rank create(Ibd50Rank ibd50Ranking);
	public Ibd50Rank delete(int id) throws SecuritiesNotFound;
	public List<Ibd50Rank> findAll();
	public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws SecuritiesNotFound;
	public Ibd50Rank findById(int id);
	/**
	 * Looks for a ticker with a specific rank.
	 * Checks to see if the ticker is in the ticker DB first.
	 * 
	 * @param rank
	 * @param ticker
	 * @return the rank object that matches the search criteria.
	 * @throws SecuritiesNotFound Thrown if either ticker isn't in the db or the ticker and 
	 * 				rank combo isn't in the db. The message will tell you which it is.
	 */
	public List<Ibd50Rank> findByRankAndTicker(int rank, Symbol ticker) throws SecuritiesNotFound;
	public List<Ibd50Rank> findByModificationTimeAfter(Date date) throws SecuritiesNotFound;
	public List<Ibd50Rank> findByRankBetweenAndActiveTrue(int startRank, int endRank) throws SecuritiesNotFound;
	/**
	 * Looks for the most current rank for a given ticker.
	 * 
	 * @param ticker
	 * @return the current ranking for the provided ticker
	 * @throws SecuritiesNotFound
	 * @throws Ibd50TooManyFound
	 */
	public Ibd50Rank findByTickerAndCurrentRankTrue(Symbol ticker) throws SecuritiesNotFound, Ibd50TooManyFound;
	public void deactivateAllCurrentRankings();
}
