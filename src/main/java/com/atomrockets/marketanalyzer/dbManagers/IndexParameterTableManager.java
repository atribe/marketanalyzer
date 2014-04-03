package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.models.BacktestModel;

/**
 * This subclass handles all database operations involving the price and volume data for each index
 * @author Allan
 *
 */
public class IndexParameterTableManager extends GenericDBSuperclass{
	
	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */

	
	//Constructor
	public IndexParameterTableManager(Connection connection) {
		log.debug("------------------------------Index Parameter Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = connection;
	}
	
	/**
	 * @param indexList
	 */
	public void tableInitialization(String[] indexList) {
		log.info("Starting Market Index Parameters Database Initialization");
		
		BacktestModel b = new BacktestModel();
		
		String tableName = b.getTableName();
	
		/*
		 * Checking to see if a table with the indexParams name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.info("     -Checking if table " + tableName+ " exists.");
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = b.tableCreationString();
			createTable(createTableSQL, tableName);
		}

		/*
		 * Checking to see if the tables are empty
		 * If they are populate them from Yahoo
		 * If not, check if they are up to date
		 * 		If not, update them
		 */
		log.info("     -Checking if table " + tableName + " is empty.");
		if(tableEmpty(tableName)){
			//if table is empty
			//populate it
			populateFreshParamDB(indexList);
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
	private void populateFreshParamDB(String[] indexList){
		
		
		//Creating a HashMap to store the parameters so they can be put in the DB
		//HashMap<String, String> parametersMap = new HashMap<String, String>();
		BacktestModel b = new BacktestModel();
		
		PreparedStatement ps = null;
		String[] columnNames = getColumnNames();
		QueryRunner runner = new QueryRunner();
		
		for(String index:indexList)
		{
			switch(index){
			case "^IXIC":
				/*
				 * Parameters with comments have been used. The rest are not yet used.
				 */
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf("2013-05-01"));
				b.setEndDate(java.sql.Date.valueOf("2013-12-31"));
				b.setdDayWindow(20);
				b.setdDayParam(9);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(true);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.009);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.11);
				b.setVolMultTop(1.09);
				b.setVolMultBot(1.01);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.007);
				b.setPriceMultTop(1.009);
				b.setPriceMultBot(1.007);
				b.setrDaysMin(3);
				b.setrDaysMax(8);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.004);
				b.setRallyVolAVG50On(true);
				b.setRallyPriceHighOn(true);
				/*  OLD Parameter Method
				parametersMap.put("fileName", "ResultsNasdaq.txt");
				parametersMap.put("startDate", "2013-05-01");//Analysis Start Date
				parametersMap.put("endDate", "2013-12-31");//Analysis End Date
				parametersMap.put("dDayWindow", "20");//Track all the d days in the past 20 days (4 weeks)
				parametersMap.put("dDayParam", "9");//10 d days in a window = sell
				parametersMap.put("churnVolRange", "0.03");
				parametersMap.put("churnPriceRange", "0.02");
				parametersMap.put("churnPriceCloseHigherOn", "true");
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
				*/
				break;
			case "^GSPC":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf("2013-05-01"));
				b.setEndDate(java.sql.Date.valueOf("2013-12-31"));
				b.setdDayWindow(20);
				b.setdDayParam(9);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(true);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.009);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.11);
				b.setVolMultTop(1.09);
				b.setVolMultBot(1.01);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.007);
				b.setPriceMultTop(1.009);
				b.setPriceMultBot(1.007);
				b.setrDaysMin(3);
				b.setrDaysMax(8);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.004);
				b.setRallyVolAVG50On(true);
				b.setRallyPriceHighOn(true);
				/*  OLD Parameter Method
				parametersMap.put("fileName", "ResultsSP500.txt");
				parametersMap.put("startDate", "2013-05-01");//Analysis Start Date
				parametersMap.put("endDate", "2013-12-31");//Analysis End Date
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
				*/
				break;
			case "^SML":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf("2013-05-01"));
				b.setEndDate(java.sql.Date.valueOf("2013-12-31"));
				b.setdDayWindow(20);
				b.setdDayParam(9);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(true);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.009);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.11);
				b.setVolMultTop(1.09);
				b.setVolMultBot(1.01);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.007);
				b.setPriceMultTop(1.009);
				b.setPriceMultBot(1.007);
				b.setrDaysMin(3);
				b.setrDaysMax(8);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.004);
				b.setRallyVolAVG50On(true);
				b.setRallyPriceHighOn(true);
				//set the same as S&P because I don't have anything saying differently
				/*  OLD Parameter Method
				parametersMap.put("fileName", "ResultsSP500.txt");
				parametersMap.put("startDate", "2013-05-01");//Analysis Start Date
				parametersMap.put("endDate", "2013-12-31");//Analysis End Date
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
				*/
				break;
			case "^MID":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf("2013-05-01"));
				b.setEndDate(java.sql.Date.valueOf("2013-12-31"));
				b.setdDayWindow(20);
				b.setdDayParam(9);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(true);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.009);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.11);
				b.setVolMultTop(1.09);
				b.setVolMultBot(1.01);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.007);
				b.setPriceMultTop(1.009);
				b.setPriceMultBot(1.007);
				b.setrDaysMin(3);
				b.setrDaysMax(8);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.004);
				b.setRallyVolAVG50On(true);
				b.setRallyPriceHighOn(true);
				//set the same as S&P because I don't have anything saying differently
				/*  OLD Parameter Method
				parametersMap.put("fileName", "ResultsSP500.txt");
				parametersMap.put("startDate", "2013-05-01");//Analysis Start Date
				parametersMap.put("endDate", "2013-12-31");//Analysis End Date
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
				*/
				break;
			case "^DJI":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf("2013-05-01"));
				b.setEndDate(java.sql.Date.valueOf("2013-12-31"));
				b.setdDayWindow(20);
				b.setdDayParam(9);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(true);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.009);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.11);
				b.setVolMultTop(1.09);
				b.setVolMultBot(1.01);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.007);
				b.setPriceMultTop(1.009);
				b.setPriceMultBot(1.007);
				b.setrDaysMin(3);
				b.setrDaysMax(8);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.004);
				b.setRallyVolAVG50On(true);
				b.setRallyPriceHighOn(true);
				/*  OLD Parameter Method
				parametersMap.put("fileName", "ResultsDow.txt");
				parametersMap.put("startDate", "2013-05-01");//Analysis Start Date
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
				*/
				break;
			}
	
			//Get a set of the entries
			//Set<String> keys = parametersMap.keySet();
	
			//Get an iterator
			//Iterator<String> itr = keys.iterator();
	
			log.info("Populating variable database for " + g_parameterTableName);
	
			//Add each entry to the DB
			/*
			while(itr.hasNext()) {
				String key = (String)itr.next();
				String value = parametersMap.get(key);
				addVarPairRecord(index, key, value);
			}
			
			parametersMap.clear();*/
			
			/*New insert method*/
			//creating DbUtils QuerryRunner
			
			
			runner.fillStatementWithBean(ps, b, columnNames);
			b = new BacktestModel();
		}

	}

	/**
	 * @param connection
	 * @param indexParams
	 * @param key
	 * @param value
	 */
	private void addVarPairRecord(String index, String key, String value) {
		String insertQuery = "INSERT INTO `" + g_parameterTableName + "` "
				+ "(symbol, var_name, var_value)"
				+ "VALUES"
				+ "(?,?,?)"
				+ " ON DUPLICATE KEY UPDATE var_value=?";
		PreparedStatement ps=null;
		try {
			ps = m_connection.prepareStatement(insertQuery);
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

	public String getStringValue(String key){

		String value;
		String query = "SELECT var_value FROM `" + g_parameterTableName + "`"
				+ " WHERE var_name=?";

		try {
			PreparedStatement selectStatement = m_connection.prepareStatement(query);
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
	
	public boolean getBooleanValue(String key){
		String stringValue = getStringValue(key);
		boolean boolValue = Boolean.parseBoolean(stringValue);
		return boolValue;
	}

	public LocalDate getDateValue(String key){
		String stringValue = getStringValue(key);
		LocalDate dateValue = new LocalDate(stringValue);
		return dateValue;
	}

	public int getIntValue(String key) {
		String stringValue = getStringValue(key);
		int intValue = Integer.parseInt(stringValue);
		return intValue;
	}
	
	public float getFloatValue(String key) {
		String stringValue = getStringValue(key);
		float floatValue = Float.parseFloat(stringValue);
		return floatValue;
	}
}
