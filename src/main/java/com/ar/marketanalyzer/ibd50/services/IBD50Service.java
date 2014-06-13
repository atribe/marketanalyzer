package com.ar.marketanalyzer.ibd50.services;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50RankingBean;
import com.ar.marketanalyzer.ibd50.beans.Ibd50TrackingBean;
import com.ar.marketanalyzer.ibd50.beans.stockOhlcvBean;
import com.ar.marketanalyzer.ibd50.dao.Ibd50RankingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50SymbolDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50TrackingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50WebDao;
import com.ar.marketanalyzer.ibd50.dao.stockOhlcvDao;

/**
 * @author Allan
 *
 */
public class IBD50Service {
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
	public IBD50Service() {
		ds = MarketPredDataSource.setDataSource();
		webDao = new Ibd50WebDao();
		symbolDao = new Ibd50SymbolDao();
		rankingDao = new Ibd50RankingDao(ds);
		trackingDao = new Ibd50TrackingDao(ds);
		ohlcvDao = new stockOhlcvDao(ds);
	}
	
	/**
	 * Constructor that uses the passed DataSource and then creates all 
	 * required DAOs with the DataSource
	 */
	public IBD50Service(DataSource passedds) {
		this.ds = passedds;
		webDao = new Ibd50WebDao();
		symbolDao = new Ibd50SymbolDao(ds);
		rankingDao = new Ibd50RankingDao(ds);
		trackingDao = new Ibd50TrackingDao(ds);
		ohlcvDao = new stockOhlcvDao(ds);
	}
	
	/**
	 * Creates all IBD50 related tables if they do not already exist
	 */
	public void tableInit() {
		symbolDao.tableInit();
		trackingDao.tableInit();
		rankingDao.tableInit();
		ohlcvDao.tableInit();
	}

	/**
	 * Drops all IBD50 related tables. Probably only want to use this
	 * in the early development phase.
	 */
	public void dropAllTables() {
		try {
			rankingDao.dropTable(Ibd50RankingBean.getTableName());
			rankingDao.dropTable(GenericDBSuperclass.SYMBOL_TABLE_NAME);
			trackingDao.dropTable(Ibd50TrackingBean.getTableName());
			ohlcvDao.dropTable(stockOhlcvBean.getTablename());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Pulls the current top 50 from investors.com and adds them to the database 
	 */
	public void updateFromIbd50Web() {
		List<Ibd50RankingBean> webIbd50 = webDao.grabIbd50();
		
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
	private void addWeeklyListToDB(List<Ibd50RankingBean> webIbd50) {
		
		if(!isDbUpToDate(webIbd50)) { //if db not up to date
			addThisWeeksListToDB(webIbd50);
		}
	}

	private boolean isDbUpToDate(List<Ibd50RankingBean>	webIbd50) {
		try {
			return rankingDao.checkIfIbdUpToDate(webIbd50);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private void addThisWeeksListToDB(List<Ibd50RankingBean> webIbd50) {
		for(Ibd50RankingBean row : webIbd50) {
			try {
				
				int symbol_id;
			
				if(isSymbolInDb(row.getSymbol())) { //if the symbol is already in the db, find the id
					symbol_id = getSymbolId(row.getSymbol());
				} else {
					symbol_id = addSymbolToDb(row.getSymbol());
				}
				
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
	
	private int addSymbolToDb(String symbol) throws SQLException {
		return symbolDao.addSymbolToDb(symbol);
	}
}
