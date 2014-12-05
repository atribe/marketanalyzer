package com.ar.marketanalyzer.plotting.highcharts.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsDDaysAndChurnDays;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class HighstockSingleValueData implements HighstockData {

	private Date x;
	private long y;
	
	/*
	 * Constructors
	 */
	public HighstockSingleValueData(){
		
	}
	public HighstockSingleValueData(SecuritiesOhlcv ohlcv) {
		setX(new Date( ohlcv.getDate().getTime() ));
		setY(ohlcv.getVolume());
	}
	
	public HighstockSingleValueData(RuleResultsDDaysAndChurnDays result) {
		setX(new Date( result.getDate().getTime() ));
		setY(result.getDdaysInWindow());
	}
	/*
	 * Helper Methods
	 */
	public static List<HighstockData> convertSecOHLCVtoSingleValue(List<SecuritiesOhlcv> secOhlcvList) {
		List<HighstockData> ohlcList = new ArrayList<HighstockData>();
		
		for(SecuritiesOhlcv secOhlcv : secOhlcvList) {
			ohlcList.add(new HighstockSingleValueData(secOhlcv));
		}
		
		return ohlcList;
	}
	public static List<HighstockData> convertDdayToSingleValue(	List<RuleResultsDDaysAndChurnDays> resultList) {
		List<HighstockData> ddayList = new ArrayList<HighstockData>();
		
		for(RuleResultsDDaysAndChurnDays result : resultList) {
			ddayList.add(new HighstockSingleValueData(result));
		}
		
		return ddayList;
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
