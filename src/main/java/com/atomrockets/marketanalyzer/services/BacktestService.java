package com.atomrockets.marketanalyzer.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.BacktestResult.parametersTypeEnum;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.beans.OHLCVData;
import com.atomrockets.marketanalyzer.beans.StockTransaction;
import com.atomrockets.marketanalyzer.dbManagers.IndexCalcsDAO;
import com.atomrockets.marketanalyzer.dbManagers.BacktestResultDAO;
import com.atomrockets.marketanalyzer.dbManagers.MarketPredDataSource;
import com.atomrockets.marketanalyzer.dbManagers.OHLCVDao;
import com.atomrockets.marketanalyzer.dbManagers.StockTransactionDAO;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

public class BacktestService extends GenericServiceSuperclass{
	IndexCalcsService m_indexCalcsService;
	public BacktestService() {
	
		m_ds = MarketPredDataSource.setDataSource();
		setM_connectionAlive(true);
		m_OHLCVDao = new OHLCVDao(m_ds);
		m_backtestResultDAO = new BacktestResultDAO(m_ds);
		m_stockTransationDAO = new StockTransactionDAO(m_ds);
		m_indexCalcsDAO = new IndexCalcsDAO(m_ds);
		m_indexCalcsService = new IndexCalcsService(m_ds); 
	
	}
	public BacktestService(DataSource ds) {
		m_ds = ds;
		m_OHLCVDao = new OHLCVDao(m_ds);
		m_backtestResultDAO = new BacktestResultDAO(m_ds);
		m_stockTransationDAO = new StockTransactionDAO(m_ds);
		m_indexCalcsDAO = new IndexCalcsDAO(m_ds);
		m_indexCalcsService = new IndexCalcsService(m_ds); 
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
		BacktestResult backtest = getBaseline(symbol);
		
		OHLCVData beginningDataPoint = m_OHLCVDao.getValidDate(symbol, new LocalDate(backtest.getStartDate()), true);
		OHLCVData endingDataPoint = m_OHLCVDao.getValidDate(symbol, new LocalDate(backtest.getEndDate()), false);
		
		StockTransaction d = new StockTransaction(backtest.getId(), beginningDataPoint, endingDataPoint);
		
		m_stockTransationDAO.insertTransaction(d);
		
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
			b = m_backtestResultDAO.getSymbolParameters(symbol, parametersTypeEnum.BASE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public BacktestResult getCurrent(String symbol) {
		BacktestResult b = null;
		try {
			b = m_backtestResultDAO.getSymbolParameters(symbol, parametersTypeEnum.CURRENT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public void runAllIndexModels(String[] indexList) {
		for(String symbol:indexList) {
			log.info("3.8.1 Starting backtest for " + symbol);
			try {
				//Getting the new backtest
				//Need a check to see if the backtest has already been run, if so then we don't need to run it again.
				BacktestResult newBacktest = getBaseline(symbol);
				
				BacktestResult currentBacktest = getCurrent(symbol);
				
				if(!newBacktest.equals(currentBacktest)) {
					newBacktest.setParametersType(parametersTypeEnum.CURRENT);
					newBacktest.setId(0); //need to set the ID to 0 so it will be auto incremented instead of updating the baseline row
					//insert the new backtest into the database. I need the autoincrement id to be set for the transaction stuff
					int id = m_backtestResultDAO.insertOrUpdateBacktest(newBacktest);
					newBacktest.setId(id);
					
					//run the backtest
					newBacktest = runIndexModels(newBacktest);
					
					//update the backtest in the DB
					m_backtestResultDAO.insertOrUpdateBacktest(newBacktest);
				} //else the model has that would have been run has already been run
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private BacktestResult runIndexModels(BacktestResult newBacktest) throws SQLException {
		/*
		 * Get the parameters you want to backtest
		 * Create a list to hold all of the transactions 
		 * Get the data from start to end date
		 * buy first
		 * sell when d day count goes above the d day param
		 * buy again at the next follow through day
		 * sell again...
		 * 
		 * add each buy sell pair to a transaction.
		 */
		
		//getting parameters from the backtest
		LocalDate firstBuyDate = new LocalDate(newBacktest.getStartDate());
		LocalDate lastSellDate = new LocalDate(newBacktest.getEndDate());
		int backtestId = newBacktest.getId();
		
		//initializing variables to be added as backtest results
		double cummulativePercentReturn = 0;
		int numberOfTrades = 0;
		int numberOfPofitableTrades = 0;
		
		//Creating list to hold the transactions
		List<StockTransaction> transactionList = new ArrayList<StockTransaction>();
		//Setting up the first transaction. You always buy first thing in a backtest
		StockTransaction transaction = new StockTransaction(backtestId);
		

		//Getting the OHLCV and Calcs data for the backtesting
		List<IndexOHLCVCalcs> OHLCVCalcsList = m_indexCalcsService.getRowsBetweenDatesBySymbol(newBacktest.getSymbol(), firstBuyDate, lastSellDate);
		transaction.OpenTransaction(OHLCVCalcsList.get(0));
		
		//looping through all the data
		for(IndexOHLCVCalcs c : OHLCVCalcsList ) {
			
			if(Boolean.TRUE.equals(c.getFollowThruDay())) { //if the day is a follow thru day, buy
				if(!transaction.isTransactionOpen()) { //if the transaction isn't already open, then open it
					transaction.OpenTransaction(c);
				}
			} else if( transaction.getBuyDate() != null && //if the buy date has already been set
					c.getDistributionDayCounter() > newBacktest.getdDayParam()) { //and if there have been too many d day sell
				transaction.CloseTransaction(c);
			}
			
			if(transaction.isTransactionClosed()) { //if the transaction is complete (all require fields have been entered
				//adding stats of this trade to the cumulative backtest results
				cummulativePercentReturn += transaction.getPercentReturn();
				numberOfTrades++;
				if(transaction.getProfitable()) {
					numberOfPofitableTrades++;
				}
				m_stockTransationDAO.insertTransaction(transaction);
				transaction = new StockTransaction(backtestId);//reseting the variable for new info
			}
			
		}
		
		//close up the last transaction if it hasn't been already
		if( transaction.isTransactionOpen() && !transaction.isTransactionClosed()) {
			transaction.CloseTransaction(OHLCVCalcsList.get(OHLCVCalcsList.size()-1));//close the transaction with the last data point in the list
			if(transaction.isTransactionClosed()) {
				cummulativePercentReturn += transaction.getPercentReturn();
				numberOfTrades++;
				if(transaction.getProfitable()) {
					numberOfPofitableTrades++;
				}
				m_stockTransationDAO.insertTransaction(transaction);
			}
		}
		
		//Add the totalReturn to the newBacktestResult
		newBacktest.setTotalPercentReturn(cummulativePercentReturn);
		newBacktest.setNumberOfTrades(numberOfTrades);
		newBacktest.setNumberOfProfitableTrades(numberOfPofitableTrades);
		
		return newBacktest;
	}

	public List<IndexOHLCVCalcs> getModelResults(String symbol, parametersTypeEnum type) {
		List<IndexOHLCVCalcs> resultList = new ArrayList<IndexOHLCVCalcs>();
		BacktestResult modelResult = null;

		if( type==parametersTypeEnum.CURRENT ) {
			modelResult = getCurrent(symbol);
		} else if (type == parametersTypeEnum.BASE) {
			modelResult = getCurrent(symbol);
		}
		
		resultList = m_indexCalcsService.getRowsBetweenDatesBySymbol(symbol, modelResult.getLocalDateStartDate(), modelResult.getLocalDateEndDate());
		
		return resultList;
	}
	
	public List<StockTransaction> getCurrentTransactions(String symbol) {
		return m_stockTransationDAO.getTransactions(getCurrent(symbol));
	}
}
