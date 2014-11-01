package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;

public class AmXYChart extends AmRectangularChart{

	/**
	* Array of Labels. Example of label object, with all possible properties:
	* {""x"": 20, ""y"": 20, ""text"": ""this is label"", ""align"": ""left"", ""size"": 12, ""color"": ""#CC0000"", ""alpha"": 1, ""rotation"": 0, ""bold"": true, ""url"": ""http://www.amcharts.com""}
	* Default Value: []
	*/
	 private Array[Label] allLabels;
	/**
	* AmExport object.
	* Default Value: 
	*/
	 private AmExport amExport;
	/**
	* The angle of the 3D part of plot area. This creates a 3D effect (if the ""depth3D"" is > 0).
	* Default Value: 0
	*/
	 private double angle;
	/**
	* Space left from axis labels/title to the chart's outside border, if autoMargins set to true.
	* Default Value: 10
	*/
	 private double autoMarginOffset;
	/**
	* Specifies if margins of a chart should be calculated automatically so that labels of axes would fit. The chart will adjust only margins with axes. Other margins will use values set with marginRight, marginTop, marginLeft and marginBottom properties.
	* Default Value: TRUE
	*/
	 private boolean autoMargins;
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
	* Cursor of a chart.
	* Default Value: 
	*/
	 private ChartCursor chartCursor;
	/**
	* Read-only. Array, holding processed chart's data.
	* Default Value: 
	*/
	 private Array[Object] chartData;
	/**
	* Chart's scrollbar.
	* Default Value: 
	*/
	 private ChartScrollbar chartScrollbar;
	/**
	* Text color.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* Specifies the colors of the graphs if the lineColor of a graph is not set. It there are more graphs then colors in this array, the chart picks random color.
	* Default Value: ['#FF6600', '#FCD202', '#B0DE09', '#0D8ECF', '#2A0CD0', '#CD0D74', '#CC0000', '#00CC00', '#0000CC', '#DDDDDD', '#999999', '#333333', '#990000']
	*/
	 private Array[Color] colors;
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
	* The depth of the 3D part of plot area. This creates a 3D effect (if the ""angle"" is > 0).
	* Default Value: 0
	*/
	 private double depth3D;
	/**
	* Object of export config. Will enable saving chart as an image for all modern browsers except IE9 (IE10+ works fine). Check this articlefor more info.
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
	* The array of graphs belonging to this chart.
	* Default Value: 
	*/
	 private Array[AmGraph] graphs;
	/**
	* Specifies if grid should be drawn above the graphs or below. Will not work properly with 3D charts.
	* Default Value: FALSE
	*/
	 private boolean gridAboveGraphs;
	/**
	* Instead of adding guides to the axes, you can push all of them to this array. In case guide has category or date defined, it will automatically will be assigned to the category axis. Otherwise to first value axis, unless you specify a different valueAxis for the guide.
	* Default Value: []
	*/
	 private Array[Guide] guides;
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
	* Time, in milliseconds after which balloon is hidden if the user rolls-out of the object. Might be useful for AmMap to avoid balloon flickering while moving mouse over the areas. Note, this is not duration of fade-out. Duration of fade-out is set in AmBalloonclass.
	* Default Value: 150
	*/
	 private double hideBalloonTime;
	/**
	* Specifies if Scrollbar of X axis (horizontal) should be hidden.
	* Default Value: FALSE
	*/
	 private boolean hideXScrollbar;
	/**
	* Specifies if Scrollbar of Y axis (vertical) should be hidden.
	* Default Value: FALSE
	*/
	 private boolean hideYScrollbar;
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
	* Number of pixels between the container's bottom border and plot area. This space can be used for bottom axis' values. If autoMargin is true and bottom side has axis, this property is ignored.
	* Default Value: 20
	*/
	 private double marginBottom;
	/**
	* Number of pixels between the container's left border and plot area. This space can be used for left axis' values. If autoMargin is true and left side has axis, this property is ignored.
	* Default Value: 20
	*/
	 private double marginLeft;
	/**
	* Number of pixels between the container's right border and plot area. This space can be used for Right axis' values. If autoMargin is true and right side has axis, this property is ignored.
	* Default Value: 20
	*/
	 private double marginRight;
	/**
	* Flag which should be set to false if you need margins to be recalculated on next chart.validateNow() call.
	* Default Value: FALSE
	*/
	 private boolean marginsUpdated;
	/**
	* Number of pixels between the container's top border and plot area. This space can be used for top axis' values. If autoMargin is true and top side has axis, this property is ignored.
	* Default Value: 20
	*/
	 private double marginTop;
	/**
	* Maximum zoom factor of the chart.
	* Default Value: 20
	*/
	 private double maxZoomFactor;
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
	* The opacity of plot area's border. Value range is 0 - 1.
	* Default Value: 0
	*/
	 private double plotAreaBorderAlpha;
	/**
	* The color of the plot area's border. Note, the it is invisible by default, as plotAreaBorderAlpha default value is 0. Set it to a value higher than 0 to make it visible.
	* Default Value: #000000
	*/
	 private Color plotAreaBorderColor;
	/**
	* Opacity of plot area. Plural form is used to keep the same property names as our Flex charts'. Flex charts can accept array of numbers to generate gradients. Although you can set array here, only first value of this array will be used.
	* Default Value: 0
	*/
	 private double plotAreaFillAlphas;
	/**
	* You can set both one color if you need a solid color or array of colors to generate gradients, for example: [""#000000"", ""#0000CC""]
	* Default Value: #FFFFFF
	*/
	 private Color plotAreaFillColors;
	/**
	* If you are using gradients to fill the plot area, you can use this property to set gradient angle. The only allowed values are horizontal and vertical: 0, 90, 180, 270.
	* Default Value: 0
	*/
	 private double plotAreaGradientAngle;
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
	* Prefixes which are used to make small numbers shorter: 2u instead of 0.000002, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
	* Default Value: [{number:1e-24, prefix:""y""},{number:1e-21, prefix:""z""},{number:1e-18, prefix:""a""},{number:1e-15, prefix:""f""},{number:1e-12, prefix:""p""},{number:1e-9, prefix:""n""},{number:1e-6, prefix:""u""},{number:1e-3, prefix:""m""}]
	*/
	 private Array[Object] prefixesOfSmallNumbers;
	/**
	* Specifies whether the animation should be sequenced or all objects should appear at once.
	* Default Value: TRUE
	*/
	 private boolean sequencedAnimation;
	/**
	* The initial opacity of the column/line. If you set startDuration to a value higher than 0, the columns/lines will fade in from startAlpha. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private double startAlpha;
	/**
	* Duration of the animation, in seconds.
	* Default Value: 0
	*/
	 private double startDuration;
	/**
	* Animation effect. Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: elastic
	*/
	 private String startEffect;
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
	* Array of trend lines added to a chart. You can add trend lines to a chart using this array or access already existing trend lines
	* Default Value: 
	*/
	 private Array[TrendLine] trendLines;
	/**
	* Type of a chart. Required when creating chart using JSON. Possible types are: serial, pie, xy, radar, funnel, gauge, map, stock.
	* Default Value: 
	*/
	 private String type;
	/**
	* Target of url.
	* Default Value: _self
	*/
	 private String urlTarget;
	/**
	* If true, prefixes will be used for big and small numbers. You can set arrays of prefixes via prefixesOfSmallNumbers and prefixesOfBigNumbers properties.
	* Default Value: FALSE
	*/
	 private boolean usePrefixes;
	/**
	* The array of value axes. Chart creates one value axis automatically, so if you need only one value axis, you don't need to create it.
	* Default Value: ValueAxis
	*/
	 private Array[ValueAxis] valueAxes;
	/**
	* Read-only. Indicates current version of a script.
	* Default Value: 
	*/
	 private String version;
	/**
	* Opacity of zoom-out button background.
	* Default Value: 0
	*/
	 private double zoomOutButtonAlpha;
	/**
	* Zoom-out button background color.
	* Default Value: #e5e5e5
	*/
	 private Color zoomOutButtonColor;
	/**
	* Name of zoom-out button image. In the images folder there is another lens image, called lensWhite.png. You might want to have white lens when background is dark. Or you can simply use your own image.
	* Default Value: lens.png
	*/
	 private String zoomOutButtonImage;
	/**
	* Size of zoom-out button image
	* Default Value: 17
	*/
	 private double zoomOutButtonImageSize;
	/**
	* Padding around the text and image.
	* Default Value: 8
	*/
	 private double zoomOutButtonPadding;
	/**
	* Opacity of zoom-out button background when mouse is over it.
	* Default Value: 1
	*/
	 private double zoomOutButtonRollOverAlpha;
	/**
	* Text in the zoom-out button.
	* Default Value: Show all
	*/
	 private String zoomOutText;

}
