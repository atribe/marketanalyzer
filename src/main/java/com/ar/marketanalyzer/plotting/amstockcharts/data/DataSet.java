package com.ar.marketanalyzer.plotting.amstockcharts.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockEvent;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.FieldMapping;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DataSet implements Serializable{
	
	private static final long serialVersionUID = 7291671568463102235L;
	
	/*
	* http://docs.amcharts.com/3/javascriptstockchart/DataSet
	*/
	
	/**
	* Category field name in your dataProvider.
	* Default Value: 
	*/
	private String categoryField;
	/**
	* Color of the data set. One of colors fromAmStockChart.colors array will be used if not set.
	* Default Value: 
	*/
	private Color color;
	/**
	* Whether this data set is selected for comparing. If you change this property, you should call stockChart.validateData() method in order the changes to be applied.
	* Default Value: FALSE
	*/
	private Boolean compared;
	/**
	* Data provider of the data set.
	* Default Value: 
	*/
	private List<DataProviderInterface> dataProvider;
	/**
	* Array of field mappings. Field mapping is an object with fromField and toField properties. fromField is a name of your value field in dataProvider. toField might be chosen freely, it will be used to set value/open/close/high/low fields for the StockGraph. Example: {fromField:""val1"", toField:""value""}.
	* Default Value: 
	*/
	private List<FieldMapping> fieldMappings = new ArrayList<FieldMapping>();
	/**
	* Specifies whether this data set should be visible in ""compare to"" list.
	* Default Value: TRUE
	*/
	private Boolean showInCompare;
	/**
	* Specifies whether this data set should be visible in ""select"" dropdown.
	* Default Value: TRUE
	*/
	private Boolean showInSelect;
	/**
	* Array of StockEvent objects.
	* Default Value: 
	*/
	private List<StockEvent> stockEvents;
	/**
	* DataSet title.
	* Default Value: 
	*/
	private String title;

	/*
	 * Constructors
	 */
	public DataSet() {
		this.categoryField = "date";
		this.color = new Color("7F8DA9");
		
		fieldMappings.add(new FieldMapping("open", "open"));
		fieldMappings.add(new FieldMapping("close", "close"));
		fieldMappings.add(new FieldMapping("high", "high"));
		fieldMappings.add(new FieldMapping("low", "low"));
		fieldMappings.add(new FieldMapping("volume", "volume"));
		fieldMappings.add(new FieldMapping("value", "value"));
	}
	public DataSet(List<DataProviderInterface> dataProviderList) {
		this();
		
		this.title = dataProviderList.get(0).getTitle();
		
		this.dataProvider = dataProviderList;
	}
	public DataSet(Color color, List<DataProviderInterface> dataProviderList) {
		this();
		
		this.title = dataProviderList.get(0).getTitle();
		this.color = color;
		
		this.dataProvider = dataProviderList;
	}

	/*
	 * Helper Methods
	 */
	public void setAsSecondaryStockFieldMappings() {
		fieldMappings.clear();
		fieldMappings.add(new FieldMapping("value", "value"));
	}
	
	/*
	* Getters and Setters
	*/
	public String getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean isCompared() {
		return compared;
	}

	public void setCompared(Boolean compared) {
		this.compared = compared;
	}

	public List<DataProviderInterface> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(List<DataProviderInterface> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public List<FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public void setFieldMappings(List<FieldMapping> fieldMappings) {
		this.fieldMappings = fieldMappings;
	}

	public Boolean isShowInCompare() {
		return showInCompare;
	}

	public void setShowInCompare(Boolean showInCompare) {
		this.showInCompare = showInCompare;
	}

	public Boolean isShowInSelect() {
		return showInSelect;
	}

	public void setShowInSelect(Boolean showInSelect) {
		this.showInSelect = showInSelect;
	}

	public List<StockEvent> getStockEvents() {
		return stockEvents;
	}

	public void setStockEvents(List<StockEvent> stockEvents) {
		this.stockEvents = stockEvents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
