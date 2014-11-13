package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmFunnelChart extends AmSlicedChart{

	/**
	* The angle of the 3D part of the chart. This creates a 3D effect (if the ""depth3D"" is > 0).
	* Default Value: 0
	*/
	private Double angle;
	/**
	* Balloon text. The following tags can be used: [[value]], [[title]], [[percents]], [[description]] or any other field name from your data provider. HTML tags can also be used.
	* Default Value: [[title]]: [[value]]\n[[description]]
	*/
	private String balloonText;
	/**
	* Width of a base (first slice) of a chart. ""100%"" means it will occupy all available space.
	* Default Value: 1
	*/
	private String baseWidth;
	/**
	* The depth of funnel/pyramid. Set angle to >0 value in order this to work. Note, neckHeight/neckWidth will become 0 if you set these properties to bigger than 0 values.
	* Default Value: 0
	*/
	private Double depth3D;
	/**
	* Specifies where labels should be placed. Allowed values are left / center / right. If you set left or right, you should increase left or right margin in order labels to be visible.
	* Default Value: center
	*/
	private String labelPosition;
	/**
	* Label text. The following tags can be used: [[value]], [[title]], [[percents]], [[description]] or any other field name from your data provider.
	* Default Value: [[title]]: [[value]]
	*/
	private String labelText;
	/**
	* Height of a funnel neck. If default value, zero is used, the funnel won't have neck at all, which will make it look like pyramid.
	* Default Value: 0
	*/
	private String neckHeight;
	/**
	* Width of a funnel neck. If default value, zero is used, the funnel won't have neck at all, which will make it look like pyramid.
	* Default Value: 0
	*/
	private String neckWidth;
	/**
	* Outline opacity. Value range is 0 - 1.
	* Default Value: 0
	*/
	private Double outlineAlpha;
	/**
	* Specifies the distance by which slice should be pulled when user clicks on it.
	* Default Value: 30
	*/
	private String pullDistance;
	/**
	* If rotate is set to true, the funnel will be rotated and will became a pyramid.
	* Default Value: FALSE
	*/
	private Boolean rotate;
	/**
	* Initial x coordinate of slices. They will animate to the final x position from this one.
	* Default Value: 0
	*/
	private Double startX;
	/**
	* Initial y coordinate of slices. They will animate to the final y position from this one.
	* Default Value: 0
	*/
	private Double startY;
	/**
	* By default, the height of a slice represents it's value. However you might want the area of a slice to represent value - set this property to ""area"" then.
	* Default Value: height
	*/
	private String valueRepresents;
	public Double getAngle() {
		return angle;
	}
	public void setAngle(Double angle) {
		this.angle = angle;
	}
	public String getBalloonText() {
		return balloonText;
	}
	public void setBalloonText(String balloonText) {
		this.balloonText = balloonText;
	}
	public String getBaseWidth() {
		return baseWidth;
	}
	public void setBaseWidth(String baseWidth) {
		this.baseWidth = baseWidth;
	}
	public Double getDepth3D() {
		return depth3D;
	}
	public void setDepth3D(Double depth3d) {
		depth3D = depth3d;
	}
	public String getLabelPosition() {
		return labelPosition;
	}
	public void setLabelPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public String getNeckHeight() {
		return neckHeight;
	}
	public void setNeckHeight(String neckHeight) {
		this.neckHeight = neckHeight;
	}
	public String getNeckWidth() {
		return neckWidth;
	}
	public void setNeckWidth(String neckWidth) {
		this.neckWidth = neckWidth;
	}
	public Double getOutlineAlpha() {
		return outlineAlpha;
	}
	public void setOutlineAlpha(Double outlineAlpha) {
		this.outlineAlpha = outlineAlpha;
	}
	public String getPullDistance() {
		return pullDistance;
	}
	public void setPullDistance(String pullDistance) {
		this.pullDistance = pullDistance;
	}
	public Boolean getRotate() {
		return rotate;
	}
	public void setRotate(Boolean rotate) {
		this.rotate = rotate;
	}
	public Double getStartX() {
		return startX;
	}
	public void setStartX(Double startX) {
		this.startX = startX;
	}
	public Double getStartY() {
		return startY;
	}
	public void setStartY(Double startY) {
		this.startY = startY;
	}
	public String getValueRepresents() {
		return valueRepresents;
	}
	public void setValueRepresents(String valueRepresents) {
		this.valueRepresents = valueRepresents;
	}
	
}
