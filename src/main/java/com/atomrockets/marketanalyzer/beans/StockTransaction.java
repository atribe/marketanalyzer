package com.atomrockets.marketanalyzer.beans;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

public class StockTransaction {
	//Table Names
	private final static String tableName = "StockTransaction";
	
	//Table Columns
	private long id;
	private long backtestId;
	private Date buyDate;
	private Date sellDate;
	private double buyPrice;
	private double sellPrice;
	private double dollarReturn;
	private double percentReturn;
	private Boolean profitable;
	
	//Empty constructor
	public StockTransaction() {
		this.buyPrice=0;
		this.sellPrice=0;
		this.dollarReturn=0;
		this.percentReturn=0;
	}
	
	public StockTransaction(long backtestID) {
		setBacktestId(backtestID);
		this.buyPrice=0;
		this.sellPrice=0;
		this.dollarReturn=0;
		this.percentReturn=0;
	}
	
	public StockTransaction(long backtestID, OHLCVData beginningDataPoint,
			OHLCVData endingDataPoint) {
		setBacktestId(backtestID);
		
		setBuyDate(beginningDataPoint.getDate());
		setBuyPrice(beginningDataPoint.getClose());
		
		setSellDate(endingDataPoint.getDate());
		setSellPrice(endingDataPoint.getClose());
		
		calcStats();
	}

	/*
	 * Database table methods
	 */
	private LinkedHashMap<String, String> getColumnNames() {
		//Creating a hashmap to store the name and type of field
		LinkedHashMap<String, String> mySQLColumnList = new LinkedHashMap<String, String>();
		//Iterating through the fields in the class to populate the hashmap
		for( Field f : getClass().getDeclaredFields()) {
			String name = f.getName(); //Getting the name of the field
			Class<?> type = f.getType(); //Getting the type of the field
			String typeName=null;
			if( name != "tableName") { //Don't add the tableName field to the hashmap, as it isn't a column in the table
				//Match the field type to the MySQL equivalent
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
				} else if (type.equals(java.sql.Timestamp.class)){
					typeName = "TIMESTAMP";
				}
				//Add that field to the hashmap
				mySQLColumnList.put(f.getName(), typeName);
			}
		}
		return mySQLColumnList;//return the hashmap
	}
	
	public String tableCreationString(){
		//Get the hashmap of class fields and types
		LinkedHashMap<String, String> fieldMap = getColumnNames();
		//Create the table create statement
		String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (" +
				" id INT NOT NULL AUTO_INCREMENT," +
				" backtestId INT NOT NULL,"; //Handle the id on its own because it has a bunch of stuff appended to it
				
		//Cycle through the hashmap and create a column for each
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if(entry.getKey() != "id" && entry.getKey() != "backtestId") {	
				createTableSQL += " " + entry.getKey() + " "+ entry.getValue() +",";
			}
		 }
		//Set stuff like primary key and foriegn key at the end
		createTableSQL += " PRIMARY KEY (id), " +
		"FOREIGN KEY (backtestId) REFERENCES `" + BacktestResult.getTableName() + "`(id)) " +
				"ENGINE = MyISAM";
		
		return createTableSQL;
	}
	
	public String[] getColumnNameList() {
		//Get the hashmap of class fields and types
		LinkedHashMap<String, String> fieldMap = getColumnNames();
		String [] columnNames = new String[fieldMap.size()];
		int counter = 0;
		
		/*
		 * Cycle through the hashmap and pull out every field name (tableName
		 *  is not in the hashmap, so it isn't in this list)
		 */
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
	//End Database table methods
	
	/*
	 * Helper Methods
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	private void calcStats() {
		this.dollarReturn = this.sellPrice-this.buyPrice;
		this.percentReturn = (this.sellPrice-this.buyPrice)/this.buyPrice;
		if(this.dollarReturn>0) {
			setProfitable(true);
		} else {
			setProfitable(false);
		}
	}
	
	public void OpenTransaction(IndexOHLCVCalcs c) {
		setBuyDate(c.getDate());
		setBuyPrice(c.getOpen());
	}
	public void CloseTransaction(IndexOHLCVCalcs c) {
		setSellDate(c.getDate());
		setSellPrice(c.getOpen());
	}
	public boolean isTransactionOpen() {
		if(this.buyDate != null &&
				this.buyPrice > 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isTransactionClosed() {
		if(this.buyDate != null &&
				this.buyPrice > 0 &&
				this.sellDate != null &&
				this.sellPrice > 0)
		{
			calcStats();
			return true;
		} else {
			return false;
		}
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
	public void setId(long id) {
		this.id = id;
	}
	public long getBacktestId() {
		return backtestId;
	}
	public void setBacktestId(long backtestId) {
		this.backtestId = backtestId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public void setBuyDate(LocalDate buyDate) {
		this.buyDate = new java.sql.Date(buyDate.toDate().getTime());
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(LocalDate sellDate) {
		this.sellDate = new java.sql.Date(sellDate.toDate().getTime());
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getDollarReturn() {
		return dollarReturn;
	}
	public void setDollarReturn(double dollarReturn) {
		this.dollarReturn = dollarReturn;
	}
	public double getPercentReturn() {
		return percentReturn;
	}
	public void setPercentReturn(double percentReturn) {
		this.percentReturn = percentReturn;
	}
	public Boolean getProfitable() {
		return profitable;
	}
	public void setProfitable(Boolean profitable) {
		this.profitable = profitable;
	}
}
