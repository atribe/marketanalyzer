package com.ar.marketanalyzer.plotting.amcharts.buildingblock;

import java.io.Serializable;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amcharts.serializers.JacksonObjectToListSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JacksonObjectToListSerializer.class)
public class Title implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7761646912579257237L;
	private Double alpha;
    private Boolean bold;
    private Color color;
    private String id;
    private Double size;
    private String text;

    /**
     * Opacity of a title.
     **/
    public Double getAlpha() {
        return alpha;
    }
    public Title setAlpha(double alpha) {
        this.alpha = alpha;
        return this;
    }

    /**
     * Specifies if title should be bold or not.
     **/
    public Boolean getBold() {
        return bold;
    }
    public Title setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    /**
     * Text color of a title.
     **/
    public Color getColor() {
        return color;
    }
    public Title setColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     * Unique id of a Title. You don't need to set it, unless you want to.
     **/
    public String getId() {
        return id;
    }
    public Title setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Text size of a title.
     **/
    public Double getSize() {
        return size;
    }
    public Title setSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * Text of a title.
     **/
    public String getText() {
        return text;
    }
    public Title setText(String text) {
        this.text = text;
        return this;
    }


}