/**
 * 
 */
package com.atomrockets.marketanalyzer.models;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

/**
 * @author Allan
 * This stands for each column heading in the download for each index
 * Date, Open, High, Low, Close, Volume, Adjusted Close
 */
public class YahooIndexData {

	private long id;
	
	private String date;
	private float open;
	private float high;
	private float low;
	private float close;
	private long volume;
	private float adjClose;
	private String symbol;
	private LocalDate convertedDate;
	
	//Empty constructed required to be a Java Bean
	public YahooIndexData() {	}
	
	public YahooIndexData(String symbol, String date, float open, float high, float low, float close, long volume, float adjClose) {
		this.symbol = symbol;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
	}
	
	public YahooIndexData(String symbol, LocalDate date, float open, float high, float low, float close, long volume, float adjClose) {
		this.symbol = symbol;
		this.convertedDate = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
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
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
		setConvertedDate(date);
	}
	
	/**
	 * @return the open
	 */
	public float getOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(float open) {
		this.open = open;
	}
	
	/**
	 * @return the high
	 */
	public float getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(float high) {
		this.high = high;
	}
	
	/**
	 * @return the low
	 */
	public float getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(float low) {
		this.low = low;
	}
	
	/**
	 * @return the close
	 */
	public float getClose() {
		return close;
	}
	/**
	 * @param close the close to set
	 */
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
		volume = Math.round(volume);
	}
	
	/**
	 * @return the adjClose
	 */
	public float getAdjClose() {
		return adjClose;
	}
	/**
	 * @param adjClose the adjClose to set
	 */
	public void setAdjClose(float adjClose) {
		this.adjClose = adjClose;
	}
	/**
	 * @return the convertedDate
	 */
	public LocalDate getConvertedDate() {
		return convertedDate;
	}
	/**
	 * @param convertedDate the convertedDate to set
	 */
	public void setConvertedDate(LocalDate convertedDate) {
		this.convertedDate = convertedDate;
		this.date = convertedDate.toString();
	}
	public void setConvertedDate(java.sql.Date convertedDate) {
		this.convertedDate = new LocalDate(convertedDate);
		this.date = this.convertedDate.toString();
	}
	public void setConvertedDate(String date) {
		this.convertedDate = new LocalDate(date);
		this.date = date;
	}
}
