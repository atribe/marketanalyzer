package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmBalloon;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.AmLegend;

public class AmChart {

	/**
	* Array of Labels. Example of label object, with all possible properties:
	{""x"": 20, ""y"": 20, ""text"": ""this is label"", ""align"": ""left"", ""size"": 12, ""color"": ""#CC0000"", ""alpha"": 1, ""rotation"": 0, ""bold"": true, ""url"": ""http://www.amcharts.com""}
	* Default Value: []
	*/
	 private Array[Label] allLabels;
	/**
	* AmExport object.
	* Default Value: 
	*/
	 private AmExport amExport;
	/**
	* Opacity of background. Set it to >0 value if you want backgroundColor to work. However we recommend changing div's background-color style for changing background color.
	* Default Value: 0
	*/
	 private double backgroundAlpha;
	/**
	* Background color. You should set backgroundAlpha to >0 value in order background to be visible. We recommend setting background color directly on a chart's DIV instead of using this property.
	* Default Value: #FFFFFF
	*/
	 private Color backgroundColor;
	/**
	* The chart creates AmBalloon class itself. If you want to customize balloon, get balloon instance using this property, and then change balloon's properties.
	* Default Value: AmBalloon
	*/
	 private AmBalloon balloon;
	/**
	* Opacity of chart's border. Value range is 0 - 1.
	* Default Value: 0
	*/
	 private double borderAlpha;
	/**
	* Color of chart's border. You should set borderAlpha >0 in order border to be visible. We recommend setting border color directly on a chart's DIV instead of using this property.
	* Default Value: #000000
	*/
	 private Color borderColor;
	/**
	* Text color.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* Non-commercial version only. Specifies position of link to amCharts site.
	* Default Value: top-left
	*/
	 private String creditsPosition;
	/**
	* Array of data objects, for example: [{country:""US"", value:524},{country:""UK"", value:624},{country:""Lithuania"", value:824}]. You can have any number of fields and use any field names. In case of AmMap, data provider should be MapData object.
	* Default Value: 
	*/
	 private Array[Object] dataProvider;
	/**
	* Decimal separator.
	* Default Value: .
	*/
	 private String decimalSeparator;
	/**
	* Object of export config. Will enable saving chart as an image for all modern browsers except IE9 (IE10+ works fine). Check this article for more info.
	* Default Value: 
	*/
	 private Object exportConfig;
	/**
	* Font family.
	* Default Value: Verdana
	*/
	 private String fontFamily;
	/**
	* Font size.
	* Default Value: 11
	*/
	 private double fontSize;
	/**
	* If you set this to true, the lines of the chart will be distorted and will produce hand-drawn effect. Try to adjust chart.handDrawScatter and chart.handDrawThickness properties for a more scattered result.
	* Default Value: FALSE
	*/
	 private boolean handDrawn;
	/**
	* Defines by how many pixels hand-drawn line (when handDrawn is set to true) will fluctuate.
	* Default Value: 2
	*/
	 private double handDrawScatter;
	/**
	* Defines by how many pixels line thickness will fluctuate (when handDrawn is set to true).
	* Default Value: 1
	*/
	 private double handDrawThickness;
	/**
	* Time, in milliseconds after which balloon is hidden if the user rolls-out of the object. Might be useful for AmMap to avoid balloon flickering while moving mouse over the areas. Note, this is not duration of fade-out. Duration of fade-out is set in AmBalloon class.
	* Default Value: 150
	*/
	 private double hideBalloonTime;
	/**
	* Allows changing language easily. Note, you should include language js file from amcharts/lang or ammap/lang folder and then use variable name used in this file, like chart.language = ""de""; Note, for maps this works differently - you use language only for country names, as there are no other strings in the maps application.
	* Default Value: 
	*/
	 private String language;
	/**
	* Legend of a chart.
	* Default Value: 
	*/
	 private AmLegend legend;
	/**
	* Read-only. Reference to the div of the legend.
	* Default Value: 
	*/
	 private String legendDiv;
	/**
	* This setting affects touch-screen devices only. If a chart is on a page, and panEventsEnabled are set to true, the page won't move if the user touches the chart first. If a chart is big enough and occupies all the screen of your touch device, the user won’t be able to move the page at all. If you think that selecting/panning the chart or moving/pinching the map is a primary purpose of your users, you should set panEventsEnabled to true, otherwise - false.
	* Default Value: TRUE
	*/
	 private boolean panEventsEnabled;
	/**
	* Specifies path to the folder where images like resize grips, lens and similar are.
	* Default Value: 
	*/
	 private String pathToImages;
	/**
	* Precision of percent values. -1 means percent values won't be rounded at all and show as they are.
	* Default Value: 2
	*/
	 private double percentPrecision;
	/**
	* Precision of values. -1 means values won't be rounded at all and show as they are.
	* Default Value: -1
	*/
	 private double precision;
	/**
	* Prefixes which are used to make big numbers shorter: 2M instead of 2000000, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e+3,prefix:""k""},{number:1e+6,prefix:""M""},{number:1e+9,prefix:""G""},{number:1e+12,prefix:""T""},{number:1e+15,prefix:""P""},{number:1e+18,prefix:""E""},{number:1e+21,prefix:""Z""},{number:1e+24,prefix:""Y""}]
	*/
	 private Array[Object] prefixesOfBigNumbers;
	/**
	* Prefixes which are used to make small numbers shorter: 2μ instead of 0.000002, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e-24, prefix:""y""},{number:1e-21, prefix:""z""},{number:1e-18, prefix:""a""},{number:1e-15, prefix:""f""},{number:1e-12, prefix:""p""},{number:1e-9, prefix:""n""},{number:1e-6, prefix:""μ""},{number:1e-3, prefix:""m""}]
	*/
	 private Array[Object] prefixesOfSmallNumbers;
	/**
	* Theme of a chart. Config files of themes can be found in amcharts/themes/ folder. More info about using themes.
	* Default Value: none
	*/
	 private String theme;
	/**
	* Thousands separator.
	* Default Value: ,
	*/
	 private String thousandsSeparator;
	/**
	* Array of Title objects.
	* Default Value: []
	*/
	 private Array[Title] titles;
	/**
	* Type of a chart. Required when creating chart using JSON. Possible types are: serial, pie, xy, radar, funnel, gauge, map, stock.
	* Default Value: 
	*/
	 private String type;
	/**
	* If true, prefixes will be used for big and small numbers. You can set arrays of prefixes via prefixesOfSmallNumbers and prefixesOfBigNumbers properties.
	* Default Value: FALSE
	*/
	 private boolean usePrefixes;
	/**
	* Read-only. Indicates current version of a script.
	* Default Value: 
	*/
	 private String version;

}
