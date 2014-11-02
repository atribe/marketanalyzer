package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.DateFormat;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.TextAlignment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ChartCursorSettings {
	/**
	* Specifies orientation of value balloon pointer.
	* Default Value: horizontal
	*/
	private String balloonPointerOrientation;
	/**
	* Specifies if bullet for each graph will follow the cursor.
	* Default Value: 
	*/
	private Boolean bulletsEnabled;
	/**
	* Size of bullets, following the cursor.
	* Default Value: 
	*/
	private Double bulletSize;
	/**
	* Opacity of the category balloon.
	* Default Value: 
	*/
	private Double categoryBalloonAlpha;
	/**
	* Color of the category balloon.
	* Default Value: 
	*/
	private Color categoryBalloonColor;
	/**
	* Array of date format objects. Date format object must have period and format items. Available periods are: fff - millisecond, ss - second, mm - minute, hh - hour, DD - date, WW - week, MM - month, YYYY - year.
	* Default Value: [{period:YYYY, format:YYYY}, {period:MM, format:MMM, YYYY}, {period:WW, format:MMM DD, YYYY}, {period:DD, format:MMM DD, YYYY}, {period:hh, format:JJ:NN}, {period:mm, format:JJ:NN}, {period:ss, format:JJ:NN:SS}, {period:fff, format:JJ:NN:SS}]
	*/
	private List<DateFormat> categoryBalloonDateFormats;
	/**
	* Specifies whether category balloon is enabled.
	* Default Value: 
	*/
	private Boolean categoryBalloonEnabled;
	/**
	* Opacity of the cursor line.
	* Default Value: 
	*/
	private Double cursorAlpha;
	/**
	* Color of the cursor line.
	* Default Value: 
	*/
	private Color cursorColor;
	/**
	* Possible values: start, middle, mouse.
	* Default Value: 
	*/
	private TextAlignment cursorPosition;
	/**
	* Set this to false if you don't want chart cursor to appear in your charts.
	* Default Value: TRUE
	*/
	private Boolean enabled;
	/**
	* Size of a graph's bullet (if available) at the cursor position. If you don't want the bullet to change it's size, set this property to 1.
	* Default Value: 1.7
	*/
	private Double graphBulletSize;
	/**
	* If this is set to true, the user will be able to pan the chart instead of zooming.
	* Default Value: 
	*/
	private Boolean pan;
	/**
	* Specifies whether value balloons are enabled. In case they are not, the balloons might be displayed anyway, when the user rolls-over the column or bullet.
	* Default Value: FALSE
	*/
	private Boolean valueBalloonsEnabled;
	/**
	* Opacity of value line. Will use cursorAlpha value if not set.
	* Default Value: 
	*/
	private Double valueLineAlpha;
	/**
	* Specifies if value balloon next to value axis labels should be displayed. If you have more than one axis, set valueLineAxis property ofChartCursor to indicate which axis should display the balloon.
	* Default Value: FALSE
	*/
	private Boolean valueLineBalloonEnabled;
	/**
	* Specifies if cursor of Serial chart should display horizontal (or vertical if chart is rotated) line. This line might help users to compare distant values of a chart. You can also enable value balloon on this line by setting valueLineAxis property of ChartCursor.
	* Default Value: FALSE
	*/
	private Boolean valueLineEnabled;
	/**
	* Specifies if the user can zoom-in the chart. If pan is set to true, zoomable is switched to false automatically.
	* Default Value: 
	*/
	private Boolean zoomable;
	
	/*
	 * Constructors 
	 */
	public ChartCursorSettings() {
		
	}
	
	/*
	 * Getters and Setters
	 */
	public String getBalloonPointerOrientation() {
		return balloonPointerOrientation;
	}
	public void setBalloonPointerOrientation(String balloonPointerOrientation) {
		this.balloonPointerOrientation = balloonPointerOrientation;
	}
	public Boolean isBulletsEnabled() {
		return bulletsEnabled;
	}
	public void setBulletsEnabled(Boolean bulletsEnabled) {
		this.bulletsEnabled = bulletsEnabled;
	}
	public Double getBulletSize() {
		return bulletSize;
	}
	public void setBulletSize(Double bulletSize) {
		this.bulletSize = bulletSize;
	}
	public Double getCategoryBalloonAlpha() {
		return categoryBalloonAlpha;
	}
	public void setCategoryBalloonAlpha(Double categoryBalloonAlpha) {
		this.categoryBalloonAlpha = categoryBalloonAlpha;
	}
	public Color getCategoryBalloonColor() {
		return categoryBalloonColor;
	}
	public void setCategoryBalloonColor(Color categoryBalloonColor) {
		this.categoryBalloonColor = categoryBalloonColor;
	}
	public List<DateFormat> getCategoryBalloonDateFormats() {
		return categoryBalloonDateFormats;
	}
	public void setCategoryBalloonDateFormats(
			List<DateFormat> categoryBalloonDateFormats) {
		this.categoryBalloonDateFormats = categoryBalloonDateFormats;
	}
	public Boolean isCategoryBalloonEnabled() {
		return categoryBalloonEnabled;
	}
	public void setCategoryBalloonEnabled(Boolean categoryBalloonEnabled) {
		this.categoryBalloonEnabled = categoryBalloonEnabled;
	}
	public Double getCursorAlpha() {
		return cursorAlpha;
	}
	public void setCursorAlpha(Double cursorAlpha) {
		this.cursorAlpha = cursorAlpha;
	}
	public Color getCursorColor() {
		return cursorColor;
	}
	public void setCursorColor(Color cursorColor) {
		this.cursorColor = cursorColor;
	}
	public TextAlignment getCursorPosition() {
		return cursorPosition;
	}
	public void setCursorPosition(TextAlignment cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Double getGraphBulletSize() {
		return graphBulletSize;
	}
	public void setGraphBulletSize(Double graphBulletSize) {
		this.graphBulletSize = graphBulletSize;
	}
	public Boolean isPan() {
		return pan;
	}
	public void setPan(Boolean pan) {
		this.pan = pan;
	}
	public Boolean isValueBalloonsEnabled() {
		return valueBalloonsEnabled;
	}
	public void setValueBalloonsEnabled(Boolean valueBalloonsEnabled) {
		this.valueBalloonsEnabled = valueBalloonsEnabled;
	}
	public Double getValueLineAlpha() {
		return valueLineAlpha;
	}
	public void setValueLineAlpha(Double valueLineAlpha) {
		this.valueLineAlpha = valueLineAlpha;
	}
	public Boolean isValueLineBalloonEnabled() {
		return valueLineBalloonEnabled;
	}
	public void setValueLineBalloonEnabled(Boolean valueLineBalloonEnabled) {
		this.valueLineBalloonEnabled = valueLineBalloonEnabled;
	}
	public Boolean isValueLineEnabled() {
		return valueLineEnabled;
	}
	public void setValueLineEnabled(Boolean valueLineEnabled) {
		this.valueLineEnabled = valueLineEnabled;
	}
	public Boolean isZoomable() {
		return zoomable;
	}
	public void setZoomable(Boolean zoomable) {
		this.zoomable = zoomable;
	}

}
