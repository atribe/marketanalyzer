package com.ar.marketanalyzer.database;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ar.marketanalyzer.spring.init.PropCache;
import com.jolbox.bonecp.BoneCPDataSource;

public class MarketPredDataSource {
	/* Get actual class name to be printed on */
	static Logger staticLog = Logger.getLogger(MarketPredDataSource.class.getName());

	private static BoneCPDataSource ds;
	
    public static DataSource setDataSource() { 
    	//if(ds==null) {
    		
			String host, port, dbURL, username, password, DBName;
			ds = new BoneCPDataSource();
			
			ds.setDriverClass(PropCache.getCachedProps("db.driver"));
			/*
			try {
				Class.forName("org.hsqldb.jdbcDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			// ************For Open Shift Account************	  
			if(System.getenv("OPENSHIFT_APP_NAME")!=null) {	
				staticLog.trace("-----Preparing to connect to OpenShift DB");
				host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");//$OPENSHIFT_MYSQL_DB_HOST is a OpenShift system variable
				port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");//$OPENSHIFT_MYSQL_DB_PORT is also an OpenShift variable
				username = PropCache.getCachedProps("OpenShiftDB.username");
				password = PropCache.getCachedProps("OpenShiftDB.password");
			}else{
				// ************For Local Account************
				staticLog.trace("-----Preparing to connect to Local Xampp DB");
				host = PropCache.getCachedProps("localhostdb.host");
				port = PropCache.getCachedProps("localhostdb.port");
				username = PropCache.getCachedProps("localhostdb.username");
				password = PropCache.getCachedProps("localhostdb.password");
			}
			
			DBName = PropCache.getCachedProps("db.dbname");
			dbURL = String.format("jdbc:mysql://%s:%s/%s", host, port, DBName);
			/*
			 * HSQLDB
			 */
			//username = "sa";
			//password = "";
			//dbURL = String.format("jdbc:hsqldb:%s", DBName);
	           
	        ds.setJdbcUrl(dbURL);  
	        ds.setUsername(username);  
	        ds.setPassword(password);
	        
	        ds.setMaxConnectionsPerPartition(10);
	        ds.setMinConnectionsPerPartition(3);
	        
	        
	        staticLog.info("Connection established to " + DBName);
    	//}
        return ds;  
    }
    
    public DataSource getDataSource() {
    	return ds;
    }
}
