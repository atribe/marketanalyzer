package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AmLegend {

	/**
	* Alignment of legend entries. Possible values are: ""left"", ""center"", ""right"".
	* Default Value: left
	*/
	 private String align;
	/**
	* Used if chart is Serial or XY. In case true, margins of the legend are adjusted and made equal to chart's margins.
	* Default Value: TRUE
	*/
	 private Boolean autoMargins;
	/**
	* Opacity of legend's background. Value range is 0 - 1
	* Default Value: 0
	*/
	 private Double backgroundAlpha;
	/**
	* Background color. You should set backgroundAlpha to >0 vallue in order background to be visible.
	* Default Value: #FFFFFF
	*/
	 private Color backgroundColor;
	/**
	* Opacity of chart's border. Value range is 0 - 1.
	* Default Value: 0
	*/
	 private Double borderAlpha;
	/**
	* Color of legend's border. You should set borderAlpha >0 in order border to be visible.
	* Default Value: #000000
	*/
	 private Color borderColor;
	/**
	* In case legend position is set to ""absolute"", you can set distance from bottom of the chart, in pixels.
	* Default Value: 
	*/
	 private Double bottom;
	/**
	* Text color.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* You can pass array of objects with title, color, markerType values, for example: [{title: ""One"", color: ""#3366CC""},{title: ""Two"", color: ""#FFCC33""}]
	* Default Value: 
	*/
	 private List<Object> data;
	/**
	* You can set id of a div or a reference to div object in case you want the legend to be placed in a separate container.
	* Default Value: 
	*/
	 private String divId;
	/**
	* Specifies if each of legend entry should be equal to the most wide entry. Won't look good if legend has more than one line.
	* Default Value: TRUE
	*/
	 private Boolean equalWidths;
	/**
	* Font size.
	* Default Value: 11
	*/
	 private Double fontSize;
	/**
	* Horizontal space between legend item and left/right border.
	* Default Value: 0
	*/
	 private Double horizontalGap;
	/**
	* The text which will be displayed in the legend. Tag [[title]] will be replaced with the title of the graph.
	* Default Value: [[title]]
	*/
	 private String labelText;
	/**
	* If width of the label is bigger than labelWidth, it will be wrapped.
	* Default Value: 
	*/
	 private Double labelWidth;
	/**
	* In case legend position is set to ""absolute"", you can set distance from left side of the chart, in pixels.
	* Default Value: 
	*/
	 private Double left;
	/**
	* Bottom margin.
	* Default Value: 0
	*/
	 private Double marginBottom;
	/**
	* Left margin. This property will be ignored if chart is Serial or XY and autoMargins property of the legend is true (default).
	* Default Value: 20
	*/
	 private Double marginLeft;
	/**
	* Right margin. This property will be ignored if chart is Serial or XY and autoMargins property of the legend is true (default).
	* Default Value: 20
	*/
	 private Double marginRight;
	/**
	* Top margin.
	* Default Value: 0
	*/
	 private Double marginTop;
	/**
	* Marker border opacity.
	* Default Value: 1
	*/
	 private Double markerBorderAlpha;
	/**
	* Marker border color. If not set, will use the same color as marker.
	* Default Value: 
	*/
	 private Color markerBorderColor;
	/**
	* Thickness of the legend border. The default value (0) means the line will be a ""hairline"" (1 px). In case marker type is line, this style will be used for line thickness.
	* Default Value: 1
	*/
	 private Double markerBorderThickness;
	/**
	* The color of the disabled marker (when the graph is hidden).
	* Default Value: #AAB3B3
	*/
	 private Color markerDisabledColor;
	/**
	* Space between legend marker and legend text, in pixels.
	* Default Value: 5
	*/
	 private Double markerLabelGap;
	/**
	* Size of the legend marker (key).
	* Default Value: 16
	*/
	 private Double markerSize;
	/**
	* Shape of the legend marker (key). Possible values are: square, circle, diamond, triangleUp, triangleDown, triangleLeft, triangleDown, bubble, line, none.
	* Default Value: square
	*/
	 private String markerType;
	/**
	* Maximum number of columns in the legend. If Legend's position is set to ""right"" or ""left"", maxColumns is automatically set to 1.
	* Default Value: 
	*/
	 private Double maxColumns;
	/**
	* The text which will be displayed in the value portion of the legend when user is not hovering above any data point. The tags should be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you want to be show - open / close / high / low / sum / average / count. For example: [[value.sum]] means that sum of all data points of value field in the selected period will be displayed.
	* Default Value: 
	*/
	 private String periodValueText;
	/**
	* Position of a legend. Possible values are: ""bottom"", ""top"", ""left"", ""right"" and ""absolute"". In case ""absolute"", you should set left and top properties too. (this setting is ignored in Stock charts). In case legend is used with AmMap, position is set to ""absolute"" automatically.
	* Default Value: bottom
	*/
	 private String position;
	/**
	* Specifies whether legend entries should be placed in reversed order.
	* Default Value: FALSE
	*/
	 private Boolean reversedOrder;
	/**
	* In case legend position is set to ""absolute"", you can set distance from right side of the chart, in pixels.
	* Default Value: 
	*/
	 private Double right;
	/**
	* Legend item text color on roll-over.
	* Default Value: #CC0000
	*/
	 private Color rollOverColor;
	/**
	* When you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be distinguished. This style specifies the opacity of the graphs.
	* Default Value: 1
	*/
	 private Double rollOverGraphAlpha;
	/**
	* You can use this property to turn all the legend entries off.
	* Default Value: TRUE
	*/
	 private Boolean showEntries;
	/**
	* Horizontal space between legend items, in pixels.
	* Default Value: 10
	*/
	 private Double spacing;
	/**
	* Whether showing/hiding of graphs by clicking on the legend marker is enabled or not. In case legend is used with AmMap, this is set to false automatically.
	* Default Value: TRUE
	*/
	 private Boolean switchable;
	/**
	* Legend switch color.
	* Default Value: #FFFFFF
	*/
	 private Color switchColor;
	/**
	* Legend switch type (in case the legend is switchable). Possible values are ""x"" and ""v"".
	* Default Value: x
	*/
	 private String switchType;
	/**
	* If true, clicking on the text will show/hide balloon of the graph. Otherwise it will show/hide graph/slice, if switchable is set to true.
	* Default Value: FALSE
	*/
	 private Boolean textClickEnabled;
	/**
	* In case legend position is set to ""absolute"", you can set distance from top of the chart, in pixels.
	* Default Value: 
	*/
	 private Double top;
	/**
	* Legend markers can mirror graph’s settings, displaying a line and a real bullet as in the graph itself. Set this property to true if you want to enable this feature.
	* Default Value: FALSE
	*/
	 private Boolean useGraphSettings;
	/**
	* Labels will use marker color if you set this to true.
	* Default Value: FALSE
	*/
	 private Boolean useMarkerColorForLabels;
	/**
	* Specifies if legend values should be use same color as corresponding markers.
	* Default Value: FALSE
	*/
	 private Boolean useMarkerColorForValues;
	/**
	* Alignment of the value text. Possible values are ""left"" and ""right"".
	* Default Value: right
	*/
	 private String valueAlign;
	/**
	* You can use it to format value labels in any way you want. Legend will call this method and will pass GraphDataItemand formatted text of currently hovered item (works only withChartCursor added to the chart). This method should return string which will be displayed as value in the legend.
	* Default Value: 
	*/
	 private String valueFunction;
	/**
	* The text which will be displayed in the value portion of the legend. You can use tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents]], [[description]].
	* Default Value: [[value]]
	*/
	 private String valueText;
	/**
	* Width of the value text.
	* Default Value: 50
	*/
	 private Double valueWidth;
	/**
	* Vertical space between legend items also between legend border and first and last legend row.
	* Default Value: 10
	*/
	 private Double verticalGap;
	/**
	* Width of a legend, when position is set to absolute.
	* Default Value: 
	*/
	 private Double width;
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Boolean isAutoMargins() {
		return autoMargins;
	}
	public void setAutoMargins(Boolean autoMargins) {
		this.autoMargins = autoMargins;
	}
	public Double getBackgroundAlpha() {
		return backgroundAlpha;
	}
	public void setBackgroundAlpha(Double backgroundAlpha) {
		this.backgroundAlpha = backgroundAlpha;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Double getBorderAlpha() {
		return borderAlpha;
	}
	public void setBorderAlpha(Double borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Double getBottom() {
		return bottom;
	}
	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public Boolean isEqualWidths() {
		return equalWidths;
	}
	public void setEqualWidths(Boolean equalWidths) {
		this.equalWidths = equalWidths;
	}
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
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
	public Double getLabelWidth() {
		return labelWidth;
	}
	public void setLabelWidth(Double labelWidth) {
		this.labelWidth = labelWidth;
	}
	public Double getLeft() {
		return left;
	}
	public void setLeft(Double left) {
		this.left = left;
	}
	public Double getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(Double marginBottom) {
		this.marginBottom = marginBottom;
	}
	public Double getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(Double marginLeft) {
		this.marginLeft = marginLeft;
	}
	public Double getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(Double marginRight) {
		this.marginRight = marginRight;
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
	public String getMarkerType() {
		return markerType;
	}
	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}
	public Double getMaxColumns() {
		return maxColumns;
	}
	public void setMaxColumns(Double maxColumns) {
		this.maxColumns = maxColumns;
	}
	public String getPeriodValueText() {
		return periodValueText;
	}
	public void setPeriodValueText(String periodValueText) {
		this.periodValueText = periodValueText;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Boolean isReversedOrder() {
		return reversedOrder;
	}
	public void setReversedOrder(Boolean reversedOrder) {
		this.reversedOrder = reversedOrder;
	}
	public Double getRight() {
		return right;
	}
	public void setRight(Double right) {
		this.right = right;
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
	public Boolean isShowEntries() {
		return showEntries;
	}
	public void setShowEntries(Boolean showEntries) {
		this.showEntries = showEntries;
	}
	public Double getSpacing() {
		return spacing;
	}
	public void setSpacing(Double spacing) {
		this.spacing = spacing;
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
	public String getSwitchType() {
		return switchType;
	}
	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}
	public Boolean isTextClickEnabled() {
		return textClickEnabled;
	}
	public void setTextClickEnabled(Boolean textClickEnabled) {
		this.textClickEnabled = textClickEnabled;
	}
	public Double getTop() {
		return top;
	}
	public void setTop(Double top) {
		this.top = top;
	}
	public Boolean isUseGraphSettings() {
		return useGraphSettings;
	}
	public void setUseGraphSettings(Boolean useGraphSettings) {
		this.useGraphSettings = useGraphSettings;
	}
	public Boolean isUseMarkerColorForLabels() {
		return useMarkerColorForLabels;
	}
	public void setUseMarkerColorForLabels(Boolean useMarkerColorForLabels) {
		this.useMarkerColorForLabels = useMarkerColorForLabels;
	}
	public Boolean isUseMarkerColorForValues() {
		return useMarkerColorForValues;
	}
	public void setUseMarkerColorForValues(Boolean useMarkerColorForValues) {
		this.useMarkerColorForValues = useMarkerColorForValues;
	}
	public String getValueAlign() {
		return valueAlign;
	}
	public void setValueAlign(String valueAlign) {
		this.valueAlign = valueAlign;
	}
	public String getValueFunction() {
		return valueFunction;
	}
	public void setValueFunction(String valueFunction) {
		this.valueFunction = valueFunction;
	}
	public String getValueText() {
		return valueText;
	}
	public void setValueText(String valueText) {
		this.valueText = valueText;
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
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}

}
