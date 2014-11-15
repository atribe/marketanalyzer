package com.ar.marketanalyzer.plotting.amstockcharts.data.dataprovider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsDDaysAndChurnDays;

public class DataProviderDday implements DataProviderInterface{
	private Date date;
	private Boolean dday;
	private Boolean churnDay;
	private Integer ddayInWindow;
	
	/*
	 * Constructors
	 */
	public DataProviderDday() {
		
	}
	public DataProviderDday(RuleResultsDDaysAndChurnDays result) {
		this.date = result.getDate();
		this.dday = result.getDday();
		this.churnDay = result.getChurnDay();
		this.ddayInWindow = result.getDdaysInWindow();
	}

	/*
	 * Helper Methods
	 */
	public static List<DataProviderInterface> convertDdayRuleResultToDataProviderDday(List<RuleResultsDDaysAndChurnDays> resultList) {
		List<DataProviderInterface> dataProviderList = new ArrayList<DataProviderInterface>();
		for(RuleResultsDDaysAndChurnDays result: resultList) {
			dataProviderList.add(new DataProviderDday(result));
		}
		return dataProviderList;
	}
	
	/*
	 * Getters and Setters
	 */
	@Override
	public Date getDate() {
		return date;
	}
	@Override
	public void setDate(Date date) {
		this.date = date;		
	}
	public Boolean getDday() {
		return dday;
	}
	public void setDday(Boolean dday) {
		this.dday = dday;
	}
	public Boolean getChurnDay() {
		return churnDay;
	}
	public void setChurnDay(Boolean churnDay) {
		this.churnDay = churnDay;
	}
	public Integer getDdayInWindow() {
		return ddayInWindow;
	}
	public void setDdayInWindow(Integer ddayInWindow) {
		this.ddayInWindow = ddayInWindow;
	}
}
