package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Alignment;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.MarkerType;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.SwitchType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class LegendSettings {

	/**
	* Alignment of legend entries. Possible values are: left, right and center.
	* Default Value: 
	*/
	private Alignment align;
	/**
	* Specifies if each legend entry should take the same space as the longest legend entry.
	* Default Value: FALSE
	*/
	private boolean equalWidths;
	/**
	* Horizontal space between legend item and left/right border.
	* Default Value: 
	*/
	private double horizontalGap;
	/**
	* The text which will be displayed in the legend. Tag [[title]] will be replaced with the title of the graph.
	* Default Value: 
	*/
	private String labelText;
	/**
	* Space below the last row of the legend, in pixels.
	* Default Value: 0
	*/
	private double marginBottom;
	/**
	* Space above the first row of the legend, in pixels.
	* Default Value: 0
	*/
	private double marginTop;
	/**
	* Opacity of marker border.
	* Default Value: 
	*/
	private double markerBorderAlpha;
	/**
	* Marker border color.
	* Default Value: 
	*/
	private Color markerBorderColor;
	/**
	* Thickness of the legend border.
	* Default Value: 
	*/
	private double markerBorderThickness;
	/**
	* The color of the disabled marker (when the graph is hidden).
	* Default Value: 
	*/
	private Color markerDisabledColor;
	/**
	* Space between legend marker and legend text, in pixels.
	* Default Value: 
	*/
	private double markerLabelGap;
	/**
	* Size of the legend marker (key).
	* Default Value: 
	*/
	private double markerSize;
	/**
	* Shape of the legend marker (key). Possible values are: square, circle, diamond, triangleUp, triangleDown, triangleLeft, triangleDown, bubble, none
	* Default Value: 
	*/
	private MarkerType markerType;
	/**
	* Specifies whether legend entries should be placed in reversed order.
	* Default Value: 
	*/
	private boolean reversedOrder;
	/**
	* Legend item text color on roll-over.
	* Default Value: 
	*/
	private Color rollOverColor;
	/**
	* When you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be distinguished. This property specifies the opacity of the graphs.
	* Default Value: 
	*/
	private double rollOverGraphAlpha;
	/**
	* Whether showing/hiding of graphs by clicking on the legend marker is enabled or not.
	* Default Value: 
	*/
	private boolean switchable;
	/**
	* Legend switch color.
	* Default Value: 
	*/
	private Color switchColor;
	/**
	* Legend switch type (in case the legend is switchable). Possible values are: x and v.
	* Default Value: 
	*/
	private SwitchType switchType;
	/**
	* Specifies whether the legend text is clickable or not. Clicking on legend text can show/hide value balloons if they are enabled.
	* Default Value: FALSE
	*/
	private boolean textClickEnabled;
	/**
	* Specifies if legend labels should be use same color as corresponding markers.
	* Default Value: 
	*/
	private boolean useMarkerColorForLabels;
	/**
	* The text which will be displayed in the value portion of the legend when graph is comparable and at least one dataSet is selected for comparing. You can use tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents]], [[description]].
	* Default Value: 
	*/
	private String valueTextComparing;
	/**
	* The text which will be displayed in the value portion of the legend. You can use tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents]], [[description]].
	* Default Value: 
	*/
	private String valueTextRegular;
	/**
	* Width of the value text. Increase this value if your values do not fit in the allocated space.
	* Default Value: 
	*/
	private double valueWidth;
	/**
	* Vertical space between legend items, in pixels.
	* Default Value: 
	*/
	private double verticalGap;
	public Alignment getAlign() {
		return align;
	}
	public void setAlign(Alignment align) {
		this.align = align;
	}
	public boolean isEqualWidths() {
		return equalWidths;
	}
	public void setEqualWidths(boolean equalWidths) {
		this.equalWidths = equalWidths;
	}
	public double getHorizontalGap() {
		return horizontalGap;
	}
	public void setHorizontalGap(double horizontalGap) {
		this.horizontalGap = horizontalGap;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public double getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(double marginBottom) {
		this.marginBottom = marginBottom;
	}
	public double getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(double marginTop) {
		this.marginTop = marginTop;
	}
	public double getMarkerBorderAlpha() {
		return markerBorderAlpha;
	}
	public void setMarkerBorderAlpha(double markerBorderAlpha) {
		this.markerBorderAlpha = markerBorderAlpha;
	}
	public Color getMarkerBorderColor() {
		return markerBorderColor;
	}
	public void setMarkerBorderColor(Color markerBorderColor) {
		this.markerBorderColor = markerBorderColor;
	}
	public double getMarkerBorderThickness() {
		return markerBorderThickness;
	}
	public void setMarkerBorderThickness(double markerBorderThickness) {
		this.markerBorderThickness = markerBorderThickness;
	}
	public Color getMarkerDisabledColor() {
		return markerDisabledColor;
	}
	public void setMarkerDisabledColor(Color markerDisabledColor) {
		this.markerDisabledColor = markerDisabledColor;
	}
	public double getMarkerLabelGap() {
		return markerLabelGap;
	}
	public void setMarkerLabelGap(double markerLabelGap) {
		this.markerLabelGap = markerLabelGap;
	}
	public double getMarkerSize() {
		return markerSize;
	}
	public void setMarkerSize(double markerSize) {
		this.markerSize = markerSize;
	}
	public MarkerType getMarkerType() {
		return markerType;
	}
	public void setMarkerType(MarkerType markerType) {
		this.markerType = markerType;
	}
	public boolean isReversedOrder() {
		return reversedOrder;
	}
	public void setReversedOrder(boolean reversedOrder) {
		this.reversedOrder = reversedOrder;
	}
	public Color getRollOverColor() {
		return rollOverColor;
	}
	public void setRollOverColor(Color rollOverColor) {
		this.rollOverColor = rollOverColor;
	}
	public double getRollOverGraphAlpha() {
		return rollOverGraphAlpha;
	}
	public void setRollOverGraphAlpha(double rollOverGraphAlpha) {
		this.rollOverGraphAlpha = rollOverGraphAlpha;
	}
	public boolean isSwitchable() {
		return switchable;
	}
	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}
	public Color getSwitchColor() {
		return switchColor;
	}
	public void setSwitchColor(Color switchColor) {
		this.switchColor = switchColor;
	}
	public SwitchType getSwitchType() {
		return switchType;
	}
	public void setSwitchType(SwitchType switchType) {
		this.switchType = switchType;
	}
	public boolean isTextClickEnabled() {
		return textClickEnabled;
	}
	public void setTextClickEnabled(boolean textClickEnabled) {
		this.textClickEnabled = textClickEnabled;
	}
	public boolean isUseMarkerColorForLabels() {
		return useMarkerColorForLabels;
	}
	public void setUseMarkerColorForLabels(boolean useMarkerColorForLabels) {
		this.useMarkerColorForLabels = useMarkerColorForLabels;
	}
	public String getValueTextComparing() {
		return valueTextComparing;
	}
	public void setValueTextComparing(String valueTextComparing) {
		this.valueTextComparing = valueTextComparing;
	}
	public String getValueTextRegular() {
		return valueTextRegular;
	}
	public void setValueTextRegular(String valueTextRegular) {
		this.valueTextRegular = valueTextRegular;
	}
	public double getValueWidth() {
		return valueWidth;
	}
	public void setValueWidth(double valueWidth) {
		this.valueWidth = valueWidth;
	}
	public double getVerticalGap() {
		return verticalGap;
	}
	public void setVerticalGap(double verticalGap) {
		this.verticalGap = verticalGap;
	}

}
