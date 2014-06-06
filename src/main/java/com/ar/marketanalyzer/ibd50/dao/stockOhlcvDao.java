package com.ar.marketanalyzer.ibd50.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.stockOhlcvBean;

public class stockOhlcvDao extends GenericDBSuperclass {
	
	public stockOhlcvDao() throws ClassNotFoundException, SQLException {
		setM_ds(MarketPredDataSource.setDataSource());
	}
	
	public stockOhlcvDao(DataSource ds) {
		setM_ds(ds);
	}
	
	public synchronized void tableInit() {
		stockOhlcvBean ohlcv = new stockOhlcvBean();
		
		String tableName = stockOhlcvBean.getTablename();
		
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = ohlcv.tableCreationString();
			createTable(createTableSQL, tableName);
		}
	}
}
