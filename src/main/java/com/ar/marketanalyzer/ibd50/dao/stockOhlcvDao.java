package com.ar.marketanalyzer.ibd50.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50RankingBean;
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
		
		if(b==null) {
			return null;
		} else {
			return b.getLocalDate();
		}
	}

	public void addOhlcvDataToDb(List<stockOhlcvBean> ohlcvData) {
		//This query ignores duplicate dates
		String insertQuery = "INSERT INTO `" + stockOhlcvBean.getTablename() + "` "
				+ "(symbol_id,date,open,high,low,close,volume) VALUES"
				+ "(?,?,?,?,?,?,?)";
		String [] columnNames = {"symbol_id","date","open","high","low","close","volume"};
		PreparedStatement ps=null;
		int batchSize = 100;
		try {
			Connection con = ds.getConnection();
			//preparing the MySQL statement
			ps = con.prepareStatement(insertQuery);
			//creating DbUtils QueryRunner
			QueryRunner runner = new QueryRunner();
			int i=0;
			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (i = ohlcvData.size()-1; i > 0 ; i--) {
				
				runner.fillStatementWithBean(ps, ohlcvData.get(i), columnNames);
				
				ps.addBatch();
				if (i % batchSize == 0) //if i/batch size remainder == 0 execute batch
				{
					ps.executeBatch();
					log.info("Executed at i="+i);
				}
			}
			//Execute the last batch, in case the last value of i isn't a multiple of batchSize
			ps.executeBatch();
			con.close();
			
			log.info("Final batch executed at i="+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			log.info(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqlEx) { } // ignore

				ps = null;
			}
		}
	}
}
