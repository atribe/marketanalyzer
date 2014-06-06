package com.ar.marketanalyzer.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ar.marketanalyzer.ibd50.IBD50Grabber;
import com.ar.marketanalyzer.ibd50.beans.IBD50Bean;

public class IBD50Dao extends GenericDBSuperclass{

	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */	
	public IBD50Dao() throws ClassNotFoundException, SQLException {
		setM_ds(MarketPredDataSource.setDataSource());
	}
	
	public IBD50Dao(DataSource ds) {
		setM_ds(ds);
	}
	
	public synchronized void tableInitialization() {
		IBD50Bean ibd = new IBD50Bean();
		
		String tableName = IBD50Bean.getTablename();
		
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = ibd.tableCreationString();
			createTable(createTableSQL, tableName);
		}
		
		if(tableEmpty(tableName)){
			//if table is empty -> populate it
			IBD50Grabber.main();
		}
	}
}
