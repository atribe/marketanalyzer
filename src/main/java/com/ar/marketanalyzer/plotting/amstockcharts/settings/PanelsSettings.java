package com.ar.marketanalyzer.plotting.amstockcharts.settings;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.BigPrefix;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.CreditsPosition;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.RecalculateToPercents;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.SmallPrefix;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StartEffect;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.Transparency;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PanelsSettings {
	/**
	* The angle of the 3D part of plot area. This creates a 3D effect (if the depth3D is > 0).
	* Default Value: 
	*/
	 private double angle;
	/**
	* Opacity of panel background. Possible values are 1 and 0. Values like 0.5 will not make it half-transparent.
	* Default Value: 0
	*/
	 private Transparency backgroundAlpha;
	/**
	* Background color of panels. Set backgroundAlpha to > 0 value in order to make background visible.
	* Default Value: #FFFFFF
	*/
	 private Color backgroundColor;
	/**
	* The gap in pixels between two columns of the same category.
	* Default Value: 
	*/
	 private double columnSpacing;
	/**
	* Relative width of columns. Valid values 0 - 1.
	* Default Value: 
	*/
	 private double columnWidth;
	/**
	* Position of amCharts link (free version only). Possible values are: top-left. top-right, bottom-left, bottom-right
	* You can adjust the position of amcharts link so that it would not overlap with contents of your chart.
	* Default Value: top-right
	*/
	 private CreditsPosition creditsPosition;
	/**
	* Separator of decimal values.
	* Default Value: 
	*/
	 private String decimalSeparator;
	/**
	* The depth of the 3D part of plot area. This creates a 3D effect (if the angle is > 0).
	* Default Value: 
	*/
	 private double depth3D;
	/**
	* Font family.
	* Default Value: 
	*/
	 private String fontFamily;
	/**
	* Font size.
	* Default Value: 
	*/
	 private String fontSize;
	/**
	* Number of pixels between the container's bottom border and plot area.
	* Default Value: 0
	*/
	 private double marginBottom;
	/**
	* Number of pixels between the container's left border and plot area. If your left valueAxis values ar not placed inside the plot area, you should set marginLeft to 80 or some close value.
	* Default Value: 0
	*/
	 private double marginLeft;
	/**
	* Number of pixels between the container's left border and plot area. If your right valueAxis values ar not placed inside the plot area, you should set marginRight to 80 or some close value.
	* Default Value: 0
	*/
	 private double marginRight;
	/**
	* Number of pixels between the container's top border and plot area.
	* Default Value: 0
	*/
	 private double marginTop;
	/**
	* Gap between panels.
	* Default Value: 8
	*/
	 private double panelSpacing;
	/**
	* This setting affects touch-screen devices only. If a chart is on a page, and panEventsEnabled are set to true, the page won't move if the user touches the chart first. If a chart is big enough and occupies all the screen of your touch device, the user won’t be able to move the page at all. That's why the default value is false. If you think that selecting or or panning the chart is a primary purpose of your chart users, you should set panEventsEnabled to true.
	* Default Value: FALSE
	*/
	 private boolean panEventsEnabled;
	/**
	* Precision of percent values.
	* Default Value: 
	*/
	 private double percentPrecision;
	/**
	* The opacity of plot area's border.
	* Default Value: 
	*/
	 private double plotAreaBorderAlpha;
	/**
	* The color of the plot area's border.
	* Default Value: 
	*/
	 private Color plotAreaBorderColor;
	/**
	* Opacity of plot area fill.
	* Default Value: 
	*/
	 private double plotAreaFillAlphas;
	/**
	* Specifies the colors used to tint the background gradient fill of the plot area.
	* Default Value: 
	*/
	 private List<Color> plotAreaFillColors;
	/**
	* Precision of values. -1 means values will not be rounded and shown as they are.
	* Default Value: 
	*/
	 private double precision;
	/**
	* Prefixes which are used to make big numbers shorter: 2M instead of 2000000, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e+3,prefix:k},{number:1e+6,prefix:M},{number:1e+9,prefix:G},{number:1e+12,prefix:T},{number:1e+15,prefix:P},{number:1e+18,prefix:E},{number:1e+21,prefix:Z},{number:1e+24,prefix:Y}]
	*/
	 private List<BigPrefix> prefixesOfBigNumbers;
	/**
	* Prefixes which are used to make small numbers shorter: 2u instead of 0.000002, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e-24, prefix:y},{number:1e-21, prefix:z},{number:1e-18, prefix:a},{number:1e-15, prefix:f},{number:1e-12, prefix:p},{number:1e-9, prefix:n},{number:1e-6, prefix:u},{number:1e-3, prefix:m}]
	*/
	 private List<SmallPrefix> prefixesOfSmallNumbers;
	/**
	* Specifies when values should be recalculated to percents. Possible values are: never, always, whenComparing.
	* Default Value: whenComparing
	*/
	 private RecalculateToPercents recalculateToPercents;
	/**
	* Specifies whether the animation should be sequenced or all objects should appear at once.
	* Default Value: 
	*/
	 private boolean sequencedAnimation;
	/**
	* The initial opacity of the column/line. If you set startDuration to a value higher than 0, the columns/lines will fade in from startAlpha.
	* Default Value: 
	*/
	 private double startAlpha;
	/**
	* Duration of the animation, in seconds.
	* Default Value: 
	*/
	 private double startDuration;
	/**
	* Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: 
	*/
	 private StartEffect startEffect;
	/**
	* Separator of thousand values.
	* Default Value: 
	*/
	 private String thousandsSeparator;
	/**
	* If true, prefixes will be used for big and small numbers.
	* Default Value: 
	*/
	 private boolean usePrefixes;
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public Transparency getBackgroundAlpha() {
		return backgroundAlpha;
	}
	public void setBackgroundAlpha(Transparency backgroundAlpha) {
		this.backgroundAlpha = backgroundAlpha;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public double getColumnSpacing() {
		return columnSpacing;
	}
	public void setColumnSpacing(double columnSpacing) {
		this.columnSpacing = columnSpacing;
	}
	public double getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(double columnWidth) {
		this.columnWidth = columnWidth;
	}
	public CreditsPosition getCreditsPosition() {
		return creditsPosition;
	}
	public void setCreditsPosition(CreditsPosition creditsPosition) {
		this.creditsPosition = creditsPosition;
	}
	public String getDecimalSeparator() {
		return decimalSeparator;
	}
	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}
	public double getDepth3D() {
		return depth3D;
	}
	public void setDepth3D(double depth3d) {
		depth3D = depth3d;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
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
	public double getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(double marginTop) {
		this.marginTop = marginTop;
	}
	public double getPanelSpacing() {
		return panelSpacing;
	}
	public void setPanelSpacing(double panelSpacing) {
		this.panelSpacing = panelSpacing;
	}
	public boolean isPanEventsEnabled() {
		return panEventsEnabled;
	}
	public void setPanEventsEnabled(boolean panEventsEnabled) {
		this.panEventsEnabled = panEventsEnabled;
	}
	public double getPercentPrecision() {
		return percentPrecision;
	}
	public void setPercentPrecision(double percentPrecision) {
		this.percentPrecision = percentPrecision;
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
	public List<Color> getPlotAreaFillColors() {
		return plotAreaFillColors;
	}
	public void setPlotAreaFillColors(List<Color> plotAreaFillColors) {
		this.plotAreaFillColors = plotAreaFillColors;
	}
	public double getPrecision() {
		return precision;
	}
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	public List<BigPrefix> getPrefixesOfBigNumbers() {
		return prefixesOfBigNumbers;
	}
	public void setPrefixesOfBigNumbers(List<BigPrefix> prefixesOfBigNumbers) {
		this.prefixesOfBigNumbers = prefixesOfBigNumbers;
	}
	public List<SmallPrefix> getPrefixesOfSmallNumbers() {
		return prefixesOfSmallNumbers;
	}
	public void setPrefixesOfSmallNumbers(List<SmallPrefix> prefixesOfSmallNumbers) {
		this.prefixesOfSmallNumbers = prefixesOfSmallNumbers;
	}
	public RecalculateToPercents getRecalculateToPercents() {
		return recalculateToPercents;
	}
	public void setRecalculateToPercents(RecalculateToPercents recalculateToPercents) {
		this.recalculateToPercents = recalculateToPercents;
	}
	public boolean isSequencedAnimation() {
		return sequencedAnimation;
	}
	public void setSequencedAnimation(boolean sequencedAnimation) {
		this.sequencedAnimation = sequencedAnimation;
	}
	public double getStartAlpha() {
		return startAlpha;
	}
	public void setStartAlpha(double startAlpha) {
		this.startAlpha = startAlpha;
	}
	public double getStartDuration() {
		return startDuration;
	}
	public void setStartDuration(double startDuration) {
		this.startDuration = startDuration;
	}
	public StartEffect getStartEffect() {
		return startEffect;
	}
	public void setStartEffect(StartEffect startEffect) {
		this.startEffect = startEffect;
	}
	public String getThousandsSeparator() {
		return thousandsSeparator;
	}
	public void setThousandsSeparator(String thousandsSeparator) {
		this.thousandsSeparator = thousandsSeparator;
	}
	public boolean isUsePrefixes() {
		return usePrefixes;
	}
	public void setUsePrefixes(boolean usePrefixes) {
		this.usePrefixes = usePrefixes;
	}

}
