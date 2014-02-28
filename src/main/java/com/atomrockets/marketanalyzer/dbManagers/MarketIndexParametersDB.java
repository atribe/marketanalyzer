package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.joda.time.LocalDate;

/**
 * This subclass handles all database operations involving the price and volume data for each index
 * @author Allan
 *
 */
public class MarketIndexParametersDB extends GenericDBSuperclass{

	/**
	 * @param indexList
	 */
	public static void indexModelParametersInitialization(Connection connection, String[] indexParametersDBNameList) {
		System.out.println("");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Starting Market Index Parameters Database Initialization");//Get a database connection

		//Iteration tracking variable for System.out.printing and debugging
		int interationCounter = 0;

		//Loop for each each index to create a database to hold model parameters
		for(String indexParams:indexParametersDBNameList) {
			interationCounter++;
			System.out.println("Loop Iteration " + interationCounter + ":");
			/*
			 * Checking to see if a table with the indexParams name exists
			 * If it does, print to the command prompt
			 * if not create the table
			 */
			System.out.println("     -Checking if table " + indexParams + " exists.");
			if(!tableExists(indexParams, connection)) {
				// Table does not exist, so create it
				String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + indexParams + "` " +
						"(Var_Name VARCHAR(100) PRIMARY KEY," +
						" Var_Value VARCHAR(50))";
				createTable(createTableSQL, connection, indexParams);
			}

			/*
			 * Checking to see if the tables are empty
			 * If they are populate them from Yahoo
			 * If not, check if they are up to date
			 * 		If not, update them
			 */
			System.out.println("     -Checking if table " + indexParams + " is empty.");
			if(tableEmpty(indexParams, connection)){
				//if table is empty
				//populate it
				populateFreshParamDB(connection, indexParams);
			}
		}
		System.out.println("--------------------------------------------------------------------");
	}

	/**
	 * Primary method to populate a price volume database after it is newly created
	 * @param connection
	 * @param indexParams
	 * 
	 */
	private static void populateFreshParamDB(Connection connection, String indexParams){
		HashMap<String, String> SP500Vars = new HashMap<String, String>();
		
		/*
		 * Parameters with comments have been used. The rest are not yet used.
		 */
		SP500Vars.put("fileName", "ResultsSP500.txt");
		SP500Vars.put("startDate", "1980-01-01");//Analysis Start Date
		SP500Vars.put("endDate", "2009-12-31");//Analysis End Date
		SP500Vars.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
		SP500Vars.put("dDayParam", "10");//10 d days in a window = sell
		SP500Vars.put("churnVolRange", "0.03");
		SP500Vars.put("churnPriceRange", "0.02");
		SP500Vars.put("chrunPriceCloseHigherOn", "true");
		SP500Vars.put("churnAVG50On", "true");
		SP500Vars.put("churnPriceTrend350n", "false");
		SP500Vars.put("churnPriceTrend35", "0.007");
		SP500Vars.put("volVolatilityOn", "false");
		SP500Vars.put("volumeMult", "1.1");
		SP500Vars.put("volMultTop", "1.1");
		SP500Vars.put("volMultBot", "1.1");
		SP500Vars.put("priceVolatilityOn", "true");
		SP500Vars.put("priceMult", "1.013");
		SP500Vars.put("priceMultTop", "1.014");
		SP500Vars.put("priceMultBot", "1.012");
		SP500Vars.put("rDaysMin", "4");
		SP500Vars.put("rDaysMax", "18");
		SP500Vars.put("pivotTrend35On", "false");
		SP500Vars.put("pivotTrend35", "-0.003");
		SP500Vars.put("rallyVolAVG50On", "false");
		SP500Vars.put("rallyPriceHighOn", "true");

		//Get a set of the entries
		Set keys = SP500Vars.keySet();

		//Get an iterator
		Iterator itr = keys.iterator();

		System.out.println("Populating variable database for " + indexParams);

		//Add each entry to the DB
		while(itr.hasNext()) {
			String key = (String)itr.next();
			String value = SP500Vars.get(key);
			addVarPairRecord(connection, indexParams, key, value);
		}


	}

	/**
	 * @param connection
	 * @param indexParams
	 * @param key
	 * @param value
	 */
	private static void addVarPairRecord(Connection connection, String indexParams, String key, String value) {
		String insertQuery = "INSERT INTO `" + indexParams + "` "
				+ "(Var_Name, Var_Value)"
				+ "VALUES"
				+ "(?,?)"
				+ " ON DUPLICATE KEY UPDATE Var_Value=?";
		PreparedStatement ps=null;
		try {
			ps = connection.prepareStatement(insertQuery);
			ps.setString(1, key);
			ps.setString(2, value);
			ps.setString(3, value);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqlEx) { } // ignore

				ps = null;
			}
		}
	}

	public static String getStringValue(Connection connection, String tableName, String key){

		String value;
		String query = "SELECT Var_Value FROM `" + tableName + "`"
				+ " WHERE Var_Name=?";

		try {
			PreparedStatement selectStatement = connection.prepareStatement(query);
			selectStatement.setString(1, key);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()) {
				value = rs.getString("Var_Value");
			} else {
				value = "Error, value not found from the given key. Or something else went really wrong.";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			value = e.toString();
			e.printStackTrace();
		}
		return value;
	}
	
	public static boolean getBooleanValue(Connection connection, String tableName, String key){
		String stringValue = getStringValue(connection,tableName,key);
		boolean boolValue = Boolean.parseBoolean(stringValue);
		return boolValue;
	}

	public static LocalDate getDateValue(Connection connection, String tableName, String key){
		String stringValue = getStringValue(connection,tableName,key);
		LocalDate dateValue = new LocalDate(stringValue);
		return dateValue;
	}

	public static int getIntValue(Connection connection, String tableName, String key) {
		String stringValue = getStringValue(connection,tableName,key);
		int intValue = Integer.parseInt(stringValue);
		return intValue;
	}
	
	public static float getFloatValue(Connection connection, String tableName, String key) {
		String stringValue = getStringValue(connection,tableName,key);
		float floatValue = Float.parseFloat(stringValue);
		return floatValue;
	}
}
