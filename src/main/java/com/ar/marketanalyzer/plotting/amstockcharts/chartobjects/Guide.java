package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import java.util.Date;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Guide {

	/**
	* If you set it to true, the guide will be displayed above the graphs.
	* Default Value: FALSE
	*/
	private boolean above;
	/**
	* Radar chart only. Specifies angle at which guide should start. Affects only fills, not lines.
	* Default Value: 
	*/
	private double angle;
	/**
	* Baloon fill color.
	* Default Value: 
	*/
	private Color balloonColor;
	/**
	* The text which will be displayed if the user rolls-over the guide.
	* Default Value: 
	*/
	private String balloonText;
	/**
	* Specifies if label should be bold or not.
	* Default Value: FALSE
	*/
	private boolean boldLabel;
	/**
	* Category of the guide (in case the guide is for category axis).
	* Default Value: 
	*/
	private String category;
	/**
	* Color of a guide label.
	* Default Value: 
	*/
	private Color color;
	/**
	* Dash length.
	* Default Value: 
	*/
	private double dashLength;
	/**
	* Date of the guide (in case the guide is for category axis and parseDates is set to true).
	* Default Value: 
	*/
	private Date date;
	/**
	* Works if a guide is added to CategoryAxis and this axis is non-date-based. If you set it to true, the guide will start (or be placed, if it's not a fill) on the beginning of the category cell and will end at the end of toCategory cell.
	* Default Value: FALSE
	*/
	private boolean expand;
	/**
	* Fill opacity. Value range is 0 - 1.
	* Default Value: 
	*/
	private double fillAlpha;
	/**
	* Fill color.
	* Default Value: 
	*/
	private Color fillColor;
	/**
	* Font size of guide label.
	* Default Value: 
	*/
	private double fontSize;
	/**
	* Unique id of a Guide. You don't need to set it, unless you want to.
	* Default Value: 
	*/
	private String id;
	/**
	* Specifies whether label should be placed inside or outside plot area.
	* Default Value: 
	*/
	private boolean inside;
	/**
	* The label which will be displayed near the guide.
	* Default Value: 
	*/
	private String label;
	/**
	* Rotation angle of a guide label.
	* Default Value: 
	*/
	private double labelRotation;
	/**
	* Line opacity.
	* Default Value: 
	*/
	private double lineAlpha;
	/**
	* Line color.
	* Default Value: 
	*/
	private Color lineColor;
	/**
	* Line thickness.
	* Default Value: 
	*/
	private double lineThickness;
	/**
	* Position of guide label. Possible values are ""left"" or ""right"" for horizontal axis and ""top"" or ""bottom"" for vertical axis.
	* Default Value: 
	*/
	private Position position;
	/**
	* Tick length.
	* Default Value: 
	*/
	private double tickLength;
	/**
	* Radar chart only. Specifies angle at which guide should end. Affects only fills, not lines.
	* Default Value: 
	*/
	private double toAngle;
	/**
	* ""To"" category of the guide (in case the guide is for category axis).
	* Default Value: 
	*/
	private String toCategory;
	/**
	* ""To"" date of the guide (in case the guide is for category axis and parseDates is set to true) If you have both date and toDate, the space between these two dates can be filled with color.
	* Default Value: 
	*/
	private Date toDate;
	/**
	* ""To"" value of the guide (in case the guide is for value axis).
	* Default Value: 
	*/
	private double toValue;
	/**
	* Value of the guide (in case the guide is for value axis).
	* Default Value: 
	*/
	private double value;
	/**
	* Value axis of a guide. As you can add guides directly to the chart, you might need to specify which which value axis should be used.
	* Default Value: 
	*/
	private ValueAxis valueAxis;
	
	public boolean isAbove() {
		return above;
	}
	public void setAbove(boolean above) {
		this.above = above;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public Color getBalloonColor() {
		return balloonColor;
	}
	public void setBalloonColor(Color balloonColor) {
		this.balloonColor = balloonColor;
	}
	public String getBalloonText() {
		return balloonText;
	}
	public void setBalloonText(String balloonText) {
		this.balloonText = balloonText;
	}
	public boolean isBoldLabel() {
		return boldLabel;
	}
	public void setBoldLabel(boolean boldLabel) {
		this.boldLabel = boldLabel;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getDashLength() {
		return dashLength;
	}
	public void setDashLength(double dashLength) {
		this.dashLength = dashLength;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isExpand() {
		return expand;
	}
	public void setExpand(boolean expand) {
		this.expand = expand;
	}
	public double getFillAlpha() {
		return fillAlpha;
	}
	public void setFillAlpha(double fillAlpha) {
		this.fillAlpha = fillAlpha;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getLabelRotation() {
		return labelRotation;
	}
	public void setLabelRotation(double labelRotation) {
		this.labelRotation = labelRotation;
	}
	public double getLineAlpha() {
		return lineAlpha;
	}
	public void setLineAlpha(double lineAlpha) {
		this.lineAlpha = lineAlpha;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public double getLineThickness() {
		return lineThickness;
	}
	public void setLineThickness(double lineThickness) {
		this.lineThickness = lineThickness;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public double getTickLength() {
		return tickLength;
	}
	public void setTickLength(double tickLength) {
		this.tickLength = tickLength;
	}
	public double getToAngle() {
		return toAngle;
	}
	public void setToAngle(double toAngle) {
		this.toAngle = toAngle;
	}
	public String getToCategory() {
		return toCategory;
	}
	public void setToCategory(String toCategory) {
		this.toCategory = toCategory;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public double getToValue() {
		return toValue;
	}
	public void setToValue(double toValue) {
		this.toValue = toValue;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public ValueAxis getValueAxis() {
		return valueAxis;
	}
	public void setValueAxis(ValueAxis valueAxis) {
		this.valueAxis = valueAxis;
	}

}
