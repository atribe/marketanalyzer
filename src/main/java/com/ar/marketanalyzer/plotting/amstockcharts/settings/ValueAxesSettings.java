package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.PositionHorizontal;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StackType;

public class ValueAxesSettings {

	/**
	* Specifies whether number for gridCount is specified automatically, according to the axis size.
	* Default Value: TRUE
	*/
	private boolean autoGridCount;
	/**
	* Axis opacity.
	* Default Value: 0
	*/
	private double axisAlpha;
	/**
	* Axis color.
	* Default Value: 
	*/
	private Color axisColor;
	/**
	* Thickness of the axis.
	* Default Value: 
	*/
	private double axisThickness;
	/**
	* Label color.
	* Default Value: 
	*/
	private Color color;
	/**
	* Length of a dash. By default, the grid line is not dashed.
	* Default Value: 
	*/
	private double dashLength;
	/**
	* Fill opacity. Every second space between grid lines can be filled with color.
	* Default Value: 
	*/
	private double fillAlpha;
	/**
	* Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	* Default Value: 
	*/
	private Color fillColor;
	/**
	* Opacity of grid lines.
	* Default Value: 
	*/
	private double gridAlpha;
	/**
	* Color of grid lines.
	* Default Value: 
	*/
	private Color gridColor;
	/**
	* Approximate number of grid lines. autoGridCount should be set to false, otherwise this property will be ignored.
	* Default Value: 
	*/
	private double gridCount;
	/**
	* Thickness of grid lines.
	* Default Value: 
	*/
	private double gridThickness;
	/**
	* Specifies whether guide values should be included when calculating min and max of the axis.
	* Default Value: 
	*/
	private boolean includeGuidesInMinMax;
	/**
	* If true, the axis will include hidden graphs when calculating min and max values.
	* Default Value: 
	*/
	private boolean includeHidden;
	/**
	* Specifies whether values should be placed inside or outside plot area. In case you set this to false, you'll have to adjust marginLeft or marginRight in [[PanelsSettings]] in order labels to be visible.
	* Default Value: TRUE
	*/
	private boolean inside;
	/**
	* Specifies whether values on axis can only be integers or both integers and doubles.
	* Default Value: 
	*/
	private boolean integersOnly;
	/**
	* Frequency at which labels should be placed.
	* Default Value: 
	*/
	private double labelFrequency;
	/**
	* Specifies whether value labels are displayed.
	* Default Value: 
	*/
	private boolean labelsEnabled;
	/**
	* Set to true if value axis is logarithmic, false otherwise.
	* Default Value: 
	*/
	private boolean logarithmic;
	/**
	* If set value axis scale (min and max numbers) will be multiplied by it. I.e. if set to 1.2 the scope of values will increase by 20%.
	* Default Value: 
	*/
	private double minMaxMultiplier;
	/**
	* The distance of the axis to the plot area, in pixels. Useful if you have more then one axis on the same side.
	* Default Value: 
	*/
	private double offset;
	/**
	* Position of the value axis. Possible values are left and right.
	* Default Value: 
	*/
	private PositionHorizontal position;
	/**
	* Set to true if value axis is reversed (smaller values on top), false otherwise.
	* Default Value: 
	*/
	private boolean reversed;
	/**
	* Specifies if first label of value axis should be displayed.
	* Default Value: 
	*/
	private boolean showFirstLabel;
	/**
	* Specifies if last label of value axis should be displayed.
	* Default Value: 
	*/
	private boolean showLastLabel;
	/**
	* Stacking mode of the axis. Possible values are: none, regular, 100%, 3d.
	* Default Value: 
	*/
	private StackType stackType;
	/**
	* Tick length.
	* Default Value: 0
	*/
	private double tickLength;
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
	public boolean isAutoGridCount() {
		return autoGridCount;
	}
	public void setAutoGridCount(boolean autoGridCount) {
		this.autoGridCount = autoGridCount;
	}
	public double getAxisAlpha() {
		return axisAlpha;
	}
	public void setAxisAlpha(double axisAlpha) {
		this.axisAlpha = axisAlpha;
	}
	public Color getAxisColor() {
		return axisColor;
	}
	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
	}
	public double getAxisThickness() {
		return axisThickness;
	}
	public void setAxisThickness(double axisThickness) {
		this.axisThickness = axisThickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getDashLength() {
		return dashLength;
	}
	public void setDashLength(double dashLength) {
		this.dashLength = dashLength;
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
	public double getGridAlpha() {
		return gridAlpha;
	}
	public void setGridAlpha(double gridAlpha) {
		this.gridAlpha = gridAlpha;
	}
	public Color getGridColor() {
		return gridColor;
	}
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}
	public double getGridCount() {
		return gridCount;
	}
	public void setGridCount(double gridCount) {
		this.gridCount = gridCount;
	}
	public double getGridThickness() {
		return gridThickness;
	}
	public void setGridThickness(double gridThickness) {
		this.gridThickness = gridThickness;
	}
	public boolean isIncludeGuidesInMinMax() {
		return includeGuidesInMinMax;
	}
	public void setIncludeGuidesInMinMax(boolean includeGuidesInMinMax) {
		this.includeGuidesInMinMax = includeGuidesInMinMax;
	}
	public boolean isIncludeHidden() {
		return includeHidden;
	}
	public void setIncludeHidden(boolean includeHidden) {
		this.includeHidden = includeHidden;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public boolean isIntegersOnly() {
		return integersOnly;
	}
	public void setIntegersOnly(boolean integersOnly) {
		this.integersOnly = integersOnly;
	}
	public double getLabelFrequency() {
		return labelFrequency;
	}
	public void setLabelFrequency(double labelFrequency) {
		this.labelFrequency = labelFrequency;
	}
	public boolean isLabelsEnabled() {
		return labelsEnabled;
	}
	public void setLabelsEnabled(boolean labelsEnabled) {
		this.labelsEnabled = labelsEnabled;
	}
	public boolean isLogarithmic() {
		return logarithmic;
	}
	public void setLogarithmic(boolean logarithmic) {
		this.logarithmic = logarithmic;
	}
	public double getMinMaxMultiplier() {
		return minMaxMultiplier;
	}
	public void setMinMaxMultiplier(double minMaxMultiplier) {
		this.minMaxMultiplier = minMaxMultiplier;
	}
	public double getOffset() {
		return offset;
	}
	public void setOffset(double offset) {
		this.offset = offset;
	}
	public PositionHorizontal getPosition() {
		return position;
	}
	public void setPosition(PositionHorizontal position) {
		this.position = position;
	}
	public boolean isReversed() {
		return reversed;
	}
	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	public boolean isShowFirstLabel() {
		return showFirstLabel;
	}
	public void setShowFirstLabel(boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}
	public boolean isShowLastLabel() {
		return showLastLabel;
	}
	public void setShowLastLabel(boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}
	public StackType getStackType() {
		return stackType;
	}
	public void setStackType(StackType stackType) {
		this.stackType = stackType;
	}
	public double getTickLength() {
		return tickLength;
	}
	public void setTickLength(double tickLength) {
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
