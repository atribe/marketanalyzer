package com.ar.marketanalyzer.ibd50.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVData;

public class Ibd50SymbolDao extends GenericDBSuperclass {

	public Ibd50SymbolDao() {
		setDs(MarketPredDataSource.setDataSource());
	}
	
	public Ibd50SymbolDao(DataSource ds) {
		setDs(ds);
	}

	public void tableInit() {
		if(!tableExists(SYMBOL_TABLE_NAME)) {		// Table does not exist, so create it
			
			String createTableSQL = "CREATE TABLE `" + SYMBOL_TABLE_NAME + "` (" +
					" symbol_id INT NOT NULL AUTO_INCREMENT," +
					" symbol char(10) NOT NULL," +
					" PRIMARY KEY (symbol_id))";
			
			createTable(createTableSQL, SYMBOL_TABLE_NAME);
		}
	}

	public boolean isSymbolInDb(String symbol) throws SQLException {
		boolean alreadyExists = false;

		Object[] result = symbolQuery(symbol);
			
		if(result != null) {
			alreadyExists = true;
		}
		
		return alreadyExists;
		
	}

	public int getIdBySymbol(String symbol) throws SQLException {
		
		Object[] result = symbolQuery(symbol);
		
		return (int) result[0];
	}
	
	private Object[] symbolQuery(String symbol) throws SQLException {
		String checkQuery = "SELECT `id` FROM `" + SYMBOL_TABLE_NAME + "`"
				+ " WHERE `symbol` = ?";
		
		QueryRunner runner = new QueryRunner(ds);
		ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
		    public Object[] handle(ResultSet rs) throws SQLException {
		        if (!rs.next()) {
		            return null;
		        }
		    
		        ResultSetMetaData meta = rs.getMetaData();
		        int cols = meta.getColumnCount();
		        Object[] result = new Object[cols];

		        for (int i = 0; i < cols; i++) {
		            result[i] = rs.getObject(i + 1);
		        }

		        return result;
		    }
		};
		
		Object[] result = runner.query(
							checkQuery,
							h,
							symbol
							);
		
		return result;
	}

	/**
	 * Inserts a symbol into the db, then queries the last_insert_id()
	 * to get the id of that newly inserted symbol
	 * 
	 * @param symbol
	 * @return symbol_id column of newly inserted symbol
	 * @throws SQLException
	 */
	public int addSymbolToDb(String symbol) throws SQLException {
		
		//Inserting the new symbol
		String insertQuery = "INSERT INTO `" + SYMBOL_TABLE_NAME + "`"
				+ "(symbol) VALUES (?)";
		
		QueryRunner runner = new QueryRunner(ds);
		
		runner.update(insertQuery, symbol);
		
		//Querying the new symbols id in the table
		int insertedId = 0;
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement(insertQuery);
		ps = con.prepareStatement("SELECT LAST_INSERT_ID();");
		ResultSet rs = ps.executeQuery();
		
		if(rs != null && rs.next()) {
			insertedId = rs.getInt(1);
		}
		 
		con.close();
		
		return insertedId;
	}
	
	
}
