package com.ar.marketanalyzer.plotting.amstockcharts.buildingblock;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ValueAxis;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ChartCursor {
	/**
	* If you set adjustment to -1, the balloon will be shown near previous, if you set it to 1 - near next data point.
	* Default Value: 0
	*/
	private double adjustment;
	/**
	* Duration of animation of a line, in seconds.
	* Default Value: 0.3
	*/
	private double animationDuration;
	/**
	* Specifies if cursor should arrange balloons so they won't overlap. If chart is rotated, it might be good idea to turn this off.
	* Default Value: TRUE
	*/
	private boolean avoidBalloonOverlapping;
	/**
	* Specifies if bullet for each graph will follow the cursor.
	* Default Value: FALSE
	*/
	private boolean bulletsEnabled;
	/**
	* Size of bullets, following the cursor.
	* Default Value: 8
	*/
	private double bulletSize;
	/**
	* Opacity of the category balloon.
	* Default Value: 1
	*/
	private double categoryBalloonAlpha;
	/**
	* Color of the category balloon. cursorColor is used if not set.
	* Default Value: 
	*/
	private Color categoryBalloonColor;
	/**
	* Category balloon date format (used only if category axis parses dates). Check this page for instructions on how to format dates.
	* Default Value: MMM DD, YYYY
	*/
	private String categoryBalloonDateFormat;
	/**
	* Specifies whether category balloon is enabled.
	* Default Value: TRUE
	*/
	private boolean categoryBalloonEnabled;
	/**
	* Allows formatting any category balloon text you want. categoryBalloonFunction should return a string which will be displayed in a balloon. When categoryBalloonFunction is called, category value (or date) is passed as an argument.
	* Default Value: 
	*/
	private String categoryBalloonFunction;
	/**
	* Text color.
	* Default Value: #FFFFFF
	*/
	private Color color;
	/**
	* Opacity of the cursor line.
	* Default Value: 1
	*/
	private double cursorAlpha;
	/**
	* Color of the cursor line.
	* Default Value: #CC0000
	*/
	private Color cursorColor;
	/**
	* Specifies where the cursor line should be placed - on the beginning of the period (day, hour, etc) or in the middle (only when parseDates property of categoryAxis is set to true). If you want the cursor to follow mouse and not to glue to the nearest data point, set mouse here. Possible values are: start, middle, mouse.
	* Default Value: middle
	*/
	private String cursorPosition;
	/**
	* Specifies whether cursor is enabled.
	* Default Value: TRUE
	*/
	private boolean enabled;
	/**
	* If set to true, instead of a cursor line user will see a fill which width will always be equal to the width of one data item. We'd recommend setting cusrsorAlpha to 0.1 or some other small number if using this feature.
	* Default Value: FALSE
	*/
	private boolean fullWidth;
	/**
	* If you make graph's bullets invisible by setting their opacity to 0 and will set graphBulletAlpha to 1, the bullets will only appear at the cursor's position.
	* Default Value: 
	*/
	private double graphBulletAlpha;
	/**
	* Size of a graph's bullet (if available) at the cursor position. If you don't want the bullet to change it's size, set this property to 1.
	* Default Value: 1.7
	*/
	private double graphBulletSize;
	/**
	* If this is set to true, only one balloon at a time will be displayed. Note, this is quite CPU consuming.
	* Default Value: FALSE
	*/
	private boolean oneBalloonOnly;
	/**
	* If this is set to true, the user will be able to pan the chart (Serial only) instead of zooming.
	* Default Value: FALSE
	*/
	private boolean pan;
	/**
	* Opacity of the selection.
	* Default Value: 0.2
	*/
	private double selectionAlpha;
	/**
	* Specifies if cursor should only mark selected area but not zoom-in after user releases mouse button.
	* Default Value: FALSE
	*/
	private boolean selectWithoutZooming;
	/**
	* If true, the graph will display balloon on next available data point if currently hovered item doesn't have value for this graph.
	* Default Value: FALSE
	*/
	private boolean showNextAvailable;
	/**
	* Specifies whether value balloons are enabled. In case they are not, the balloons might be displayed anyway, when the user rolls-over the column or bullet.
	* Default Value: TRUE
	*/
	private boolean valueBalloonsEnabled;
	/**
	* Opacity of value line. Will use cursorAlpha value if not set.
	* Default Value: 
	*/
	private double valueLineAlpha;
	/**
	* Axis of value line. If you set valueLineBalloonEnabled to true, but you have more than one axis, you can use this property to indicate which axis should display balloon.
	* Default Value: 
	*/
	private ValueAxis valueLineAxis;
	/**
	* Specifies if value balloon next to value axis labels should be displayed. If you have more than one axis, set valueLineAxis property to indicate which axis should display the balloon.
	* Default Value: FALSE
	*/
	private boolean valueLineBalloonEnabled;
	/**
	* Specifies if cursor of Serial chart should display horizontal (or vertical if chart is rotated) line. This line might help users to compare distant values of a chart. You can also enable value balloon on this line by setting valueLineAxis property of ChartCursor.
	* Default Value: FALSE
	*/
	private boolean valueLineEnabled;
	/**
	* Specifies if the user can zoom-in the chart. If pan is set to true, zoomable is switched to false automatically.
	* Default Value: TRUE
	*/
	private boolean zoomable;
	/**
	* Read-only. Indicates if currently user is selecting some chart area to zoom-in.
	* Default Value: 
	*/
	private boolean zooming;
	public double getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(double adjustment) {
		this.adjustment = adjustment;
	}
	public double getAnimationDuration() {
		return animationDuration;
	}
	public void setAnimationDuration(double animationDuration) {
		this.animationDuration = animationDuration;
	}
	public boolean isAvoidBalloonOverlapping() {
		return avoidBalloonOverlapping;
	}
	public void setAvoidBalloonOverlapping(boolean avoidBalloonOverlapping) {
		this.avoidBalloonOverlapping = avoidBalloonOverlapping;
	}
	public boolean isBulletsEnabled() {
		return bulletsEnabled;
	}
	public void setBulletsEnabled(boolean bulletsEnabled) {
		this.bulletsEnabled = bulletsEnabled;
	}
	public double getBulletSize() {
		return bulletSize;
	}
	public void setBulletSize(double bulletSize) {
		this.bulletSize = bulletSize;
	}
	public double getCategoryBalloonAlpha() {
		return categoryBalloonAlpha;
	}
	public void setCategoryBalloonAlpha(double categoryBalloonAlpha) {
		this.categoryBalloonAlpha = categoryBalloonAlpha;
	}
	public Color getCategoryBalloonColor() {
		return categoryBalloonColor;
	}
	public void setCategoryBalloonColor(Color categoryBalloonColor) {
		this.categoryBalloonColor = categoryBalloonColor;
	}
	public String getCategoryBalloonDateFormat() {
		return categoryBalloonDateFormat;
	}
	public void setCategoryBalloonDateFormat(String categoryBalloonDateFormat) {
		this.categoryBalloonDateFormat = categoryBalloonDateFormat;
	}
	public boolean isCategoryBalloonEnabled() {
		return categoryBalloonEnabled;
	}
	public void setCategoryBalloonEnabled(boolean categoryBalloonEnabled) {
		this.categoryBalloonEnabled = categoryBalloonEnabled;
	}
	public String getCategoryBalloonFunction() {
		return categoryBalloonFunction;
	}
	public void setCategoryBalloonFunction(String categoryBalloonFunction) {
		this.categoryBalloonFunction = categoryBalloonFunction;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getCursorAlpha() {
		return cursorAlpha;
	}
	public void setCursorAlpha(double cursorAlpha) {
		this.cursorAlpha = cursorAlpha;
	}
	public Color getCursorColor() {
		return cursorColor;
	}
	public void setCursorColor(Color cursorColor) {
		this.cursorColor = cursorColor;
	}
	public String getCursorPosition() {
		return cursorPosition;
	}
	public void setCursorPosition(String cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isFullWidth() {
		return fullWidth;
	}
	public void setFullWidth(boolean fullWidth) {
		this.fullWidth = fullWidth;
	}
	public double getGraphBulletAlpha() {
		return graphBulletAlpha;
	}
	public void setGraphBulletAlpha(double graphBulletAlpha) {
		this.graphBulletAlpha = graphBulletAlpha;
	}
	public double getGraphBulletSize() {
		return graphBulletSize;
	}
	public void setGraphBulletSize(double graphBulletSize) {
		this.graphBulletSize = graphBulletSize;
	}
	public boolean isOneBalloonOnly() {
		return oneBalloonOnly;
	}
	public void setOneBalloonOnly(boolean oneBalloonOnly) {
		this.oneBalloonOnly = oneBalloonOnly;
	}
	public boolean isPan() {
		return pan;
	}
	public void setPan(boolean pan) {
		this.pan = pan;
	}
	public double getSelectionAlpha() {
		return selectionAlpha;
	}
	public void setSelectionAlpha(double selectionAlpha) {
		this.selectionAlpha = selectionAlpha;
	}
	public boolean isSelectWithoutZooming() {
		return selectWithoutZooming;
	}
	public void setSelectWithoutZooming(boolean selectWithoutZooming) {
		this.selectWithoutZooming = selectWithoutZooming;
	}
	public boolean isShowNextAvailable() {
		return showNextAvailable;
	}
	public void setShowNextAvailable(boolean showNextAvailable) {
		this.showNextAvailable = showNextAvailable;
	}
	public boolean isValueBalloonsEnabled() {
		return valueBalloonsEnabled;
	}
	public void setValueBalloonsEnabled(boolean valueBalloonsEnabled) {
		this.valueBalloonsEnabled = valueBalloonsEnabled;
	}
	public double getValueLineAlpha() {
		return valueLineAlpha;
	}
	public void setValueLineAlpha(double valueLineAlpha) {
		this.valueLineAlpha = valueLineAlpha;
	}
	public ValueAxis getValueLineAxis() {
		return valueLineAxis;
	}
	public void setValueLineAxis(ValueAxis valueLineAxis) {
		this.valueLineAxis = valueLineAxis;
	}
	public boolean isValueLineBalloonEnabled() {
		return valueLineBalloonEnabled;
	}
	public void setValueLineBalloonEnabled(boolean valueLineBalloonEnabled) {
		this.valueLineBalloonEnabled = valueLineBalloonEnabled;
	}
	public boolean isValueLineEnabled() {
		return valueLineEnabled;
	}
	public void setValueLineEnabled(boolean valueLineEnabled) {
		this.valueLineEnabled = valueLineEnabled;
	}
	public boolean isZoomable() {
		return zoomable;
	}
	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}
	public boolean isZooming() {
		return zooming;
	}
	public void setZooming(boolean zooming) {
		this.zooming = zooming;
	}

}
