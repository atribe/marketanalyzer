package com.ar.marketanalyzer.ibd50.beans;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.indexbacktest.beans.YahooOHLCV;

public class stockOhlcvBean {
	private final static String tableName = "ibd50_stock_ohlcv";
	
	private long id;
	private long symbol_id;
	private String symbol;
	private Date date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private long volume;
	private BigDecimal adjClose;
	
	/*
	 * Constructors
	 */
	//Empty constructed required to be a Java Bean
	public stockOhlcvBean() {}
	
	public stockOhlcvBean(YahooOHLCV y, int symbol_id) {
		setSymbol(y.getSymbol());
		setSymbol_id(symbol_id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date parsed=null;
		try {
			parsed = format.parse(y.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setDate(new java.sql.Date(parsed.getTime()));
		setOpen(new BigDecimal(y.getOpen()));
		setHigh(new BigDecimal(y.getHigh()));
		setLow(new BigDecimal(y.getLow()));
		setClose(new BigDecimal(y.getClose()));
		setVolume(y.getVolume());
		setAdjClose(new BigDecimal(y.getAdjClose()));
	}
	
	public stockOhlcvBean(String symbol, Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long volume, BigDecimal adjClose) {
		setSymbol(symbol);
		setDate(date);
		setOpen(open);
		setHigh(high);
		setLow(low);
		setClose(close);
		setVolume(volume);
		setAdjClose(adjClose);
	}
	//End Constructors
	
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
			if( name != "tableName" && name != "symbol" ) { //Don't add the tableName field to the hashmap, as it isn't a column in the table
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
				} else if (type.equals(java.math.BigDecimal.class)){
					typeName = "DECIMAL(10,2)";
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
		String createTableSQL = "CREATE TABLE `" + tableName + "` (" +
				" id INT NOT NULL AUTO_INCREMENT,"; //Handle the id on its own because it has a bunch of stuff appended to it
		//Cycle through the hashmap and create a column for each
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if(entry.getKey() != "id" && entry.getKey() != "dateString") {	
				createTableSQL += " " + entry.getKey() + " "+ entry.getValue() +",";
			}
		 }
		//Set stuff like primary key and foriegn key at the end
		createTableSQL += " PRIMARY KEY (id), " +
				" FOREIGN KEY (symbol_id) REFERENCES " + GenericDBSuperclass.SYMBOL_TABLE_NAME +"(symbol_id)" +
				" ) ENGINE = MyISAM";
		
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
	 * Getters and Setters
	 */
	public static String getTablename() {
		return tableName;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSymbol_id() {
		return symbol_id;
	}
	
	public void setSymbol_id(long symbol_id) {
		this.symbol_id = symbol_id;		
	}
	
	public void setSymbol_id(int symbol_id) {
		this.symbol_id = symbol_id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDate(String date) {
		this.date = Date.valueOf(date);//Not the most elegant way to do it
	}
	public LocalDate getLocalDate() {
		return new LocalDate(this.date);
	}
	
	
	public BigDecimal getOpen() {
		return open;
	}
	
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	
	
	public BigDecimal getHigh() {
		return high;
	}
	
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	
	public BigDecimal getLow() {
		return low;
	}
	
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	
	public BigDecimal getClose() {
		return close;
	}
	
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	
	
	public long getVolume() {
		return volume;
	}
	
	public void setVolume(long volume) {
		this.volume = volume;
	}
	
	public void setVolume(double volume) {
		volume = Math.round(volume);
	}
	
	
	public BigDecimal getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}
	//End Getters and Setters

}