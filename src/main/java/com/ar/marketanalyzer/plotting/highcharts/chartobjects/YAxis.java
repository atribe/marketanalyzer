package com.ar.marketanalyzer.plotting.highcharts.chartobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class YAxis {

	private Labels labels;
	private Title title;
	private String top;
	private String height;
	private Integer offset;
	private Integer lineWidth;
	private Boolean opposite;
	
	public Labels getLabels() {
		return labels;
	}
	public void setLabels(Labels labels) {
		this.labels = labels;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}
	public Boolean getOpposite() {
		return opposite;
	}
	public void setOpposite(Boolean opposite) {
		this.opposite = opposite;
	}
}
