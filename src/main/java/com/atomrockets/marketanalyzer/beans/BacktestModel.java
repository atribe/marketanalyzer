package com.atomrockets.marketanalyzer.beans;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;



import java.util.Map;

import org.joda.time.LocalDate;

public class BacktestModel {
	private final static String tableName="backtest";
	private int id;

	//Stock or index symbol
	private String symbol;
	
	//Is this row in the DB the most current parameters or are they an archived result
	private Boolean currentParameters;
	private Timestamp entryTimestamp; //The time the model results were entered into the DB
	
	//Parameters
	private Date startDate;
	private Date endDate;
	private int dDayWindow;
	private int dDayParam;
	private double dDayPriceDrop;
	private double churnVolRange;
	private double churnPriceRange;
	private Boolean churnPriceCloseHigherOn;
	private Boolean churnAVG50On;
	private Boolean churnPriceTrend35On;
	private double churnPriceTrend35;
	private Boolean volVolatilityOn;
	private double volumeMult;
	private double volMultTop;
	private double volMultBot;
	private Boolean priceVolatilityOn;
	private double priceMult;
	private double priceMultTop;
	private double priceMultBot;
	private int rDaysMin;
	private int rDaysMax;
	private Boolean pivotTrend35On;
	private double pivotTrend35;
	private Boolean rallyVolAVG50On;
	private Boolean rallyPriceHighOn;
	
	//empty constructor to qualify as a javabean
	public BacktestModel() { }
	//constructor that sets the symbol
	public BacktestModel(String symbol) {
		setSymbol(symbol);
	}
	
	//Static method to assist in creating the table
	private LinkedHashMap<String, String> getColumnNames() {
		LinkedHashMap<String, String> mySQLColumnList = new LinkedHashMap<String, String>();
		for( Field f : getClass().getDeclaredFields()) {
			String name = f.getName();
			Class<?> type = f.getType();
			String typeName=null;
			if(name != "tableName") {
				if(type.equals(Boolean.class)) {
					typeName = "TINYINT(1)";
				} else if (type.equals(double.class)){
					typeName = "DOUBLE";
				} else if (type.equals(int.class)){
					typeName = "INT";
				} else if (type.equals(long.class)){
					typeName = "BIGINT(50)";
				} else if (type.equals(String.class)){
					typeName = "VARCHAR(10)";
				} else if (type.equals(java.sql.Date.class)){
					typeName = "DATE";
				} else if (type.equals(java.sql.Timestamp.class)){
					typeName = "TIMESTAMP";
				}
				mySQLColumnList.put(f.getName(), typeName);
			}
		}
		return mySQLColumnList;
	}
	
	public String tableCreationString(){
		LinkedHashMap<String, String> fieldMap = getColumnNames();
		String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (" +
				" id INT not NULL AUTO_INCREMENT,";
		
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if(entry.getKey() != "id") {	
				createTableSQL += " " + entry.getKey() + " "+ entry.getValue() +",";
			}
		 }
				
		createTableSQL += " PRIMARY KEY (id))";
		
		return createTableSQL;
	}
	
	public String[] getColumnNameList() {
		LinkedHashMap<String, String> fieldMap = getColumnNames();
		String [] columnNames = new String[fieldMap.size()];
		int counter = 0;
		
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			columnNames[counter] = entry.getKey();
			counter++;
		 }
		
