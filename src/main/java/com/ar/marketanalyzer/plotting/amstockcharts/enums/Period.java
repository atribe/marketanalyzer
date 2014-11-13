package com.ar.marketanalyzer.plotting.amstockcharts.enums;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Period {

	private PeriodEnum period;
	private Boolean selected;
	private Integer count;
	private String label;
	
	public Period() {
		
	}
	public Period(PeriodEnum period, String label) {
		this(period, null, label); 
	}
	public Period(PeriodEnum period, Integer count, String label) {
		this(period, null, count, label);
	}
	public Period(PeriodEnum period, Boolean selected, Integer count, String label) {
		this.period = period;
		this.selected = selected;
		this.count = count;
		this.label = label;
	}
	
	public PeriodEnum getPeriod() {
		return period;
	}
	public void setPeriod(PeriodEnum period) {
		this.period = period;
	}
	public Boolean isSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
