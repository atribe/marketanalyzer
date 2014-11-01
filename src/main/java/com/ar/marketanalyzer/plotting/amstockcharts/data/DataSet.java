package com.ar.marketanalyzer.plotting.amstockcharts.data;

import java.io.Serializable;
import java.util.List;

import com.ar.marketanalyzer.plotting.amcharts.serializers.JacksonObjectToListSerializer;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.StockEvent;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.FieldMapping;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JacksonObjectToListSerializer.class)
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
	 private boolean compared;
	/**
	* Data provider of the data set.
	* Default Value: 
	*/
	 private List<Object> dataProvider;
	/**
	* Array of field mappings. Field mapping is an object with fromField and toField properties. fromField is a name of your value field in dataProvider. toField might be chosen freely, it will be used to set value/open/close/high/low fields for the StockGraph. Example: {fromField:""val1"", toField:""value""}.
	* Default Value: 
	*/
	 private List<FieldMapping> fieldMappings;
	/**
	* Specifies whether this data set should be visible in ""compare to"" list.
	* Default Value: TRUE
	*/
	 private boolean showInCompare;
	/**
	* Specifies whether this data set should be visible in ""select"" dropdown.
	* Default Value: TRUE
	*/
	 private boolean showInSelect;
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

	
	public DataSet() {
		
	}

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

	public boolean isCompared() {
		return compared;
	}

	public void setCompared(boolean compared) {
		this.compared = compared;
	}

	public List<Object> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(List<Object> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public List<FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public void setFieldMappings(List<FieldMapping> fieldMappings) {
		this.fieldMappings = fieldMappings;
	}

	public boolean isShowInCompare() {
		return showInCompare;
	}

	public void setShowInCompare(boolean showInCompare) {
		this.showInCompare = showInCompare;
	}

	public boolean isShowInSelect() {
		return showInSelect;
	}

	public void setShowInSelect(boolean showInSelect) {
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
