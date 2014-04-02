package com.atomrockets.marketanalyzer.services;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.atomrockets.marketanalyzer.dbManagers.IndexCalcsDAO;
import com.atomrockets.marketanalyzer.dbManagers.IndexParameterTableManager;
import com.atomrockets.marketanalyzer.dbManagers.IndexYahooDataTableManager;
import com.atomrockets.marketanalyzer.models.IndexCalcs;

public class GenericServiceSuperclass {

	//Connection to the database
		protected Connection m_connection;
		protected boolean m_connectionAlive;
		
		//logger
		protected Logger log = Logger.getLogger(this.getClass().getName());
		
		//Database Table Managers
		protected IndexYahooDataTableManager m_indexYahooTable;
		protected IndexParameterTableManager m_indexParamTable;
		protected IndexCalcsDAO m_indexCalcsDAO;
		
		protected List<IndexCalcs> m_IndexCalcList;
		
		public void setM_connectionAlive(boolean m_connectionAlive) {
			this.m_connectionAlive = m_connectionAlive;
		}
}
