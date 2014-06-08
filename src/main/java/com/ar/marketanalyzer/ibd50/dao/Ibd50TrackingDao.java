package com.ar.marketanalyzer.ibd50.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50RankingBean;
import com.ar.marketanalyzer.ibd50.beans.Ibd50TrackingBean;

public class Ibd50TrackingDao extends GenericDBSuperclass {

	public Ibd50TrackingDao() throws ClassNotFoundException, SQLException {
		setDs(MarketPredDataSource.setDataSource());
	}
	
	public Ibd50TrackingDao(DataSource ds) {
		setDs(ds);
	}
	
	public synchronized void tableInit() {
		Ibd50TrackingBean ibd50Tracking = new Ibd50TrackingBean();
		
		String tableName = Ibd50TrackingBean.getTableName();
		
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = ibd50Tracking.tableCreationString();
			/*
			String createTableSQL = "create table `" + tableName + "` ( "
				      + "   " + tableName + "_id INT PRIMARY KEY, "
				      + "   symbol_id INT, " 
				      + "   join_date DATE, "
				      + "   leave_date DATE, "
				      + "   join_price DECIMAL, "
				      + "   last_price DECIMAL, "
				      + "   percent_return DOUBLE, "
				      + "   FOREIGN KEY (symbol_id) REFERENCES " + SYMBOL_TABLE_NAME +"(symbol_id)"
				      + " ) ENGINE = MyISAM";
			*/
			createTable(createTableSQL, tableName);
		}
	}

	public boolean checkIfIbdUpToDate(List<Ibd50RankingBean> webIbd50) {
		boolean upToDate = false;
		
		
		
		
		return upToDate;
	}
}
