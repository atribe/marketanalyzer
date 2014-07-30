package com.ar.marketanalyzer.ibd50.services;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.Ibd50NotFound;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50RankService {

	public Ibd50Rank create(Ibd50Rank ibd50Ranking);
	public Ibd50Rank delete(int id) throws Ibd50NotFound;
	public List<Ibd50Rank> findAll();
	public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws Ibd50NotFound;
	public Ibd50Rank findById(int id);
	/**
	 * Looks for a ticker with a specific rank.
	 * Checks to see if the ticker is in the ticker DB first.
	 * 
	 * @param rank
	 * @param ticker
	 * @return the rank object that matches the search criteria.
	 * @throws Ibd50NotFound Thrown if either ticker isn't in the db or the ticker and 
	 * 				rank combo isn't in the db. The message will tell you which it is.
	 */
	public List<Ibd50Rank> findByRankAndTicker(int rank, TickerSymbol ticker) throws Ibd50NotFound;
	public List<Ibd50Rank> findByModificationTimeAfter(Date date) throws Ibd50NotFound;
	//public List<Ibd50Rank> findByRankBetweenAndActiveTrue(int startRank, int endRank) throws GenericIbd50NotFound;
	/**
	 * Looks for the most current rank for a given ticker.
	 * 
	 * @param ticker
	 * @return the current ranking for the provided ticker
	 * @throws Ibd50NotFound
	 * @throws Ibd50TooManyFound
	 */
	public Ibd50Rank findByTickerAndCurrentRankTrue(TickerSymbol ticker) throws Ibd50NotFound, Ibd50TooManyFound;
}
