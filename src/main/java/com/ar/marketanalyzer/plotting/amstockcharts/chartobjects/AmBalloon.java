package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AmBalloon {

	/**
	* If this is set to true, border color instead of background color will be changed when user rolls-over the slice, graph, etc.
	* Default Value: TRUE
	*/
	 private Boolean adjustBorderColor;
	/**
	* Duration of balloon movement from previous point to current point, in seconds.
	* Default Value: 0.3
	*/
	 private Double animationDuration;
	/**
	* Balloon border opacity. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private Double borderAlpha;
	/**
	* Balloon border color. Will only be used of adjustBorderColor is false.
	* Default Value: #FFFFFF
	*/
	 private Color borderColor;
	/**
	* Balloon border thickness.
	* Default Value: 2
	*/
	 private Double borderThickness;
	/**
	* Color of text in the balloon.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* Balloon corner radius.
	* Default Value: 0
	*/
	 private Double cornerRadius;
	/**
	* Duration of a fade out animation, in seconds.
	* Default Value: 0.3
	*/
	 private Double fadeOutDuration;
	/**
	* Balloon background opacity.
	* Default Value: 0.8
	*/
	 private Double fillAlpha;
	/**
	* Balloon background color. Usually balloon background color is set by the chart. Only if ""adjustBorderColor"" is ""true"" this color will be used.
	* Default Value: #FFFFFF
	*/
	 private Color fillColor;
	/**
	* Specifies if balloon should follow mouse when hovering the slice/column/bullet or stay in fixed position (this does not affect balloon behavior if ChartCursor is used).
	* Default Value: FALSE
	*/
	 private Boolean fixedPosition;
	/**
	* Size of text in the balloon. Chart's fontSize is used by default.
	* Default Value: 
	*/
	 private Double fontSize;
	/**
	* Horizontal padding of the balloon.
	* Default Value: 8
	*/
	 private Double horizontalPadding;
	/**
	* Maximum width of a balloon.
	* Default Value: 
	*/
	 private Double maxWidth;
	/**
	* Defines horizontal distance from mouse pointer to balloon pointer. If you set it to a small value, the balloon might flicker, as mouse might lose focus on hovered object.
	* Default Value: 1
	*/
	 private Double offsetX;
	/**
	* Defines vertical distance from mouse pointer to balloon pointer. If you set it to a small value, the balloon might flicker, as mouse might lose focus on hovered object.
	* Default Value: 6
	*/
	 private Double offsetY;
	/**
	* The width of the pointer (arrow) ""root"". Only used if cornerRadius is 0.
	* Default Value: 6
	*/
	 private Double pointerWidth;
	/**
	* Opacity of a shadow.
	* Default Value: 0.4
	*/
	 private Double shadowAlpha;
	/**
	* Color of a shadow.
	* Default Value: #000000
	*/
	 private Color shadowColor;
	/**
	* If cornerRadius of a balloon is >0, showBullet is set to true for value balloons when ChartCursor is used. If you don't want the bullet near the balloon, set it to false: chart.balloon.showBullet = false
	* Default Value: FALSE
	*/
	 private Boolean showBullet;
	/**
	* Text alignment, possible values ""left"", ""middle"" and ""right""
	* Default Value: middle
	*/
	 private String textAlign;
	/**
	* Vertical padding of the balloon.
	* Default Value: 4
	*/
	 private Double verticalPadding;
	public Boolean isAdjustBorderColor() {
		return adjustBorderColor;
	}
	public void setAdjustBorderColor(Boolean adjustBorderColor) {
		this.adjustBorderColor = adjustBorderColor;
	}
	public Double getAnimationDuration() {
		return animationDuration;
	}
	public void setAnimationDuration(Double animationDuration) {
		this.animationDuration = animationDuration;
	}
	public Double getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(Double borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Double getBorderThickness() {
		return borderThickness;
	}
	public void setBorderThickness(Double borderThickness) {
		this.borderThickness = borderThickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Double getCornerRadius() {
		return cornerRadius;
	}
	public void setCornerRadius(Double cornerRadius) {
		this.cornerRadius = cornerRadius;
	}
	public Double getFadeOutDuration() {
		return fadeOutDuration;
	}
	public void setFadeOutDuration(Double fadeOutDuration) {
		this.fadeOutDuration = fadeOutDuration;
	}
	public Double getFillAlpha() {
		return fillAlpha;
	}
	public void setFillAlpha(Double fillAlpha) {
		this.fillAlpha = fillAlpha;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public Boolean isFixedPosition() {
		return fixedPosition;
	}
	public void setFixedPosition(Boolean fixedPosition) {
		this.fixedPosition = fixedPosition;
	}
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
	public Double getHorizontalPadding() {
		return horizontalPadding;
	}
	public void setHorizontalPadding(Double horizontalPadding) {
		this.horizontalPadding = horizontalPadding;
	}
	public Double getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(Double maxWidth) {
		this.maxWidth = maxWidth;
	}
	public Double getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(Double offsetX) {
		this.offsetX = offsetX;
	}
	public Double getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(Double offsetY) {
		this.offsetY = offsetY;
	}
	public Double getPointerWidth() {
		return pointerWidth;
	}
	public void setPointerWidth(Double pointerWidth) {
		this.pointerWidth = pointerWidth;
	}
	public Double getShadowAlpha() {
		return shadowAlpha;
	}
	public void setShadowAlpha(Double shadowAlpha) {
		this.shadowAlpha = shadowAlpha;
	}
	public Color getShadowColor() {
		return shadowColor;
	}
	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}
	public Boolean isShowBullet() {
		return showBullet;
	}
	public void setShowBullet(Boolean showBullet) {
		this.showBullet = showBullet;
	}
	public String getTextAlign() {
		return textAlign;
	}
	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}
	public Double getVerticalPadding() {
		return verticalPadding;
	}
	public void setVerticalPadding(Double verticalPadding) {
		this.verticalPadding = verticalPadding;
	}

}
