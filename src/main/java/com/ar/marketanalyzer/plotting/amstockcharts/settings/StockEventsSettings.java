package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StockEventType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class StockEventsSettings {

	/**
	* Opacity of bullet background.
	* Default Value: 1
	*/
	private double backgroundAlpha;
	/**
	* Color of bullet background.
	* Default Value: #DADADA
	*/
	private Color backgroundColor;
	/**
	* Background color of a roll-over balloon.
	* Default Value: #CC0000
	*/
	private Color balloonColor;
	/**
	* Opacity of bullet border.
	* Default Value: 1
	*/
	private double borderAlpha;
	/**
	* Bullet border color.
	* Default Value: #888888
	*/
	private Color borderColor;
	/**
	* Roll-over background color.
	* Default Value: #CC0000
	*/
	private Color rollOverColor;
	/**
	* Allows placing event bullets at open/close/low/high values.
	* Default Value: close
	*/
	private String showAt;
	/**
	* Type of bullet. Possible values are: flag, sign, pin, triangleUp, triangleDown, triangleLeft, triangleRight, text, arrowUp, arrowDown.
	* Default Value: sign
	*/
	private StockEventType type;
	
	public double getBackgroundAlpha() {
		return backgroundAlpha;
	}
	public void setBackgroundAlpha(double backgroundAlpha) {
		this.backgroundAlpha = backgroundAlpha;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getBalloonColor() {
		return balloonColor;
	}
	public void setBalloonColor(Color balloonColor) {
		this.balloonColor = balloonColor;
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
	public Color getRollOverColor() {
		return rollOverColor;
	}
	public void setRollOverColor(Color rollOverColor) {
		this.rollOverColor = rollOverColor;
	}
	public String getShowAt() {
		return showAt;
	}
	public void setShowAt(String showAt) {
		this.showAt = showAt;
	}
	public StockEventType getType() {
		return type;
	}
	public void setType(StockEventType type) {
		this.type = type;
	}

}
