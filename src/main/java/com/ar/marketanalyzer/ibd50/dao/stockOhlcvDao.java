package com.ar.marketanalyzer.ibd50.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.stockOhlcvBean;


public class stockOhlcvDao extends GenericDBSuperclass {
	
	public stockOhlcvDao() throws ClassNotFoundException, SQLException {
		setDs(MarketPredDataSource.setDataSource());
	}
	
	public stockOhlcvDao(DataSource ds) {
		setDs(ds);
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

	public LocalDate getLatestDate(Integer symbol_id) throws SQLException {
		stockOhlcvBean b;
		String tableName = stockOhlcvBean.getTablename();
		
		String query = "Select *"
						+ " FROM `" + tableName + "` o"
						+ " WHERE o.symbol_id=?"
						+ " ORDER BY date "
						+ " DESC LIMIT 1";
		
		QueryRunner runner = new QueryRunner(ds);
		ResultSetHandler<stockOhlcvBean> h = new BeanHandler<stockOhlcvBean>(stockOhlcvBean.class);
		
		b = runner.query(
				query,
				h,
				symbol_id
				);
		
		return b.getLocalDate();
	}

	public void addOhlcvDataToDb(List<stockOhlcvBean> ohlcvData) {
		
	}
}
