package com.ar.marketanalyzer.ibd50.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;

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
					" companyName char(50)," +
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
		String checkQuery = "SELECT `symbol_id` FROM `" + SYMBOL_TABLE_NAME + "`"
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
	public int addSymbolToDb(String symbol, String companyName) throws SQLException {
		
		//Inserting the new symbol
		String insertQuery = "INSERT INTO `" + SYMBOL_TABLE_NAME + "`"
				+ "(symbol,companyName) VALUES (?,?)";
		
		Connection con = ds.getConnection();
		
		PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, symbol);
		ps.setString(2, companyName);
		ps.executeUpdate(); 
		
		int insertedId = 0;
		
		ResultSet keys = ps.getGeneratedKeys();    
		if( keys.next() ) { 
			insertedId = keys.getInt(1);
		}
		con.close();
		
		return insertedId;
	}
	
	
}
