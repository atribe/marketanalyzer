package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.List;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmBalloon;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmLegend;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.Label;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.Title;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.AmTheme;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.BigPrefix;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.ChartTypeAm;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.SmallPrefix;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmChart {

	/**
	* Array of Labels. Example of label object, with all possible properties:
	{""x"": 20, ""y"": 20, ""text"": ""this is label"", ""align"": ""left"", ""size"": 12, ""color"": ""#CC0000"", ""alpha"": 1, ""rotation"": 0, ""bold"": true, ""url"": ""http://www.amcharts.com""}
	* Default Value: []
	*/
	protected List<Label> allLabels;
	/**
	* AmExport object.
	* Default Value: 
	*/
	protected AmExport amExport;
	/**
	* Opacity of background. Set it to >0 value if you want backgroundColor to work. However we recommend changing div's background-color style for changing background color.
	* Default Value: 0
	*/
	protected Double backgroundAlpha;
	/**
	* Background color. You should set backgroundAlpha to >0 value in order background to be visible. We recommend setting background color directly on a chart's DIV instead of using this property.
	* Default Value: #FFFFFF
	*/
	protected Color backgroundColor;
	/**
	* The chart creates AmBalloon class itself. If you want to customize balloon, get balloon instance using this property, and then change balloon's properties.
	* Default Value: AmBalloon
	*/
	protected AmBalloon balloon;
	/**
	* Opacity of chart's border. Value range is 0 - 1.
	* Default Value: 0
	*/
	protected Double borderAlpha;
	/**
	* Color of chart's border. You should set borderAlpha >0 in order border to be visible. We recommend setting border color directly on a chart's DIV instead of using this property.
	* Default Value: #000000
	*/
	protected Color borderColor;
	/**
	* Text color.
	* Default Value: #000000
	*/
	protected Color color;
	/**
	* Non-commercial version only. Specifies position of link to amCharts site.
	* Default Value: top-left
	*/
	protected String creditsPosition;
	/**
	* Array of data objects, for example: [{country:""US"", value:524},{country:""UK"", value:624},{country:""Lithuania"", value:824}]. You can have any number of fields and use any field names. In case of AmMap, data provider should be MapData object.
	* Default Value: 
	*/
	protected List<Object> dataProvider;
	/**
	* Decimal separator.
	* Default Value: .
	*/
	protected String decimalSeparator;
	/**
	* Object of export config. Will enable saving chart as an image for all modern browsers except IE9 (IE10+ works fine). Check this article for more info.
	* Default Value: 
	*/
	protected Object exportConfig;
	/**
	* Font family.
	* Default Value: Verdana
	*/
	protected String fontFamily;
	/**
	* Font size.
	* Default Value: 11
	*/
	protected Double fontSize;
	/**
	* If you set this to true, the lines of the chart will be distorted and will produce hand-drawn effect. Try to adjust chart.handDrawScatter and chart.handDrawThickness properties for a more scattered result.
	* Default Value: FALSE
	*/
	protected Boolean handDrawn;
	/**
	* Defines by how many pixels hand-drawn line (when handDrawn is set to true) will fluctuate.
	* Default Value: 2
	*/
	protected Double handDrawScatter;
	/**
	* Defines by how many pixels line thickness will fluctuate (when handDrawn is set to true).
	* Default Value: 1
	*/
	protected Double handDrawThickness;
	/**
	* Time, in milliseconds after which balloon is hidden if the user rolls-out of the object. Might be useful for AmMap to avoid balloon flickering while moving mouse over the areas. Note, this is not duration of fade-out. Duration of fade-out is set in AmBalloon class.
	* Default Value: 150
	*/
	protected Double hideBalloonTime;
	/**
	* Allows changing language easily. Note, you should include language js file from amcharts/lang or ammap/lang folder and then use variable name used in this file, like chart.language = ""de""; Note, for maps this works differently - you use language only for country names, as there are no other strings in the maps application.
	* Default Value: 
	*/
	protected String language;
	/**
	* Legend of a chart.
	* Default Value: 
	*/
	protected AmLegend legend;
	/**
	* Read-only. Reference to the div of the legend.
	* Default Value: 
	*/
	protected String legendDiv;
	/**
	* This setting affects touch-screen devices only. If a chart is on a page, and panEventsEnabled are set to true, the page won't move if the user touches the chart first. If a chart is big enough and occupies all the screen of your touch device, the user won’t be able to move the page at all. If you think that selecting/panning the chart or moving/pinching the map is a primary purpose of your users, you should set panEventsEnabled to true, otherwise - false.
	* Default Value: TRUE
	*/
	protected Boolean panEventsEnabled;
	/**
	* Specifies path to the folder where images like resize grips, lens and similar are.
	* Default Value: 
	*/
	protected String pathToImages;
	/**
	* Precision of percent values. -1 means percent values won't be rounded at all and show as they are.
	* Default Value: 2
	*/
	protected Double percentPrecision;
	/**
	* Precision of values. -1 means values won't be rounded at all and show as they are.
	* Default Value: -1
	*/
	protected Double precision;
	/**
	* Prefixes which are used to make big numbers shorter: 2M instead of 2000000, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e+3,prefix:""k""},{number:1e+6,prefix:""M""},{number:1e+9,prefix:""G""},{number:1e+12,prefix:""T""},{number:1e+15,prefix:""P""},{number:1e+18,prefix:""E""},{number:1e+21,prefix:""Z""},{number:1e+24,prefix:""Y""}]
	*/
	protected List<BigPrefix> prefixesOfBigNumbers;
	/**
	* Prefixes which are used to make small numbers shorter: 2μ instead of 0.000002, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e-24, prefix:""y""},{number:1e-21, prefix:""z""},{number:1e-18, prefix:""a""},{number:1e-15, prefix:""f""},{number:1e-12, prefix:""p""},{number:1e-9, prefix:""n""},{number:1e-6, prefix:""μ""},{number:1e-3, prefix:""m""}]
	*/
	protected List<SmallPrefix> prefixesOfSmallNumbers;
	/**
	* Theme of a chart. Config files of themes can be found in amcharts/themes/ folder. More info about using themes.
	* Default Value: none
	*/
	protected AmTheme theme;
	/**
	* Thousands separator.
	* Default Value: ,
	*/
	protected String thousandsSeparator;
	/**
	* Array of Title objects.
	* Default Value: []
	*/
	protected List<Title> titles;
	/**
	* Type of a chart. Required when creating chart using JSON. Possible types are: serial, pie, xy, radar, funnel, gauge, map, stock.
	* Default Value: 
	*/
	protected ChartTypeAm type;
	/**
	* If true, prefixes will be used for big and small numbers. You can set arrays of prefixes via prefixesOfSmallNumbers and prefixesOfBigNumbers properties.
	* Default Value: FALSE
	*/
	protected Boolean usePrefixes;
	/**
	* Read-only. Indicates current version of a script.
	* Default Value: 
	*/
	protected String version;
	
	/*
	 * Constructors
	 */
	public AmChart() {
		//this.pathToImages = "js/amcharts/images/";
	}
	public AmChart(ChartTypeAm chartType, AmTheme theme) {
		//this.pathToImages = "js/amcharts/images/";
		
		this.type = chartType;
		this.theme = theme;
	}
	
	/*
	 * Getters and Setters
	 */
	public List<Label> getAllLabels() {
		return allLabels;
	}
	public void setAllLabels(List<Label> allLabels) {
		this.allLabels = allLabels;
	}
	public AmExport getAmExport() {
		return amExport;
	}
	public void setAmExport(AmExport amExport) {
		this.amExport = amExport;
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
	public AmBalloon getBalloon() {
		return balloon;
	}
	public void setBalloon(AmBalloon balloon) {
		this.balloon = balloon;
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getCreditsPosition() {
		return creditsPosition;
	}
	public void setCreditsPosition(String creditsPosition) {
		this.creditsPosition = creditsPosition;
	}
	public List<Object> getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(List<Object> dataProvider) {
		this.dataProvider = dataProvider;
	}
	public String getDecimalSeparator() {
		return decimalSeparator;
	}
	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}
	public Object getExportConfig() {
		return exportConfig;
	}
	public void setExportConfig(Object exportConfig) {
		this.exportConfig = exportConfig;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
	public Boolean isHandDrawn() {
		return handDrawn;
	}
	public void setHandDrawn(Boolean handDrawn) {
		this.handDrawn = handDrawn;
	}
	public Double getHandDrawScatter() {
		return handDrawScatter;
	}
	public void setHandDrawScatter(Double handDrawScatter) {
		this.handDrawScatter = handDrawScatter;
	}
	public Double getHandDrawThickness() {
		return handDrawThickness;
	}
	public void setHandDrawThickness(Double handDrawThickness) {
		this.handDrawThickness = handDrawThickness;
	}
	public Double getHideBalloonTime() {
		return hideBalloonTime;
	}
	public void setHideBalloonTime(Double hideBalloonTime) {
		this.hideBalloonTime = hideBalloonTime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public AmLegend getLegend() {
		return legend;
	}
	public void setLegend(AmLegend legend) {
		this.legend = legend;
	}
	public String getLegendDiv() {
		return legendDiv;
	}
	public void setLegendDiv(String legendDiv) {
		this.legendDiv = legendDiv;
	}
	public Boolean isPanEventsEnabled() {
		return panEventsEnabled;
	}
	public void setPanEventsEnabled(Boolean panEventsEnabled) {
		this.panEventsEnabled = panEventsEnabled;
	}
	public String getPathToImages() {
		return pathToImages;
	}
	public void setPathToImages(String pathToImages) {
		this.pathToImages = pathToImages;
	}
	public Double getPercentPrecision() {
		return percentPrecision;
	}
	public void setPercentPrecision(Double percentPrecision) {
		this.percentPrecision = percentPrecision;
	}
	public Double getPrecision() {
		return precision;
	}
	public void setPrecision(Double precision) {
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
	public AmTheme getTheme() {
		return theme;
	}
	public void setTheme(AmTheme theme) {
		this.theme = theme;
	}
	public String getThousandsSeparator() {
		return thousandsSeparator;
	}
	public void setThousandsSeparator(String thousandsSeparator) {
		this.thousandsSeparator = thousandsSeparator;
	}
	public List<Title> getTitles() {
		return titles;
	}
	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}
	public ChartTypeAm getType() {
		return type;
	}
	public void setType(ChartTypeAm type) {
		this.type = type;
	}
	public Boolean isUsePrefixes() {
		return usePrefixes;
	}
	public void setUsePrefixes(Boolean usePrefixes) {
		this.usePrefixes = usePrefixes;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}