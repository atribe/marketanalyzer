package com.ar.marketanalyzer.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * This class is the Parent class to all database classes (DAOs)
 * 
 * List protected members used by classes that inherit:
 * ds - DataSource for mySQL database and connection pool
 * SYMBOL_TABLE_NAME - constant for stock symbols table name
 * log - the non-static log to be used in each class that inherits this
 * 
 * @author Allan
 */
public class GenericDBSuperclass {

	protected static Logger staticLog = Logger.getLogger(GenericDBSuperclass.class.getName());
	//Setting the logger for the class
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	//Member variable of the connection, so I don't have to keep passing it between methods
	//This variable only needs to be set once in the classes that inherit this class.
	protected static DataSource ds;
	
	//used in the ibd50 stuff
	/**
	 * Constant that holds the name of the stock symbol table
	 */
	public final static String SYMBOL_TABLE_NAME = "stock_symbols";
	
	public static DataSource getDs() {
		return ds;
	}

	public static void setDs(DataSource ds) {
		GenericDBSuperclass.ds = ds;
	}

	/**
	 * This method checks to see if a table by the given name
	 * exists in the database.
	 * 
	 * @param tableName
	 * @return boolean - true if table exists, false if table name not found
	 */
	public boolean tableExists(String tableName){
		boolean tableExists = false;

		DatabaseMetaData metadata = null;
		ResultSet tables = null;

		try {
			Connection con = ds.getConnection();
			metadata = con.getMetaData();
			con.close();
			tables = metadata.getTables(null, null, tableName, null);

			if (tables.next()) {
				// Table exists
				log.info(tables.getString("TABLE_TYPE") + " " 
						+ tables.getString("TABLE_NAME")
						+ " already exists.");
				tableExists = true;
			}
			else {
				tableExists = false;
				log.info("Table " + tableName + " does not yet exist.");
			}
		} catch (SQLException ex){
			// handle any errors
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed
			if (tables != null) {
				try {
					tables.close();
				} catch (SQLException sqlEx) { } // ignore

				tables = null;
			}
		}

		return tableExists;
	}

	/**
	 * Creates a mySQL table from the provided name and creation String
	 * 
	 * @param createTableSQL
	 * @param tableName - name of the table to check
	 * @return boolean - TRUE if table was created, FALSE if table wasn't created
	 */
	protected synchronized boolean createTable(String createTableSQL, String tableName){
		boolean success = false;
		Statement createStatement = null;

		try {
			Connection con = ds.getConnection();
			createStatement = con.createStatement();
			createStatement.executeUpdate(createTableSQL);
			con.close();
			success = true;
		} catch (SQLException ex){
			// handle any errors
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
			
			success = false;
		}
		finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (createStatement != null) {
				try {
					createStatement.close();
				} catch (SQLException sqlEx) { } // ignore

				createStatement = null;
			}
		}
		if (!success) {
			log.info("          Table '" + tableName + "' wasn't created");
			return false;
		} else {
			log.info("          Table '" + tableName + "' has been created");
			return true;
		}
	}

	/**
	 * Checks if a table has any contents
	 * 
	 * @param tableName - name of the table to check
	 * @return boolean - TRUE is empty or FALSE is not empty
	 */
	protected boolean tableEmpty(String tableName) {
		boolean empty = true;
		Statement queryStatement = null;
		ResultSet rs = null;

		int i = 0;

		try {
			Connection con = ds.getConnection();
			queryStatement = con.createStatement();
			rs = queryStatement.executeQuery("SELECT * FROM `" + tableName + "`");
			con.close();
			while (rs.next())
			{
				empty = false;
				i++;
				if(i>2) {
					break;
				}
			}
		} catch (SQLException ex){
			// handle any errors
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		}
		finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) { } // ignore

				rs = null;
			}
			if (queryStatement != null) {
				try {
					queryStatement.close();
				} catch (SQLException sqlEx) { } // ignore

				queryStatement = null;
			}
		}
		if(empty) {
			log.info("          Table '" + tableName +"' is empty.");
		} else {
			log.info("          Table '" + tableName +"' has some stuff in it.");
		}
		return empty;
	}

	/**
	 * Performs a mySQL TRUNCATE.
	 * <p>
	 * The behind the scenes process actually drops the table and recreates it
	 * so it is really fast.
	 *  
	 * @param tableName - Name of the table to be reset
	 * @throws SQLException
	 */
	public void resetTable(String tableName) throws SQLException {
		String truncQuery = "TRUNCATE TABLE `" + tableName + "`";
		Connection con = ds.getConnection();
		Statement s = con.createStatement();
		s.execute(truncQuery);
		con.close();
	}
	
	/**
	 * Drops a mySQL table.
	 * 
	 * @param tableName - Name of the table to drop
	 * @return boolean - If successful TRUE is returned, if failed FALSE is returned
	 * @throws SQLException
	 */
	public boolean dropTable(String tableName) throws SQLException {
		String dropQuery = "DROP TABLE IF EXISTS `" + tableName + "`";
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement(dropQuery);
		boolean success = ps.execute();
		con.close();
		return success;
	}
	
	/**
	 * Returns the last ID autoincremented by the table 
	 * 
	 * @param tableName - Name of the table to get the last ID from
	 * @return Returns the last ID from the table
	 * @throws SQLException
	 */
	protected long getLastRowId(String tableName) throws SQLException {
		Statement queryStatement = null;
		ResultSet rs = null;
		Connection con = ds.getConnection();
		queryStatement = con.createStatement();
		rs = queryStatement.executeQuery("select max(`id`) as last_id from `" + tableName + "`");
		con.close();
		long lastId = 0;
		if(rs.next())
			lastId = rs.getLong("last_id");
		return lastId;
	}
}
