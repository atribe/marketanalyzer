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
import com.ar.marketanalyzer.ibd50.beans.Ibd50TrackingBean;

public class Ibd50TrackingDao extends GenericDBSuperclass {
	
	private static String tableName = Ibd50TrackingBean.getTableName();

	public Ibd50TrackingDao() throws ClassNotFoundException, SQLException {
		setDs(MarketPredDataSource.setDataSource());
	}
	
	public Ibd50TrackingDao(DataSource ds) {
		setDs(ds);
	}
	
	public synchronized void tableInit() {
		Ibd50TrackingBean ibd50Tracking = new Ibd50TrackingBean();
		
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

	public boolean isSymbolIdActive(int symbol_id) throws SQLException {

		boolean symbolActive = false;

		Object[] result = idActiveQuery(symbol_id);
			
		if(result != null) {
			symbolActive = true;
		}
		
		return symbolActive;
		
	}

	public int getIdByActiveSymbolId(int symbol_id) throws SQLException {
		
		Object[] result = idActiveQuery(symbol_id);
		
		return (int) result[0];
	}
	
	private Object[] idActiveQuery(int symbol_id) throws SQLException {
		String checkQuery = "SELECT `tracking_id` FROM `" + tableName + "`"
				+ " WHERE `symbol_id` = ?"
				+ " AND `active` = 1";
		
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
							symbol_id
							);
		
		return result;
	}

	public int addSymbolToDb(int symbol_id) throws SQLException {
		//Inserting the new symbol
		Ibd50TrackingBean d = new Ibd50TrackingBean(symbol_id);
		
		String insertQuery = d.getInsertOrUpdateQuery();
		
		String[] columnNames = d.getColumnNameList();
		
		PreparedStatement ps = null;
		Connection con = ds.getConnection();
		ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
		
		QueryRunner runner = new QueryRunner();
		
		runner.fillStatementWithBean(ps, d, columnNames);
		
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
