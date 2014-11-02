package com.ar.marketanalyzer.plotting.amstockcharts.chartobjects;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.ChartTypeAm;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AmGraph {

	/**
	* Name of the alpha field in your dataProvider.
	* Default Value: 
	*/
	protected String alphaField;
	/**
	* Value balloon color. Will use graph or data item color if not set.
	* Default Value: 
	*/
	protected Color balloonColor;
	/**
	* If you set some function, the graph will call it and pass GraphDataItem and AmGraph object to it. This function should return a string which will be displayed in a balloon.
	* Default Value: 
	*/
	protected String balloonFunction;
	/**
	* Balloon text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]] or any other field name from your data provider. HTML tags can also be used.
	* Default Value: [[value]]
	*/
	protected String balloonText;
	/**
	* Specifies if the line graph should be placed behind column graphs
	* Default Value: FALSE
	*/
	protected boolean behindColumns;
	/**
	* Type of the bullets. Possible values are: ""none"", ""round"", ""square"", ""triangleUp"", ""triangleDown"", ""triangleLeft"", ""triangleRight"", ""bubble"", ""diamond"", ""xError"", ""yError"" and ""custom"".
	* Default Value: none
	*/
	protected String bullet;
	/**
	* Opacity of bullets. Value range is 0 - 1.
	* Default Value: 1
	*/
	protected double bulletAlpha;
	/**
	* bulletAxis value is used when you are building error chart. Error chart is a regular serial or XY chart with bullet type set to ""xError"" or ""yError"". The graph should know which axis should be used to determine the size of this bullet - that's when bulletAxis should be set. Besides that, you should also set graph.errorField. You can also use other bullet types with this feature too. For example, if you set bulletAxis for XY chart, the size of a bullet will change as you zoom the chart.
	* Default Value: 
	*/
	protected ValueAxis bulletAxis;
	/**
	* Bullet border opacity.
	* Default Value: 0
	*/
	protected double bulletBorderAlpha;
	/**
	* Bullet border color. Will use lineColor if not set.
	* Default Value: 
	*/
	protected Color bulletBorderColor;
	/**
	* Bullet border thickness.
	* Default Value: 2
	*/
	protected double bulletBorderThickness;
	/**
	* Bullet color. Will use lineColor if not set.
	* Default Value: 
	*/
	protected Color bulletColor;
	/**
	* Name of the bullet field in your dataProvider.
	* Default Value: 
	*/
	protected String bulletField;
	/**
	* Bullet offset. Distance from the actual data point to the bullet. Can be used to place custom bullets above the columns.
	* Default Value: 0
	*/
	protected double bulletOffset;
	/**
	* Bullet size.
	* Default Value: 8
	*/
	protected double bulletSize;
	/**
	* Name of the bullet size field in your dataProvider.
	* Default Value: 
	*/
	protected String bulletSizeField;
	/**
	* Name of the close field (used by candlesticks and ohlc) in your dataProvider.
	* Default Value: 
	*/
	protected String closeField;
	/**
	* In case you want to place this graph's columns in front of other columns, set this to false. In case ""true"", the columns will be clustered next to each other.
	* Default Value: TRUE
	*/
	protected boolean clustered;
	/**
	* Color of value labels. Will use chart's color if not set.
	* Default Value: 
	*/
	protected Color color;
	/**
	* Name of the color field in your dataProvider.
	* Default Value: 
	*/
	protected String colorField;
	/**
	* You can specify custom column width for each graph individually. Value range is 0 - 1 (we set relative width, not pixel width here).
	* Default Value: 
	*/
	protected double columnWidth;
	/**
	* Specifies whether to connect data points if data is missing. The default value is true.
	* Default Value: TRUE
	*/
	protected boolean connect;
	/**
	* Corner radius of column. It can be set both in pixels or in percents. The chart's depth and angle styles must be set to 0. The default value is 0. Note, cornerRadiusTop will be applied for all corners of the column, JavaScript charts do not have a possibility to set separate corner radius for top and bottom. As we want all the property names to be the same both on JS and Flex, we didn't change this too.
	* Default Value: 0
	*/
	protected double cornerRadiusTop;
	/**
	* If bulletsEnabled of ChartCurosor is true, a bullet on each graph follows the cursor. You can set opacity of each graphs bullet. In case you want to disable these bullets for a certain graph, set opacity to 0.
	* Default Value: 1
	*/
	protected double cursorBulletAlpha;
	/**
	* Path to the image of custom bullet.
	* Default Value: 
	*/
	protected String customBullet;
	/**
	* Name of the custom bullet field in your dataProvider.
	* Default Value: 
	*/
	protected String customBulletField;
	/**
	* Path to the image for legend marker.
	* Default Value: 
	*/
	protected String customMarker;
	/**
	* Dash length. If you set it to a value greater than 0, the graph line (or columns border) will be dashed.
	* Default Value: 0
	*/
	protected double dashLength;
	/**
	* Name of the dash length field in your dataProvider. This property adds a possibility to change graphs’ line from solid to dashed on any data point. You can also make columns border dashed using this setting.
	* Default Value: 
	*/
	protected String dashLengthField;
	/**
	* Name of the description field in your dataProvider.
	* Default Value: 
	*/
	protected String descriptionField;
	/**
	* Name of error value field in your data provider.
	* Default Value: 
	*/
	protected String errorField;
	/**
	* Opacity of fill. Plural form is used to keep the same property names as our Flex charts'. Flex charts can accept array of numbers to generate gradients. Although you can set array here, only first value of this array will be used.
	* Default Value: 0
	*/
	protected double fillAlphas;
	/**
	* Fill color. Will use lineColor if not set. You can also set array of colors here.
	* Default Value: 
	*/
	protected Color fillColors;
	/**
	* Name of the fill colors field in your dataProvider. This property adds a possibility to change line graphs’ fill color on any data point to create highlighted sections of the graph. Works only with AmSerialChart.
	* Default Value: 
	*/
	protected String fillColorsField;
	/**
	* XY chart only. If you set this property to id or reference of your X or Y axis, and the fillAlphas is > 0, the area between graph and axis will be filled with color, like in this demo.
	* Default Value: 
	*/
	protected ValueAxis fillToAxis;
	/**
	* You can set another graph here and if fillAlpha is >0, the area from this graph to fillToGraph will be filled (instead of filling the area to the X axis).
	* Default Value: 
	*/
	protected AmGraph fillToGraph;
	/**
	* Column width in pixels. If you set this property, columns will be of a fixed width and won't adjust to the available space.
	* Default Value: 
	*/
	protected double fixedColumnWidth;
	/**
	* Size of value labels text. Will use chart's fontSize if not set.
	* Default Value: 
	*/
	protected double fontSize;
	/**
	* Orientation of the gradient fills (only for ""column"" graph type). Possible values are ""vertical"" and ""horizontal"".
	* Default Value: vertical
	*/
	protected String gradientOrientation;
	/**
	* Specifies whether the graph is hidden. Do not use this to show/hide the graph, use hideGraph(graph) and showGraph(graph) methods instead.
	* Default Value: FALSE
	*/
	protected boolean hidden;
	/**
	* If there are more data points than hideBulletsCount, the bullets will not be shown. 0 means the bullets will always be visible.
	* Default Value: 0
	*/
	protected double hideBulletsCount;
	/**
	* Name of the high field (used by candlesticks and ohlc) in your dataProvider.
	* Default Value: 
	*/
	protected String highField;
	/**
	* Unique id of a graph. It is not required to set one, unless you want to use this graph for as your scrollbar's graph and need to indicate which graph should be used.
	* Default Value: 
	*/
	protected String id;
	/**
	* Whether to include this graph when calculating min and max value of the axis.
	* Default Value: TRUE
	*/
	protected boolean includeInMinMax;
	/**
	* Data label text anchor.
	* Default Value: auto
	*/
	protected String labelAnchor;
	/**
	* Name of label color field in data provider.
	* You can use it to format labels of data items in any way you want. Graph will call this function and pass reference to GraphDataItem and formatted text as attributes. This function should return string which will be displayed as label.
	* Default Value: 
	*/
	protected String labelColorField;
	/**
	* Offset of data label.
	* Default Value: 0
	*/
	protected double labelOffset;
	/**
	* Position of value label. Possible values are: ""bottom"", ""top"", ""right"", ""left"", ""inside"", ""middle"". Sometimes position is changed by the chart, depending on a graph type, rotation, etc.
	* Default Value: top
	*/
	protected String labelPosition;
	/**
	* Rotation of a data label.
	* Default Value: 0
	*/
	protected double labelRotation;
	/**
	* Value label text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]].
	* Default Value: 
	*/
	protected String labelText;
	/**
	* Legend marker opacity. Will use lineAlpha if not set. Value range is 0 - 1.
	* Default Value: 
	*/
	protected double legendAlpha;
	/**
	* Legend marker color. Will use lineColor if not set.
	* Default Value: 
	*/
	protected Color legendColor;
	/**
	* The text which will be displayed in the value portion of the legend when user is not hovering above any data point. The tags should be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you want to be show - open / close / high / low / sum / average / count. For example: [[value.sum]] means that sum of all data points of value field in the selected period will be displayed.
	* Default Value: 
	*/
	protected String legendPeriodValueText;
	/**
	* Legend value text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]] You can also use custom fields from your dataProvider. If not set, uses Legend's valueText.
	* Default Value: 
	*/
	protected String legendValueText;
	/**
	* Opacity of the line (or column border). Value range is 0 - 1.
	* Default Value: 1
	*/
	protected double lineAlpha;
	/**
	* Color of the line (or column border). If you do not set any, the color fromAmCoordinateChart.colors array will be used for each subsequent graph.
	* Default Value: 
	*/
	protected Color lineColor;
	/**
	* Name of the line color field in your dataProvider. This property adds a possibility to change graphs’ line color on any data point to create highlighted sections of the graph. Works only with AmSerialChart.
	* Default Value: 
	*/
	protected String lineColorField;
	/**
	* Specifies thickness of the graph line (or column border).
	* Default Value: 1
	*/
	protected double lineThickness;
	/**
	* Name of the low field (used by candlesticks and ohlc) in your dataProvider.
	* Default Value: 
	*/
	protected String lowField;
	/**
	* Legend marker type. You can set legend marker (key) type for individual graphs. Possible values are: square, circle, diamond, triangleUp, triangleDown, triangleLeft, triangleDown, bubble, line, none.
	* Default Value: 
	*/
	protected String markerType;
	/**
	* Specifies size of the bullet which value is the biggest (XY chart).
	* Default Value: 50
	*/
	protected double maxBulletSize;
	/**
	* Specifies minimum size of the bullet (XY chart).
	* Default Value: 0
	*/
	protected double minBulletSize;
	/**
	* It is useful if you have really lots of data points. Based on this property the graph will omit some of the lines (if the distance between points is less that minDistance, in pixels). This will not affect the bullets or indicator in anyway, so the user will not see any difference (unless you set minValue to a bigger value, let say 5), but will increase performance as less lines will be drawn. By setting value to a bigger number you can also make your lines look less jagged.
	* Default Value: 1
	*/
	protected double minDistance;
	/**
	* If you use different colors for your negative values, a graph below zero line is filled with negativeColor. With this property you can define a different base value at which colors should be changed to negative colors.
	* Default Value: 0
	*/
	protected double negativeBase;
	/**
	* Fill opacity of negative part of the graph. Will use fillAlphas if not set.
	* Default Value: 
	*/
	protected double negativeFillAlphas;
	/**
	* Fill color of negative part of the graph. Will use fillColors if not set.
	* Default Value: 
	*/
	protected Color negativeFillColors;
	/**
	* Opacity of the negative portion of the line (or column border). Value range is 0 - 1.
	* Default Value: 1
	*/
	protected double negativeLineAlpha;
	/**
	* Color of the line (or column) when the values are negative. In case the graph type is candlestick or ohlc, negativeLineColor is used when close value is less then open value.
	* Default Value: 
	*/
	protected Color negativeLineColor;
	/**
	* If you set it to true, column chart will begin new stack. This allows having Clustered and Stacked column/bar chart.
	* Default Value: FALSE
	*/
	protected boolean newStack;
	/**
	* In case you want to have a step line graph without risers, you should set this to true.
	* Default Value: FALSE
	*/
	protected boolean noStepRisers;
	/**
	* Name of the open field (used by floating columns, candlesticks and ohlc) in your dataProvider.
	* Default Value: 
	*/
	protected String openField;
	/**
	* Value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values. For example: {""url"":""../amcharts/patterns/black/pattern1.png"", ""width"":4, ""height"":4}. If you want to have individual patterns for each column, define patterns in data provider and set graph.patternField property. Check amcharts/patterns folder for some patterns. You can create your own patterns and use them. Note, x, y, randomX and randomY properties won't work with IE8 and older. 3D bar/Pie charts won't work properly with patterns.
	* Default Value: 
	*/
	protected Object pattern;
	/**
	* Field name in your data provider which holds pattern information. Value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values. For example: {""url"":""../amcharts/patterns/black/pattern1.png"", ""width"":4, ""height"":4}. Check amcharts/patterns folder for some patterns. You can create your own patterns and use them. Note, x, y, randomX and randomY properties won't work with IE8 and older. 3D bar/Pie charts won't work properly with patterns.
	* Default Value: 
	*/
	protected String patternField;
	/**
	* This property can be used by step graphs - you can set how many periods one horizontal line should span.
	* Default Value: 1
	*/
	protected double periodSpan;
	/**
	* Specifies where data points should be placed - on the beginning of the period (day, hour, etc) or in the middle (only when parseDates property of categoryAxis is set to true). This setting affects Serial chart only. Possible values are ""start"", ""middle"" and ""end""
	* Default Value: middle
	*/
	protected String pointPosition;
	/**
	* Precision of values. Will use chart's precision if not set any.
	* Default Value: 
	*/
	protected double precision;
	/**
	* If this is set to true, candlesticks will be colored in a different manner - if current close is less than current open, the candlestick will be empty, otherwise - filled with color. If previous close is less than current close, the candlestick will use positive color, otherwise - negative color.
	* Default Value: FALSE
	*/
	protected boolean proCandlesticks;
	/**
	* If graph's type is column and labelText is set, graph hides labels which do not fit into the column's space. If you don't want these labels to be hidden, set this to true.
	* Default Value: FALSE
	*/
	protected boolean showAllValueLabels;
	/**
	* Specifies whether the value balloon of this graph is shown when mouse is over data item or chart's indicator is over some series.
	* Default Value: TRUE
	*/
	protected boolean showBalloon;
	/**
	* Specifies graphs value at which cursor is showed. This is only important for candlestick and ohlc charts, also if column chart has ""open"" value. Possible values are: ""open"", ""close"", ""high"", ""low"".
	* Default Value: close
	*/
	protected String showBalloonAt;
	/**
	* Works with candlestick graph type, you can set it to open, close, high, low. If you set it to high, the events will be shown at the tip of the high line.
	* Default Value: close
	*/
	protected String showBulletsAt;
	/**
	* If you want mouse pointer to change to hand when hovering the graph, set this property to true.
	* Default Value: FALSE
	*/
	protected boolean showHandOnHover;
	/**
	* It can only be used together with topRadius (when columns look like cylinders). If you set it to true, the cylinder will be lowered down so that the center of it's bottom circle would be right on category axis.
	* Default Value: FALSE
	*/
	protected boolean showOnAxis;
	/**
	* If the value axis of this graph has stack types like ""regular"" or ""100%"" You can exclude this graph from stacking.
	* Default Value: TRUE
	*/
	protected boolean stackable;
	/**
	* Step graph only. Specifies to which direction step should be drawn.
	* Default Value: right
	*/
	protected String stepDirection;
	/**
	* If you set it to false, the graph will not be hidden when user clicks on legend entry.
	* Default Value: TRUE
	*/
	protected boolean switchable;
	/**
	* Graph title.
	* Default Value: 
	*/
	protected String title;
	/**
	* If you set this to 1, columns will become cylinders (must set depth3D and angle properties of a chart to >0 values in order this to be visible). you can make columns look like cones (set topRadius to 0) or even like some glasses (set to bigger than 1). We strongly recommend setting grid opacity to 0 in order this to look good.
	* Default Value: 
	*/
	protected double topRadius;
	/**
	* Type of the graph. Possible values are: ""line"", ""column"", ""step"", ""smoothedLine"", ""candlestick"", ""ohlc"". XY and Radar charts can only display ""line"" type graphs.
	* Default Value: line
	*/
	protected ChartTypeAm type;
	/**
	* Name of the url field in your dataProvider.
	* Default Value: 
	*/
	protected String urlField;
	/**
	* Target to open URLs in, i.e. _blank, _top, etc.
	* Default Value: 
	*/
	protected String urlTarget;
	/**
	* If negativeLineColor and/or negativeFillColors are set and useNegativeColorIfDown is set to true (default is false), the line, step and column graphs will use these colors for lines, bullets or columns if previous value is bigger than current value. In case you set openField for the graph, the graph will compare current value with openField value instead of comparing to previous value. Here is a demo.
	* Default Value: FALSE
	*/
	protected boolean useNegativeColorIfDown;
	/**
	* Specifies which value axis the graph will use. Will use the first value axis if not set. You can use reference to the real ValueAxis object or set value axis id.
	* Default Value: ValueAxis
	*/
	protected ValueAxis valueAxis;
	/**
	* Name of the value field in your dataProvider.
	* Default Value: 
	*/
	protected String valueField;
	/**
	* Specifies whether this graph should be shown in the Legend.
	* Default Value: TRUE
	*/
	protected boolean visibleInLegend;
	/**
	* XY chart only. A horizontal value axis object to attach graph to.
	* Default Value: ValueAxis
	*/
	protected ValueAxis xAxis;
	/**
	* XY chart only. Name of the x field in your dataProvider.
	* Default Value: 
	*/
	protected String xField;
	/**
	* XY chart only. A vertical value axis object to attach graph to.
	* Default Value: ValueAxis
	*/
	protected ValueAxis yAxis;
	/**
	* XY chart only. Name of the y field in your dataProvider.
	* Default Value: 
	*/
	protected String yField;
	
	/*
	 * Constructors
	 */
	public AmGraph() {
		
	}
	
	/*
	 * Getters and Setters
	 */
	public String getAlphaField() {
		return alphaField;
	}
	public void setAlphaField(String alphaField) {
		this.alphaField = alphaField;
	}
	public Color getBalloonColor() {
		return balloonColor;
	}
	public void setBalloonColor(Color balloonColor) {
		this.balloonColor = balloonColor;
	}
	public String getBalloonFunction() {
		return balloonFunction;
	}
	public void setBalloonFunction(String balloonFunction) {
		this.balloonFunction = balloonFunction;
	}
	public String getBalloonText() {
		return balloonText;
	}
	public void setBalloonText(String balloonText) {
		this.balloonText = balloonText;
	}
	public boolean isBehindColumns() {
		return behindColumns;
	}
	public void setBehindColumns(boolean behindColumns) {
		this.behindColumns = behindColumns;
	}
	public String getBullet() {
		return bullet;
	}
	public void setBullet(String bullet) {
		this.bullet = bullet;
	}
	public double getBulletAlpha() {
		return bulletAlpha;
	}
	public void setBulletAlpha(double bulletAlpha) {
		this.bulletAlpha = bulletAlpha;
	}
	public ValueAxis getBulletAxis() {
		return bulletAxis;
	}
	public void setBulletAxis(ValueAxis bulletAxis) {
		this.bulletAxis = bulletAxis;
	}
	public double getBulletBorderAlpha() {
		return bulletBorderAlpha;
	}
	public void setBulletBorderAlpha(double bulletBorderAlpha) {
		this.bulletBorderAlpha = bulletBorderAlpha;
	}
	public Color getBulletBorderColor() {
		return bulletBorderColor;
	}
	public void setBulletBorderColor(Color bulletBorderColor) {
		this.bulletBorderColor = bulletBorderColor;
	}
	public double getBulletBorderThickness() {
		return bulletBorderThickness;
	}
	public void setBulletBorderThickness(double bulletBorderThickness) {
		this.bulletBorderThickness = bulletBorderThickness;
	}
	public Color getBulletColor() {
		return bulletColor;
	}
	public void setBulletColor(Color bulletColor) {
		this.bulletColor = bulletColor;
	}
	public String getBulletField() {
		return bulletField;
	}
	public void setBulletField(String bulletField) {
		this.bulletField = bulletField;
	}
	public double getBulletOffset() {
		return bulletOffset;
	}
	public void setBulletOffset(double bulletOffset) {
		this.bulletOffset = bulletOffset;
	}
	public double getBulletSize() {
		return bulletSize;
	}
	public void setBulletSize(double bulletSize) {
		this.bulletSize = bulletSize;
	}
	public String getBulletSizeField() {
		return bulletSizeField;
	}
	public void setBulletSizeField(String bulletSizeField) {
		this.bulletSizeField = bulletSizeField;
	}
	public String getCloseField() {
		return closeField;
	}
	public void setCloseField(String closeField) {
		this.closeField = closeField;
	}
	public boolean isClustered() {
		return clustered;
	}
	public void setClustered(boolean clustered) {
		this.clustered = clustered;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getColorField() {
		return colorField;
	}
	public void setColorField(String colorField) {
		this.colorField = colorField;
	}
	public double getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(double columnWidth) {
		this.columnWidth = columnWidth;
	}
	public boolean isConnect() {
		return connect;
	}
	public void setConnect(boolean connect) {
		this.connect = connect;
	}
	public double getCornerRadiusTop() {
		return cornerRadiusTop;
	}
	public void setCornerRadiusTop(double cornerRadiusTop) {
		this.cornerRadiusTop = cornerRadiusTop;
	}
	public double getCursorBulletAlpha() {
		return cursorBulletAlpha;
	}
	public void setCursorBulletAlpha(double cursorBulletAlpha) {
		this.cursorBulletAlpha = cursorBulletAlpha;
	}
	public String getCustomBullet() {
		return customBullet;
	}
	public void setCustomBullet(String customBullet) {
		this.customBullet = customBullet;
	}
	public String getCustomBulletField() {
		return customBulletField;
	}
	public void setCustomBulletField(String customBulletField) {
		this.customBulletField = customBulletField;
	}
	public String getCustomMarker() {
		return customMarker;
	}
	public void setCustomMarker(String customMarker) {
		this.customMarker = customMarker;
	}
	public double getDashLength() {
		return dashLength;
	}
	public void setDashLength(double dashLength) {
		this.dashLength = dashLength;
	}
	public String getDashLengthField() {
		return dashLengthField;
	}
	public void setDashLengthField(String dashLengthField) {
		this.dashLengthField = dashLengthField;
	}
	public String getDescriptionField() {
		return descriptionField;
	}
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = descriptionField;
	}
	public String getErrorField() {
		return errorField;
	}
	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}
	public double getFillAlphas() {
		return fillAlphas;
	}
	public void setFillAlphas(double fillAlphas) {
		this.fillAlphas = fillAlphas;
	}
	public Color getFillColors() {
		return fillColors;
	}
	public void setFillColors(Color fillColors) {
		this.fillColors = fillColors;
	}
	public String getFillColorsField() {
		return fillColorsField;
	}
	public void setFillColorsField(String fillColorsField) {
		this.fillColorsField = fillColorsField;
	}
	public ValueAxis getFillToAxis() {
		return fillToAxis;
	}
	public void setFillToAxis(ValueAxis fillToAxis) {
		this.fillToAxis = fillToAxis;
	}
	public AmGraph getFillToGraph() {
		return fillToGraph;
	}
	public void setFillToGraph(AmGraph fillToGraph) {
		this.fillToGraph = fillToGraph;
	}
	public double getFixedColumnWidth() {
		return fixedColumnWidth;
	}
	public void setFixedColumnWidth(double fixedColumnWidth) {
		this.fixedColumnWidth = fixedColumnWidth;
	}
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	public String getGradientOrientation() {
		return gradientOrientation;
	}
	public void setGradientOrientation(String gradientOrientation) {
		this.gradientOrientation = gradientOrientation;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public double getHideBulletsCount() {
		return hideBulletsCount;
	}
	public void setHideBulletsCount(double hideBulletsCount) {
		this.hideBulletsCount = hideBulletsCount;
	}
	public String getHighField() {
		return highField;
	}
	public void setHighField(String highField) {
		this.highField = highField;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isIncludeInMinMax() {
		return includeInMinMax;
	}
	public void setIncludeInMinMax(boolean includeInMinMax) {
		this.includeInMinMax = includeInMinMax;
	}
	public String getLabelAnchor() {
		return labelAnchor;
	}
	public void setLabelAnchor(String labelAnchor) {
		this.labelAnchor = labelAnchor;
	}
	public String getLabelColorField() {
		return labelColorField;
	}
	public void setLabelColorField(String labelColorField) {
		this.labelColorField = labelColorField;
	}
	public double getLabelOffset() {
		return labelOffset;
	}
	public void setLabelOffset(double labelOffset) {
		this.labelOffset = labelOffset;
	}
	public String getLabelPosition() {
		return labelPosition;
	}
	public void setLabelPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}
	public double getLabelRotation() {
		return labelRotation;
	}
	public void setLabelRotation(double labelRotation) {
		this.labelRotation = labelRotation;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public double getLegendAlpha() {
		return legendAlpha;
	}
	public void setLegendAlpha(double legendAlpha) {
		this.legendAlpha = legendAlpha;
	}
	public Color getLegendColor() {
		return legendColor;
	}
	public void setLegendColor(Color legendColor) {
		this.legendColor = legendColor;
	}
	public String getLegendPeriodValueText() {
		return legendPeriodValueText;
	}
	public void setLegendPeriodValueText(String legendPeriodValueText) {
		this.legendPeriodValueText = legendPeriodValueText;
	}
	public String getLegendValueText() {
		return legendValueText;
	}
	public void setLegendValueText(String legendValueText) {
		this.legendValueText = legendValueText;
	}
	public double getLineAlpha() {
		return lineAlpha;
	}
	public void setLineAlpha(double lineAlpha) {
		this.lineAlpha = lineAlpha;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public String getLineColorField() {
		return lineColorField;
	}
	public void setLineColorField(String lineColorField) {
		this.lineColorField = lineColorField;
	}
	public double getLineThickness() {
		return lineThickness;
	}
	public void setLineThickness(double lineThickness) {
		this.lineThickness = lineThickness;
	}
	public String getLowField() {
		return lowField;
	}
	public void setLowField(String lowField) {
		this.lowField = lowField;
	}
	public String getMarkerType() {
		return markerType;
	}
	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}
	public double getMaxBulletSize() {
		return maxBulletSize;
	}
	public void setMaxBulletSize(double maxBulletSize) {
		this.maxBulletSize = maxBulletSize;
	}
	public double getMinBulletSize() {
		return minBulletSize;
	}
	public void setMinBulletSize(double minBulletSize) {
		this.minBulletSize = minBulletSize;
	}
	public double getMinDistance() {
		return minDistance;
	}
	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}
	public double getNegativeBase() {
		return negativeBase;
	}
	public void setNegativeBase(double negativeBase) {
		this.negativeBase = negativeBase;
	}
	public double getNegativeFillAlphas() {
		return negativeFillAlphas;
	}
	public void setNegativeFillAlphas(double negativeFillAlphas) {
		this.negativeFillAlphas = negativeFillAlphas;
	}
	public Color getNegativeFillColors() {
		return negativeFillColors;
	}
	public void setNegativeFillColors(Color negativeFillColors) {
		this.negativeFillColors = negativeFillColors;
	}
	public double getNegativeLineAlpha() {
		return negativeLineAlpha;
	}
	public void setNegativeLineAlpha(double negativeLineAlpha) {
		this.negativeLineAlpha = negativeLineAlpha;
	}
	public Color getNegativeLineColor() {
		return negativeLineColor;
	}
	public void setNegativeLineColor(Color negativeLineColor) {
		this.negativeLineColor = negativeLineColor;
	}
	public boolean isNewStack() {
		return newStack;
	}
	public void setNewStack(boolean newStack) {
		this.newStack = newStack;
	}
	public boolean isNoStepRisers() {
		return noStepRisers;
	}
	public void setNoStepRisers(boolean noStepRisers) {
		this.noStepRisers = noStepRisers;
	}
	public String getOpenField() {
		return openField;
	}
	public void setOpenField(String openField) {
		this.openField = openField;
	}
	public Object getPattern() {
		return pattern;
	}
	public void setPattern(Object pattern) {
		this.pattern = pattern;
	}
	public String getPatternField() {
		return patternField;
	}
	public void setPatternField(String patternField) {
		this.patternField = patternField;
	}
	public double getPeriodSpan() {
		return periodSpan;
	}
	public void setPeriodSpan(double periodSpan) {
		this.periodSpan = periodSpan;
	}
	public String getPointPosition() {
		return pointPosition;
	}
	public void setPointPosition(String pointPosition) {
		this.pointPosition = pointPosition;
	}
	public double getPrecision() {
		return precision;
	}
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	public boolean isProCandlesticks() {
		return proCandlesticks;
	}
	public void setProCandlesticks(boolean proCandlesticks) {
		this.proCandlesticks = proCandlesticks;
	}
	public boolean isShowAllValueLabels() {
		return showAllValueLabels;
	}
	public void setShowAllValueLabels(boolean showAllValueLabels) {
		this.showAllValueLabels = showAllValueLabels;
	}
	public boolean isShowBalloon() {
		return showBalloon;
	}
	public void setShowBalloon(boolean showBalloon) {
		this.showBalloon = showBalloon;
	}
	public String getShowBalloonAt() {
		return showBalloonAt;
	}
	public void setShowBalloonAt(String showBalloonAt) {
		this.showBalloonAt = showBalloonAt;
	}
	public String getShowBulletsAt() {
		return showBulletsAt;
	}
	public void setShowBulletsAt(String showBulletsAt) {
		this.showBulletsAt = showBulletsAt;
	}
	public boolean isShowHandOnHover() {
		return showHandOnHover;
	}
	public void setShowHandOnHover(boolean showHandOnHover) {
		this.showHandOnHover = showHandOnHover;
	}
	public boolean isShowOnAxis() {
		return showOnAxis;
	}
	public void setShowOnAxis(boolean showOnAxis) {
		this.showOnAxis = showOnAxis;
	}
	public boolean isStackable() {
		return stackable;
	}
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}
	public String getStepDirection() {
		return stepDirection;
	}
	public void setStepDirection(String stepDirection) {
		this.stepDirection = stepDirection;
	}
	public boolean isSwitchable() {
		return switchable;
	}
	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getTopRadius() {
		return topRadius;
	}
	public void setTopRadius(double topRadius) {
		this.topRadius = topRadius;
	}
	public ChartTypeAm getType() {
		return type;
	}
	public void setType(ChartTypeAm type) {
		this.type = type;
	}
	public String getUrlField() {
		return urlField;
	}
	public void setUrlField(String urlField) {
		this.urlField = urlField;
	}
	public String getUrlTarget() {
		return urlTarget;
	}
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}
	public boolean isUseNegativeColorIfDown() {
		return useNegativeColorIfDown;
	}
	public void setUseNegativeColorIfDown(boolean useNegativeColorIfDown) {
		this.useNegativeColorIfDown = useNegativeColorIfDown;
	}
	public ValueAxis getValueAxis() {
		return valueAxis;
	}
	public void setValueAxis(ValueAxis valueAxis) {
		this.valueAxis = valueAxis;
	}
	public String getValueField() {
		return valueField;
	}
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public boolean isVisibleInLegend() {
		return visibleInLegend;
	}
	public void setVisibleInLegend(boolean visibleInLegend) {
		this.visibleInLegend = visibleInLegend;
	}
	public ValueAxis getxAxis() {
		return xAxis;
	}
	public void setxAxis(ValueAxis xAxis) {
		this.xAxis = xAxis;
	}
	public String getxField() {
		return xField;
	}
	public void setxField(String xField) {
		this.xField = xField;
	}
	public ValueAxis getyAxis() {
		return yAxis;
	}
	public void setyAxis(ValueAxis yAxis) {
		this.yAxis = yAxis;
	}
	public String getyField() {
		return yField;
	}
	public void setyField(String yField) {
		this.yField = yField;
	}

}
