package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PositionHorizontal;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StackType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ValueAxesSettings {

	/**
	* Specifies whether number for gridCount is specified automatically, according to the axis size.
	* Default Value: TRUE
	*/
	private Boolean autoGridCount;
	/**
	* Axis opacity.
	* Default Value: 0
	*/
	private Double axisAlpha;
	/**
	* Axis color.
	* Default Value: 
	*/
	private Color axisColor;
	/**
	* Thickness of the axis.
	* Default Value: 
	*/
	private Double axisThickness;
	/**
	* Label color.
	* Default Value: 
	*/
	private Color color;
	/**
	* Length of a dash. By default, the grid line is not dashed.
	* Default Value: 
	*/
	private Integer dashLength;
	/**
	* Fill opacity. Every second space between grid lines can be filled with color.
	* Default Value: 
	*/
	private Double fillAlpha;
	/**
	* Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: 
	*/
	private Color fillColor;
	/**
	* Opacity of grid lines.
	* Default Value: 
	*/
	private Double gridAlpha;
	/**
	* Color of grid lines.
	* Default Value: 
	*/
	private Color gridColor;
	/**
	* Approximate number of grid lines. autoGridCount should be set to false, otherwise this property will be ignored.
	* Default Value: 
	*/
	private Double gridCount;
	/**
	* Thickness of grid lines.
	* Default Value: 
	*/
	private Double gridThickness;
	/**
	* Specifies whether guide values should be included when calculating min and max of the axis.
	* Default Value: 
	*/
	private Boolean includeGuidesInMinMax;
	/**
	* If true, the axis will include hidden graphs when calculating min and max values.
	* Default Value: 
	*/
	private Boolean includeHidden;
	/**
	* Specifies whether values should be placed inside or outside plot area. In case you set this to false, you'll have to adjust marginLeft or marginRight in [[PanelsSettings]] in order labels to be visible.
	* Default Value: TRUE
	*/
	private Boolean inside;
	/**
	* Specifies whether values on axis can only be integers or both integers and Doubles.
	* Default Value: 
	*/
	private Boolean integersOnly;
	/**
	* Frequency at which labels should be placed.
	* Default Value: 
	*/
	private Double labelFrequency;
	/**
	* Specifies whether value labels are displayed.
	* Default Value: 
	*/
	private Boolean labelsEnabled;
	/**
	* Set to true if value axis is logarithmic, false otherwise.
	* Default Value: 
	*/
	private Boolean logarithmic;
	/**
	* If set value axis scale (min and max numbers) will be multiplied by it. I.e. if set to 1.2 the scope of values will increase by 20%.
	* Default Value: 
	*/
	private Double minMaxMultiplier;
	/**
	* The distance of the axis to the plot area, in pixels. Useful if you have more then one axis on the same side.
	* Default Value: 
	*/
	private Double offset;
	/**
	* Position of the value axis. Possible values are left and right.
	* Default Value: 
	*/
	private PositionHorizontal position;
	/**
	* Set to true if value axis is reversed (smaller values on top), false otherwise.
	* Default Value: 
	*/
	private Boolean reversed;
	/**
	* Specifies if first label of value axis should be displayed.
	* Default Value: 
	*/
	private Boolean showFirstLabel;
	/**
	* Specifies if last label of value axis should be displayed.
	* Default Value: 
	*/
	private Boolean showLastLabel;
	/**
	* Stacking mode of the axis. Possible values are: none, regular, 100%, 3d.
	* Default Value: 
	*/
	private StackType stackType;
	/**
	* Tick length.
	* Default Value: 0
	*/
	private Double tickLength;
	/**
	* Unit which will be added to the value label.
	* Default Value: 
	*/
	private String unit;
	/**
	* Position of the unit. Possible values are left or right.
	* Default Value: 
	*/
	private PositionHorizontal unitPosition;
	
	/*
	 * Constructors
	 */
	public ValueAxesSettings() {
		dashLength = 5;
	}
	
	/*
	 * Getters and Setters
	 */
	public Boolean isAutoGridCount() {
		return autoGridCount;
	}
	public void setAutoGridCount(Boolean autoGridCount) {
		this.autoGridCount = autoGridCount;
	}
	public Double getAxisAlpha() {
		return axisAlpha;
	}
	public void setAxisAlpha(Double axisAlpha) {
		this.axisAlpha = axisAlpha;
	}
	public Color getAxisColor() {
		return axisColor;
	}
	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
	}
	public Double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(Double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Integer getDashLength() {
		return dashLength;
	}
	public void setDashLength(Integer dashLength) {
		this.dashLength = dashLength;
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
	public Double getGridAlpha() {
		return gridAlpha;
	}
	public void setGridAlpha(Double gridAlpha) {
		this.gridAlpha = gridAlpha;
	}
	public Color getGridColor() {
		return gridColor;
	}
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}
	public Double getGridCount() {
		return gridCount;
	}
	public void setGridCount(Double gridCount) {
		this.gridCount = gridCount;
	}
	public Double getGridThickness() {
		return gridThickness;
	}
	public void setGridThickness(Double gridThickness) {
		this.gridThickness = gridThickness;
	}
	public Boolean isIncludeGuidesInMinMax() {
		return includeGuidesInMinMax;
	}
	public void setIncludeGuidesInMinMax(Boolean includeGuidesInMinMax) {
		this.includeGuidesInMinMax = includeGuidesInMinMax;
	}
	public Boolean isIncludeHidden() {
		return includeHidden;
	}
	public void setIncludeHidden(Boolean includeHidden) {
		this.includeHidden = includeHidden;
	}
	public Boolean isInside() {
		return inside;
	}
	public void setInside(Boolean inside) {
		this.inside = inside;
	}
	public Boolean isIntegersOnly() {
		return integersOnly;
	}
	public void setIntegersOnly(Boolean integersOnly) {
		this.integersOnly = integersOnly;
	}
	public Double getLabelFrequency() {
		return labelFrequency;
	}
	public void setLabelFrequency(Double labelFrequency) {
		this.labelFrequency = labelFrequency;
	}
	public Boolean isLabelsEnabled() {
		return labelsEnabled;
	}
	public void setLabelsEnabled(Boolean labelsEnabled) {
		this.labelsEnabled = labelsEnabled;
	}
	public Boolean isLogarithmic() {
		return logarithmic;
	}
	public void setLogarithmic(Boolean logarithmic) {
		this.logarithmic = logarithmic;
	}
	public Double getMinMaxMultiplier() {
		return minMaxMultiplier;
	}
	public void setMinMaxMultiplier(Double minMaxMultiplier) {
		this.minMaxMultiplier = minMaxMultiplier;
	}
	public Double getOffset() {
		return offset;
	}
	public void setOffset(Double offset) {
		this.offset = offset;
	}
	public PositionHorizontal getPosition() {
		return position;
	}
	public void setPosition(PositionHorizontal position) {
		this.position = position;
	}
	public Boolean isReversed() {
		return reversed;
	}
	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}
	public Boolean isShowFirstLabel() {
		return showFirstLabel;
	}
	public void setShowFirstLabel(Boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}
	public Boolean isShowLastLabel() {
		return showLastLabel;
	}
	public void setShowLastLabel(Boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}
	public StackType getStackType() {
		return stackType;
	}
	public void setStackType(StackType stackType) {
		this.stackType = stackType;
	}
	public Double getTickLength() {
		return tickLength;
	}
	public void setTickLength(Double tickLength) {
		this.tickLength = tickLength;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public PositionHorizontal getUnitPosition() {
		return unitPosition;
	}
	public void setUnitPosition(PositionHorizontal unitPosition) {
		this.unitPosition = unitPosition;
	}

}
