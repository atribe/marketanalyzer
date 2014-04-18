package com.atomrockets.marketanalyzer.services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.OHLCVData;
import com.atomrockets.marketanalyzer.beans.StockTransaction;
import com.atomrockets.marketanalyzer.dbManagers.IndexCalcsDAO;
import com.atomrockets.marketanalyzer.dbManagers.BacktestResultDAO;
import com.atomrockets.marketanalyzer.dbManagers.MarketPredDataSource;
import com.atomrockets.marketanalyzer.dbManagers.OHLCVDao;
import com.atomrockets.marketanalyzer.dbManagers.StockTransactionDAO;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

public class BacktestService extends GenericServiceSuperclass{
	
	public BacktestService() {
	
		m_ds = MarketPredDataSource.setDataSource();
		setM_connectionAlive(true);
		m_OHLCVDao = new OHLCVDao(m_ds);
		m_backtestResultDAO = new BacktestResultDAO(m_ds);
		m_stockTransationDAO = new StockTransactionDAO(m_ds);
		m_indexCalcsDAO = new IndexCalcsDAO(m_ds);
	
	}
	public BacktestService(DataSource ds) {
		m_ds = ds;
		m_OHLCVDao = new OHLCVDao(m_ds);
		m_backtestResultDAO = new BacktestResultDAO(m_ds);
		m_stockTransationDAO = new StockTransactionDAO(m_ds);
		m_indexCalcsDAO = new IndexCalcsDAO(m_ds);
	}
	
	public List<String> getIndexList()
	{
		String[] indexList = PropCache.getCachedProps("index.names").split(",");
		return Arrays.asList(indexList);
	}
	
	@Override
	public void setM_connectionAlive(boolean m_connectionAlive) {
		this.m_connectionAlive = m_connectionAlive;
	}
	
	public void init(String[] indexList) {
		try {
			//Creating the tables if they aren't created already
			m_backtestResultDAO.tableInitialization(indexList);
			
			m_stockTransationDAO.tableInitialization(indexList);
			
			//String tableName = StockTransaction.getTableName();
			
			//Check to see if there is a baseline 
			for(String symbol:indexList) {
				runBaseline(symbol);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void runBaseline(String symbol) throws SQLException {
		BacktestResult backtest = m_backtestResultDAO.getSymbolParameters(symbol);
		
		OHLCVData beginningDataPoint = m_OHLCVDao.getBySymbolAndDate(symbol, new LocalDate(backtest.getStartDate()));
		OHLCVData endingDataPoint = m_OHLCVDao.getBySymbolAndDate(symbol, new LocalDate(backtest.getEndDate()));
		
		StockTransaction d = new StockTransaction(backtest.getId(), beginningDataPoint, endingDataPoint);
		
		m_stockTransationDAO.insertTransaction(d);
		//TODO
		/*
		 * There needs to be a follow up method that inserts results into the backtest table
		 */
		backtest.setNumberOfTrades(1);
		if(d.getProfitable()) {
			backtest.setNumberOfProfitableTrades(1);
		} else {
			backtest.setNumberOfProfitableTrades(0);
		}
		backtest.setTotalPercentReturn(d.getPercentReturn());
		
		m_backtestResultDAO.insertOrUpdateBacktest(backtest);
	}
	
	public BacktestResult getBaseline(String symbol) {
		BacktestResult b = null;
		try {
			b = m_backtestResultDAO.getSymbolParameters(symbol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
