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

	private static final String m_parameterTableName = "indexparameters";
	/**
	 * @param indexList
	 */
	public static void indexModelParametersInitialization(Connection connection, String[] indexList) {
		log.info("");
		log.info("--------------------------------------------------------------------");
		log.info("Starting Market Index Parameters Database Initialization");

			/*
			 * Checking to see if a table with the indexParams name exists
			 * If it does, print to the command prompt
			 * if not create the table
			 */
			log.info("     -Checking if table " + m_parameterTableName + " exists.");
			if(!tableExists(m_parameterTableName, connection)) {
				// Table does not exist, so create it
				String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + m_parameterTableName + "` (" +
						" id INT not NULL AUTO_INCREMENT," +
						" symbol VARCHAR(10)," +
						" var_name VARCHAR(100)," +
						" var_value VARCHAR(50)," +
						" PRIMARY KEY (id))";
				createTable(createTableSQL, connection, m_parameterTableName);
			}

			/*
			 * Checking to see if the tables are empty
			 * If they are populate them from Yahoo
			 * If not, check if they are up to date
			 * 		If not, update them
			 */
			log.info("     -Checking if table " + m_parameterTableName + " is empty.");
			if(tableEmpty(m_parameterTableName, connection)){
				//if table is empty
				//populate it
				populateFreshParamDB(connection, indexList);
			}
		log.info("-------------------------End of Index Parameters Table Setup------------------------------");
	}

	/**
	 * Primary method to populate a price volume database after it is newly created
	 * @param connection
	 * @param indexList 
	 * @param indexParams
	 * 
	 */
	private static void populateFreshParamDB(Connection connection, String[] indexList){
		
		//Creating a HashMap to store the parameters so they can be put in the DB
		HashMap<String, String> parametersMap = new HashMap<String, String>();
		
		for(String index:indexList)
		{
			switch(index){
			case "^IXIC":
				/*
				 * Parameters with comments have been used. The rest are not yet used.
				 */
				parametersMap.put("fileName", "ResultsNasdaq.txt");
				parametersMap.put("startDate", "1980-01-01");//Analysis Start Date
				parametersMap.put("endDate", "2009-12-31");//Analysis End Date
				parametersMap.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
				parametersMap.put("dDayParam", "9");//10 d days in a window = sell
				parametersMap.put("churnVolRange", "0.03");
				parametersMap.put("churnPriceRange", "0.02");
				parametersMap.put("chrunPriceCloseHigherOn", "true");
				parametersMap.put("churnAVG50On", "false");
				parametersMap.put("churnPriceTrend350n", "false");
				parametersMap.put("churnPriceTrend35", "0.009");
				parametersMap.put("volVolatilityOn", "false");
				parametersMap.put("volumeMult", "1.11");
				parametersMap.put("volMultTop", "1.09");
				parametersMap.put("volMultBot", "1.01");
				parametersMap.put("priceVolatilityOn", "false");
				parametersMap.put("priceMult", "1.007");
				parametersMap.put("priceMultTop", "1.009");
				parametersMap.put("priceMultBot", "1.007");
				parametersMap.put("rDaysMin", "3");
				parametersMap.put("rDaysMax", "8");
				parametersMap.put("pivotTrend35On", "false");
				parametersMap.put("pivotTrend35", "-0.004");
				parametersMap.put("rallyVolAVG50On", "true");
				parametersMap.put("rallyPriceHighOn", "true");
				break;
			case "^GSPC":
				parametersMap.put("fileName", "ResultsSP500.txt");
				parametersMap.put("startDate", "1980-01-01");//Analysis Start Date
				parametersMap.put("endDate", "2009-12-31");//Analysis End Date
				parametersMap.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
				parametersMap.put("dDayParam", "10");//10 d days in a window = sell
				parametersMap.put("churnVolRange", "0.03");
				parametersMap.put("churnPriceRange", "0.02");
				parametersMap.put("chrunPriceCloseHigherOn", "true");
				parametersMap.put("churnAVG50On", "true");
				parametersMap.put("churnPriceTrend350n", "false");
				parametersMap.put("churnPriceTrend35", "0.007");
				parametersMap.put("volVolatilityOn", "false");
				parametersMap.put("volumeMult", "1.1");
				parametersMap.put("volMultTop", "1.1");
				parametersMap.put("volMultBot", "1.1");
				parametersMap.put("priceVolatilityOn", "true");
				parametersMap.put("priceMult", "1.013");
				parametersMap.put("priceMultTop", "1.014");
				parametersMap.put("priceMultBot", "1.012");
				parametersMap.put("rDaysMin", "4");
				parametersMap.put("rDaysMax", "18");
				parametersMap.put("pivotTrend35On", "false");
				parametersMap.put("pivotTrend35", "-0.003");
				parametersMap.put("rallyVolAVG50On", "false");
				parametersMap.put("rallyPriceHighOn", "true");
				break;
			case "^SML":
				//set the same as S&P because I don't have anything saying differently
				parametersMap.put("fileName", "ResultsSP500.txt");
				parametersMap.put("startDate", "1980-01-01");//Analysis Start Date
				parametersMap.put("endDate", "2009-12-31");//Analysis End Date
				parametersMap.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
				parametersMap.put("dDayParam", "10");//10 d days in a window = sell
				parametersMap.put("churnVolRange", "0.03");
				parametersMap.put("churnPriceRange", "0.02");
				parametersMap.put("chrunPriceCloseHigherOn", "true");
				parametersMap.put("churnAVG50On", "true");
				parametersMap.put("churnPriceTrend350n", "false");
				parametersMap.put("churnPriceTrend35", "0.007");
				parametersMap.put("volVolatilityOn", "false");
				parametersMap.put("volumeMult", "1.1");
				parametersMap.put("volMultTop", "1.1");
				parametersMap.put("volMultBot", "1.1");
				parametersMap.put("priceVolatilityOn", "true");
				parametersMap.put("priceMult", "1.013");
				parametersMap.put("priceMultTop", "1.014");
				parametersMap.put("priceMultBot", "1.012");
				parametersMap.put("rDaysMin", "4");
				parametersMap.put("rDaysMax", "18");
				parametersMap.put("pivotTrend35On", "false");
				parametersMap.put("pivotTrend35", "-0.003");
				parametersMap.put("rallyVolAVG50On", "false");
				parametersMap.put("rallyPriceHighOn", "true");
				break;
			case "^MID":
				//set the same as S&P because I don't have anything saying differently
				parametersMap.put("fileName", "ResultsSP500.txt");
				parametersMap.put("startDate", "1980-01-01");//Analysis Start Date
				parametersMap.put("endDate", "2009-12-31");//Analysis End Date
				parametersMap.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
				parametersMap.put("dDayParam", "10");//10 d days in a window = sell
				parametersMap.put("churnVolRange", "0.03");
				parametersMap.put("churnPriceRange", "0.02");
				parametersMap.put("chrunPriceCloseHigherOn", "true");
				parametersMap.put("churnAVG50On", "true");
				parametersMap.put("churnPriceTrend350n", "false");
				parametersMap.put("churnPriceTrend35", "0.007");
				parametersMap.put("volVolatilityOn", "false");
				parametersMap.put("volumeMult", "1.1");
				parametersMap.put("volMultTop", "1.1");
				parametersMap.put("volMultBot", "1.1");
				parametersMap.put("priceVolatilityOn", "true");
				parametersMap.put("priceMult", "1.013");
				parametersMap.put("priceMultTop", "1.014");
				parametersMap.put("priceMultBot", "1.012");
				parametersMap.put("rDaysMin", "4");
				parametersMap.put("rDaysMax", "18");
				parametersMap.put("pivotTrend35On", "false");
				parametersMap.put("pivotTrend35", "-0.003");
				parametersMap.put("rallyVolAVG50On", "false");
				parametersMap.put("rallyPriceHighOn", "true");

				break;
			case "^DJI":
				parametersMap.put("fileName", "ResultsDow.txt");
				parametersMap.put("startDate", "1980-01-01");//Analysis Start Date
				parametersMap.put("endDate", "2009-12-31");//Analysis End Date
				parametersMap.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
				parametersMap.put("dDayParam", "10");//10 d days in a window = sell
				parametersMap.put("churnVolRange", "0.05");
				parametersMap.put("churnPriceRange", "0.02");
				parametersMap.put("chrunPriceCloseHigherOn", "true");
				parametersMap.put("churnAVG50On", "true");
				parametersMap.put("churnPriceTrend350n", "false");
				parametersMap.put("churnPriceTrend35", "0.003");
				parametersMap.put("volVolatilityOn", "true");
				parametersMap.put("volumeMult", "1.3");
				parametersMap.put("volMultTop", "1.18");
				parametersMap.put("volMultBot", "1.05");
				parametersMap.put("priceVolatilityOn", "false");
				parametersMap.put("priceMult", "1.011");
				parametersMap.put("priceMultTop", "1.011");
				parametersMap.put("priceMultBot", "1.008");
				parametersMap.put("rDaysMin", "5");
				parametersMap.put("rDaysMax", "18");
				parametersMap.put("pivotTrend35On", "false");
				parametersMap.put("pivotTrend35", "-0.003");
				parametersMap.put("rallyVolAVG50On", "false");
				parametersMap.put("rallyPriceHighOn", "true");
				break;
			}
	
			//Get a set of the entries
			Set<String> keys = parametersMap.keySet();
	
			//Get an iterator
			Iterator<String> itr = keys.iterator();
	
			log.info("Populating variable database for " + m_parameterTableName);
	
			//Add each entry to the DB
			while(itr.hasNext()) {
				String key = (String)itr.next();
				String value = parametersMap.get(key);
				addVarPairRecord(connection, index, key, value);
			}
			
			parametersMap.clear();
		}

	}

	/**
	 * @param connection
	 * @param indexParams
	 * @param key
	 * @param value
	 */
	private static void addVarPairRecord(Connection connection, String index, String key, String value) {
		String insertQuery = "INSERT INTO `" + m_parameterTableName + "` "
				+ "(symbol, var_name, var_value)"
				+ "VALUES"
				+ "(?,?,?)"
				+ " ON DUPLICATE KEY UPDATE var_value=?";
		PreparedStatement ps=null;
		try {
			ps = connection.prepareStatement(insertQuery);
			ps.setString(1, index);
			ps.setString(2, key);
			ps.setString(3, value);
			ps.setString(4, value);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.info("SQLException: " + e.getMessage());
			log.info("SQLState: " + e.getSQLState());
			log.info("VendorError: " + e.getErrorCode());
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

	public static String getStringValue(Connection connection, String key){

		String value;
		String query = "SELECT var_value FROM `" + m_parameterTableName + "`"
				+ " WHERE var_name=?";

		try {
			PreparedStatement selectStatement = connection.prepareStatement(query);
			selectStatement.setString(1, key);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()) {
				value = rs.getString("var_value");
			} else {
				value = "Error, value not found from the given key. Or something else went really wrong.";
			}
		} catch (SQLException e) {
			log.info("SQLException: " + e.getMessage());
			log.info("SQLState: " + e.getSQLState());
			log.info("VendorError: " + e.getErrorCode());
			value = e.toString();
			e.printStackTrace();
		}
		return value;
	}
	
	public static boolean getBooleanValue(Connection connection, String key){
		String stringValue = getStringValue(connection,key);
		boolean boolValue = Boolean.parseBoolean(stringValue);
		return boolValue;
	}

	public static LocalDate getDateValue(Connection connection, String key){
		String stringValue = getStringValue(connection,key);
		LocalDate dateValue = new LocalDate(stringValue);
		return dateValue;
	}

	public static int getIntValue(Connection connection, String key) {
		String stringValue = getStringValue(connection,key);
		int intValue = Integer.parseInt(stringValue);
		return intValue;
	}
	
	public static float getFloatValue(Connection connection, String key) {
		String stringValue = getStringValue(connection,key);
		float floatValue = Float.parseFloat(stringValue);
		return floatValue;
	}
}
