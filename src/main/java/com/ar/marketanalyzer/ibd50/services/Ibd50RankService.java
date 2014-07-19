package com.ar.marketanalyzer.ibd50.services;

import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50RankService {

	public Ibd50Rank create(Ibd50Rank ibd50Ranking);
	public Ibd50Rank delete(int id) throws GenericIbd50NotFound;
	public List<Ibd50Rank> findAll();
	public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws GenericIbd50NotFound;
	public Ibd50Rank findById(int id);
	public List<Ibd50Rank> findByRankAndTicker(int rank, TickerSymbol ticker) throws GenericIbd50NotFound;
	public List<Ibd50Rank> findByModificationTimeAfter(Date date) throws GenericIbd50NotFound;
	public List<Ibd50Rank> findByActiveTrueAndRankBetween(int startRank, int endRank) throws GenericIbd50NotFound;
}
