package com.ar.marketanalyzer.indexbacktest.services;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVCalcs;
import com.ar.marketanalyzer.indexbacktest.dao.BacktestResultDAO;
import com.ar.marketanalyzer.indexbacktest.dao.IndexCalcsDAO;
import com.ar.marketanalyzer.indexbacktest.dao.OHLCVDao;
import com.ar.marketanalyzer.indexbacktest.dao.StockTransactionDAO;

public class IndexBacktestServiceSuperclass {

	//Connection to the database
	DataSource m_ds;
	protected boolean m_connectionAlive;
	
	//logger
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	//Database Table Managers
	protected OHLCVDao m_OHLCVDao;
	protected BacktestResultDAO m_backtestResultDAO;
	protected StockTransactionDAO m_stockTransationDAO;
	protected IndexCalcsDAO m_indexCalcsDAO;
	
	protected List<IndexOHLCVCalcs> m_IndexCalcList;
	
	public void setM_connectionAlive(boolean m_connectionAlive) {
		this.m_connectionAlive = m_connectionAlive;
	}
	
	public boolean isM_connectionAlive() {
		return m_connectionAlive;
	}
}
