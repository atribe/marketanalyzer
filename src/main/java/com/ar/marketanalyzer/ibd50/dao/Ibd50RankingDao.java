package com.ar.marketanalyzer.ibd50.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50RankingBean;

public class Ibd50RankingDao extends GenericDBSuperclass{

	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */	
	public Ibd50RankingDao() throws ClassNotFoundException, SQLException {
		setDs(MarketPredDataSource.setDataSource());
	}
	
	public Ibd50RankingDao(DataSource ds) {
		setDs(ds);
	}
	
	public synchronized void tableInit() {
		Ibd50RankingBean ibd = new Ibd50RankingBean();
		
		String tableName = Ibd50RankingBean.getTableName();
		
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = ibd.tableCreationString();
			createTable(createTableSQL, tableName);
		}
	}

	public void symbolTableInit() {
		if(!tableExists(SYMBOL_TABLE_NAME)) {		// Table does not exist, so create it
			
			String createTableSQL = "CREATE TABLE `" + SYMBOL_TABLE_NAME + "` (" +
					" symbol_id INT NOT NULL AUTO_INCREMENT," +
					" symbol char(10) NOT NULL," +
					" PRIMARY KEY (symbol_id))";
			
			createTable(createTableSQL, SYMBOL_TABLE_NAME);
		}		
	}
}
