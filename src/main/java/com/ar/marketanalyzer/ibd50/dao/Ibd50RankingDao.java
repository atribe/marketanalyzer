package com.ar.marketanalyzer.ibd50.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50RankingBean;
import com.ar.marketanalyzer.ibd50.beans.Ibd50TrackingBean;

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

	/**
	 * This should check and see if the rankDate of the first bean in the list 
	 * is before the next Monday(when the new list comes out) [not a new entry]
	 * or after it [new entry] 
	 * 
	 * @param webIbd50
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkIfIbdUpToDate(List<Ibd50RankingBean> webIbd50) throws SQLException {
		Ibd50RankingBean b;
		String tableName = Ibd50RankingBean.getTableName();
		String symbol = webIbd50.get(0).getSymbol();
		LocalDate downloadedDate =  webIbd50.get(0).getLocalDateRankDate();
		
		String query = "Select rankDate"
						+ " FROM `" + tableName + "` r"
						+ " INNER JOIN " + SYMBOL_TABLE_NAME + " s on r.symbol_id = s.symbol_id"
						+ " WHERE symbol=?";
		
		QueryRunner runner = new QueryRunner(ds);
		ResultSetHandler<Ibd50RankingBean> h = new BeanHandler<Ibd50RankingBean>(Ibd50RankingBean.class);
		
		b = runner.query(
				query,
				h,
				symbol
				);
		
		//if no result was found then the db must not be up to date
		if(b == null) {
			return false;
		}
		
		LocalDate adjustedDownloadedDate = adjustDateToMonday(downloadedDate);
		
		//if the downloaded day is after the database day the database is not up to date
		if(adjustedDownloadedDate.isAfter(b.getLocalDateRankDate()))
		{
			return false;
		} else {
			return true;
		}
	}

	private LocalDate adjustDateToMonday(LocalDate downloadedDate) {

		if(downloadedDate.getDayOfWeek() == DateTimeConstants.MONDAY) {
			return downloadedDate;
		} else {
			return downloadedDate.withDayOfWeek(DateTimeConstants.MONDAY);
		}
	}

	public int addRowToDb(Ibd50RankingBean row) throws SQLException {
				
		String insertQuery = row.getInsertOrUpdateQuery();
		
		String[] columnNames = row.getColumnNameList();
		
		PreparedStatement ps = null;
		Connection con = ds.getConnection();
		ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
		
		QueryRunner runner = new QueryRunner();
		
		runner.fillStatementWithBean(ps, row, columnNames);
		
		ps.execute();
		
		//Querying the new symbols id in the table
		int insertedId = 0;
		
		ResultSet keys = ps.getGeneratedKeys();    
		if( keys.next() ) { 
			insertedId = keys.getInt(1);
		}
		 
		con.close();
		
		return insertedId;
	}
}