		return columnNames;
	}
	
	@SuppressWarnings("unused") //entry is not used at some point (on purpose), and the warning was annoying
	public String getInsertOrUpdateQuery() {
		LinkedHashMap<String, String> fieldMap = getColumnNames();
		
		//******Start add string builder******
		String insertQuery = "INSERT INTO `" + getTableName() + "` "
				+ "(";
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			insertQuery += entry.getKey() + ",";
		 }
		
		/*This line is to clean off the last "," added by the previous loop.
		So my code isn't perfect, but it is still pretty cool.*/
		insertQuery = insertQuery.substring(0, insertQuery.length()-1);
		
		insertQuery += ") VALUES"
				+ "(";
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			insertQuery += "?,";
		 }
		
		//Another last comma removal
		insertQuery = insertQuery.substring(0, insertQuery.length()-1);
		
		insertQuery += ") ON DUPLICATE KEY UPDATE ";
		
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			insertQuery += entry.getKey() + "=VALUES(" + entry.getKey() + "), ";
		}
		
		//Another last comma removal
		insertQuery = insertQuery.substring(0, insertQuery.length()-2);
		//******End add string builder******
		
		return insertQuery;
	}
	public String getParameterQuery() {
		String query = "SELECT *"
				+ " FROM `" + getTableName() + "`"
				+ " WHERE symbol = ?"
				+ " AND currentParameters = 1";
		
		return query;
	}
	
	/*
	 * Getters and Setters
	 */
	public static String getTableName() {
		return tableName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Boolean getCurrentParameters() {
		return currentParameters;
	}
	public void setCurrentParameters(Boolean currentParameters) {
		this.currentParameters = currentParameters;
	}

	public Timestamp getEntryTimestamp() {
		return entryTimestamp;
	}
	public void setEntryTimestamp(Timestamp entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getStartDate() {
		return startDate;
	}
	public LocalDate getLocalDateStartDate() {
		return new LocalDate(startDate);
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getdDayWindow() {
		return dDayWindow;
	}
	public void setdDayWindow(int dDayWindow) {
		this.dDayWindow = dDayWindow;
	}

	public int getdDayParam() {
		return dDayParam;
	}
	public void setdDayParam(int dDayParam) {
		this.dDayParam = dDayParam;
	}

	public double getdDayPriceDrop() {
		return dDayPriceDrop;
	}
	public void setdDayPriceDrop(double dDayPriceDrop) {
		this.dDayPriceDrop = dDayPriceDrop;
	}
	public double getChurnVolRange() {
		return churnVolRange;
	}

	public void setChurnVolRange(double churnVolRange) {
		this.churnVolRange = (double) churnVolRange;
	}

	public double getChurnPriceRange() {
		return churnPriceRange;
	}

	public void setChurnPriceRange(double churnPriceRange) {
		this.churnPriceRange = (double) churnPriceRange;
	}

	public Boolean getChurnPriceCloseHigherOn() {
		return churnPriceCloseHigherOn;
	}
	public void setChurnPriceCloseHigherOn(Boolean churnPriceCloseHigherOn) {
		this.churnPriceCloseHigherOn = churnPriceCloseHigherOn;
	}

	public Boolean getChurnAVG50On() {
		return churnAVG50On;
	}
	public void setChurnAVG50On(Boolean churnAVG50On) {
		this.churnAVG50On = churnAVG50On;
	}

	public Boolean getChurnPriceTrend35On() {
		return churnPriceTrend35On;
	}
	public void setChurnPriceTrend35On(Boolean churnPriceTrend35On) {
		this.churnPriceTrend35On = churnPriceTrend35On;
	}

	public double getChurnPriceTrend35() {
		return churnPriceTrend35;
	}
	public void setChurnPriceTrend35(double churnPriceTrend35) {
		this.churnPriceTrend35 = (double)churnPriceTrend35;
	}
	
	public Boolean getVolVolatilityOn() {
		return volVolatilityOn;
	}
	public void setVolVolatilityOn(Boolean volVolatilityOn) {
		this.volVolatilityOn = volVolatilityOn;
	}

	public double getVolumeMult() {
		return volumeMult;
	}
	public void setVolumeMult(double volumeMult) {
		this.volumeMult = (double)volumeMult;
	}
	
	public double getVolMultTop() {
		return volMultTop;
	}
	public void setVolMultTop(double volMultTop) {
		this.volMultTop = (double)volMultTop;
	}

	public double getVolMultBot() {
		return volMultBot;
	}
	public void setVolMultBot(double volMultBot) {
		this.volMultBot = (double)volMultBot;
	}

	public Boolean getPriceVolatilityOn() {
		return priceVolatilityOn;
	}
	public void setPriceVolatilityOn(Boolean priceVolatilityOn) {
		this.priceVolatilityOn = priceVolatilityOn;
	}

	public double getPriceMult() {
		return priceMult;
	}
	public void setPriceMult(double priceMult) {
		this.priceMult = (double)priceMult;
	}
	
	public double getPriceMultTop() {
		return priceMultTop;
	}
	public void setPriceMultTop(double priceMultTop) {
		this.priceMultTop = (double)priceMultTop;
	}
	
	public double getPriceMultBot() {
		return priceMultBot;
	}
	public void setPriceMultBot(double priceMultBot) {
		this.priceMultBot = (double)priceMultBot;
	}
	
	public int getrDaysMin() {
		return rDaysMin;
	}
	public void setrDaysMin(int rDaysMin) {
		this.rDaysMin = rDaysMin;
	}

	public int getrDaysMax() {
		return rDaysMax;
	}
	public void setrDaysMax(int rDaysMax) {
		this.rDaysMax = rDaysMax;
	}

	public Boolean getPivotTrend35On() {
		return pivotTrend35On;
	}
	public void setPivotTrend35On(Boolean pivotTrend35On) {
		this.pivotTrend35On = pivotTrend35On;
	}
	
	public double getPivotTrend35() {
		return pivotTrend35;
	}
	public void setPivotTrend35(double pivotTrend35) {
		this.pivotTrend35 = (double)pivotTrend35;
	}
	
	public Boolean getRallyVolAVG50On() {
		return rallyVolAVG50On;
	}
	public void setRallyVolAVG50On(Boolean rallyVolAVG50On) {
		this.rallyVolAVG50On = rallyVolAVG50On;
	}

	public Boolean getRallyPriceHighOn() {
		return rallyPriceHighOn;
	}
	public void setRallyPriceHighOn(Boolean rallyPriceHighOn) {
		this.rallyPriceHighOn = rallyPriceHighOn;
	}
}
