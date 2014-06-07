package com.ar.marketanalyzer.ibd50.beans;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;

public class Ibd50RankingBean {
	
	private final static String tableName = "IBD50";
	
	private int ranking_id;
	private Date rankDate;
	private String symbol;
	private String companyName;
	private int rank;
	private BigDecimal currentPrice;
	private BigDecimal priceChange;
	private double pricePercentChange;
	private double percentOffHigh;
	private long volume; //need to add 000 to this number
	private double volumePercentChange;
	private double compositeRating;
	private double epsRating;
	private double rsRating;
	private String smrRating;
	private int accDisRating;
	private int groupRelStrRating;
	private double epsPercentChangeLastQtr;
	private double epsPercentChangePriorQtr;
	private double epsPercentChangeCurrentQtr;
	private double epsEstPercentChangeCurrentYear;
	private double salesPercentChangeLastQtr;
	private double annualROELastYear;
	private double annualProfitMarginLatestYear;
	private double managmentOwnPercent;
	private int qtrsRisingSponsorship;
	
	/*
	 * Constructor
	 */
	public Ibd50RankingBean() {
		rankDate =  new Date(new LocalDate().toDate().getTime()); //aka today
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
			if( name != "tableName" ) { //Don't add the tableName field to the hashmap, as it isn't a column in the table
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
					typeName = "DECIMAL";
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
				" ranking_id INT NOT NULL AUTO_INCREMENT,"; //Handle the id on its own because it has a bunch of stuff appended to it
		createTableSQL += " symbol_id INT,";
		createTableSQL += " tracking_id INT,";
		//Cycle through the hashmap and create a column for each
		for(Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if(entry.getKey() != "ranking_id" && entry.getKey() != "symbol") {	
				createTableSQL += " " + entry.getKey() + " "+ entry.getValue() +",";
			}
		 }
		//Set stuff like primary key and foriegn key at the end
		createTableSQL += " PRIMARY KEY (ranking_id), " +
				" FOREIGN KEY (symbol_id) REFERENCES `" + GenericDBSuperclass.SYMBOL_TABLE_NAME +"`(symbol_id)," +
				" FOREIGN KEY (tracking_id) REFERENCES ibd50_tracking(tracking_id)" +
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
	
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*
	 * Getters and Setters
	 */
	public static String getTableName() {
		return tableName;
	}
	public int getId() {
		return ranking_id;
	}
	public void setId(int id) {
		this.ranking_id = id;
	}
	public Date getRankDate() {
		return rankDate;
	}
	public void setRankDate(Date rankDate) {
		this.rankDate = rankDate;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public BigDecimal getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
	}
	public double getPricePercentChange() {
		return pricePercentChange;
	}
	public void setPricePercentChange(double pricePercentChange) {
		this.pricePercentChange = pricePercentChange;
	}
	public double getPercentOffHigh() {
		return percentOffHigh;
	}
	public void setPercentOffHigh(double percentOffHigh) {
		this.percentOffHigh = percentOffHigh;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public double getVolumePercentChange() {
		return volumePercentChange;
	}
	public void setVolumePercentChange(double volumePercentChange) {
		this.volumePercentChange = volumePercentChange;
	}
	public double getCompositeRating() {
		return compositeRating;
	}
	public void setCompositeRating(double compositeRating) {
		this.compositeRating = compositeRating;
	}
	public double getEpsRating() {
		return epsRating;
	}
	public void setEpsRating(double epsRating) {
		this.epsRating = epsRating;
	}
	public double getRsRating() {
		return rsRating;
	}
	public void setRsRating(double rsRating) {
		this.rsRating = rsRating;
	}
	public String getSmrRating() {
		return smrRating;
	}
	public void setSmrRating(String smrRating) {
		this.smrRating = smrRating;
	}
	public int getAccDisRating() {
		return accDisRating;
	}
	public void setAccDisRating(int accDisRating) {
		this.accDisRating = accDisRating;
	}
	public int getGroupRelStrRating() {
		return groupRelStrRating;
	}
	public void setGroupRelStrRating(int groupRelStrRating) {
		this.groupRelStrRating = groupRelStrRating;
	}
	public double getEpsPercentChangeLastQtr() {
		return epsPercentChangeLastQtr;
	}
	public void setEpsPercentChangeLastQtr(double epsPercentChangeLastQtr) {
		this.epsPercentChangeLastQtr = epsPercentChangeLastQtr;
	}
	public double getEpsPercentChangePriorQtr() {
		return epsPercentChangePriorQtr;
	}
	public void setEpsPercentChangePriorQtr(double epsPercentChangePriorQtr) {
		this.epsPercentChangePriorQtr = epsPercentChangePriorQtr;
	}
	public double getEpsPercentChangeCurrentQtr() {
		return epsPercentChangeCurrentQtr;
	}
	public void setEpsPercentChangeCurrentQtr(double epsPercentChangeCurrentQtr) {
		this.epsPercentChangeCurrentQtr = epsPercentChangeCurrentQtr;
	}
	public double getEpsEstPercentChangeCurrentYear() {
		return epsEstPercentChangeCurrentYear;
	}
	public void setEpsEstPercentChangeCurrentYear(
			double epsEstPercentChangeCurrentYear) {
		this.epsEstPercentChangeCurrentYear = epsEstPercentChangeCurrentYear;
	}
	public double getSalesPercentChangeLastQtr() {
		return salesPercentChangeLastQtr;
	}
	public void setSalesPercentChangeLastQtr(double salesPercentChangeLastQtr) {
		this.salesPercentChangeLastQtr = salesPercentChangeLastQtr;
	}
	public double getAnnualROELastYear() {
		return annualROELastYear;
	}
	public void setAnnualROELastYear(double annualROELastYear) {
		this.annualROELastYear = annualROELastYear;
	}
	public double getAnnualProfitMarginLatestYear() {
		return annualProfitMarginLatestYear;
	}
	public void setAnnualProfitMarginLatestYear(double annualProfitMarginLatestYear) {
		this.annualProfitMarginLatestYear = annualProfitMarginLatestYear;
	}
	public double getManagmentOwnPercent() {
		return managmentOwnPercent;
	}
	public void setManagmentOwnPercent(double managmentOwnPercent) {
		this.managmentOwnPercent = managmentOwnPercent;
	}
	public int getQtrsRisingSponsorship() {
		return qtrsRisingSponsorship;
	}
	public void setQtrsRisingSponsorship(int qtrsRisingSponsorship) {
		this.qtrsRisingSponsorship = qtrsRisingSponsorship;
	}
	
}
