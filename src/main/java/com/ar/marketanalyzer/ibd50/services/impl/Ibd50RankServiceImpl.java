package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50RankRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankService;
import com.ar.marketanalyzer.ibd50.services.TickerSymbolService;

@Service
public class Ibd50RankServiceImpl implements Ibd50RankService{

	@Resource
	private Ibd50RankRepository ibd50RankingRepo;
	
	@Autowired
	private TickerSymbolService tickerSymbolService;
	
	@Override
	@Transactional
	public Ibd50Rank create(Ibd50Rank ibd50Ranking) {
		Ibd50Rank createdIbd50Ranking = ibd50Ranking;
		return ibd50RankingRepo.save(createdIbd50Ranking);
	}

	@Override
	@Transactional
	public Ibd50Rank findById(int id) {
		return ibd50RankingRepo.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50Rank delete(int id) throws GenericIbd50NotFound {
		Ibd50Rank deletedIbd50Ranking = ibd50RankingRepo.findOne(id);
		
		if(deletedIbd50Ranking == null) {
			throw new GenericIbd50NotFound();
		} 

		ibd50RankingRepo.delete(id);
		
		return deletedIbd50Ranking;	
	}

	@Override
	@Transactional
	public List<Ibd50Rank> findAll() {
		return ibd50RankingRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws GenericIbd50NotFound {
		Ibd50Rank updatedIbd50Ranking = ibd50RankingRepo.findOne(ibd50Ranking.getId());
		
		if( updatedIbd50Ranking == null) {
			throw new GenericIbd50NotFound();
		}
		
		//TODO Filler until I really write this code
		updatedIbd50Ranking.setRank(ibd50Ranking.getRank());
		
		return updatedIbd50Ranking;
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public List<Ibd50Rank> findByRankAndTicker(int rank, TickerSymbol ticker) throws GenericIbd50NotFound {
		TickerSymbol foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());
		
		if( foundTickerSymbol == null ) {
			throw new GenericIbd50NotFound("By Rank and Ticker search for " + ticker.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<Ibd50Rank> rankingList = ibd50RankingRepo.findByRankAndTickerOrderByRankDateAsc(rank, foundTickerSymbol);
		
		if(rankingList.isEmpty()) {
			throw new GenericIbd50NotFound("The Ticker '" + foundTickerSymbol.getSymbol() + "' was not found with rank " + rank + " in the Rank DB");
		}
		
		return rankingList ;
	}

	@Override
	public List<Ibd50Rank> findByModificationTimeAfter(Date date) throws GenericIbd50NotFound {
		List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByModificationTimeAfter(date);
		if( foundRanking == null) {
			throw new GenericIbd50NotFound();
		}
		return foundRanking;
	}

	@Override
	public List<Ibd50Rank> findByActiveTrueAndRankBetween(int startRank,	int endRank) throws GenericIbd50NotFound{
		List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByActiveTrueAndRankBetween(startRank, endRank);
		
		if( foundRanking == null) {
			throw new GenericIbd50NotFound();
		}
		
		return foundRanking;
	}
}
