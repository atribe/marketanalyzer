package com.ar.marketanalyzer.plotting.amstockcharts.data;

import java.io.Serializable;

import com.ar.marketanalyzer.plotting.amcharts.serializers.JacksonObjectToListSerializer;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JacksonObjectToListSerializer.class)
public class DataSetSelector implements Serializable{

	private static final long serialVersionUID = 8537634961860679728L;
	/*
	 * http://docs.amcharts.com/3/javascriptstockchart/DataSetSelector
	 */
	
	/**
	* DataSet title.
	* Default Value: 
	*/
	 private String title;
	/**
	* Text displayed in the ""compare to"" combobox (when position is ""top"" or ""bottom"").
	* Default Value: Select...
	*/
	 private String comboBoxSelectText;
	/**
	* Text displayed near ""compare to"" list.
	* Default Value: Compare to:
	*/
	 private String compareText;
	/**
	* The maximum height of the Compare to field in pixels.
	* Default Value: 150
	*/
	 private double listHeight;
	/**
	* Possible values: ""right"", ""left"", ""top"", ""bottom"". ""top"" and ""bottom"" positions has a limitation - only one data set can be selected for comparing.
	* Default Value: right, left, top, bottom
	*/
	 private Position position;
	/**
	* Text displayed near ""Select"" dropDown.
	* Default Value: Select:
	*/
	 private String selectText;
	/**
	* Width of a Data set selector, when position is ""left"" or ""right"".
	* Default Value: 180
	*/
	 private double width;
	 
	 public DataSetSelector() {
		 
	 }
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComboBoxSelectText() {
		return comboBoxSelectText;
	}
	public void setComboBoxSelectText(String comboBoxSelectText) {
		this.comboBoxSelectText = comboBoxSelectText;
	}
	public String getCompareText() {
		return compareText;
	}
	public void setCompareText(String compareText) {
		this.compareText = compareText;
	}
	public double getListHeight() {
		return listHeight;
	}
	public void setListHeight(double listHeight) {
		this.listHeight = listHeight;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getSelectText() {
		return selectText;
	}
	public void setSelectText(String selectText) {
		this.selectText = selectText;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}

}
