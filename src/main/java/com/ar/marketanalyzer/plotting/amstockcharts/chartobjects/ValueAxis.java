package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;

public class ValueAxis extends AxisBase{

	/**
	* Radar chart only. Specifies distance from axis to the axis title (category)
	* Default Value: 10
	*/
	private double axisTitleOffset;
	/**
	* Read-only. Coordinate of the base value.
	* Default Value: 
	*/
	private double baseCoord;
	/**
	* Specifies base value of the axis.
	* Default Value: 0
	*/
	private double baseValue;
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
	private boolean includeGuidesInMinMax;
	/**
	* If true, the axis will include hidden graphs when calculating min and max values.
	* Default Value: FALSE
	*/
	private boolean includeHidden;
	/**
	* Specifies whether values on axis can only be integers or both integers and doubles.
	* You can use this function to format Value axis labels. This function is called and these parameters are passed: 
	* labelFunction(value, valueText, valueAxis);
	* Where value is numeric value, valueText is formatted string and valueAxis is a reference to valueAxis object. 
	* Your function should return string.
	* Default Value: FALSE
	*/
	private boolean integersOnly;
	/**
	* Specifies if this value axis' scale should be logarithmic.
	* Default Value: FALSE
	*/
	private boolean logarithmic;
	/**
	* Read-only. Maximum value of the axis.
	* Default Value: 
	*/
	private double max;
	/**
	* If you don't want max value to be calculated by the chart, set it using this property. This value might still be adjusted so that it would be possible to draw grid at rounded intervals.
	* Default Value: 
	*/
	private double maximum;
	/**
	* Read-only. Minimum value of the axis.
	* Default Value: 
	*/
	private double min;
	/**
	* If you don't want min value to be calculated by the chart, set it using this property. This value might still be adjusted so that it would be possible to draw grid at rounded intervals.
	* Default Value: 
	*/
	private double minimum;
	/**
	* If set value axis scale (min and max numbers) will be multiplied by it. I.e. if set to 1.2 the scope of values will increase by 20%.
	* Default Value: 1
	*/
	private double minMaxMultiplier;
	/**
	* Possible values are: ""top"", ""bottom"", ""left"", ""right"". If axis is vertical, default position is ""left"". If axis is horizontal, default position is ""bottom"".
	* Default Value: left
	*/
	private String position;
	/**
	* Precision (number of decimals) of values.
	* Default Value: 
	*/
	private double precision;
	/**
	* Radar chart only. Specifies if categories (axes' titles) should be displayed near axes)
	* Default Value: TRUE
	*/
	private boolean radarCategoriesEnabled;
	/**
	* Specifies if graphs's values should be recalculated to percents.
	* Default Value: FALSE
	*/
	private boolean recalculateToPercents;
	/**
	* Specifies if value axis should be reversed (smaller values on top).
	* Default Value: FALSE
	*/
	private boolean reversed;
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
	private double step;
	/**
	* In case you synchronize one value axis with another, you need to set the synchronization multiplier. Use synchronizeWithAxis method to set with which axis it should be synced.
	* Default Value: 
	*/
	private double synchronizationMultiplier;
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
	private double treatZeroAs;
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
	private boolean usePrefixes;
	/**
	* If true, values will always be formatted using scientific notation (5e+8, 5e-8...) Otherwise only values bigger then 1e+21 and smaller then 1e-7 will be displayed in scientific notation.
	* Default Value: FALSE
	*/
	private boolean useScientificNotation;

	public double getAxisTitleOffset() {
		return axisTitleOffset;
	}
	public void setAxisTitleOffset(double axisTitleOffset) {
		this.axisTitleOffset = axisTitleOffset;
	}
	public double getBaseCoord() {
		return baseCoord;
	}
	public void setBaseCoord(double baseCoord) {
		this.baseCoord = baseCoord;
	}
	public double getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(double baseValue) {
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
	public boolean isIntegersOnly() {
		return integersOnly;
	}
	public void setIntegersOnly(boolean integersOnly) {
		this.integersOnly = integersOnly;
	}
	public boolean isLogarithmic() {
		return logarithmic;
	}
	public void setLogarithmic(boolean logarithmic) {
		this.logarithmic = logarithmic;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMaximum() {
		return maximum;
	}
	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMinimum() {
		return minimum;
	}
	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}
	public double getMinMaxMultiplier() {
		return minMaxMultiplier;
	}
	public void setMinMaxMultiplier(double minMaxMultiplier) {
		this.minMaxMultiplier = minMaxMultiplier;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getPrecision() {
		return precision;
	}
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	public boolean isRadarCategoriesEnabled() {
		return radarCategoriesEnabled;
	}
	public void setRadarCategoriesEnabled(boolean radarCategoriesEnabled) {
		this.radarCategoriesEnabled = radarCategoriesEnabled;
	}
	public boolean isRecalculateToPercents() {
		return recalculateToPercents;
	}
	public void setRecalculateToPercents(boolean recalculateToPercents) {
		this.recalculateToPercents = recalculateToPercents;
	}
	public boolean isReversed() {
		return reversed;
	}
	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	public String getStackType() {
		return stackType;
	}
	public void setStackType(String stackType) {
		this.stackType = stackType;
	}
	public double getStep() {
		return step;
	}
	public void setStep(double step) {
		this.step = step;
	}
	public double getSynchronizationMultiplier() {
		return synchronizationMultiplier;
	}
	public void setSynchronizationMultiplier(double synchronizationMultiplier) {
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
	public double getTreatZeroAs() {
		return treatZeroAs;
	}
	public void setTreatZeroAs(double treatZeroAs) {
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
	public boolean isUsePrefixes() {
		return usePrefixes;
	}
	public void setUsePrefixes(boolean usePrefixes) {
		this.usePrefixes = usePrefixes;
	}
	public boolean isUseScientificNotation() {
		return useScientificNotation;
	}
	public void setUseScientificNotation(boolean useScientificNotation) {
		this.useScientificNotation = useScientificNotation;
	}

}
