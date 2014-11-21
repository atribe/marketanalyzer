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
	private long volume;
	
	/*
	 * Constructors
	 */
	public HighstockV(){
		
	}
	public HighstockV(SecuritiesOhlcv ohlcv) {
		setX(new Date( ohlcv.getDate().getTime() ));
		setVolume(ohlcv.getVolume());
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
	
	public Date getX() {
		return x;
	}
	public void setX(Date x) {
		this.x = x;
	}
	/*
	 * Getters and Setters
	 */
	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}
}
