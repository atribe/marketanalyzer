package com.ar.marketanalyzer.indexbacktest.beans;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class IndexCalcs {
	protected final static String tableName="IndexCalcs";
	
	public static enum dayActionType {
		SELL(-1), 
		HOLD(0), 
		BUY(1);
		
		private final int value;
		dayActionType(int value) { this.value = value; }
		public int getValue() {return value; }
	};
	
	
	protected long id;
	protected long OHLCid;
	
	//Statistic variables
	protected BigDecimal closeAvg50;
	protected BigDecimal closeAvg100;
	protected BigDecimal closeAvg200;
	protected long volumeAvg50;
	protected double priceTrend35;
	
	//Analysis variables
	protected Boolean distributionDay;
	protected Boolean churnDay;
	protected int distributionDayCounter;
	protected Boolean pivotDay;
	protected Boolean followThruDay;
	
	//set by the enum dayActionType. sell=-1, hold=0, buy=1
	protected int dayAction;
	
	//Empty constructor
	public IndexCalcs() {}
	
	//Static method to assist in creating the table
	protected LinkedHashMap<String, String> getColumnNames() {
		LinkedHashMap<String, String> mySQLColumnList = new LinkedHashMap<String, String>();
		for( Field f : getClass().getDeclaredFields()) {
			String name = f.getName();
			Class<?> type = f.getType();
			String typeName=null;
			//Don't include the stuff from the yahoo OHLC table in this table
			if( name != "tableName" && name != "dayActionType") {
				if(type.equals(Boolean.class)) {
					typeName = "TINYINT(1)";
				} else if (type.equals(double.class)){
					typeName = "DOUBLE";
				} else if (type.equals(int.class)){
					typeName = "INT";
				} else if (type.equals(long.class)){
					typeName = "BIGINT";
				} else if (type.equals(String.class)){
					typeName = "VARCHAR(10)";
				} else if (type.equals(java.sql.Date.class)){
					typeName = "DATE";
				} else if (type.equals(java.math.BigDecimal.class)){
					typeName = "DECIMAL";
				} else if (type.equals(java.sql.Timestamp.class)){
					typeName = "TIMESTAMP";
				}
				
				//Special cases
				if( name == "dayAction" ) {
					typeName = "TINYINT(1) DEFAULT '0'";
				}
				mySQLColumnList.put(f.getName(), typeName);
			}
		}
		return mySQLColumnList;
	}
	
	public String tableCreationString(){
		LinkedHashMap<String, String> fieldMap = getColumnNames();
		String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (" +
				" id INT NOT NULL AUTO_INCREMENT," +
				" OHLCid INT NOT NULL,";
		
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if(entry.getKey() != "id" && entry.getKey() != "OHLCid") {	
				createTableSQL += " " + entry.getKey() + " "+ entry.getValue() +",";
			}
		 }
				
		createTableSQL += " PRIMARY KEY (id)," +
		"FOREIGN KEY (OHLCid) REFERENCES `" + IndexOHLCVData.getTablename() + "`(id)) " +
				"ENGINE = MyISAM";
		
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
		String insertQuery = "INSERT INTO `" + tableName + "` "
				+ "(";
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			insertQuery += entry.getKey() + ",";
		 }
		
		//This line is to clean off the last "," added by the previous loop.
		//So my code isn't perfect, but it is still pretty cool.
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

	/*
	 * Helper Methods
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*
	 * Getters and Setters
	 */
	public static String getTableName() {
		return tableName;
	}

	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getOHLCid() {
		return OHLCid;
	}
	public void setOHLCid(int oHLCid) {
		OHLCid = oHLCid;
	}
	public void setOHLCid(long oHLCid) {
		OHLCid = oHLCid;
	}

	public BigDecimal getCloseAvg50() {
		return closeAvg50;
	}
	public void setCloseAvg50(BigDecimal closeAvg50) {
		this.closeAvg50 = closeAvg50;
	}

	public BigDecimal getCloseAvg100() {
		return closeAvg100;
	}
	public void setCloseAvg100(BigDecimal closeAvg100) {
		this.closeAvg100 = closeAvg100;
	}

	public BigDecimal getCloseAvg200() {
		return closeAvg200;
	}
	public void setCloseAvg200(BigDecimal closeAvg200) {
		this.closeAvg200 = closeAvg200;
	}

	public long getVolumeAvg50() {
		return volumeAvg50;
	}
	public void setVolumeAvg50(long volumeAvg50) {
		this.volumeAvg50 = volumeAvg50;
	}

	public double getPriceTrend35() {
		return priceTrend35;
	}
	public void setPriceTrend35(double priceTrend35) {
		this.priceTrend35 = priceTrend35;
	}
	
	public Boolean getDistributionDay() {
		return distributionDay;
	}
	public void setDistributionDay(Boolean distributionDay) {
		this.distributionDay = distributionDay;
	}

	public int getDistributionDayCounter() {
		return distributionDayCounter;
	}
	public void setDistributionDayCounter(int distributionDayCounter) {
		this.distributionDayCounter = distributionDayCounter;
	}

	public int getDayAction() {
		return dayAction;
	}
	public void setDayAction(int dayAction) {
		this.dayAction = dayAction;
	}
	public void setDayAction(dayActionType dayAction) {
		this.dayAction = dayAction.getValue();
	}

	public Boolean getChurnDay() {
		return churnDay;
	}
	public void setChurnDay(Boolean churnDay) {
		this.churnDay = churnDay;
	}

	public Boolean getPivotDay() {
		return pivotDay;
	}

	public void setPivotDay(Boolean pivotDay) {
		this.pivotDay = pivotDay;
	}

	public Boolean getFollowThruDay() {
		return followThruDay;
	}
	public void setFollowThruDay(Boolean followThruDay) {
		this.followThruDay = followThruDay;
	}
	public void setFollowThruDaySafe(Boolean followThruDay) {
		//if the followThruDay is null, then it can be set to something
		//if the followThruDay is false, then it can be set to something
		if(this.followThruDay == null) {
			this.followThruDay = followThruDay;
		} else if(this.followThruDay.equals(false)) {
			this.followThruDay = followThruDay;
		}
	}
}
