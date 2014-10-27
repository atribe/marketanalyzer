package com.ar.marketanalyzer.plotting.amcharts.buildingblock;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Color implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5631193035688975546L;
	public static final Color BLACK = create("000000");
    public static final Color WHITE = create("FFFFFF");

    private String colorHexVal;

    public Color() {};
    public Color(String colorHexVal) {
    	this.colorHexVal = colorHexVal;
    }

    public static Color create(String colorHexVal) {
        return new Color(colorHexVal);
    }

    // Append # to color values as we have hex values
    @Override
    public String toString() {
        return "#" + this.colorHexVal;
    }
	public String getColorHexVal() {
		return colorHexVal;
	}
	public void setColorHexVal(String colorHexVal) {
		this.colorHexVal = colorHexVal;
	}
}