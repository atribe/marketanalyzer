package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
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
		setM_ds(MarketPredDataSource.setDataSource());
	}
	
	public StockTransactionDAO(DataSource ds) {
		log.debug("------------------------------StockTransactionDAO Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_ds = ds;
	}
	
	public void tableInitialization(String[] indexList) {
		log.info("Starting StockTransaction Database Initialization");
		
		StockTransaction d = new StockTransaction();
		
		String tableName = StockTransaction.getTableName();
		
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
		Connection con = m_ds.getConnection();
		ps = con.prepareStatement(insertQuery);
		
		QueryRunner runner = new QueryRunner();
		
		runner.fillStatementWithBean(ps, d, columnNames);
		
		ps.execute();
		con.close();
	}

	private long transactionAlreadyInDB(StockTransaction d) throws SQLException {
		StockTransaction inDBAlready = new StockTransaction();
		
		String query = "SELECT id"
				+ " FROM `" + StockTransaction.getTableName() + "`"
				+ " WHERE backtestId = ?"
				+ " AND buyDate = ?"
				+ " AND sellDate = ?";
		
		QueryRunner runner = new QueryRunner(m_ds);
		ResultSetHandler<StockTransaction> h = new BeanHandler<StockTransaction>(StockTransaction.class);
		
		inDBAlready = runner.query(
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

	public List<StockTransaction> getTransactions(BacktestResult backtest) {
		List<StockTransaction> transactionList = new ArrayList<StockTransaction>();
		
		String query = "SELECT *" +
				" FROM `" + StockTransaction.getTableName() + "` S" +
				" WHERE S.backtestId= ?" +
				" ORDER BY S.buyDate ASC";
		
		QueryRunner run = new QueryRunner();
		
		ResultSetHandler<List<StockTransaction>> h = new BeanListHandler<StockTransaction>(StockTransaction.class);
		
		try{
			Connection con = m_ds.getConnection();
			transactionList = run.query(
		    		con, //connection
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		backtest.getId());

			con.close();
		        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return transactionList;
	}
}
