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
 * This class is the Parent class to all database classes
 * @author Allan
 *
 */
public class GenericDBSuperclass {

	protected static Logger staticLog = Logger.getLogger(GenericDBSuperclass.class.getName());
	//Setting the logger for the class
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	//Member variable of the connection, so I don't have to keep passing it between methods
	//This variable only needs to be set once in the classes that inherit this class.
	protected static DataSource m_ds;
	
	//used in the ibd50 stuff
	public final static String SYMBOL_TABLE_NAME = "stock_symbols";
	
	public static DataSource getM_ds() {
		return m_ds;
	}

	public static void setM_ds(DataSource m_ds) {
		GenericDBSuperclass.m_ds = m_ds;
	}

	public boolean tableExists(String tableName){
		boolean tableExists = false;

		DatabaseMetaData metadata = null;
		ResultSet tables = null;

		try {
			Connection con = m_ds.getConnection();
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

	protected synchronized boolean createTable(String createTableSQL, String tableName){
		int status=0;
		Statement createStatement = null;

		try {
			Connection con = m_ds.getConnection();
			createStatement = con.createStatement();
			status = createStatement.executeUpdate(createTableSQL);
			con.close();
		} catch (SQLException ex){
			// handle any errors
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
			
			status = 1;
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
		if (status>0)
		{
			log.info("          Table '" + tableName + "' wasn't created");
			return false;
		}
		else
		{
			log.info("          Table '" + tableName + "' has been created");
			return true;
		}
	}

	protected boolean tableEmpty(String tableName) {
		boolean empty = true;
		Statement queryStatement = null;
		ResultSet rs = null;

		int i = 0;

		try {
			Connection con = m_ds.getConnection();
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

	public void resetTable(String tableName) throws SQLException {
		String truncQuery = "TRUNCATE TABLE `" + tableName + "`";
		Connection con = m_ds.getConnection();
		Statement s = con.createStatement();
		s.execute(truncQuery);
		con.close();
	}
	
	public boolean dropTable(String tableName) throws SQLException {
		String dropQuery = "DROP TABLE IF EXISTS `" + tableName + "`";
		Connection con = m_ds.getConnection();
		PreparedStatement ps = con.prepareStatement(dropQuery);
		boolean success = ps.execute();
		con.close();
		return success;
	}
	
	protected long getLastRowId(String tableName) throws SQLException {
		Statement queryStatement = null;
		ResultSet rs = null;
		Connection con = m_ds.getConnection();
		queryStatement = con.createStatement();
		rs = queryStatement.executeQuery("select max(`id`) as last_id from `" + tableName + "`");
		con.close();
		long lastId = 0;
		if(rs.next())
			lastId = rs.getLong("last_id");
		return lastId;
	}
}
