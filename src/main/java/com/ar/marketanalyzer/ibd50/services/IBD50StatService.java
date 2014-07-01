package com.ar.marketanalyzer.ibd50.services;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.dao.Ibd50RankingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50SymbolDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50TrackingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50WebDao;
import com.ar.marketanalyzer.ibd50.dao.stockOhlcvDao;

public class IBD50StatService {
	private DataSource ds;
	
	private Ibd50WebDao webDao;
	private Ibd50SymbolDao symbolDao;
	private Ibd50RankingDao rankingDao;
	private stockOhlcvDao ohlcvDao;
	private Ibd50TrackingDao trackingDao;
	
	public IBD50StatService() {
		ds = MarketPredDataSource.setDataSource();
		//webDao = new Ibd50WebDao();
		//symbolDao = new Ibd50SymbolDao();
		//rankingDao = new Ibd50RankingDao(ds);
		//trackingDao = new Ibd50TrackingDao(ds);
		//ohlcvDao = new stockOhlcvDao(ds);
	}
	
	public IBD50StatService(DataSource passedds) {
		ds = passedds;
		//webDao = new Ibd50WebDao();
		//symbolDao = new Ibd50SymbolDao();
		//rankingDao = new Ibd50RankingDao(ds);
		//trackingDao = new Ibd50TrackingDao(ds);
		//ohlcvDao = new stockOhlcvDao(ds);
	}
	
	public void tableInit() {
		
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
	}
}
