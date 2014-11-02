package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.ChartCursor;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.ChartScrollbar;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.TrendLine;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmRectangularChart extends AmCoordinateChart {

	/**
	* The angle of the 3D part of plot area. This creates a 3D effect (if the ""depth3D"" is > 0).
	* Default Value: 0
	*/
	protected double angle;
	/**
	* Space left from axis labels/title to the chart's outside border, if autoMargins set to true.
	* Default Value: 10
	*/
	protected double autoMarginOffset;
	/**
	* Specifies if margins of a chart should be calculated automatically so that labels of axes would fit. The chart will adjust only margins with axes. Other margins will use values set with marginRight, marginTop, marginLeft and marginBottom properties.
	* Default Value: TRUE
	*/
	protected boolean autoMargins;
	/**
	* Cursor of a chart.
	* Default Value: 
	*/
	protected ChartCursor chartCursor;
	/**
	* Chart's scrollbar.
	* Default Value: 
	*/
	protected ChartScrollbar chartScrollbar;
	/**
	* The depth of the 3D part of plot area. This creates a 3D effect (if the ""angle"" is > 0).
	* Default Value: 0
	*/
	protected double depth3D;
	/**
	* Number of pixels between the container's bottom border and plot area. This space can be used for bottom axis' values. If autoMargin is true and bottom side has axis, this property is ignored.
	* Default Value: 20
	*/
	protected double marginBottom;
	/**
	* Number of pixels between the container's left border and plot area. This space can be used for left axis' values. If autoMargin is true and left side has axis, this property is ignored.
	* Default Value: 20
	*/
	protected double marginLeft;
	/**
	* Number of pixels between the container's right border and plot area. This space can be used for Right axis' values. If autoMargin is true and right side has axis, this property is ignored.
	* Default Value: 20
	*/
	protected double marginRight;
	/**
	* Flag which should be set to false if you need margins to be recalculated on next chart.validateNow() call.
	* Default Value: FALSE
	*/
	protected boolean marginsUpdated;
	/**
	* Number of pixels between the container's top border and plot area. This space can be used for top axis' values. If autoMargin is true and top side has axis, this property is ignored.
	* Default Value: 20
	*/
	protected double marginTop;
	/**
	* The opacity of plot area's border. Value range is 0 - 1.
	* Default Value: 0
	*/
	protected double plotAreaBorderAlpha;
	/**
	* The color of the plot area's border. Note, the it is invisible by default, as plotAreaBorderAlpha default value is 0. Set it to a value higher than 0 to make it visible.
	* Default Value: #000000
	*/
	protected Color plotAreaBorderColor;
	/**
	* Opacity of plot area. Plural form is used to keep the same property names as our Flex charts'. Flex charts can accept array of numbers to generate gradients. Although you can set array here, only first value of this array will be used.
	* Default Value: 0
	*/
	protected double plotAreaFillAlphas;
	/**
	* You can set both one color if you need a solid color or array of colors to generate gradients, for example: [""#000000"", ""#0000CC""]
	* Default Value: #FFFFFF
	*/
	protected Color plotAreaFillColors;
	/**
	* If you are using gradients to fill the plot area, you can use this property to set gradient angle. The only allowed values are horizontal and vertical: 0, 90, 180, 270.
	* Default Value: 0
	*/
	protected double plotAreaGradientAngle;
	/**
	* Array of trend lines added to a chart. You can add trend lines to a chart using this array or access already existing trend lines
	* Default Value: 
	*/
	protected List<TrendLine> trendLines;
	/**
	* Opacity of zoom-out button background.
	* Default Value: 0
	*/
	protected double zoomOutButtonAlpha;
	/**
	* Zoom-out button background color.
	* Default Value: #e5e5e5
	*/
	protected Color zoomOutButtonColor;
	/**
	* Name of zoom-out button image. In the images folder there is another lens image, called lensWhite.png. You might want to have white lens when background is dark. Or you can simply use your own image.
	* Default Value: lens.png
	*/
	protected String zoomOutButtonImage;
	/**
	* Size of zoom-out button image
	* Default Value: 17
	*/
	protected double zoomOutButtonImageSize;
	/**
	* Padding around the text and image.
	* Default Value: 8
	*/
	protected double zoomOutButtonPadding;
	/**
	* Opacity of zoom-out button background when mouse is over it.
	* Default Value: 1
	*/
	protected double zoomOutButtonRollOverAlpha;
	/**
	* Text in the zoom-out button.
	* Default Value: Show all
	*/
	protected String zoomOutText;
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getAutoMarginOffset() {
		return autoMarginOffset;
	}
	public void setAutoMarginOffset(double autoMarginOffset) {
		this.autoMarginOffset = autoMarginOffset;
	}
	public boolean isAutoMargins() {
		return autoMargins;
	}
	public void setAutoMargins(boolean autoMargins) {
		this.autoMargins = autoMargins;
	}
	public ChartCursor getChartCursor() {
		return chartCursor;
	}
	public void setChartCursor(ChartCursor chartCursor) {
		this.chartCursor = chartCursor;
	}
	public ChartScrollbar getChartScrollbar() {
		return chartScrollbar;
	}
	public void setChartScrollbar(ChartScrollbar chartScrollbar) {
		this.chartScrollbar = chartScrollbar;
	}
	public double getDepth3D() {
		return depth3D;
	}
	public void setDepth3D(double depth3d) {
		depth3D = depth3d;
	}
	public double getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(double marginBottom) {
		this.marginBottom = marginBottom;
	}
	public double getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(double marginLeft) {
		this.marginLeft = marginLeft;
	}
	public double getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(double marginRight) {
		this.marginRight = marginRight;
	}
	public boolean isMarginsUpdated() {
		return marginsUpdated;
	}
	public void setMarginsUpdated(boolean marginsUpdated) {
		this.marginsUpdated = marginsUpdated;
	}
	public double getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(double marginTop) {
		this.marginTop = marginTop;
	}
	public double getPlotAreaBorderAlpha() {
		return plotAreaBorderAlpha;
	}
	public void setPlotAreaBorderAlpha(double plotAreaBorderAlpha) {
		this.plotAreaBorderAlpha = plotAreaBorderAlpha;
	}
	public Color getPlotAreaBorderColor() {
		return plotAreaBorderColor;
	}
	public void setPlotAreaBorderColor(Color plotAreaBorderColor) {
		this.plotAreaBorderColor = plotAreaBorderColor;
	}
	public double getPlotAreaFillAlphas() {
		return plotAreaFillAlphas;
	}
	public void setPlotAreaFillAlphas(double plotAreaFillAlphas) {
		this.plotAreaFillAlphas = plotAreaFillAlphas;
	}
	public Color getPlotAreaFillColors() {
		return plotAreaFillColors;
	}
	public void setPlotAreaFillColors(Color plotAreaFillColors) {
		this.plotAreaFillColors = plotAreaFillColors;
	}
	public double getPlotAreaGradientAngle() {
		return plotAreaGradientAngle;
	}
	public void setPlotAreaGradientAngle(double plotAreaGradientAngle) {
		this.plotAreaGradientAngle = plotAreaGradientAngle;
	}
	public List<TrendLine> getTrendLines() {
		return trendLines;
	}
	public void setTrendLines(List<TrendLine> trendLines) {
		this.trendLines = trendLines;
	}
	public double getZoomOutButtonAlpha() {
		return zoomOutButtonAlpha;
	}
	public void setZoomOutButtonAlpha(double zoomOutButtonAlpha) {
		this.zoomOutButtonAlpha = zoomOutButtonAlpha;
	}
	public Color getZoomOutButtonColor() {
		return zoomOutButtonColor;
	}
	public void setZoomOutButtonColor(Color zoomOutButtonColor) {
		this.zoomOutButtonColor = zoomOutButtonColor;
	}
	public String getZoomOutButtonImage() {
		return zoomOutButtonImage;
	}
	public void setZoomOutButtonImage(String zoomOutButtonImage) {
		this.zoomOutButtonImage = zoomOutButtonImage;
	}
	public double getZoomOutButtonImageSize() {
		return zoomOutButtonImageSize;
	}
	public void setZoomOutButtonImageSize(double zoomOutButtonImageSize) {
		this.zoomOutButtonImageSize = zoomOutButtonImageSize;
	}
	public double getZoomOutButtonPadding() {
		return zoomOutButtonPadding;
	}
	public void setZoomOutButtonPadding(double zoomOutButtonPadding) {
		this.zoomOutButtonPadding = zoomOutButtonPadding;
	}
	public double getZoomOutButtonRollOverAlpha() {
		return zoomOutButtonRollOverAlpha;
	}
	public void setZoomOutButtonRollOverAlpha(double zoomOutButtonRollOverAlpha) {
		this.zoomOutButtonRollOverAlpha = zoomOutButtonRollOverAlpha;
	}
	public String getZoomOutText() {
		return zoomOutText;
	}
	public void setZoomOutText(String zoomOutText) {
		this.zoomOutText = zoomOutText;
	}

}
