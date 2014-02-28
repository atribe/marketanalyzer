/**
 * 
 */
package com.atomrockets.marketanalyzer.beans;

import java.util.Date;

import org.joda.time.LocalDate;

/**
 * @author Allan
 * This stands for each column heading in the download for each index
 * Date, Open, High, Low, Close, Volume, Adjusted Close
 */
public class YahooDOHLCVARow {

	private int id;
	private String date;
	private float Open;
	private float High;
	private float Low;
	private float Close;
	private long Volume;
	private float AdjClose;
	
	private LocalDate convertedDate;
	
	public void YahooDOHLCVArow() {	}
	
	@Override
    public String toString() {
		return "id: " + getId()
				+ "\nDate: " + getDate() + " or " + getConvertedDate().toString() 
				+ "\nOpen: " + getOpen() 
				+ "\nHigh: " + getHigh()
				+ "\nLow: " + getLow()
				+ "\nClose: " + getClose()
				+ "\nVolume: " + getVolume();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
		return Open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(float open) {
		Open = open;
	}
	
	/**
	 * @return the high
	 */
	public float getHigh() {
		return High;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(float high) {
		High = high;
	}
	
	/**
	 * @return the low
	 */
	public float getLow() {
		return Low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(float low) {
		Low = low;
	}
	
	/**
	 * @return the close
	 */
	public float getClose() {
		return Close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(float close) {
		Close = close;
	}
	
	/**
	 * @return the volume
	 */
	public long getVolume() {
		return Volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(long volume) {
		Volume = volume;
	}
	public void setVolume(float volume) {
		Volume = Math.round(volume);
	}
	
	/**
	 * @return the adjClose
	 */
	public float getAdjClose() {
		return AdjClose;
	}
	/**
	 * @param adjClose the adjClose to set
	 */
	public void setAdjClose(float adjClose) {
		AdjClose = adjClose;
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
	}
	public void setConvertedDate(java.sql.Date convertedDate) {
		this.convertedDate = new LocalDate(convertedDate);
	}
	public void setConvertedDate(String date) {
		this.convertedDate = new LocalDate(date);
	}

	
}
