package com.ar.marketanalyzer.plotting.highcharts.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class HighstockV implements HighstockData {

	private Date x;
	private long y;
	
	/*
	 * Constructors
	 */
	public HighstockV(){
		
	}
	public HighstockV(SecuritiesOhlcv ohlcv) {
		setX(new Date( ohlcv.getDate().getTime() ));
		setY(ohlcv.getVolume());
	}
	
	/*
	 * Helper Methods
	 */
	public static List<HighstockData> convertSecOHLCVtoOHLC(List<SecuritiesOhlcv> secOhlcvList) {
		List<HighstockData> ohlcList = new ArrayList<HighstockData>();
		
		for(SecuritiesOhlcv secOhlcv : secOhlcvList) {
			ohlcList.add(new HighstockV(secOhlcv));
		}
		
		return ohlcList;
	}
	
	@Override
	public String toString() {
		return x.toString() + " V:" + y;
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
	public long getY() {
		return y;
	}

	public void setY(long volume) {
		this.y = volume;
	}
}
