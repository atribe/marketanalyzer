package com.atomrockets.marketanalyzer.models;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;



import java.util.Map;

import org.joda.time.LocalDate;

public class BacktestModel {
	private final String tableName="backtest";
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
	private float churnVolRange;
	private float churnPriceRange;
	private Boolean churnPriceCloseHigherOn;
	private Boolean churnAVG50On;
	private Boolean churnPriceTrend35On;
	private float churnPriceTrend35;
	private Boolean volVolatilityOn;
	private float volumeMult;
	private float volMultTop;
	private float volMultBot;
	private Boolean priceVolatilityOn;
	private float priceMult;
	private float priceMultTop;
	private float priceMultBot;
	private int rDaysMin;
	private int rDaysMax;
	private Boolean pivotTrend35On;
	private float pivotTrend35;
	private Boolean rallyVolAVG50On;
	private Boolean rallyPriceHighOn;
	
	
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
				} else if (type.equals(float.class)){
					typeName = "FLOAT(20)";
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

	/*
	 * Getters and Setters
	 */
	public String getTableName() {
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

	public float getChurnVolRange() {
		return churnVolRange;
	}

	public void setChurnVolRange(float churnVolRange) {
		this.churnVolRange = churnVolRange;
	}
	public void setChurnVolRange(double churnVolRange) {
		this.churnVolRange = (float) churnVolRange;
	}

	public float getChurnPriceRange() {
		return churnPriceRange;
	}

	public void setChurnPriceRange(float churnPriceRange) {
		this.churnPriceRange = churnPriceRange;
	}
	public void setChurnPriceRange(double churnPriceRange) {
		this.churnPriceRange = (float) churnPriceRange;
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

	public float getChurnPriceTrend35() {
		return churnPriceTrend35;
	}

	public void setChurnPriceTrend35(float churnPriceTrend35) {
		this.churnPriceTrend35 = churnPriceTrend35;
	}
	public void setChurnPriceTrend35(double churnPriceTrend35) {
		this.churnPriceTrend35 = (float)churnPriceTrend35;
	}
	public Boolean getVolVolatilityOn() {
		return volVolatilityOn;
	}

	public void setVolVolatilityOn(Boolean volVolatilityOn) {
		this.volVolatilityOn = volVolatilityOn;
	}

	public float getVolumeMult() {
		return volumeMult;
	}

	public void setVolumeMult(float volumeMult) {
		this.volumeMult = volumeMult;
	}
	public void setVolumeMult(double volumeMult) {
		this.volumeMult = (float)volumeMult;
	}
	public float getVolMultTop() {
		return volMultTop;
	}

	public void setVolMultTop(float volMultTop) {
		this.volMultTop = volMultTop;
	}
	public void setVolMultTop(double volMultTop) {
		this.volMultTop = (float)volMultTop;
	}

	public float getVolMultBot() {
		return volMultBot;
	}

	public void setVolMultBot(float volMultBot) {
		this.volMultBot = volMultBot;
	}
	public void setVolMultBot(double volMultBot) {
		this.volMultBot = (float)volMultBot;
	}

	public Boolean getPriceVolatilityOn() {
		return priceVolatilityOn;
	}

	public void setPriceVolatilityOn(Boolean priceVolatilityOn) {
		this.priceVolatilityOn = priceVolatilityOn;
	}

	public float getPriceMult() {
		return priceMult;
	}

	public void setPriceMult(float priceMult) {
		this.priceMult = priceMult;
	}
	public void setPriceMult(double priceMult) {
		this.priceMult = (float)priceMult;
	}
	public float getPriceMultTop() {
		return priceMultTop;
	}

	public void setPriceMultTop(float priceMultTop) {
		this.priceMultTop = priceMultTop;
	}
	public void setPriceMultTop(double priceMultTop) {
		this.priceMultTop = (float)priceMultTop;
	}
	public float getPriceMultBot() {
		return priceMultBot;
	}

	public void setPriceMultBot(float priceMultBot) {
		this.priceMultBot = priceMultBot;
	}
	public void setPriceMultBot(double priceMultBot) {
		this.priceMultBot = (float)priceMultBot;
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

	public float getPivotTrend35() {
		return pivotTrend35;
	}

	public void setPivotTrend35(float pivotTrend35) {
		this.pivotTrend35 = pivotTrend35;
	}
	public void setPivotTrend35(double pivotTrend35) {
		this.pivotTrend35 = (float)pivotTrend35;
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
