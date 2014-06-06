package com.ar.marketanalyzer.indexbacktest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.indexbacktest.beans.IndexCalcs;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVCalcs;

public class IndexCalcsDAO extends GenericDBSuperclass{

	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * table names
	 * 
	 */
	
	public IndexCalcsDAO() {
		setM_ds(MarketPredDataSource.setDataSource());
	}
			
	public IndexCalcsDAO(DataSource ds) {
		log.debug("------------------------------Index Analysis Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_ds = ds;
	}

	public void tableInitialization(String[] indexList) {
		log.info("Starting Index Analysis Database Initialization");
		
		IndexCalcs c = new IndexCalcs();
		
		String tableName = IndexCalcs.getTableName();
		
		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.info("     -Checking if table " + tableName + " exists.");
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = c.tableCreationString();
			createTable(createTableSQL, tableName);
		}
		
	}

	public void addAllRowsToDB(String indexTableName, List<IndexOHLCVCalcs> analysisRows) {
		
		IndexCalcs c = new IndexCalcs();
		
		String insertQuery = c.getInsertOrUpdateQuery();
		
		String[] columnNames = c.getColumnNameList();
		
		//Batch add
		//Follow the initalAddRecordsFromData method in the MarketIndexDB class
		PreparedStatement ps=null;
		int batchSize = 1000;
		
		try {
			long counter = 0;
			//preparing the MySQL statement
			Connection con = m_ds.getConnection();
			ps = con.prepareStatement(insertQuery);
			//creating DbUtils QueryRunner
			QueryRunner runner = new QueryRunner();
			
			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (IndexOHLCVCalcs row:analysisRows) {
				
				/*
				 * New code using DbUtils
				 */
				runner.fillStatementWithBean(ps, row, columnNames);
				/*
				 * Wasn't that awesome
				 */
				ps.addBatch();
				counter++;
				
				if (counter % batchSize == 0) { //if i/batch size remainder == 0 execute batch
					ps.executeBatch();
					log.info("IndexCalc insert executed at i="+counter);
				}
			}
			//execute the batch at the end for the leftovers that didn't hit counter%batch==0
			ps.executeBatch();
			con.close();
			log.info("IndexCalc insert executed at i="+counter);
			
		} catch (SQLException e) {
			log.info("SQLException: " + e.getMessage());
			log.info("SQLState: " + e.getSQLState());
			log.info("VendorError: " + e.getErrorCode());
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
