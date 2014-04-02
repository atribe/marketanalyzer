package com.atomrockets.marketanalyzer.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.atomrockets.marketanalyzer.dbManagers.GenericDBSuperclass;
import com.atomrockets.marketanalyzer.dbManagers.IndexCalcsDAO;
import com.atomrockets.marketanalyzer.dbManagers.IndexParameterTableManager;
import com.atomrockets.marketanalyzer.dbManagers.IndexYahooDataTableManager;
import com.atomrockets.marketanalyzer.models.IndexCalcs;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

public class BacktestService extends GenericServiceSuperclass{
	
	public BacktestService() {
		try {
			m_connection = GenericDBSuperclass.getConnection();
			setM_connectionAlive(true);
			m_indexYahooTable = new IndexYahooDataTableManager(m_connection);
			m_indexParamTable = new IndexParameterTableManager(m_connection);
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
		m_indexYahooTable = new IndexYahooDataTableManager(m_connection);
		m_indexParamTable = new IndexParameterTableManager(m_connection);
		m_indexCalcsDAO = new IndexCalcsDAO(m_connection);
	}
	
	public List<String> getIndexList()
	{
		String[] indexList = PropCache.getCachedProps("index.names").split(",");
		return Arrays.asList(indexList);
	}
	
	public void setM_connectionAlive(boolean m_connectionAlive) {
		this.m_connectionAlive = m_connectionAlive;
	}
}
