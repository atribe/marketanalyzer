package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Position;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ValueAxis extends AxisBase{

	/**
	* Radar chart only. Specifies distance from axis to the axis title (category)
	* Default Value: 10
	*/
	private Double axisTitleOffset;
	/**
	* Read-only. Coordinate of the base value.
	* Default Value: 
	*/
	private Double baseCoord;
	/**
	* Specifies base value of the axis.
	* Default Value: 0
	*/
	private Double baseValue;
	/**
	* If your values represents time units, and you want value axis labels to be formatted as duration, you have to set the duration unit. Possible values are: ""ss"", ""mm"", ""hh"" and ""DD"".
	* Default Value: 
	*/
	private String duration;
	/**
	* If duration property is set, you can specify what string should be displayed next to day, hour, minute and second.
	* Default Value: {DD:'d. ', hh:':', mm:':',ss:''}
	*/
	private Object durationUnits;
	/**
	* Radar chart only. Possible values are: ""polygons"" and ""circles"". Set ""circles"" for polar charts.
	* Default Value: polygons
	*/
	private String gridType;
	/**
	* Unique id of value axis. It is not required to set it, unless you need to tell the graph which exact value axis it should use.
	* Default Value: 
	*/
	private String id;
	/**
	* Specifies whether guide values should be included when calculating min and max of the axis.
	* Default Value: FALSE
	*/
	private Boolean includeGuidesInMinMax;
	/**
	* If true, the axis will include hidden graphs when calculating min and max values.
	* Default Value: FALSE
	*/
	private Boolean includeHidden;
	/**
	* Specifies whether values on axis can only be integers or both integers and Doubles.
	* You can use this function to format Value axis labels. This function is called and these parameters are passed: 
	* labelFunction(value, valueText, valueAxis);
	* Where value is numeric value, valueText is formatted string and valueAxis is a reference to valueAxis object. 
	* Your function should return string.
	* Default Value: FALSE
	*/
	private Boolean integersOnly;
	/**
	* Specifies if this value axis' scale should be logarithmic.
	* Default Value: FALSE
	*/
	private Boolean logarithmic;
	/**
	* Read-only. Maximum value of the axis.
	* Default Value: 
	*/
	private Double max;
	/**
	* If you don't want max value to be calculated by the chart, set it using this property. This value might still be adjusted so that it would be possible to draw grid at rounded intervals.
	* Default Value: 
	*/
	private Double maximum;
	/**
	* Read-only. Minimum value of the axis.
	* Default Value: 
	*/
	private Double min;
	/**
	* If you don't want min value to be calculated by the chart, set it using this property. This value might still be adjusted so that it would be possible to draw grid at rounded intervals.
	* Default Value: 
	*/
	private Double minimum;
	/**
	* If set value axis scale (min and max numbers) will be multiplied by it. I.e. if set to 1.2 the scope of values will increase by 20%.
	* Default Value: 1
	*/
	private Double minMaxMultiplier;
	/**
	* Possible values are: ""top"", ""bottom"", ""left"", ""right"". If axis is vertical, default position is ""left"". If axis is horizontal, default position is ""bottom"".
	* Default Value: left
	*/
	private Position position;
	/**
	* Precision (number of decimals) of values.
	* Default Value: 
	*/
	private Double precision;
	/**
	* Radar chart only. Specifies if categories (axes' titles) should be displayed near axes)
	* Default Value: TRUE
	*/
	private Boolean radarCategoriesEnabled;
	/**
	* Specifies if graphs's values should be recalculated to percents.
	* Default Value: FALSE
	*/
	private Boolean recalculateToPercents;
	/**
	* Specifies if value axis should be reversed (smaller values on top).
	* Default Value: FALSE
	*/
	private Boolean reversed;
	/**
	* Stacking mode of the axis. Possible values are: ""none"", ""regular"", ""100%"", ""3d"".
	Note, only graphs of one type will be stacked.
	* Default Value: none
	*/
	private String stackType;
	/**
	* Read-only. Value difference between two grid lines.
	* Default Value: 
	*/
	private Double step;
	/**
	* In case you synchronize one value axis with another, you need to set the synchronization multiplier. Use synchronizeWithAxis method to set with which axis it should be synced.
	* Default Value: 
	*/
	private Double synchronizationMultiplier;
	/**
	* One value axis can be synchronized with another value axis. You can use both reference to your axis or id of the axis here. You should set synchronizationMultiplyer in order for this to work.
	* Default Value: 
	*/
	private ValueAxis synchronizeWith;
	/**
	* If this value axis is stacked and has columns, setting valueAxis.totalText = ""[[total]]"" will make it to display total value above the most-top column.
	* Default Value: 
	*/
	private String totalText;
	/**
	* Color of total text.
	* Default Value: 
	*/
	private Color totalTextColor;
	/**
	* This allows you to have logarithmic value axis and have zero values in the data. You must set it to >0 value in order to work.
	* Default Value: 0
	*/
	private Double treatZeroAs;
	/**
	* Unit which will be added to the value label.
	* Default Value: 
	*/
	private String unit;
	/**
	* Position of the unit. Possible values are ""left"" and ""right"".
	* Default Value: right
	*/
	private String unitPosition;
	/**
	* If true, prefixes will be used for big and small numbers. You can set arrays of prefixes directly to the chart object via prefixesOfSmallNumbers and prefixesOfBigNumbers.
	* Default Value: FALSE
	*/
	private Boolean usePrefixes;
	/**
	* If true, values will always be formatted using scientific notation (5e+8, 5e-8...) Otherwise only values bigger then 1e+21 and smaller then 1e-7 will be displayed in scientific notation.
	* Default Value: FALSE
	*/
	private Boolean useScientificNotation;
	public ValueAxis() { 
		dashLength = 5.0;
	}
	
	public ValueAxis(String id) {
		this();

		this.id = id;
	}
	public Double getAxisTitleOffset() {
		return axisTitleOffset;
	}
	public void setAxisTitleOffset(Double axisTitleOffset) {
		this.axisTitleOffset = axisTitleOffset;
	}
	public Double getBaseCoord() {
		return baseCoord;
	}
	public void setBaseCoord(Double baseCoord) {
		this.baseCoord = baseCoord;
	}
	public Double getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Double baseValue) {
		this.baseValue = baseValue;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Object getDurationUnits() {
		return durationUnits;
	}
	public void setDurationUnits(Object durationUnits) {
		this.durationUnits = durationUnits;
	}
	public String getGridType() {
		return gridType;
	}
	public void setGridType(String gridType) {
		this.gridType = gridType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Boolean isIntegersOnly() {
		return integersOnly;
	}
	public void setIntegersOnly(Boolean integersOnly) {
		this.integersOnly = integersOnly;
	}
	public Boolean isLogarithmic() {
		return logarithmic;
	}
	public void setLogarithmic(Boolean logarithmic) {
		this.logarithmic = logarithmic;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMaximum() {
		return maximum;
	}
	public void setMaximum(Double maximum) {
		this.maximum = maximum;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMinimum() {
		return minimum;
	}
	public void setMinimum(Double minimum) {
		this.minimum = minimum;
	}
	public Double getMinMaxMultiplier() {
		return minMaxMultiplier;
	}
	public void setMinMaxMultiplier(Double minMaxMultiplier) {
		this.minMaxMultiplier = minMaxMultiplier;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Double getPrecision() {
		return precision;
	}
	public void setPrecision(Double precision) {
		this.precision = precision;
	}
	public Boolean isRadarCategoriesEnabled() {
		return radarCategoriesEnabled;
	}
	public void setRadarCategoriesEnabled(Boolean radarCategoriesEnabled) {
		this.radarCategoriesEnabled = radarCategoriesEnabled;
	}
	public Boolean isRecalculateToPercents() {
		return recalculateToPercents;
	}
	public void setRecalculateToPercents(Boolean recalculateToPercents) {
		this.recalculateToPercents = recalculateToPercents;
	}
	public Boolean isReversed() {
		return reversed;
	}
	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}
	public String getStackType() {
		return stackType;
	}
	public void setStackType(String stackType) {
		this.stackType = stackType;
	}
	public Double getStep() {
		return step;
	}
	public void setStep(Double step) {
		this.step = step;
	}
	public Double getSynchronizationMultiplier() {
		return synchronizationMultiplier;
	}
	public void setSynchronizationMultiplier(Double synchronizationMultiplier) {
		this.synchronizationMultiplier = synchronizationMultiplier;
	}
	public ValueAxis getSynchronizeWith() {
		return synchronizeWith;
	}
	public void setSynchronizeWith(ValueAxis synchronizeWith) {
		this.synchronizeWith = synchronizeWith;
	}
	public String getTotalText() {
		return totalText;
	}
	public void setTotalText(String totalText) {
		this.totalText = totalText;
	}
	public Color getTotalTextColor() {
		return totalTextColor;
	}
	public void setTotalTextColor(Color totalTextColor) {
		this.totalTextColor = totalTextColor;
	}
	public Double getTreatZeroAs() {
		return treatZeroAs;
	}
	public void setTreatZeroAs(Double treatZeroAs) {
		this.treatZeroAs = treatZeroAs;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitPosition() {
		return unitPosition;
	}
	public void setUnitPosition(String unitPosition) {
		this.unitPosition = unitPosition;
	}
	public Boolean isUsePrefixes() {
		return usePrefixes;
	}
	public void setUsePrefixes(Boolean usePrefixes) {
		this.usePrefixes = usePrefixes;
	}
	public Boolean isUseScientificNotation() {
		return useScientificNotation;
	}
	public void setUseScientificNotation(Boolean useScientificNotation) {
		this.useScientificNotation = useScientificNotation;
	}

}
