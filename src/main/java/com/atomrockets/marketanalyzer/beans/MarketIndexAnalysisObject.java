package com.atomrockets.marketanalyzer.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTables;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

@Entity
@Table(name="marketIndexAnalysis")

@SecondaryTables({
	@SecondaryTable(name="yahooDataTable", pkJoinColumns={
			@PrimaryKeyJoinColumn(name="yd_id", referencedColumnName="id")
	})
})
public class MarketIndexAnalysisObject implements Serializable {
	
	@Id
	@Column(name="id")
	private int id;
	
	//Data from the yahooDataTable
	@Column(name="date", table="yahooDataTable" )
	private String date;
	private LocalDate convertedDate;
	
	@Column(name="open", table="yahooDataTable" )
	private float open;
	
	@Column(name="high", table="yahooDataTable" )
	private float high;
	
	@Column(name="low", table="yahooDataTable" )
	private float low;
	
	@Column(name="close", table="yahooDataTable" )
	private float close;
	
	@Column(name="volume", table="yahooDataTable" )
	private long volume;
	
	//Statistic variables
	@Column(name="closeAvg50")
	private float closeAvg50;
	
	@Column(name="closeAvg100")
	private float closeAvg100;
	
	@Column(name="closeAvg200")
	private float closeAvg200;
	
	@Column(name="volumeAvg50")
	private long volumeAvg50;
	
	@Column(name="priceTrend35")
	private float priceTrend35;
	
	//Analysis variables
	@Column(name="isDDay")
	private boolean isDDay;
	
	@Column(name="isChurnDay")
	private boolean isChurnDay;
	
	@Column(name="dDayCounter")
	private int dDayCounter;
	//add more stuff as needed here

	public MarketIndexAnalysisObject() {
		dDayCounter=0;
	}

	@Override
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
		 }
	/*
    public String toString() {
		return "\nid: " + getId()
				+ "\nDate: " + getDate().toString()
				+ "\nOpen: " + getOpen()
				+ "\nHigh: " + getHigh()
				+ "\nLow: " + getLow()
				+ "\nClose: " + getClose()
				+ "\nIs D-Day: " + isDDay()
				+ "\nIs Churn Day: " + isChurnDay()
				+ "\nD-Day Count: " + getdDayCounter() + "\n";
	}
	*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the isDDay
	 */
	public boolean isDDay() {
		return isDDay;
	}
	/**
	 * @param isDDay the isDDay to set
	 */
	public void setDDay(boolean isDDay) {
		this.isDDay = isDDay;
	}

	/**
	 * @return the isChurnDay
	 */
	public boolean isChurnDay() {
		return isChurnDay;
	}
	/**
	 * @param isChurnDay the isChurnDay to set
	 */
	public void setChurnDay(boolean isChurnDay) {
		this.isChurnDay = isChurnDay;
	}

	/**
	 * @return the dDayCounter
	 */
	public int getdDayCounter() {
		return dDayCounter;
	}
	/**
	 * @param dDayCounter the dDayCounter to set
	 */
	public void setdDayCounter(int dDayCounter) {
		this.dDayCounter = dDayCounter;
	}
	public void addDDayCounter() {
		dDayCounter++;
	}
}
