package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;

public class AmPieChart extends AmSlicedChart{

	/**
	* Array of Labels. Example of label object, with all possible properties:
	{""x"": 20, ""y"": 20, ""text"": ""this is label"", ""align"": ""left"", ""size"": 12, ""color"": ""#CC0000"", ""alpha"": 1, ""rotation"": 0, ""bold"": true, ""url"": ""http://www.amcharts.com""}
	* Default Value: []
	*/
	 private Array[Label] allLabels;
	/**
	* Opacity of all slices.
	* Default Value: 1
	*/
	 private double alpha;
	/**
	* Name of the field in chart's dataProvider which holds slice's alpha.
	* Default Value: 
	*/
	 private String alphaField;
	/**
	* AmExport object.
	* Default Value: 
	*/
	 private AmExport amExport;
	/**
	* Pie lean angle (for 3D effect). Valid range is 0 - 90.
	* Default Value: 0
	*/
	 private double angle;
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
	* Balloon text. The following tags can be used: [[value]], [[title]], [[percents]], [[description]] or any other field name from your data provider. HTML tags can also be used.
	* Default Value: [[title]]: [[percents]]% ([[value]])\n[[description]]
	*/
	 private String balloonText;
	/**
	* Color of the first slice. All the other will be colored with darker or brighter colors.
	* Default Value: 
	*/
	 private Color baseColor;
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
	* Lightness increase of each subsequent slice. This is only useful if baseColor is set. Use negative values for darker colors. Value range is from -255 to 255.
	* Default Value: 30
	*/
	 private double brightnessStep;
	/**
	* Read-only. Array of Slice objects.
	* Default Value: 
	*/
	 private Array[Object] chartData;
	/**
	* Text color.
	* Default Value: #000000
	*/
	 private Color color;
	/**
	* Name of the field in chart's dataProvider which holds slice's color.
	* Default Value: 
	*/
	 private String colorField;
	/**
	* Specifies the colors of the slices, if the slice color is not set. If there are more slices than colors in this array, the chart picks random color.
	* Default Value: [""#FF0F00"", ""#FF6600"", ""#FF9E01"", ""#FCD202"", ""#F8FF01"", ""#B0DE09"", ""#04D215"", ""#0D8ECF"", ""#0D52D1"", ""#2A0CD0"", ""#8A0CCF"", ""#CD0D74"", ""#754DEB"", ""#DDDDDD"", ""#999999"", ""#333333"", ""#000000"", ""#57032A"", ""#CA9726"", ""#990000"", ""#4B0C25""]
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
	* Depth of the pie (for 3D effect).
	* Default Value: 0
	*/
	 private double depth3D;
	/**
	* Name of the field in chart's dataProvider which holds a string with description.
	* Default Value: 
	*/
	 private String descriptionField;
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
	* Example: [0,10]. Will make slices to be filled with color gradients.
	* Default Value: []
	*/
	 private Array[Number] gradientRatio;
	/**
	* Opacity of the group slice. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private double groupedAlpha;
	/**
	* Color of the group slice. The default value is not set - this means the next available color from ""colors"" array will be used.
	* Default Value: 
	*/
	 private Color groupedColor;
	/**
	* Description of the group slice.
	* Default Value: 
	*/
	 private String groupedDescription;
	/**
	* If this is set to true, the group slice will be pulled out when the chart loads.
	* Default Value: FALSE
	*/
	 private boolean groupedPulled;
	/**
	* Title of the group slice.
	* Default Value: Other
	*/
	 private String groupedTitle;
	/**
	* If there is more than one slice whose percentage of the pie is less than this number, those slices will be grouped together into one slice. This is the ""other"" slice. It will always be the last slice in a pie.
	* Default Value: 0
	*/
	 private double groupPercent;
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
	* Slices with percent less then hideLabelsPercent won't display labels This is useful to avoid cluttering up the chart, if you have a lot of small slices. 0 means all labels will be shown.
	* Default Value: 0
	*/
	 private double hideLabelsPercent;
	/**
	* Opacity of a hovered slice. Value range is 0 - 1.
	* Default Value: 1
	*/
	 private double hoverAlpha;
	/**
	* Inner radius of the pie, in pixels or percents.
	* Default Value: 0
	*/
	 private Number/String innerRadius;
	/**
	* You can use it to format data labels in any way you want. Chart will call this method and will pass Slice object and formatted text as attributes. This function should return string which will be displayed as label.
	* Default Value: 
	*/
	 private 0 labelFunction;
	/**
	* The distance between the label and the slice, in pixels. You can use negative values to put the label on the slice.
	* Default Value: 20
	*/
	 private double labelRadius;
	/**
	* Name of the field in dataProvider which specifies the length of a tick. Note, the chart will not try to arrange labels automatically if this property is set.
	* Default Value: 
	*/
	 private String labelRadiusField;
	/**
	* Specifies whether data labels are visible.
	* Default Value: TRUE
	*/
	 private boolean labelsEnabled;
	/**
	* Label text. The following tags can be used: [[value]], [[title]], [[percents]], [[description]] or any other field name from your data provider.
	* Default Value: [[title]]: [[percents]]%
	*/
	 private String labelText;
	/**
	* Label tick opacity. Value range is 0 - 1.
	* Default Value: 0.2
	*/
	 private double labelTickAlpha;
	/**
	* Label tick color.
	* Default Value: #000000
	*/
	 private Color labelTickColor;
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
	* Bottom margin of the chart.
	* Default Value: 10
	*/
	 private double marginBottom;
	/**
	* Left margin of the chart.
	* Default Value: 0
	*/
	 private double marginLeft;
	/**
	* Right margin of the chart.
	* Default Value: 0
	*/
	 private double marginRight;
	/**
	* Top margin of the chart.
	* Default Value: 10
	*/
	 private double marginTop;
	/**
	* If width of the label is bigger than maxLabelWidth, it will be wrapped.
	* Default Value: 200
	*/
	 private double maxLabelWidth;
	/**
	* Minimum radius of the pie, in pixels.
	* Default Value: 10
	*/
	 private double minRadius;
	/**
	* Outline opacity. Value range is 0 - 1.
	* Default Value: 0
	*/
	 private double outlineAlpha;
	/**
	* Outline color.
	* Default Value: #FFFFFF
	*/
	 private Color outlineColor;
	/**
	* Pie outline thickness.
	* Default Value: 1
	*/
	 private double outlineThickness;
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
	* Field name in your data provider which holds pattern information. Value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values. For example: {""url"":""../amcharts/patterns/black/pattern1.png"", ""width"":4, ""height"":4}. Check amcharts/patterns folder for some patterns. You can create your own patterns and use them. Note, x, y, randomX and randomY properties won't work with IE8 and older. 3D bar/Pie charts won't work properly with patterns.
	* Default Value: 
	*/
	 private String patternField;
	/**
	* Precision of percent values. -1 means percent values won't be rounded at all and show as they are.
	* Default Value: 2
	*/
	 private double percentPrecision;
	/**
	* Opacity of the slices. You can set the opacity of individual slice too.
	* Default Value: 1
	*/
	 private double pieAlpha;
	/**
	* You can set fixed position of a pie center, in pixels or in percents.
	* Default Value: 
	*/
	 private Number/String pieX;
	/**
	* You can set fixed position of a pie center, in pixels or in percents.
	* Default Value: 
	*/
	 private Number/String pieY;
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
	* Name of the field in chart's dataProvider which holds a boolean value telling the chart whether this slice must be pulled or not.
	* Default Value: 
	*/
	 private String pulledField;
	/**
	* Pull out duration, in seconds.
	* Default Value: 1
	*/
	 private double pullOutDuration;
	/**
	* Pull out effect. Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: bounce
	*/
	 private String pullOutEffect;
	/**
	* If this is set to true, only one slice can be pulled out at a time. If the viewer clicks on a slice, any other pulled-out slice will be pulled in.
	* Default Value: FALSE
	*/
	 private boolean pullOutOnlyOne;
	/**
	* Pull out radius, in pixels or percents
	* Default Value: 0.2
	*/
	 private Number/String pullOutRadius;
	/**
	* Radius of a pie, in pixels or percents. By default, radius is calculated automatically.
	* Default Value: 
	*/
	 private Number/String radius;
	/**
	* Specifies whether the animation should be sequenced or all slices should appear at once.
	* Default Value: TRUE
	*/
	 private boolean sequencedAnimation;
	/**
	* Initial opacity of all slices. Slices will fade in from startAlpha.
	* Default Value: 0
	*/
	 private double startAlpha;
	/**
	* Angle of the first slice, in degrees. This will work properly only if ""depth3D"" is set to 0. If ""depth3D"" is greater than 0, then there can be two angles only: 90 and 270. Value range is 0-360.
	* Default Value: 90
	*/
	 private double startAngle;
	/**
	* Duration of the animation, in seconds.
	* Default Value: 1
	*/
	 private double startDuration;
	/**
	* Animation effect. Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: bounce
	*/
	 private String startEffect;
	/**
	* Radius of the positions from which the slices will fly in.
	* Default Value: 5
	*/
	 private Number/String startRadius;
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
	* Name of the field in chart's dataProvider which holds slice's title.
	* Default Value: 
	*/
	 private String titleField;
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
	* Name of the field in chart's dataProvider which holds url which would be accessed if the user clicks on a slice.
	* Default Value: 
	*/
	 private String urlField;
	/**
	* If url is specified for a slice, it will be opened when user clicks on it. urlTarget specifies target of this url. Use _blank if you want url to be opened in a new window.
	* Default Value: _self
	*/
	 private String urlTarget;
	/**
	* If true, prefixes will be used for big and small numbers. You can set arrays of prefixes via prefixesOfSmallNumbers and prefixesOfBigNumbers properties.
	* Default Value: FALSE
	*/
	 private boolean usePrefixes;
	/**
	* Name of the field in chart's dataProvider which holds slice's value.
	* Default Value: 
	*/
	 private String valueField;
	/**
	* Read-only. Indicates current version of a script.
	* Default Value: 
	*/
	 private String version;
	/**
	* Name of the field in chart's dataProvider which holds boolean variable defining whether this data item should have an entry in the legend.
	* Default Value: 
	*/
	 private String visibleInLegendField;
	/**
	* Name of the field in chart's dataProvider which holds boolean variable defining whether this data item should have an entry in the legend.
	* Default Value: 
	*/
	 private String visibleInLegendField;

}
