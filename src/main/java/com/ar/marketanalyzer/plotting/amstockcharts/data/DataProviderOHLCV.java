package com.ar.marketanalyzer.plotting.amstockcharts.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;

public class DataProviderOHLCV implements DataProviderInterface{

	private String symbol;
	private Date date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private long volume;
	private BigDecimal value;
	
	/*
	 * Constructors
	 */
	public DataProviderOHLCV() {
		
	}
	public DataProviderOHLCV(String symbol, Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long volume) {
		this.symbol = symbol;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.value = close;
	}
	
	/*
	 * Helper Methods
	 */
	@Override
	public String getTitle() {
		return this.symbol;
	}
	
	public static List<DataProviderInterface> convertSecuritiesOhlcvToDataProviderOHLCV(List<SecuritiesOhlcv> ohlcvList) {
		List<DataProviderInterface> dataProviderList = new ArrayList<DataProviderInterface>();
		for(SecuritiesOhlcv ohlcv: ohlcvList) {
			dataProviderList.add(new DataProviderOHLCV(ohlcv.getSymbol().getName(), ohlcv.getDate(), ohlcv.getOpen(), ohlcv.getHigh(), ohlcv.getLow(), ohlcv.getClose(), ohlcv.getVolume()));
		}
		return dataProviderList;
	}
	
	/*
	 * Getters and Setters
	 */
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
		this.value = close;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
