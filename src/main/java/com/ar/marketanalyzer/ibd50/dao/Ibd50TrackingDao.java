package com.ar.marketanalyzer.ibd50.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50TrackingBean;

public class Ibd50TrackingDao extends GenericDBSuperclass {

	public Ibd50TrackingDao() throws ClassNotFoundException, SQLException {
		setM_ds(MarketPredDataSource.setDataSource());
	}
	
	public Ibd50TrackingDao(DataSource ds) {
		setM_ds(ds);
	}
	
	public synchronized void tableInit() {
		Ibd50TrackingBean ibd50Tracking = new Ibd50TrackingBean();
		
		String tableName = Ibd50TrackingBean.getTableName();
		
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = ibd50Tracking.tableCreationString();
			createTable(createTableSQL, tableName);
		}
	}
}
