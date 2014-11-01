package com.ar.marketanalyzer.plotting.amstockcharts.enums;

import java.io.Serializable;

import com.ar.marketanalyzer.plotting.amcharts.serializers.ColorSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(using = ColorSerializer.class)
public class Color implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5631193035688975546L;
	
	/**
	 * #000000
	 */
	public static final Color BLACK = create("000000");
    /**
     * #FFFFFF
     */
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
