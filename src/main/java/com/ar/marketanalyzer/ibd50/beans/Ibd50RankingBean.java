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
	
	private final static String tableName = "ibd50_ranking";
	
	private Integer ranking_id;
	private Integer symbol_id;
	private Integer tracking_id;
	private Date rankDate;
	private String symbol;
	private String companyName;
	private Integer rank;
	private BigDecimal currentPrice;
	private BigDecimal priceChange;
	private Double pricePercentChange;
	private Double percentOffHigh;
	private Long volume; //need to add 000 to this number
	private Double volumePercentChange;
	private Double compositeRating;
	private Double epsRating;
	private Double rsRating;
	private String smrRating;
	private String accDisRating;
	private String groupRelStrRating;
	private Double epsPercentChangeLastQtr;
	private Double epsPercentChangePriorQtr;
	private Double epsPercentChangeCurrentQtr;
	private Double epsEstPercentChangeCurrentYear;
	private Double salesPercentChangeLastQtr;
	private Double annualROELastYear;
	private Double annualProfitMarginLatestYear;
	private Double managmentOwnPercent;
	private Integer qtrsRisingSponsorship;
	
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
			if( name != "tableName" && name != "symbol" && name != "companyName") { //Don't add the tableName field to the hashmap, as it isn't a column in the table
				//Match the field type to the MySQL equivalent
				if(type.equals(Boolean.class)) {
					typeName = "TINYINT(1)";
				} else if (type.equals(Double.class)){
					typeName = "DOUBLE";
				} else if (type.equals(Integer.class)){
					typeName = "INT";
				} else if (type.equals(Long.class)){
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
				" ranking_id INT NOT NULL AUTO_INCREMENT,"; //Handle the id on its own because it has a bunch of stuff appended to it
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
	public Integer getRanking_id() {
		return ranking_id;
	}
	public void setRanking_id(Integer ranking_id) {
		this.ranking_id = ranking_id;
	}
	public Integer getSymbol_id() {
		return symbol_id;
	}
	public void setSymbol_id(Integer symbol_id) {
		this.symbol_id = symbol_id;
	}
	public Integer getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(Integer tracking_id) {
		this.tracking_id = tracking_id;
	}
	public void setId(int id) {
		this.ranking_id = new Integer(id);
	}
	public Date getRankDate() {
		return rankDate;
	}
	public LocalDate getLocalDateRankDate() {
		return new LocalDate(rankDate);
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public void setRank(int rank) {
		this.rank = new Integer(rank);
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
	public Double getPricePercentChange() {
		return pricePercentChange;
	}
	public void setPricePercentChange(Double pricePercentChange) {
		this.pricePercentChange = pricePercentChange;
	}
	public void setPricePercentChange(double pricePercentChange) {
		this.pricePercentChange = new Double(pricePercentChange);
	}
	public Double getPercentOffHigh() {
		return percentOffHigh;
	}
	public void setPercentOffHigh(Double percentOffHigh) {
		this.percentOffHigh = percentOffHigh;
	}
	public void setPercentOffHigh(double percentOffHigh) {
		this.percentOffHigh = new Double(percentOffHigh);
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public void setVolume(long volume) {
		this.volume = new Long(volume);
	}
	public Double getVolumePercentChange() {
		return volumePercentChange;
	}
	public void setVolumePercentChange(Double volumePercentChange) {
		this.volumePercentChange = volumePercentChange;
	}
	public void setVolumePercentChange(double volumePercentChange) {
		this.volumePercentChange = new Double(volumePercentChange);
	}
	public Double getCompositeRating() {
		return compositeRating;
	}
	public void setCompositeRating(Double compositeRating) {
		this.compositeRating = compositeRating;
	}
	public void setCompositeRating(double compositeRating) {
		this.compositeRating = new Double(compositeRating);
	}
	public Double getEpsRating() {
		return epsRating;
	}
	public void setEpsRating(Double epsRating) {
		this.epsRating = epsRating;
	}
	public void setEpsRating(double epsRating) {
		this.epsRating = new Double(epsRating);
	}
	public Double getRsRating() {
		return rsRating;
	}
	public void setRsRating(Double rsRating) {
		this.rsRating = rsRating;
	}
	public void setRsRating(double rsRating) {
		this.rsRating = new Double(rsRating);
	}
	public String getSmrRating() {
		return smrRating;
	}
	public void setSmrRating(String smrRating) {
		this.smrRating = smrRating;
	}
	public String getAccDisRating() {
		return accDisRating;
	}
	public void setAccDisRating(String accDisRating) {
		this.accDisRating = accDisRating;
	}
	public String getGroupRelStrRating() {
		return groupRelStrRating;
	}
	public void setGroupRelStrRating(String groupRelStrRating) {
		this.groupRelStrRating = groupRelStrRating;
	}
	public Double getEpsPercentChangeLastQtr() {
		return epsPercentChangeLastQtr;
	}
	public void setEpsPercentChangeLastQtr(Double epsPercentChangeLastQtr) {
		this.epsPercentChangeLastQtr = epsPercentChangeLastQtr;
	}
	public void setEpsPercentChangeLastQtr(double epsPercentChangeLastQtr) {
		this.epsPercentChangeLastQtr = new Double(epsPercentChangeLastQtr);
	}
	public Double getEpsPercentChangePriorQtr() {
		return epsPercentChangePriorQtr;
	}
	public void setEpsPercentChangePriorQtr(Double epsPercentChangePriorQtr) {
		this.epsPercentChangePriorQtr = epsPercentChangePriorQtr;
	}
	public void setEpsPercentChangePriorQtr(double epsPercentChangePriorQtr) {
		this.epsPercentChangePriorQtr = new Double(epsPercentChangePriorQtr);
	}
	public Double getEpsPercentChangeCurrentQtr() {
		return epsPercentChangeCurrentQtr;
	}
	public void setEpsPercentChangeCurrentQtr(Double epsPercentChangeCurrentQtr) {
		this.epsPercentChangeCurrentQtr = epsPercentChangeCurrentQtr;
	}
	public void setEpsPercentChangeCurrentQtr(double epsPercentChangeCurrentQtr) {
		this.epsPercentChangeCurrentQtr = new Double(epsPercentChangeCurrentQtr);
	}
	public Double getEpsEstPercentChangeCurrentYear() {
		return epsEstPercentChangeCurrentYear;
	}
	public void setEpsEstPercentChangeCurrentYear(Double epsEstPercentChangeCurrentYear) {
		this.epsEstPercentChangeCurrentYear = epsEstPercentChangeCurrentYear;
	}
	public void setEpsEstPercentChangeCurrentYear(double epsEstPercentChangeCurrentYear) {
		this.epsEstPercentChangeCurrentYear = new Double(epsEstPercentChangeCurrentYear);
	}
	public Double getSalesPercentChangeLastQtr() {
		return salesPercentChangeLastQtr;
	}
	public void setSalesPercentChangeLastQtr(Double salesPercentChangeLastQtr) {
		this.salesPercentChangeLastQtr = salesPercentChangeLastQtr;
	}
	public void setSalesPercentChangeLastQtr(double salesPercentChangeLastQtr) {
		this.salesPercentChangeLastQtr = new Double(salesPercentChangeLastQtr);
	}
	public Double getAnnualROELastYear() {
		return annualROELastYear;
	}
	public void setAnnualROELastYear(Double annualROELastYear) {
		this.annualROELastYear = annualROELastYear;
	}
	public void setAnnualROELastYear(double annualROELastYear) {
		this.annualROELastYear = new Double(annualROELastYear);
	}
	public Double getAnnualProfitMarginLatestYear() {
		return annualProfitMarginLatestYear;
	}
	public void setAnnualProfitMarginLatestYear(Double annualProfitMarginLatestYear) {
		this.annualProfitMarginLatestYear = annualProfitMarginLatestYear;
	}
	public void setAnnualProfitMarginLatestYear(double annualProfitMarginLatestYear) {
		this.annualProfitMarginLatestYear = new Double(annualProfitMarginLatestYear);
	}
	public Double getManagmentOwnPercent() {
		return managmentOwnPercent;
	}
	public void setManagmentOwnPercent(Double managmentOwnPercent) {
		this.managmentOwnPercent = managmentOwnPercent;
	}
	public void setManagmentOwnPercent(double managmentOwnPercent) {
		this.managmentOwnPercent = new Double(managmentOwnPercent);
	}
	public Integer getQtrsRisingSponsorship() {
		return qtrsRisingSponsorship;
	}
	public void setQtrsRisingSponsorship(Integer qtrsRisingSponsorship) {
		this.qtrsRisingSponsorship = qtrsRisingSponsorship;
	}
	public void setQtrsRisingSponsorship(int qtrsRisingSponsorship) {
		this.qtrsRisingSponsorship = new Integer(qtrsRisingSponsorship);
	}
	
}
