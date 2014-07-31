package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.Ibd50NotFound;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
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
	
	/**
	 * Looks up the provided ticker from the ticker db.
	 * First it looks to see if the ticker ID is set and uses it.
	 * If the ID is null it then looks up the symbol
	 * 
	 * @param ticker
	 * @return the ticker from the DB that matches the provided ticker.
	 * @throws Ibd50NotFound This is thrown if the ticker is not found by either ID or symbol.
	 */
	private TickerSymbol lookUpTicker(TickerSymbol ticker) throws Ibd50NotFound {
		TickerSymbol foundTicker;
		
		try {
			if( ticker.getId() != null ) {
				foundTicker = tickerSymbolService.findById(ticker.getId());
			} else {
				foundTicker = tickerSymbolService.findBySymbol(ticker.getSymbol());
			}
		} catch (Ibd50NotFound e) {
			throw e;
		}
				
		return foundTicker;
	}
	
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
	@Transactional(rollbackFor=Ibd50NotFound.class)
	public Ibd50Rank delete(int id) throws Ibd50NotFound {
		Ibd50Rank deletedIbd50Ranking = ibd50RankingRepo.findOne(id);
		
		if(deletedIbd50Ranking == null) {
			throw new Ibd50NotFound();
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
	@Transactional(rollbackFor=Ibd50NotFound.class)
	public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws Ibd50NotFound {
		Ibd50Rank updatedIbd50Ranking = ibd50RankingRepo.findOne(ibd50Ranking.getId());
		
		if( updatedIbd50Ranking == null) {
			throw new Ibd50NotFound();
		}
		
		//TODO Filler until I really write this code
		updatedIbd50Ranking.setRank(ibd50Ranking.getRank());
		
		return updatedIbd50Ranking;
	}

	@Override
	@Transactional(rollbackFor=Ibd50NotFound.class)
	public List<Ibd50Rank> findByRankAndTicker(int rank, TickerSymbol ticker) throws Ibd50NotFound {
		TickerSymbol foundTickerSymbol;
		try {
			foundTickerSymbol = lookUpTicker(ticker);								// First check to see if the ticker is valid
		} catch (Ibd50NotFound e) {													// Catch if it is not
			throw e;																// Throw that exception up to the next level to be dealt with.
		}
		
		List<Ibd50Rank> rankingList = ibd50RankingRepo.findByRankAndTickerOrderByRankDateAsc(rank, foundTickerSymbol);	//Using the found ticker look up for the provided rank
		
		if(rankingList.isEmpty()) {													// Check if any were found with the desired rank and ticker
			throw new Ibd50NotFound("The Ticker '" + foundTickerSymbol.getSymbol()	// If not throw an exception with the problem explained in the message 
					+ "' was not found with rank " + rank + " in the Rank DB");
		}
		
		return rankingList ;														// If results were found then return them
	}

	@Override
	public List<Ibd50Rank> findByModificationTimeAfter(Date date) throws Ibd50NotFound {
		List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByModificationTimeAfter(date);
		if( foundRanking == null) {
			throw new Ibd50NotFound();
		}
		return foundRanking;
	}

	
	/*
	 * Keeping as an example of a cross table query with a named query
	@Override
	public List<Ibd50Rank> findByRankBetweenAndActiveTrue(int startRank,	int endRank) throws GenericIbd50NotFound{
		List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByRankBetweenAndActiveTrue(startRank, endRank);
		
		if( foundRanking == null) {
			throw new GenericIbd50NotFound();
		}
		
		return foundRanking;
	}
	*/
	
	@Override
	@Transactional
	public Ibd50Rank findByTickerAndCurrentRankTrue(TickerSymbol ticker) throws Ibd50NotFound, Ibd50TooManyFound {
		
		TickerSymbol foundTickerSymbol;
		try {
			foundTickerSymbol = lookUpTicker(ticker);								// First check to see if the ticker is valid
		} catch (Ibd50NotFound e) {													// Catch if it is not
			throw e;																// Throw that exception up to the next level to be dealt with.
		}
		
		List<Ibd50Rank> rankingList = ibd50RankingRepo.findByTickerAndActiveRankingTrue(foundTickerSymbol);
		
		if(rankingList.isEmpty()) {													// Check if any were found with the desired rank and ticker
			throw new Ibd50NotFound("The Ticker '" + foundTickerSymbol.getSymbol()	// If not throw an exception with the problem explained in the message 
					+ "' was not found with any current ranks.");
		} else if (rankingList.size() > 1) {
			throw new Ibd50TooManyFound("To many current=true for ticker " + foundTickerSymbol.getSymbol() 
					+ ". Go check your code to make sure they are being deactivated properly.");
		}
		
		return rankingList.get(0);
	}

	@Override
	@Transactional
	public void deactivateAllCurrentRankings() {
		ibd50RankingRepo.deactivateActiveRanking();
	}
}
