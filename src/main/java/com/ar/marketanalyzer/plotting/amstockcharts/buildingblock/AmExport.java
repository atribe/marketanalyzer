package com.ar.marketanalyzer.plotting.amstockcharts.buildingblock;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AmExport {

	/**
	* Bottom position of export button. You might need to set top to undefined for this to work.
	* Default Value: 
	*/
	 private double bottom;
	/**
	* Opacity of a button.
	* Default Value: 0.75
	*/
	 private double buttonAlpha;
	/**
	* Name of export button image.
	* Default Value: export.png
	*/
	 private String buttonIcon;
	/**
	* Roll-over color of button background.
	* Default Value: #EFEFEF
	*/
	 private Color buttonRollOverColor;
	/**
	* Text, displayed in a tool-tip.
	* Default Value: ""Save chart as an image""
	*/
	 private String buttonTitle;
	/**
	* Specifies if export of JPG should be enabled.
	* Default Value: FALSE
	*/
	 private boolean exportJPG;
	/**
	* Specifies if export of PDF should be enabled.
	* Default Value: FALSE
	*/
	 private boolean exportPDF;
	/**
	* Specifies if export of PNG should be enabled.
	* Default Value: TRUE
	*/
	 private boolean exportPNG;
	/**
	* Specifies if export of SVG should be enabled.
	* Default Value: FALSE
	*/
	 private boolean exportSVG;
	/**
	* Background color of a saved image
	* Default Value: #FFFFFF
	*/
	 private Color imageBackgroundColor;
	/**
	* File name of a saved image.
	* Default Value: amChart
	*/
	 private String imageFileName;
	/**
	* Left position of export button. You might need to set right to undefined for this to work.
	* Default Value: 
	*/
	 private double left;
	/**
	* Right position of export button. You might need to set left to undefined for this to work.
	* Default Value: 0
	*/
	 private double right;
	/**
	* Text roll-over color.
	* Default Value: #CC0000
	*/
	 private Color textRollOverColor;
	/**
	* Top position of export button. You might need to set bottom to undefined for this to work.
	* Default Value: 0
	*/
	 private double top;
	/**
	* advanced configration object AmChart.exportConfig
	* Default Value: exportConfig
	*/
	 private Object userCFG;
	public double getBottom() {
		return bottom;
	}
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}
	public double getButtonAlpha() {
		return buttonAlpha;
	}
	public void setButtonAlpha(double buttonAlpha) {
		this.buttonAlpha = buttonAlpha;
	}
	public String getButtonIcon() {
		return buttonIcon;
	}
	public void setButtonIcon(String buttonIcon) {
		this.buttonIcon = buttonIcon;
	}
	public Color getButtonRollOverColor() {
		return buttonRollOverColor;
	}
	public void setButtonRollOverColor(Color buttonRollOverColor) {
		this.buttonRollOverColor = buttonRollOverColor;
	}
	public String getButtonTitle() {
		return buttonTitle;
	}
	public void setButtonTitle(String buttonTitle) {
		this.buttonTitle = buttonTitle;
	}
	public boolean isExportJPG() {
		return exportJPG;
	}
	public void setExportJPG(boolean exportJPG) {
		this.exportJPG = exportJPG;
	}
	public boolean isExportPDF() {
		return exportPDF;
	}
	public void setExportPDF(boolean exportPDF) {
		this.exportPDF = exportPDF;
	}
	public boolean isExportPNG() {
		return exportPNG;
	}
	public void setExportPNG(boolean exportPNG) {
		this.exportPNG = exportPNG;
	}
	public boolean isExportSVG() {
		return exportSVG;
	}
	public void setExportSVG(boolean exportSVG) {
		this.exportSVG = exportSVG;
	}
	public Color getImageBackgroundColor() {
		return imageBackgroundColor;
	}
	public void setImageBackgroundColor(Color imageBackgroundColor) {
		this.imageBackgroundColor = imageBackgroundColor;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public Color getTextRollOverColor() {
		return textRollOverColor;
	}
	public void setTextRollOverColor(Color textRollOverColor) {
		this.textRollOverColor = textRollOverColor;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public Object getUserCFG() {
		return userCFG;
	}
	public void setUserCFG(Object userCFG) {
		this.userCFG = userCFG;
	}

}
