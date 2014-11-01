package com.ar.marketanalyzer.plotting.amstockcharts.data;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GraphDataItem {

	/**
	* Opacity of the data item.
	* Default Value: 
	*/
	 private double alpha;
	/**
	* Bullet type.
	* Default Value: 
	*/
	 private String bullet;
	/**
	* Bullet size.
	* Default Value: 
	*/
	 private double bulletSize;
	/**
	* Category value.
	* Default Value: 
	*/
	 private String category;
	/**
	* Color of the data item.
	* Default Value: 
	*/
	 private Color color;
	/**
	* Custom bullet (path to file name).
	* Default Value: 
	*/
	 private String customBullet;
	/**
	* Original object from data provider.
	* Default Value: 
	*/
	 private Object dataContext;
	/**
	* Description.
	* Default Value: 
	*/
	 private String description;
	/**
	* Array of colors of the data item, used by column and candlestick chart only.
	* Default Value: 
	*/
	 private List<Color> fillColors;
	/**
	* Object which holds percents when recalculateToPercents is set to true.
	* Default Value: 
	*/
	 private Object percents;
	/**
	* SerialDataItem of this graphDataItem
	* Default Value: 
	*/
	 private SerialDataItem serialDataItem;
	/**
	* url
	* Default Value: 
	*/
	 private String url;
	/**
	* Object which holds values of the data item (value, open, close, low, high).
	* Default Value: 
	*/
	 private Object values;
	/**
	* x coordinate of the data item.
	* Default Value: 
	*/
	 private double x;
	/**
	* y coordinate of the data item.
	* Default Value: 
	*/
	 private double y;
	 
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	public String getBullet() {
		return bullet;
	}
	public void setBullet(String bullet) {
		this.bullet = bullet;
	}
	public double getBulletSize() {
		return bulletSize;
	}
	public void setBulletSize(double bulletSize) {
		this.bulletSize = bulletSize;
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
	public String getCustomBullet() {
		return customBullet;
	}
	public void setCustomBullet(String customBullet) {
		this.customBullet = customBullet;
	}
	public Object getDataContext() {
		return dataContext;
	}
	public void setDataContext(Object dataContext) {
		this.dataContext = dataContext;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Color> getFillColors() {
		return fillColors;
	}
	public void setFillColors(List<Color> fillColors) {
		this.fillColors = fillColors;
	}
	public Object getPercents() {
		return percents;
	}
	public void setPercents(Object percents) {
		this.percents = percents;
	}
	public SerialDataItem getSerialDataItem() {
		return serialDataItem;
	}
	public void setSerialDataItem(SerialDataItem serialDataItem) {
		this.serialDataItem = serialDataItem;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Object getValues() {
		return values;
	}
	public void setValues(Object values) {
		this.values = values;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
