package com.ar.marketanalyzer.plotting.highcharts.data;

import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class HighstockOHLC implements HighstockData {

	private Date x;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	
	/*
	 * Constructors
	 */
	public HighstockOHLC(){
		
	}
	public HighstockOHLC(SecuritiesOhlcv ohlcv) {
		setX(new Date( ohlcv.getDate().getTime() ));
		setOpen(ohlcv.getOpen());
		setHigh(ohlcv.getHigh());
		setLow(ohlcv.getLow());
		setClose(ohlcv.getClose());
	}
	
	/*
	 * Helper Methods
	 */
	public static List<HighstockData> convertSecOHLCVtoOHLC(List<SecuritiesOhlcv> secOhlcvList) {
		List<HighstockData> ohlcList = new ArrayList<HighstockData>();
		
		for(SecuritiesOhlcv secOhlcv : secOhlcvList) {
			ohlcList.add(new HighstockOHLC(secOhlcv));
		}
		
		return ohlcList;
	}
	
	@Override
	public String toString() {
		return x.toString() + " O:" + open + " H:" + high + " L:" + low + " C:" + close;
	}
	
	/*
	 * Getters and Setters
	 */
	public Date getX() {
		return x;
	}
	public void setX(Date x) {
		this.x = x;
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
	
}
