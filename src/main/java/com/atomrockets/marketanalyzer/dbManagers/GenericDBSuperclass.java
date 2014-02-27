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

import com.atomrockets.marketanalyzer.spring.controller.AccountController;
import com.atomrockets.marketanalyzer.spring.init.PropertiesLoader;

/**
 * This class is the Parent class to all database classes
 * @author Allan
 *
 */
public class GenericDBSuperclass {

	static Logger log = Logger.getLogger(GenericDBSuperclass.class.getName());
	
	protected static final PropertiesLoader propertiesLoader = new PropertiesLoader();
	/**
	 * Establishes a connection to the database
	 * @return connection
	 */
	public static Connection getConnection() {
	
		Properties prop = propertiesLoader.loadActivePropertiesFile();
		
		Connection connection = null;
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
			
			connection = DriverManager.getConnection(dbURL, username, password);

			log.info("Connection established to " + DBName);
		} catch (ClassNotFoundException e) { //Handle errors for Class.forName
			log.info("Database Driver not found in MarketDB.java with teedixindices "+e);
		} catch (SQLException ex){
			// handle any errors
			log.info("Exception loading Database Driver in MarketDB.java with teedixindices");
			log.info("Did you forget to turn on Apache and MySQLL again? From Exception:");
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		}
		return connection;
	}

	protected static boolean tableExists(String tableName, Connection connection){
		boolean tableExists = false;

		DatabaseMetaData metadata = null;
		ResultSet tables = null;

		try {
			metadata = connection.getMetaData();
			tables = metadata.getTables(null, null, tableName, null);

			if (tables.next()) {
				// Table exists
				System.out.println(
						"          " + tables.getString("TABLE_TYPE") 
						+ " " + tables.getString("TABLE_NAME")
						+ " already exists.");
				tableExists = true;
			}
			else {
				tableExists = false;
				System.out.println("          Table " + tableName + "does not yet exist, let me try and create that for you.");
			}
		} catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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

	protected static synchronized boolean createTable(String createTableSQL, Connection connection, String tableName){
		int status=0;
		Statement createStatement = null;

		try {
			createStatement = connection.createStatement();
			status = createStatement.executeUpdate(createTableSQL);
		} catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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
			System.out.println("          Table '" + tableName + "' wasn't created");
			return true;
		}
		else
		{
			System.out.println("          Table '" + tableName + "' has been created");
			return false;
		}
	}

	protected static boolean tableEmpty(String tableName, Connection connection){
		boolean empty = true;
		Statement queryStatement = null;
		ResultSet rs = null;

		int i = 0;

		try {
			queryStatement = connection.createStatement();
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
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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
			System.out.println("          Table '" + tableName +"' is empty.");
		} else {
			System.out.println("          Table '" + tableName +"' has some stuff in it.");
		}
		return empty;
	}

	public static void resetTable(Connection connection, String tableName) throws SQLException {
		String truncQuery = "TRUNCATE TABLE `" + tableName + "`"; 
		Statement s = connection.createStatement();
		s.execute(truncQuery);
	}
	
	public static boolean dropTable(Connection connection, String tableName) throws SQLException {
		String dropQuery = "DROP TABLE IF EXISTS `" + tableName + "`";
		PreparedStatement ps = connection.prepareStatement(dropQuery);
		return ps.execute();
	}
	
	protected static int getLastRowId(Connection connection, String tableName) throws SQLException {
		Statement queryStatement = null;
		ResultSet rs = null;
		
		queryStatement = connection.createStatement();
		rs = queryStatement.executeQuery("select max(`id`) as last_id from `" + tableName + "`");
		int lastId = 0;
		if(rs.next())
			lastId = rs.getInt("last_id");
		return lastId;
	}
}
