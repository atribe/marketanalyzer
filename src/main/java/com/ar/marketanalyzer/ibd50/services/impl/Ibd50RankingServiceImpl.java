package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50RankingRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankingService;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Service
public class Ibd50RankingServiceImpl implements Ibd50RankingService{

	@Resource
	private Ibd50RankingRepository ibd50RankingRepo;
	
	@Autowired
	private TickerSymbolService tickerSymbolService;
	
	@Override
	@Transactional
	public Ibd50Ranking create(Ibd50Ranking ibd50Ranking) {
		Ibd50Ranking createdIbd50Ranking = ibd50Ranking;
		return ibd50RankingRepo.save(createdIbd50Ranking);
	}

	@Override
	@Transactional
	public Ibd50Ranking findById(int id) {
		return ibd50RankingRepo.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50Ranking delete(int id) throws GenericIbd50NotFound {
		Ibd50Ranking deletedIbd50Ranking = ibd50RankingRepo.findOne(id);
		
		if(deletedIbd50Ranking == null) {
			throw new GenericIbd50NotFound();
		} 

		ibd50RankingRepo.delete(id);
		
		return deletedIbd50Ranking;	
	}

	@Override
	@Transactional
	public List<Ibd50Ranking> findAll() {
		return ibd50RankingRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50Ranking update(Ibd50Ranking ibd50Ranking) throws GenericIbd50NotFound {
		Ibd50Ranking updatedIbd50Ranking = ibd50RankingRepo.findOne(ibd50Ranking.getRankingId());
		
		if( updatedIbd50Ranking == null) {
			throw new GenericIbd50NotFound();
		}
		
		//TODO Filler until I really write this code
		updatedIbd50Ranking.setRank(ibd50Ranking.getRank());
		
		return updatedIbd50Ranking;
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public List<Ibd50Ranking> findByRankAndTicker(int rank, TickerSymbol ticker) throws GenericIbd50NotFound {
		TickerSymbol foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());
		
		if( foundTickerSymbol == null ) {
			throw new GenericIbd50NotFound("By Rank and Ticker search for " + ticker.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<Ibd50Ranking> rankingList = ibd50RankingRepo.findByRankAndTickerOrderByRankDateAsc(rank, ticker);
		
		if(rankingList.isEmpty()) {
			throw new GenericIbd50NotFound("The Ticker '" + ticker.getSymbol() + "' was not found with rank " + rank + " in the Rank DB");
		}
		
		return rankingList ;
	}
}
