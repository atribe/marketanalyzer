package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.buildingblock.AmExport;

public class AmSlicedChart extends AmChart{

	/**
	* Array of Labels. Example of label object, with all possible properties:
	* {""x"": 20, ""y"": 20, ""text"": ""this is label"", ""align"": ""left"", ""size"": 12, ""color"": ""#CC0000"", ""alpha"": 1, ""rotation"": 0, ""bold"": true, ""url"": ""http://www.amcharts.com""}
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

}
