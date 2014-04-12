package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.atomrockets.marketanalyzer.beans.IndexCalcs;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;

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
		try {
			
			m_connection = getConnection();
			
		} catch (ClassNotFoundException e) {
			// Handles errors if the JDBC driver class not found.
			log.error("Database Driver not found in " + GenericDBSuperclass.class.getSimpleName() + ". Error as follows: "+e);
		} catch (SQLException ex){
			// Handles any errors from MySQL
			log.error("Did you forget to turn on Apache and MySQLL again? From Exception:");
			log.error("SQLException: " + ex.getMessage());
			log.error("SQLState: " + ex.getSQLState());
			log.error("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				m_connection.close();
			} catch (NullPointerException ne) {
				//do nothing, just means that the connection was never initialized
				log.debug("Connection didn't need to be closed, it was never initialized");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
	public IndexCalcsDAO(Connection connection) {
		log.debug("------------------------------Index Analysis Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = connection;
	}

	public void tableInitialization(String[] indexList) {
		log.info("Starting Index Analysis Database Initialization");
		
		IndexCalcs c = new IndexCalcs();
		
		@SuppressWarnings("static-access")
		String tableName = c.getTableName();
		
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
		int batchSize = 100;
		
		try {
			long counter = 0;
			//preparing the MySQL statement
			ps = m_connection.prepareStatement(insertQuery);
			//creating DbUtils QuerryRunner
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
