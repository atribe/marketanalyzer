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
	private Boolean equalWidths;
	/**
	* Horizontal space between legend item and left/right border.
	* Default Value: 
	*/
	private Double horizontalGap;
	/**
	* The text which will be displayed in the legend. Tag [[title]] will be replaced with the title of the graph.
	* Default Value: 
	*/
	private String labelText;
	/**
	* Space below the last row of the legend, in pixels.
	* Default Value: 0
	*/
	private Double marginBottom;
	/**
	* Space above the first row of the legend, in pixels.
	* Default Value: 0
	*/
	private Double marginTop;
	/**
	* Opacity of marker border.
	* Default Value: 
	*/
	private Double markerBorderAlpha;
	/**
	* Marker border color.
	* Default Value: 
	*/
	private Color markerBorderColor;
	/**
	* Thickness of the legend border.
	* Default Value: 
	*/
	private Double markerBorderThickness;
	/**
	* The color of the disabled marker (when the graph is hidden).
	* Default Value: 
	*/
	private Color markerDisabledColor;
	/**
	* Space between legend marker and legend text, in pixels.
	* Default Value: 
	*/
	private Double markerLabelGap;
	/**
	* Size of the legend marker (key).
	* Default Value: 
	*/
	private Double markerSize;
	/**
	* Shape of the legend marker (key). Possible values are: square, circle, diamond, triangleUp, triangleDown, triangleLeft, triangleDown, bubble, none
	* Default Value: 
	*/
	private MarkerType markerType;
	/**
	* Specifies whether legend entries should be placed in reversed order.
	* Default Value: 
	*/
	private Boolean reversedOrder;
	/**
	* Legend item text color on roll-over.
	* Default Value: 
	*/
	private Color rollOverColor;
	/**
	* When you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be distinguished. This property specifies the opacity of the graphs.
	* Default Value: 
	*/
	private Double rollOverGraphAlpha;
	/**
	* Whether showing/hiding of graphs by clicking on the legend marker is enabled or not.
	* Default Value: 
	*/
	private Boolean switchable;
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
	private Boolean textClickEnabled;
	/**
	* Specifies if legend labels should be use same color as corresponding markers.
	* Default Value: 
	*/
	private Boolean useMarkerColorForLabels;
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
	private Double valueWidth;
	/**
	* Vertical space between legend items, in pixels.
	* Default Value: 
	*/
	private Double verticalGap;
	public Alignment getAlign() {
		return align;
	}
	public void setAlign(Alignment align) {
		this.align = align;
	}
	public Boolean isEqualWidths() {
		return equalWidths;
	}
	public void setEqualWidths(Boolean equalWidths) {
		this.equalWidths = equalWidths;
	}
	public Double getHorizontalGap() {
		return horizontalGap;
	}
	public void setHorizontalGap(Double horizontalGap) {
		this.horizontalGap = horizontalGap;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public Double getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(Double marginBottom) {
		this.marginBottom = marginBottom;
	}
	public Double getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(Double marginTop) {
		this.marginTop = marginTop;
	}
	public Double getMarkerBorderAlpha() {
		return markerBorderAlpha;
	}
	public void setMarkerBorderAlpha(Double markerBorderAlpha) {
		this.markerBorderAlpha = markerBorderAlpha;
	}
	public Color getMarkerBorderColor() {
		return markerBorderColor;
	}
	public void setMarkerBorderColor(Color markerBorderColor) {
		this.markerBorderColor = markerBorderColor;
	}
	public Double getMarkerBorderThickness() {
		return markerBorderThickness;
	}
	public void setMarkerBorderThickness(Double markerBorderThickness) {
		this.markerBorderThickness = markerBorderThickness;
	}
	public Color getMarkerDisabledColor() {
		return markerDisabledColor;
	}
	public void setMarkerDisabledColor(Color markerDisabledColor) {
		this.markerDisabledColor = markerDisabledColor;
	}
	public Double getMarkerLabelGap() {
		return markerLabelGap;
	}
	public void setMarkerLabelGap(Double markerLabelGap) {
		this.markerLabelGap = markerLabelGap;
	}
	public Double getMarkerSize() {
		return markerSize;
	}
	public void setMarkerSize(Double markerSize) {
		this.markerSize = markerSize;
	}
	public MarkerType getMarkerType() {
		return markerType;
	}
	public void setMarkerType(MarkerType markerType) {
		this.markerType = markerType;
	}
	public Boolean isReversedOrder() {
		return reversedOrder;
	}
	public void setReversedOrder(Boolean reversedOrder) {
		this.reversedOrder = reversedOrder;
	}
	public Color getRollOverColor() {
		return rollOverColor;
	}
	public void setRollOverColor(Color rollOverColor) {
		this.rollOverColor = rollOverColor;
	}
	public Double getRollOverGraphAlpha() {
		return rollOverGraphAlpha;
	}
	public void setRollOverGraphAlpha(Double rollOverGraphAlpha) {
		this.rollOverGraphAlpha = rollOverGraphAlpha;
	}
	public Boolean isSwitchable() {
		return switchable;
	}
	public void setSwitchable(Boolean switchable) {
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
	public Boolean isTextClickEnabled() {
		return textClickEnabled;
	}
	public void setTextClickEnabled(Boolean textClickEnabled) {
		this.textClickEnabled = textClickEnabled;
	}
	public Boolean isUseMarkerColorForLabels() {
		return useMarkerColorForLabels;
	}
	public void setUseMarkerColorForLabels(Boolean useMarkerColorForLabels) {
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
	public Double getValueWidth() {
		return valueWidth;
	}
	public void setValueWidth(Double valueWidth) {
		this.valueWidth = valueWidth;
	}
	public Double getVerticalGap() {
		return verticalGap;
	}
	public void setVerticalGap(Double verticalGap) {
		this.verticalGap = verticalGap;
	}

}
