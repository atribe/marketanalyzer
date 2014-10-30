package com.ar.marketanalyzer.plotting.amcharts.buildingblock;

import java.io.Serializable;
import java.util.List;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amcharts.enums.MarkerType;
import com.ar.marketanalyzer.plotting.amcharts.enums.Position;
import com.ar.marketanalyzer.plotting.amcharts.enums.PositionHorizontal;
import com.ar.marketanalyzer.plotting.amcharts.enums.SwitchType;
import com.ar.marketanalyzer.plotting.amcharts.serializers.JacksonObjectToListSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JacksonObjectToListSerializer.class)
public class AmLegend implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8727554174463667845L;
	/**
	 * Alignment of legend entries. Possible values are: "left", "center", "right".
	 */
	private Position align;
    /**
     * Used if chart is Serial or XY. In case true, margins of the legend are adjusted and made equal to chart's margins.
     */
    private boolean autoMargins;
    /**
     * Opacity of legend's background. Value range is 0 - 1
     */
    private double backgroundAlpha;
    /**
     * Background color. You should set backgroundAlpha to >0 vallue in order background to be visible.
     */
    private Color backgroundColor;
    /**
     * Opacity of chart's border. Value range is 0 - 1.
     */
    private double borderAlpha;
    /**
     * Color of legend's border. You should set borderAlpha >0 in order border to be visible.
     */
    private Color borderColor;
    /**
     * In case legend position is set to "absolute", you can set distance from bottom of the chart, in pixels.
     */
    private double bottom;
    /**
     * text color
     */
    private Color color;
    /**
     * You can pass array of objects with title, color, markerType values, for example: [{title: "One", color: "#3366CC"},{title: "Two", color: "#FFCC33"}]
     */
    private List<Object> data;
    /**
     * You can set id of a div or a reference to div object in case you want the legend to be placed in a separate container.
     */
    private String divId;
    /**
     * Specifies if each of legend entry should be equal to the most wide entry. Won't look good if legend has more than one line.
     */
    private boolean equalWidths;
    /**
     * Font size.
     */
    private double fontSize;
    /**
     * Horizontal space between legend item and left/right border.
     */
    private double horizontalGap;
    /**
     * The text which will be displayed in the legend. Tag [[title]] will be replaced with the title of the graph.
     */
    private String labelText;
    /**
     * If width of the label is bigger than labelWidth, it will be wrapped.
     */
    private double labelWidth;
    /**
     * In case legend position is set to "absolute", you can set distance from left side of the chart, in pixels.
     */
    private double left;
    /**
     * Bottom margin.
     */
    private double marginBottom;
    /**
     * Left margin. This property will be ignored if chart is Serial or XY and autoMargins property of the legend is true (default).
     */
    private double marginLeft;
    /**
     * Right margin. This property will be ignored if chart is Serial or XY and autoMargins property of the legend is true (default).
     */
    private double marginRight;
    /**
     * Top margin.
     */
    private double marginTop;
    /**
     * Marker border opacity.
     */
    private double markerBorderAlpha;
    /**
     * Marker border color. If not set, will use the same color as marker.
     */
    private Color markerBorderColor;
    /**
     * Thickness of the legend border. The default value (0) means the line will be a "hairline" (1 px). In case marker type is line, this style will be used for line thickness.
     */
    private double markerBorderThickness;
    /**
     * The color of the disabled marker (when the graph is hidden).
     */
    private Color markerDisabledColor;
    /**
     * Space between legend marker and legend text, in pixels.
     */
    private double markerLabelGap;
    /**
     * Size of the legend marker (key).
     */
    private double markerSize;
    /**
     * Shape of the legend marker (key). Possible values are: square, circle, diamond, triangleUp, triangleDown, triangleLeft, triangleDown, bubble, line, none.
     */
    private MarkerType markerType;
    /**
     * Maximum number of columns in the legend. If Legend's position is set to "right" or "left", maxColumns is automatically set to 1.
     */
    private double maxColumns;
    /**
     * The text which will be displayed in the value portion of the legend when user is not hovering above any data point. The tags should be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you want to be show - open / close / high / low / sum / average / count. For example: [[value.sum]] means that sum of all data points of value field in the selected period will be displayed.
     */
    private String periodValueText;
    /**
     * Position of a legend. Possible values are: "bottom", "top", "left", "right" and "absolute". In case "absolute", you should set left and top properties too. (this setting is ignored in Stock charts). In case legend is used with AmMap, position is set to "absolute" automatically.
     */
    private Position position;
    /**
     * Specifies whether legend entries should be placed in reversed order.
     */
    private boolean reversedOrder;
    /**
     * In case legend position is set to "absolute", you can set distance from right side of the chart, in pixels.
     */
    private double right;
    /**
     * Legend item text color on roll-over.
     */
    private Color rollOverColor;
    /**
     * When you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be distinguished. This style specifies the opacity of the graphs.
     */
    private double rollOverGraphAlpha;
    /**
     * You can use this property to turn all the legend entries off.
     */
    private boolean showEntries;
    /**
     * Horizontal space between legend items, in pixels.
     */
    private double spacing;
    /**
     * Whether showing/hiding of graphs by clicking on the legend marker is enabled or not. In case legend is used with AmMap, this is set to false automatically.
     */
    private boolean switchable;
    /**
     * Legend switch color.
     */
    private Color switchColor;
    /**
     * Legend switch type (in case the legend is switchable). Possible values are "x" and "v".
     */
    private SwitchType switchType;
    /**
     * If true, clicking on the text will show/hide balloon of the graph. Otherwise it will show/hide graph/slice, if switchable is set to true.
     */
    private boolean textClickEnabled;
    /**
     * In case legend position is set to "absolute", you can set distance from top of the chart, in pixels.
     */
    private double top;
    /**
     * Legend markers can mirror graph’s settings, displaying a line and a real bullet as in the graph itself. Set this property to true if you want to enable this feature.
     */
    private boolean useGraphSettings;
    /**
     * Labels will use marker color if you set this to true.
     */
    private boolean useMarkerColorForLabels;
    /**
     * Specifies if legend values should be use same color as corresponding markers.
     */
    private boolean useMarkerColorForValues;
    /**
     * Alignment of the value text. Possible values are "left" and "right".
     */
    private PositionHorizontal valueAlign;
    
    /* 
     * Not implemented valueFunction. See:
     * http://docs.amcharts.com/3/javascriptmaps/AmLegend#valueFunction
     */
    
    /**
     * The text which will be displayed in the value portion of the legend. You can use tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents]], [[description]].
     */
    private String valueText;
    /**
     * Width of the value text.
     */
    private double valueWidth;
    /**
     * Vertical space between legend items also between legend border and first and last legend row.
     */
    private double verticalGap;
    /**
     * Width of a legend, when position is set to absolute.
     */
    private double width;
    /**
     * Alignment of legend entries. Possible values are: "left", "center", "right".
     **/
    
    
    public Position getAlign() {
        return align;
    }
    
    /*
     * Getters and Setters
     */
    public AmLegend setAlign(Position align) {
        this.align = align;
        return this;
    }

    /**
     * Used if chart is Serial or XY. In case true, margins of the legend are adjusted and
     * made equal to chart's margins.
     **/
    public boolean getAutoMargins() {
        return autoMargins;
    }
    public AmLegend setAutoMargins(boolean autoMargins) {
        this.autoMargins = autoMargins;
        return this;
    }

    /**
     * Opacity of legend's background. Value range is 0 - 1
     **/
    public double getBackgroundAlpha() {
        return backgroundAlpha;
    }
    public AmLegend setBackgroundAlpha(double backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
        return this;
    }

    /**
     * Background color. You should set backgroundAlpha to >0 vallue in order background
     * to be visible.
     **/
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public AmLegend setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * Opacity of chart's border. Value range is 0 - 1.
     **/
    public double getBorderAlpha() {
        return borderAlpha;
    }
    public AmLegend setBorderAlpha(double borderAlpha) {
        this.borderAlpha = borderAlpha;
        return this;
    }

    /**
     *      *Color of legend's border. You should set borderAlpha >0 in order border to be visible.
     **/
    public Color getBorderColor() {
        return borderColor;
    }
    public AmLegend setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    /**
     * In case legend position is set to "absolute", you can set distance from bottom of
     * the chart, in pixels.
     **/
    public double getBottom() {
        return bottom;
    }
    public AmLegend setBottom(double bottom) {
        this.bottom = bottom;
        return this;
    }

    /**
     * Text color.
     **/
    public Color getColor() {
        return color;
    }
    public AmLegend setColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     * You can pass array of objects with title, color, markerType values, for example:
     * [{title: "One", color: "#3366CC"},{title: "Two", color: "#FFCC33"}]
     **/
    public List<Object> getData() {
        return data;
    }
    public AmLegend setData(List<Object> data) {
        this.data = data;
        return this;
    }

    /**
     * You can set id of a div or a reference to div object in case you want the legend
     * to be placed in a separate container.
     **/
    public String getDivId() {
        return divId;
    }
    public AmLegend setDivId(String divId) {
        this.divId = divId;
        return this;
    }

    /**
     * Specifies if each of legend entry should be equal to the most wide entry. Won't look
     * good if legend has more than one line.
     **/
    public boolean getEqualWidths() {
        return equalWidths;
    }
    public AmLegend setEqualWidths(boolean equalWidths) {
        this.equalWidths = equalWidths;
        return this;
    }

    /**
     * Font size.
     **/
    public double getFontSize() {
        return fontSize;
    }
    public AmLegend setFontSize(double fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    /**
     * Horizontal space between legend item and left/right border.
     **/
    public double getHorizontalGap() {
        return horizontalGap;
    }
    public AmLegend setHorizontalGap(double horizontalGap) {
        this.horizontalGap = horizontalGap;
        return this;
    }

    /**
     * The text which will be displayed in the legend. Tag [[title]] will be replaced with
     * the title of the graph.
     **/
    public String getLabelText() {
        return labelText;
    }
    public AmLegend setLabelText(String labelText) {
        this.labelText = labelText;
        return this;
    }

    /**
     * In case legend position is set to "absolute", you can set distance from left side
     * of the chart, in pixels.
     **/
    public double getLeft() {
        return left;
    }
    public AmLegend setLeft(double left) {
        this.left = left;
        return this;
    }

    /**
     * Bottom margin.
     **/
    public double getMarginBottom() {
        return marginBottom;
    }
    public AmLegend setMarginBottom(double marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    /**
     * Left margin. This property will be ignored if chart is Serial or XY and autoMargins
     * property of the legend is true (default).
     **/
    public double getMarginLeft() {
        return marginLeft;
    }
    public AmLegend setMarginLeft(double marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    /**
     * Right margin. This property will be ignored if chart is Serial or XY and autoMargins
     * property of the legend is true (default).
     **/
    public double getMarginRight() {
        return marginRight;
    }
    public AmLegend setMarginRight(double marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    /**
     * Top margin.
     **/
    public double getMarginTop() {
        return marginTop;
    }
    public AmLegend setMarginTop(double marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    /**
     * Marker border opacity.
     **/
    public double getMarkerBorderAlpha() {
        return markerBorderAlpha;
    }
    public AmLegend setMarkerBorderAlpha(double markerBorderAlpha) {
        this.markerBorderAlpha = markerBorderAlpha;
        return this;
    }

    /**
     * Marker border color. If not set, will use the same color as marker.
     **/
    public Color getMarkerBorderColor() {
        return markerBorderColor;
    }
    public AmLegend setMarkerBorderColor(Color markerBorderColor) {
        this.markerBorderColor = markerBorderColor;
        return this;
    }

    /**
     * Thickness of the legend border. The default value (0) means the line will be a "hairline"
     * (1 px). In case marker type is line, this style will be used for line thickness.
     **/
    public double getMarkerBorderThickness() {
        return markerBorderThickness;
    }
    public AmLegend setMarkerBorderThickness(double markerBorderThickness) {
        this.markerBorderThickness = markerBorderThickness;
        return this;
    }

    /**
     * The color of the disabled marker (when the graph is hidden).
     **/
    public Color getMarkerDisabledColor() {
        return markerDisabledColor;
    }
    public AmLegend setMarkerDisabledColor(Color markerDisabledColor) {
        this.markerDisabledColor = markerDisabledColor;
        return this;
    }

    /**
     * Space between legend marker and legend text, in pixels.
     **/
    public double getMarkerLabelGap() {
        return markerLabelGap;
    }
    public AmLegend setMarkerLabelGap(double markerLabelGap) {
        this.markerLabelGap = markerLabelGap;
        return this;
    }

    /**
     * Size of the legend marker (key).
     **/
    public double getMarkerSize() {
        return markerSize;
    }
    public AmLegend setMarkerSize(double markerSize) {
        this.markerSize = markerSize;
        return this;
    }

    /**
     * Shape of the legend marker (key). Possible values are: square, circle, diamond, triangleUp,
     * triangleDown, triangleLeft, triangleDown, bubble, line, none.
     **/
    public MarkerType getMarkerType() {
        return markerType;
    }
    public AmLegend setMarkerType(MarkerType markerType) {
        this.markerType = markerType;
        return this;
    }

    /**
     * Maximum number of columns in the legend. If Legend's position is set to "right" or
     * "left", maxColumns is automatically set to 1.
     **/
    public double getMaxColumns() {
        return maxColumns;
    }
    public AmLegend setMaxColumns(double maxColumns) {
        this.maxColumns = maxColumns;
        return this;
    }

    /**
     * The text which will be displayed in the value portion of the legend when user is
     * not hovering above any data point. The tags should be made out of two parts - the
     * name of a field (value / open / close / high / low) and the value of the period you
     * want to be show - open / close / high / low / sum / average / count. For example:
     * [[value.sum]] means that sum of all data points of value field in the selected period
     * will be displayed.
     **/
    public String getPeriodValueText() {
        return periodValueText;
    }
    public AmLegend setPeriodValueText(String periodValueText) {
        this.periodValueText = periodValueText;
        return this;
    }

    /**
     * Position of a legend. Possible values are: "bottom", "top", "left", "right" and "absolute".
     * In case "absolute", you should set left and top properties too. (this setting is
     * ignored in Stock charts). In case legend is used with AmMap, position is set to "absolute"
     * automatically.
     **/
    public Position getPosition() {
        return position;
    }
    public AmLegend setPosition(Position position) {
        this.position = position;
        return this;
    }

    /**
     * Specifies whether legend entries should be placed in reversed order.
     **/
    public boolean getReversedOrder() {
        return reversedOrder;
    }
    public AmLegend setReversedOrder(boolean reversedOrder) {
        this.reversedOrder = reversedOrder;
        return this;
    }

    /**
     * In case legend position is set to "absolute", you can set distance from right side
     * of the chart, in pixels.
     **/
    public double getRight() {
        return right;
    }
    public AmLegend setRight(double right) {
        this.right = right;
        return this;
    }

    /**
     * Legend item text color on roll-over.
     **/
    public Color getRollOverColor() {
        return rollOverColor;
    }
    public AmLegend setRollOverColor(Color rollOverColor) {
        this.rollOverColor = rollOverColor;
        return this;
    }

    /**
     * When you roll-over the legend entry, all other graphs can reduce their opacity, so
     * that the graph you rolled-over would be distinguished. This style specifies the opacity
     * of the graphs.
     **/
    public double getRollOverGraphAlpha() {
        return rollOverGraphAlpha;
    }
    public AmLegend setRollOverGraphAlpha(double rollOverGraphAlpha) {
        this.rollOverGraphAlpha = rollOverGraphAlpha;
        return this;
    }

    /**
     * You can use this property to turn all the legend entries off.
     **/
    public boolean getShowEntries() {
        return showEntries;
    }
    public AmLegend setShowEntries(boolean showEntries) {
        this.showEntries = showEntries;
        return this;
    }

    /**
     * Horizontal space between legend items, in pixels.
     **/
    public double getSpacing() {
        return spacing;
    }
    public AmLegend setSpacing(double spacing) {
        this.spacing = spacing;
        return this;
    }

    /**
     * Whether showing/hiding of graphs by clicking on the legend marker is enabled or not.
     * In case legend is used with AmMap, this is set to false automatically.
     **/
    public boolean getSwitchable() {
        return switchable;
    }
    public AmLegend setSwitchable(boolean switchable) {
        this.switchable = switchable;
        return this;
    }

    /**
     * Legend switch color.
     **/
    public Color getSwitchColor() {
        return switchColor;
    }
    public AmLegend setSwitchColor(Color switchColor) {
        this.switchColor = switchColor;
        return this;
    }

    /**
     * Legend switch type (in case the legend is switchable). Possible values are "x" and
     * "v".
     **/
    public SwitchType getSwitchType() {
        return switchType;
    }
    public AmLegend setSwitchType(SwitchType switchType) {
        this.switchType = switchType;
        return this;
    }

    /**
     * If true, clicking on the text will show/hide balloon of the graph. Otherwise it will
     * show/hide graph/slice, if switchable is set to true.
     **/
    public boolean getTextClickEnabled() {
        return textClickEnabled;
    }
    public AmLegend setTextClickEnabled(boolean textClickEnabled) {
        this.textClickEnabled = textClickEnabled;
        return this;
    }

    /**
     * In case legend position is set to "absolute", you can set distance from top of the
     * chart, in pixels.
     **/
    public double getTop() {
        return top;
    }
    public AmLegend setTop(double top) {
        this.top = top;
        return this;
    }

    /**
     * Legend markers can mirror graphâ€™s settings, displaying a line and a real bullet as
     * in the graph itself. Set this property to true if you want to enable this feature.
     **/
    public boolean getUseGraphSettings() {
        return useGraphSettings;
    }
    public AmLegend setUseGraphSettings(boolean useGraphSettings) {
        this.useGraphSettings = useGraphSettings;
        return this;
    }

    /**
     * Labels will use marker color if you set this to true.
     **/
    public boolean getUseMarkerColorForLabels() {
        return useMarkerColorForLabels;
    }
    public AmLegend setUseMarkerColorForLabels(boolean useMarkerColorForLabels) {
        this.useMarkerColorForLabels = useMarkerColorForLabels;
        return this;
    }

    /**
     * Specifies if legend values should be use same color as corresponding markers.
     **/
    public boolean getUseMarkerColorForValues() {
        return useMarkerColorForValues;
    }
    public AmLegend setUseMarkerColorForValues(boolean useMarkerColorForValues) {
        this.useMarkerColorForValues = useMarkerColorForValues;
        return this;
    }

    /**
     * Alignment of the value text. Possible values are "left" and "right".
     **/
    public PositionHorizontal getValueAlign() {
        return valueAlign;
    }
    public AmLegend setValueAlign(PositionHorizontal valueAlign) {
        this.valueAlign = valueAlign;
        return this;
    }

    /**
     * The text which will be displayed in the value portion of the legend. You can use
     * tags like [[value]], [[open]], [[high]], [[low]], [[close]], [[percents]], [[description]].
     **/
    public String getValueText() {
        return valueText;
    }
    public AmLegend setValueText(String valueText) {
        this.valueText = valueText;
        return this;
    }

    /**
     * Width of the value text.
     **/
    public double getValueWidth() {
        return valueWidth;
    }
    public AmLegend setValueWidth(double valueWidth) {
        this.valueWidth = valueWidth;
        return this;
    }

    /**
     * Vertical space between legend items also between legend border and first and last
     * legend row.
     **/
    public double getVerticalGap() {
        return verticalGap;
    }
    public AmLegend setVerticalGap(double verticalGap) {
        this.verticalGap = verticalGap;
        return this;
    }

    /**
     * Width of a legend, when position is set to absolute.
     **/
    public double getWidth() {
        return width;
    }
    public AmLegend setWidth(double width) {
        this.width = width;
        return this;
    }
	public double getLabelWidth() {
		return labelWidth;
	}
	public void setLabelWidth(double labelWidth) {
		this.labelWidth = labelWidth;
	}


}