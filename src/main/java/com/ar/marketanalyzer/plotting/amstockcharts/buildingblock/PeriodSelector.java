package com.ar.marketanalyzer.plotting.amstockcharts.buildingblock;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Period;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PeriodEnum;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PeriodSelector {

	/**
	* Date format of date input fields. Check�this page�for possible codes. Note, only numeric date formats are allowed, so don't use MMM or MMMM month format.
	Please note that two-digit years (YY) is NOT supported in this setting.
	* Default Value: DD-MM-YYYY
	*/
	private String dateFormat;
	/**
	* Text displayed next to from date input field.
	* Default Value: From:
	*/
	private String fromText;
	/**
	* Specifies if period buttons with date range bigger than available data should be hidden.
	* Default Value: TRUE
	*/
	private Boolean hideOutOfScopePeriods;
	/**
	* Specifies whether period selector displays from and to date input fields.
	* Default Value: TRUE
	*/
	private Boolean inputFieldsEnabled;
	/**
	* Width of date input fields, in pixels. Works only if period selector is horizontal.
	* Default Value: 100
	*/
	private Double inputFieldWidth;
	/**
	* Array of predefined period objects. Period object has 4 properties - period, count, label and selected. Possible period values are: ss - seconds, mm - minutes, hh - hours, DD - days, MM - months and YYYY - years. property count specifies how many periods this button will select. label will be displayed on a button and selected is a Boolean which specifies if this button is selected when chart is initialized or not. Example: {period:DD, count:10, label:10 days, selected:false}.
	* Default Value: 
	*/
	private List<Period> periods;
	/**
	* Text displayed next to predefined period buttons.
	* Default Value: Zoom:
	*/
	private String periodsText;
	/**
	* Possible values: right, left, top, bottom.
	* Default Value: bottom
	*/
	private Position position;
	/**
	* Specifies whether predefined period buttons should select a period from the beginning or the end of the data.
	* Default Value: FALSE
	*/
	private Boolean selectFromStart;
	/**
	* Text displayed next to to date input field.
	* Default Value: To:
	*/
	private String toText;
	/**
	* Width of a period selector, when position is left or right.
	* Default Value: 180
	*/
	private Double width;
	
	/*
	 * Constructors
	 */
	public PeriodSelector() {
		//position = Position.bottom;
		
		periods = new ArrayList<Period>();
		periods.add( new Period(PeriodEnum.DD, 1, "1 day") );
		periods.add( new Period(PeriodEnum.DD, true, 5, "5 days") );
		periods.add( new Period(PeriodEnum.MM, 1, "1 month") );
		periods.add( new Period(PeriodEnum.YYYY, 1, "1 year") );
		periods.add( new Period(PeriodEnum.YTD, "YTD") );
		periods.add( new Period(PeriodEnum.MAX, "MAX") );
	}
	
	/*
	 * Getters and Setters
	 */
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getFromText() {
		return fromText;
	}
	public void setFromText(String fromText) {
		this.fromText = fromText;
	}
	public Boolean isHideOutOfScopePeriods() {
		return hideOutOfScopePeriods;
	}
	public void setHideOutOfScopePeriods(Boolean hideOutOfScopePeriods) {
		this.hideOutOfScopePeriods = hideOutOfScopePeriods;
	}
	public Boolean isInputFieldsEnabled() {
		return inputFieldsEnabled;
	}
	public void setInputFieldsEnabled(Boolean inputFieldsEnabled) {
		this.inputFieldsEnabled = inputFieldsEnabled;
	}
	public Double getInputFieldWidth() {
		return inputFieldWidth;
	}
	public void setInputFieldWidth(Double inputFieldWidth) {
		this.inputFieldWidth = inputFieldWidth;
	}
	public List<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}
	public String getPeriodsText() {
		return periodsText;
	}
	public void setPeriodsText(String periodsText) {
		this.periodsText = periodsText;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Boolean isSelectFromStart() {
		return selectFromStart;
	}
	public void setSelectFromStart(Boolean selectFromStart) {
		this.selectFromStart = selectFromStart;
	}
	public String getToText() {
		return toText;
	}
	public void setToText(String toText) {
		this.toText = toText;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}

}
