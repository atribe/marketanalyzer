package com.atomrockets.marketanalyzer.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

public class IndexCalcs implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5343792322441098873L;

	private long id;
	
	//Data from the yahooDataTable
	private String symbol;
	private String date;
	private LocalDate convertedDate;
	private float open;
	private float high;
	private float low;
	private float close;
	private long volume;
	
	//Statistic variables
	private float closeAvg50;
	private float closeAvg100;
	private float closeAvg200;
	private long volumeAvg50;
	private float priceTrend35;
	
	//Analysis variables
	private Boolean distributionDay;
	private Boolean churnDay;
	private int distributionDayCounter;
	private Boolean followThruDay;
	
	//Buy, sell, hold
	private String dayAction;
	//add more stuff as needed here

	public IndexCalcs() {
		distributionDayCounter=0;
	}

	@Override
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
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
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the date
	 */
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		setConvertedDate(date);
	}

	/**
	 * @param date the date to set
	 */
	public LocalDate getConvertedDate() {
		return convertedDate;
	}
	public void setConvertedDate(LocalDate date) {
		this.convertedDate = date;
		this.date=convertedDate.toString();
	}
	public void setConvertedDate(java.sql.Date date) {
		this.convertedDate = new LocalDate(date);
		this.date = this.convertedDate.toString();
	}
	public void setConvertedDate(String date) {
		this.convertedDate = new LocalDate(date);
		this.date=date;
	}

	
	
	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	/**
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public void setVolume(float volume) {
		this.volume = Math.round(volume);
	}
			
	/**
	 * @return the closeAvg50
	 */
	public float getCloseAvg50() {
		return closeAvg50;
	}
	/**
	 * @param closeAvg50 the closeAvg50 to set
	 */
	public void setCloseAvg50(float closeAvg50) {
		this.closeAvg50 = closeAvg50;
	}

	/**
	 * @return the closeAvg100
	 */
	public float getCloseAvg100() {
		return closeAvg100;
	}
	/**
	 * @param closeAvg100 the closeAvg100 to set
	 */
	public void setCloseAvg100(float closeAvg100) {
		this.closeAvg100 = closeAvg100;
	}

	/**
	 * @return the closeAvg200
	 */
	public float getCloseAvg200() {
		return closeAvg200;
	}
	/**
	 * @param closeAvg200 the closeAvg200 to set
	 */
	public void setCloseAvg200(float closeAvg200) {
		this.closeAvg200 = closeAvg200;
	}

	/**
	 * @return the volumeAvg50
	 */
	public long getVolumeAvg50() {
		return volumeAvg50;
	}
	/**
	 * @param volumeAvg50 the volumeAvg50 to set
	 */
	public void setVolumeAvg50(long volumeAvg50) {
		this.volumeAvg50 = volumeAvg50;
	}

	/**
	 * @return the priceTrend35
	 */
	public float getPriceTrend35() {
		return priceTrend35;
	}
	/**
	 * @param priceTrend35 the priceTrend35 to set
	 */
	public void setPriceTrend35(float priceTrend35) {
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

	public void addDDayCounter() {
		setDistributionDayCounter(getDistributionDayCounter() + 1);
	}

	public String getDayAction() {
		return dayAction;
	}

	public void setDayAction(String dayAction) {
		this.dayAction = dayAction;
	}

	public Boolean getChurnDay() {
		return churnDay;
	}

	public void setChurnDay(Boolean churnDay) {
		this.churnDay = churnDay;
	}

	public Boolean getFollowThruDay() {
		return followThruDay;
	}

	public void setFollowThruDay(Boolean followThruDay) {
		this.followThruDay = followThruDay;
	}
}