package com.ar.marketanalyzer.ibd50.services;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.dao.Ibd50RankingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50SymbolDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50TrackingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50WebDao;
import com.ar.marketanalyzer.ibd50.dao.stockOhlcvDao;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.indexbacktest.dao.YahooDataRetriever;

/**
 * @author Allan
 *
 */
public class IBD50DBService {
	private DataSource ds;
	
	private Ibd50WebDao webDao;
	private Ibd50SymbolDao symbolDao;
	private Ibd50RankingDao rankingDao;
	private stockOhlcvDao ohlcvDao;
	private Ibd50TrackingDao trackingDao;
	
	/**
	 * Constructor that gets a new DataSource and then creates all 
	 * required DAOs with the DataSource
	 */
	public IBD50DBService() {
		ds = MarketPredDataSource.setDataSource();
		webDao = new Ibd50WebDao();
		//symbolDao = new Ibd50SymbolDao();
		//rankingDao = new Ibd50RankingDao(ds);
		//trackingDao = new Ibd50TrackingDao(ds);
		ohlcvDao = new stockOhlcvDao(ds);
	}
	
	/**
	 * Constructor that uses the passed DataSource and then creates all 
	 * required DAOs with the DataSource
	 */
	public IBD50DBService(DataSource passedds) {
		this.ds = passedds;
		webDao = new Ibd50WebDao();
		//symbolDao = new Ibd50SymbolDao(ds);
		//rankingDao = new Ibd50RankingDao(ds);
		//trackingDao = new Ibd50TrackingDao(ds);
		ohlcvDao = new stockOhlcvDao(ds);
	}
	
	/**
	 * Creates all IBD50 related tables if they do not already exist
	 */
	public void tableInit() {
		symbolDao.tableInit();
		//trackingDao.tableInit();
		//rankingDao.tableInit();
		ohlcvDao.tableInit();
	}

	/**
	 * Drops all IBD50 related tables. Probably only want to use this
	 * in the early development phase.
	 */
	public void dropAllTables() {
	}
	
	/**
	 * Pulls the current top 50 from investors.com and adds them to the database 
	 */
	public void updateFromIbd50Web() {
		List<Ibd50Ranking> webIbd50 = webDao.grabIbd50();
		
		addWeeklyListToDB(webIbd50);
	}
	
	/**
	 * Adds weekly list to the database
	 * <p>
	 * Operations are as follows
	 * <ol>
	 * <li>Check to see if there already is an entry in the Ranking DB for this weeks list</li>
	 * <li>Cycle through each stock in the list</li>
	 * <ol>
	 * 	<li>Check to see if a stock is in the symbol database</li>
	 * 	<ul><li>If not add it</li></ul>
	 * 	<li>Check to see if that symbol has an active tracking DB entry</li>
	 * 		<ul><li>If not add it to the tracking DB</li></ul>
	 * 	<li>Check to see if that symbol is in the ranking DB</li>
	 * 		<ul><li>If not add it to the ranking DB</li></ul>
	 * 	<li>Check to see if that symbol has updated OHLCV data</li>
	 * 		<ul><li>If not update the data from Yahoo</li></ul>
	 * </ol>
	 * </ol>
	 * @param webIbd50
	 */
	private void addWeeklyListToDB(List<Ibd50Ranking> webIbd50) {
		
		if(!isDbUpToDate(webIbd50)) { //if db not up to date
			addThisWeeksListToDB(webIbd50);
		}
	}

	private boolean isDbUpToDate(List<Ibd50Ranking>	webIbd50) {
		try {
			return rankingDao.checkIfIbdUpToDate(webIbd50);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private void addThisWeeksListToDB(List<Ibd50Ranking> webIbd50) {
		for(Ibd50Ranking row : webIbd50) {
			try {
				
				int symbol_id;

				if( isSymbolInDb( row.getSymbol() ) ) { 								//if the symbol is already in the db, 
					symbol_id = getSymbolId( row.getSymbol() ); 						//find the id
				} else {																//if not
					symbol_id = addSymbolToDb( row.getSymbol(), row.getCompanyName() );	//add it, return the id
				}
				
				int tracking_id;
				
				if( isSymbolIdActivelyTracked( symbol_id) ) {							//if the symbol id is already in the tracking db
					tracking_id = getTrackingIdBySymbolId(symbol_id);					//find the id
				} else {																//if not
					tracking_id = addTrackingToDb( symbol_id );							//add it, return the id
				}
				
				//row.setSymbol_id(symbol_id);											//add the symbol id to the row
				//row.setTrackingId(tracking_id);										//add the tracking id to the row
				
				// this method only runs when the database is not up to date, so I don't need to check
				// if the symbol is in the ranking db, I just need to add it
				addRowToRankingDb(row);													//add the row to the db
				
				//Check to see if the OHLCV data is up to date for this stock symbol
				runOhlcvUpdate(row);															//add the last 6 months of OHLCV data to the db
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean isSymbolInDb(String symbol) throws SQLException {
		return symbolDao.isSymbolInDb(symbol);
	}
	private int getSymbolId(String symbol) throws SQLException {
		return symbolDao.getIdBySymbol(symbol);
	}
	private int addSymbolToDb(String symbol, String companyName) throws SQLException {
		return symbolDao.addSymbolToDb(symbol,companyName);
	}
	
	private boolean isSymbolIdActivelyTracked(int symbol_id) throws SQLException {
		return trackingDao.isSymbolIdActive(symbol_id);
	}
	private int getTrackingIdBySymbolId(int symbol_id) throws SQLException {
		return trackingDao.getIdByActiveSymbolId( symbol_id );
	}
	private int addTrackingToDb(int symbol_id) throws SQLException {
		return trackingDao.addSymbolToDb(symbol_id);
	}
	
	private void addRowToRankingDb(Ibd50Ranking row) throws SQLException {
		rankingDao.addRowToDb(row);
	}
	
	/**
	 * Check to see if the symbol is already in the db.
	 * If so then get the last date
	 * 		if that date is within six months of today
	 * 			use that date as the start date and today as the end date
	 *	if not in the db
	 *		use six months ago as the start date and today as the end date
	 *  	
	 * 
	 * @param row
	 * @throws SQLException 
	 */
	private void runOhlcvUpdate(Ibd50Ranking row) throws SQLException {
		final int monthsOfData = 6; 
		/*
		LocalDate latestDate = ohlcvDao.getLatestDate(row.getSymbol_id());
		LocalDate today = new LocalDate();
		LocalDate startDate = today.minusMonths(monthsOfData);
		if(latestDate!=null) {
			if( latestDate.isAfter(startDate) ) { 		//if the date in the db is after the proposed start date
				startDate = latestDate;					//then use the date from the db
			}											//else keep original start date
		}
		List<stockOhlcvBean> ohlcvData = YahooDataRetriever.getStockFromYahoo(row.getSymbol(), startDate, today, row.getSymbol_id());
		
		ohlcvDao.addOhlcvDataToDb(ohlcvData);
		*/
	}
}
