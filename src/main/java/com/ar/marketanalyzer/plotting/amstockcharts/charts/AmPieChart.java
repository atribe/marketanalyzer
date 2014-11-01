package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmPieChart extends AmSlicedChart{

	/**
	* Pie lean angle (for 3D effect). Valid range is 0 - 90.
	* Default Value: 0
	*/
	private double angle;
	/**
	* Balloon text. The following tags can be used: [[value]], [[title]], [[percents]], [[description]] or any other field name from your data provider. HTML tags can also be used.
	* Default Value: [[title]]: [[percents]]% ([[value]])\n[[description]]
	*/
	private String balloonText;
	/**
	* Depth of the pie (for 3D effect).
	* Default Value: 0
	*/
	private double depth3D;
	/**
	* Inner radius of the pie, in pixels or percents.
	* Default Value: 0
	*/
	private double innerRadius;
	/**
	* The distance between the label and the slice, in pixels. You can use negative values to put the label on the slice.
	* Default Value: 20
	*/
	private double labelRadius;
	/**
	* Name of the field in dataProvider which specifies the length of a tick. Note, the chart will not try to arrange labels automatically if this property is set.
	* Default Value: 
	*/
	private String labelRadiusField;
	/**
	* Label text. The following tags can be used: [[value]], [[title]], [[percents]], [[description]] or any other field name from your data provider.
	* Default Value: [[title]]: [[percents]]%
	*/
	private String labelText;
	/**
	* Minimum radius of the pie, in pixels.
	* Default Value: 10
	*/
	private double minRadius;
	/**
	* Opacity of the slices. You can set the opacity of individual slice too.
	* Default Value: 1
	*/
	private double pieAlpha;
	/**
	* You can set fixed position of a pie center, in pixels or in percents.
	* Default Value: 
	*/
	private String pieX;
	/**
	* You can set fixed position of a pie center, in pixels or in percents.
	* Default Value: 
	*/
	private String pieY;
	/**
	* Pull out radius, in pixels or percents
	* Default Value: 0.2
	*/
	private String pullOutRadius;
	/**
	* Radius of a pie, in pixels or percents. By default, radius is calculated automatically.
	* Default Value: 
	*/
	private String radius;
	/**
	* Angle of the first slice, in degrees. This will work properly only if ""depth3D"" is set to 0. If ""depth3D"" is greater than 0, then there can be two angles only: 90 and 270. Value range is 0-360.
	* Default Value: 90
	*/
	private double startAngle;
	/**
	* Radius of the positions from which the slices will fly in.
	* Default Value: 5
	*/
	private String startRadius;
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public String getBalloonText() {
		return balloonText;
	}
	public void setBalloonText(String balloonText) {
		this.balloonText = balloonText;
	}
	public double getDepth3D() {
		return depth3D;
	}
	public void setDepth3D(double depth3d) {
		depth3D = depth3d;
	}
	public double getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(double innerRadius) {
		this.innerRadius = innerRadius;
	}
	public double getLabelRadius() {
		return labelRadius;
	}
	public void setLabelRadius(double labelRadius) {
		this.labelRadius = labelRadius;
	}
	public String getLabelRadiusField() {
		return labelRadiusField;
	}
	public void setLabelRadiusField(String labelRadiusField) {
		this.labelRadiusField = labelRadiusField;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public double getMinRadius() {
		return minRadius;
	}
	public void setMinRadius(double minRadius) {
		this.minRadius = minRadius;
	}
	public double getPieAlpha() {
		return pieAlpha;
	}
	public void setPieAlpha(double pieAlpha) {
		this.pieAlpha = pieAlpha;
	}
	public String getPieX() {
		return pieX;
	}
	public void setPieX(String pieX) {
		this.pieX = pieX;
	}
	public String getPieY() {
		return pieY;
	}
	public void setPieY(String pieY) {
		this.pieY = pieY;
	}
	public String getPullOutRadius() {
		return pullOutRadius;
	}
	public void setPullOutRadius(String pullOutRadius) {
		this.pullOutRadius = pullOutRadius;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public double getStartAngle() {
		return startAngle;
	}
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	public String getStartRadius() {
		return startRadius;
	}
	public void setStartRadius(String startRadius) {
		this.startRadius = startRadius;
	}
	
}
