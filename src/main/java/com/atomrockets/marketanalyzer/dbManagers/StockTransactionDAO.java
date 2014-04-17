package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.atomrockets.marketanalyzer.beans.StockTransaction;

public class StockTransactionDAO extends GenericDBSuperclass{
	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * table names
	 * 
	 */
	
	public StockTransactionDAO() {
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
	
	public StockTransactionDAO(Connection connection) {
		log.debug("------------------------------StockTransactionDAO Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = connection;
	}
	
	public void tableInitialization(String[] indexList) {
		log.info("Starting StockTransaction Database Initialization");
		
		StockTransaction d = new StockTransaction();
		
		@SuppressWarnings("static-access")
		String tableName = d.getTableName();
		
		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.info("     -Checking if table " + tableName + " exists.");
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = d.tableCreationString();
			createTable(createTableSQL, tableName);
		}
	}

	public void insertTransaction(StockTransaction d) throws SQLException {
		
		/*
		 * if transaction is not in db id will be set to -1
		 * If transaction is in the db the id will be set to the id from the db
		 */
		
		long id = transactionAlreadyInDB(d);
		
		if(id>0) {
			d.setId(id);
		}
		String insertQuery = d.getInsertOrUpdateQuery();
		
		String[] columnNames = d.getColumnNameList();
		
		PreparedStatement ps = null;
	
		ps = m_connection.prepareStatement(insertQuery);
		
		QueryRunner runner = new QueryRunner();
		
		runner.fillStatementWithBean(ps, d, columnNames);
		
		ps.execute();
	}

	private long transactionAlreadyInDB(StockTransaction d) throws SQLException {
		StockTransaction inDBAlready = new StockTransaction();
		
		String query = "SELECT id"
				+ " FROM `" + StockTransaction.getTableName() + "`"
				+ " WHERE backtestId = ?"
				+ " AND buyDate = ?"
				+ " AND sellDate = ?";
		
		QueryRunner runner = new QueryRunner();
		ResultSetHandler<StockTransaction> h = new BeanHandler<StockTransaction>(StockTransaction.class);
		
		inDBAlready = runner.query(
						m_connection,
						query,
						h,
						d.getBacktestId(),
						d.getBuyDate(),
						d.getSellDate()	);
		
		if(inDBAlready==null) {
			return -1;
		} else {
			return inDBAlready.getId();
		}
		
	}
}
