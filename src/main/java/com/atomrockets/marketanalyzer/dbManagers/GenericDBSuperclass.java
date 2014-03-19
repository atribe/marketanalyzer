package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.atomrockets.marketanalyzer.spring.init.PropertiesLoader;

/**
 * This class is the Parent class to all database classes
 * @author Allan
 *
 */
public class GenericDBSuperclass {

	static Logger staticLog = Logger.getLogger(GenericDBSuperclass.class.getName());
	//Setting the logger for the class
	Logger log = Logger.getLogger(this.getClass().getName());
	
	//Member variable of the connection, so I don't have to keep passing it between methods
	//This variable only needs to be set once in the classes that inherit this class.
	protected static Connection m_connection;
	
	protected static final PropertiesLoader propertiesLoader = new PropertiesLoader();
	/**
	 * Establishes a connection to the database
	 * @return connection
	 */
	public static Connection getConnection() {
	
		Properties prop = propertiesLoader.loadActivePropertiesFile();
		
		m_connection = null;
		String host, port, dbURL, username, password, DBName;
		try {
			//Loading the JDBC MySQL drivers that are used by java.sql.Connection
			Class.forName(prop.getProperty("db.driver"));//loads the mysql driver from the property file

			// ************For Open Shift Account************	  
			//if(LoadProperties.environment.trim().equalsIgnoreCase("production")){
			if(System.getenv("OPENSHIFT_APP_NAME")!=null) {	
				host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");//$OPENSHIFT_MYSQL_DB_HOST is a OpenShift system variable
				port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");//$OPENSHIFT_MYSQL_DB_PORT is also an OpenShift variable
			}else{
				// ************For Local Account************
				host = prop.getProperty("db.host");
				port = prop.getProperty("db.port");
			}
			
			DBName = prop.getProperty("db.dbname");
			dbURL = "jdbc:mysql://"+host+":"+port+"/" + DBName;
			
			username = prop.getProperty("db.username");
			password = prop.getProperty("db.password");
			
			m_connection = DriverManager.getConnection(dbURL, username, password);

			staticLog.info("Connection established to " + DBName);
		} catch (ClassNotFoundException e) { //Handle errors for Class.forName
			staticLog.info("Database Driver not found in MarketDB.java with teedixindices "+e);
		} catch (SQLException ex){
			// handle any errors
			staticLog.info("Exception loading Database Driver in MarketDB.java with teedixindices");
			staticLog.info("Did you forget to turn on Apache and MySQLL again? From Exception:");
			staticLog.info("SQLException: " + ex.getMessage());
			staticLog.info("SQLState: " + ex.getSQLState());
			staticLog.info("VendorError: " + ex.getErrorCode());
		}
		return m_connection;
	}

	protected boolean tableExists(String tableName){
		boolean tableExists = false;

		DatabaseMetaData metadata = null;
		ResultSet tables = null;

		try {
			metadata = m_connection.getMetaData();
			tables = metadata.getTables(null, null, tableName, null);

			if (tables.next()) {
				// Table exists
				log.info(
						"          " + tables.getString("TABLE_TYPE") 
						+ " " + tables.getString("TABLE_NAME")
						+ " already exists.");
				tableExists = true;
			}
			else {
				tableExists = false;
				log.info("          Table " + tableName + " does not yet exist, let me try and create that for you.");
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
			createStatement = m_connection.createStatement();
			status = createStatement.executeUpdate(createTableSQL);
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
			return true;
		}
		else
		{
			log.info("          Table '" + tableName + "' has been created");
			return false;
		}
	}

	protected boolean tableEmpty(String tableName) {
		boolean empty = true;
		Statement queryStatement = null;
		ResultSet rs = null;

		int i = 0;

		try {
			queryStatement = m_connection.createStatement();
			rs = queryStatement.executeQuery("SELECT * FROM `" + tableName + "`");
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
		Statement s = m_connection.createStatement();
		s.execute(truncQuery);
	}
	
	public boolean dropTable(String tableName) throws SQLException {
		String dropQuery = "DROP TABLE IF EXISTS `" + tableName + "`";
		PreparedStatement ps = m_connection.prepareStatement(dropQuery);
		return ps.execute();
	}
	
	protected int getLastRowId(String tableName) throws SQLException {
		Statement queryStatement = null;
		ResultSet rs = null;
		
		queryStatement = m_connection.createStatement();
		rs = queryStatement.executeQuery("select max(`yd_id`) as last_id from `" + tableName + "`");
		int lastId = 0;
		if(rs.next())
			lastId = rs.getInt("last_id");
		return lastId;
	}
}