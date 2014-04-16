package com.atomrockets.marketanalyzer.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.IndexCalcs;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.beans.OHLCVData;
import com.atomrockets.marketanalyzer.beans.StockTransaction;
import com.atomrockets.marketanalyzer.dbManagers.GenericDBSuperclass;
import com.atomrockets.marketanalyzer.dbManagers.IndexCalcsDAO;
import com.atomrockets.marketanalyzer.dbManagers.BacktestResultDAO;
import com.atomrockets.marketanalyzer.dbManagers.OHLCVDao;
import com.atomrockets.marketanalyzer.dbManagers.StockTransactionDAO;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

public class BacktestService extends GenericServiceSuperclass{
	
	public BacktestService() {
		try {
			m_connection = GenericDBSuperclass.getConnection();
			setM_connectionAlive(true);
			m_OHLCVDao = new OHLCVDao(m_connection);
			m_backtestResultDAO = new BacktestResultDAO(m_connection);
			m_stockTransationDAO = new StockTransactionDAO(m_connection);
			m_indexCalcsDAO = new IndexCalcsDAO(m_connection);
		} catch (ClassNotFoundException e) {
			// Handles errors if the JDBC driver class not found.
			setM_connectionAlive(false);
			log.error("Database Driver not found in " + GenericDBSuperclass.class.getSimpleName() + ". Error as follows: "+e);
		} catch (SQLException ex){
			// Handles any errors from MySQL
			setM_connectionAlive(false);
			log.error("Did you forget to turn on Apache and MySQLL again? From Exception:");
			log.error("SQLException: " + ex.getMessage());
			log.error("SQLState: " + ex.getSQLState());
			log.error("VendorError: " + ex.getErrorCode());
		}
	}
	public BacktestService(Connection connection) {
		m_connection = connection;
		m_OHLCVDao = new OHLCVDao(m_connection);
		m_backtestResultDAO = new BacktestResultDAO(m_connection);
		m_stockTransationDAO = new StockTransactionDAO(m_connection);
		m_indexCalcsDAO = new IndexCalcsDAO(m_connection);
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
	}
}
