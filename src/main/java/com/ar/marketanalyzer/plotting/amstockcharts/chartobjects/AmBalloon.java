package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;

public class AmBalloon {

	/**
	* If this is set to true, border color instead of background color will be changed when user rolls-over the slice, graph, etc.
	* Default Value: TRUE
	*/
	 private boolean adjustBorderColor;
	/**
	* Duration of balloon movement from previous point to current point, in seconds.
	* Default Value: 0.3
	*/
	 private double animationDuration;
	/**
	* Balloon border opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private double borderAlpha;
	/**
	* Balloon border color. Will only be used of adjustBorderColor is false.
	* Default Value: #FFFFFF
	*/
	 private Color borderColor;
	/**
	* Balloon border thickness.
	* Default Value: 2
	*/
	 private double borderThickness;
	/**
	* Color of text in the balloon.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* Balloon corner radius.
	* Default Value: 0
	*/
	 private double cornerRadius;
	/**
	* Duration of a fade out animation, in seconds.
	* Default Value: 0.3
	*/
	 private double fadeOutDuration;
	/**
	* Balloon background opacity.
	* Default Value: 0.8
	*/
	 private double fillAlpha;
	/**
	* Balloon background color. Usually balloon background color is set by the chart. Only if ""adjustBorderColor"" is ""true"" this color will be used.
	* Default Value: #FFFFFF
	*/
	 private Color fillColor;
	/**
	* Specifies if balloon should follow mouse when hovering the slice/column/bullet or stay in fixed position (this does not affect balloon behavior if ChartCursor is used).
	* Default Value: FALSE
	*/
	 private boolean fixedPosition;
	/**
	* Size of text in the balloon. Chart's fontSize is used by default.
	* Default Value: 
	*/
	 private double fontSize;
	/**
	* Horizontal padding of the balloon.
	* Default Value: 8
	*/
	 private double horizontalPadding;
	/**
	* Maximum width of a balloon.
	* Default Value: 
	*/
	 private double maxWidth;
	/**
	* Defines horizontal distance from mouse pointer to balloon pointer. If you set it to a small value, the balloon might flicker, as mouse might lose focus on hovered object.
	* Default Value: 1
	*/
	 private double offsetX;
	/**
	* Defines vertical distance from mouse pointer to balloon pointer. If you set it to a small value, the balloon might flicker, as mouse might lose focus on hovered object.
	* Default Value: 6
	*/
	 private double offsetY;
	/**
	* The width of the pointer (arrow) ""root"". Only used if cornerRadius is 0.
	* Default Value: 6
	*/
	 private double pointerWidth;
	/**
	* Opacity of a shadow.
	* Default Value: 0.4
	*/
	 private double shadowAlpha;
	/**
	* Color of a shadow.
	* Default Value: #000000
	*/
	 private Color shadowColor;
	/**
	* If cornerRadius of a balloon is >0, showBullet is set to true for value balloons when ChartCursor is used. If you don't want the bullet near the balloon, set it to false: chart.balloon.showBullet = false
	* Default Value: FALSE
	*/
	 private boolean showBullet;
	/**
	* Text alignment, possible values ""left"", ""middle"" and ""right""
	* Default Value: middle
	*/
	 private String textAlign;
	/**
	* Vertical padding of the balloon.
	* Default Value: 4
	*/
	 private double verticalPadding;
	public boolean isAdjustBorderColor() {
		return adjustBorderColor;
	}
	public void setAdjustBorderColor(boolean adjustBorderColor) {
		this.adjustBorderColor = adjustBorderColor;
	}
	public double getAnimationDuration() {
		return animationDuration;
	}
	public void setAnimationDuration(double animationDuration) {
		this.animationDuration = animationDuration;
	}
	public double getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(double borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public double getBorderThickness() {
		return borderThickness;
	}
	public void setBorderThickness(double borderThickness) {
		this.borderThickness = borderThickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getCornerRadius() {
		return cornerRadius;
	}
	public void setCornerRadius(double cornerRadius) {
		this.cornerRadius = cornerRadius;
	}
	public double getFadeOutDuration() {
		return fadeOutDuration;
	}
	public void setFadeOutDuration(double fadeOutDuration) {
		this.fadeOutDuration = fadeOutDuration;
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
	public boolean isFixedPosition() {
		return fixedPosition;
	}
	public void setFixedPosition(boolean fixedPosition) {
		this.fixedPosition = fixedPosition;
	}
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	public double getHorizontalPadding() {
		return horizontalPadding;
	}
	public void setHorizontalPadding(double horizontalPadding) {
		this.horizontalPadding = horizontalPadding;
	}
	public double getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}
	public double getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}
	public double getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}
	public double getPointerWidth() {
		return pointerWidth;
	}
	public void setPointerWidth(double pointerWidth) {
		this.pointerWidth = pointerWidth;
	}
	public double getShadowAlpha() {
		return shadowAlpha;
	}
	public void setShadowAlpha(double shadowAlpha) {
		this.shadowAlpha = shadowAlpha;
	}
	public Color getShadowColor() {
		return shadowColor;
	}
	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}
	public boolean isShowBullet() {
		return showBullet;
	}
	public void setShowBullet(boolean showBullet) {
		this.showBullet = showBullet;
	}
	public String getTextAlign() {
		return textAlign;
	}
	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}
	public double getVerticalPadding() {
		return verticalPadding;
	}
	public void setVerticalPadding(double verticalPadding) {
		this.verticalPadding = verticalPadding;
	}

}
