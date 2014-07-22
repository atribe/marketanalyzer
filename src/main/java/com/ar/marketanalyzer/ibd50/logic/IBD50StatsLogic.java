package com.ar.marketanalyzer.ibd50.logic;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50CustomIndex;
import com.ar.marketanalyzer.ibd50.models.Ibd50IndexShares;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.services.Ibd50CustomIndexService;
import com.ar.marketanalyzer.ibd50.services.Ibd50IndexSharesService;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankService;
import com.ar.marketanalyzer.ibd50.services.StockOhlcvService;

@Service
public class IBD50StatsLogic {
	
	//logger
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private Ibd50CustomIndexService customIndexService;
	@Autowired
	private Ibd50IndexSharesService indexSharesService;
	@Autowired
	private Ibd50RankService rankingService;
	@Autowired
	private StockOhlcvService ohlcvService;
	
	public IBD50StatsLogic() {

	}
	
	public void calcIbd50Stats(){ 
		/*
		 * What stats do I need to calculate
		 * 
		 * ibd50 index - how well the whole list has done
		 * ibd50 top 10, 10-20, 20-30, 30-40, 40-50, 1-25, 26-50 index
		 * 		each custom index could be handled through one method, simply takes
		 * 		in the numbers of the stocks to be put in the index, then outputs new value of the index
		 * 		I guess I need a custom index table for this.
		 * 
		 * return since added to the list
		 * 
		 */
		
		createDefaultIndices();
	}
	
	public void createDefaultIndices() {
		/*
		 * I want to have a 1-50 index
		 * I want a 1-10 index
		 * 11-20
		 * 21-30
		 * 31-40
		 * 41-50
		 * 1-25
		 * 26-50
		 */
		String[] indexName = {
				"IBD50 Index",
				"IBD50 Top 10 Index",
				"IBD50 11-20 Index",
				"IBD50 21-30 Index",
				"IBD50 31-40 Index",
				"IBD50 41-50 Index",
				"IBD50 Top Half Index",
				"IBD50 Bottom Half Index"
		};
		int[] indexStart = {
				1,
				1,
				11,
				21,
				31,
				41,
				1,
				26
		};
		int[] indexStop = {
				50,
				10,
				20,
				30,
				40,
				50,
				25,
				50
		};
		
		for(int i = 0; i < indexName.length; i++) {
			Ibd50CustomIndex ibd50CustomIndex = new Ibd50CustomIndex();
			ibd50CustomIndex.setIndexName(indexName[i]);
			ibd50CustomIndex.setRankRangeStart(indexStart[i]);
			ibd50CustomIndex.setRankRangeEnd(indexStop[i]);
			
			ibd50CustomIndex = customIndexService.create(ibd50CustomIndex);
			
			populateCustomIndex(ibd50CustomIndex);
		}
	}

	private void populateCustomIndex(Ibd50CustomIndex ibd50CustomIndex) {
		
		try {
			List<Ibd50Rank> rankingsInRange = rankingService.findByRankBetweenAndActiveTrue(ibd50CustomIndex.getRankRangeStart(), ibd50CustomIndex.getRankRangeEnd());
		
			LocalDate mostRecentlyEnteredRankings = null;
			
			for(Ibd50Rank ranking: rankingsInRange) {
				//List<StockOhlcv> ohlcvList = ohlcvService.findByTickerAndDateAfter(ranking.getTicker(), date) 
				
				Ibd50IndexShares indexShares = new Ibd50IndexShares();
				
				indexShares.setIndex(ibd50CustomIndex);
				indexShares.setRanking(ranking);
				
				
			}
		
			
		
		} catch (GenericIbd50NotFound e) {
			log.info("Unable to populate custom index " + ibd50CustomIndex.getIndexName() + " because couldn't find any IBD50 Rankings in the specified range.");
			e.printStackTrace();
		}
	}
}
