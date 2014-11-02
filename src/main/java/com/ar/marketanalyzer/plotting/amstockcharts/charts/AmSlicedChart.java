package com.ar.marketanalyzer.plotting.amstockcharts.charts;

import java.util.List;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amstockcharts.chartobjects.Slice;
import com.ar.marketanalyzer.plotting.amstockcharts.enums.StartEffect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AmSlicedChart extends AmChart{
	
	/**
	* Opacity of all slices.
	* Default Value: 1
	*/
	protected Double alpha;
	/**
	* Name of the field in chart's dataProvider which holds slice's alpha.
	* Default Value: 
	*/
	protected String alphaField;
	/**
	* Color of the first slice. All the other will be colored with darker or brighter colors.
	* Default Value: 
	*/
	protected Color baseColor;
	/**
	* Lightness increase of each subsequent slice. This is only useful if baseColor is set. Use negative values for darker colors. Value range is from -255 to 255.
	* Default Value: 30
	*/
	protected Double brightnessStep;
	/**
	* Read-only. Array of Slice objects.
	* Default Value: 
	*/
	protected List<Slice> chartData;
	/**
	* Name of the field in chart's dataProvider which holds slice's color.
	* Default Value: 
	*/
	protected String colorField;
	/**
	* Specifies the colors of the slices, if the slice color is not set. If there are more slices than colors in this array, the chart picks random color.
	* Default Value: [#FF0F00, #FF6600, #FF9E01, #FCD202, #F8FF01, #B0DE09, #04D215, #0D8ECF, #0D52D1, #2A0CD0, #8A0CCF, #CD0D74, #754DEB, #DDDDDD, #999999, #333333, #000000, #57032A, #CA9726, #990000, #4B0C25]
	*/
	protected List<Color> colors;
	/**
	* Name of the field in chart's dataProvider which holds a string with description.
	* Default Value: 
	*/
	protected String descriptionField;
	/**
	* Example: [0,10]. Will make slices to be filled with color gradients.
	* Default Value: []
	*/
	protected List<Double> gradientRatio;
	/**
	* Opacity of the group slice. Value range is 0 - 1.
	* Default Value: 1
	*/
	protected Double groupedAlpha;
	/**
	* Color of the group slice. The default value is not set - this means the next available color from colors array will be used.
	* Default Value: 
	*/
	protected Color groupedColor;
	/**
	* Description of the group slice.
	* Default Value: 
	*/
	protected String groupedDescription;
	/**
	* If this is set to true, the group slice will be pulled out when the chart loads.
	* Default Value: FALSE
	*/
	protected Boolean groupedPulled;
	/**
	* Title of the group slice.
	* Default Value: Other
	*/
	protected String groupedTitle;
	/**
	* If there is more than one slice whose percentage of the pie is less than this number, those slices will be grouped together into one slice. This is the other slice. It will always be the last slice in a pie.
	* Default Value: 0
	*/
	protected Double groupPercent;
	/**
	* Slices with percent less then hideLabelsPercent won't display labels This is useful to avoid cluttering up the chart, if you have a lot of small slices. 0 means all labels will be shown.
	* Default Value: 0
	*/
	protected Double hideLabelsPercent;
	/**
	* Opacity of a hovered slice. Value range is 0 - 1.
	* Default Value: 1
	*/
	protected Double hoverAlpha;
	/**
	* You can use it to format data labels in any way you want. Chart will call this method and will pass Slice object and formatted text as attributes. This function should return string which will be displayed as label.
	* Default Value: 
	*/
	protected String labelFunction;
	/**
	* Specifies whether data labels are visible.
	* Default Value: TRUE
	*/
	protected Boolean labelsEnabled;
	/**
	* Label tick opacity. Value range is 0 - 1.
	* Default Value: 0.2
	*/
	protected Double labelTickAlpha;
	/**
	* Label tick color.
	* Default Value: #000000
	*/
	protected Color labelTickColor;
	/**
	* Bottom margin of the chart.
	* Default Value: 10
	*/
	protected Double marginBottom;
	/**
	* Left margin of the chart.
	* Default Value: 0
	*/
	protected Double marginLeft;
	/**
	* Right margin of the chart.
	* Default Value: 0
	*/
	protected Double marginRight;
	/**
	* Top margin of the chart.
	* Default Value: 10
	*/
	protected Double marginTop;
	/**
	* If width of the label is bigger than maxLabelWidth, it will be wrapped.
	* Default Value: 200
	*/
	protected Double maxLabelWidth;
	/**
	* Outline opacity. Value range is 0 - 1.
	* Default Value: 0
	*/
	protected Double outlineAlpha;
	/**
	* Outline color.
	* Default Value: #FFFFFF
	*/
	protected Color outlineColor;
	/**
	* Pie outline thickness.
	* Default Value: 1
	*/
	protected Double outlineThickness;
	/**
	* Field name in your data provider which holds pattern information. Value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values. For example: {url:../amcharts/patterns/black/pattern1.png, width:4, height:4}. Check amcharts/patterns folder for some patterns. You can create your own patterns and use them. Note, x, y, randomX and randomY properties won't work with IE8 and older. 3D bar/Pie charts won't work properly with patterns.
	* Default Value: 
	*/
	protected String patternField;
	/**
	* Name of the field in chart's dataProvider which holds a Boolean value telling the chart whether this slice must be pulled or not.
	* Default Value: 
	*/
	protected String pulledField;
	/**
	* Pull out duration, in seconds.
	* Default Value: 1
	*/
	protected Double pullOutDuration;
	/**
	* Pull out effect. Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: bounce
	*/
	protected StartEffect pullOutEffect;
	/**
	* If this is set to true, only one slice can be pulled out at a time. If the viewer clicks on a slice, any other pulled-out slice will be pulled in.
	* Default Value: FALSE
	*/
	protected Boolean pullOutOnlyOne;
	/**
	* Specifies whether the animation should be sequenced or all slices should appear at once.
	* Default Value: TRUE
	*/
	protected Boolean sequencedAnimation;
	/**
	* Initial opacity of all slices. Slices will fade in from startAlpha.
	* Default Value: 0
	*/
	protected Double startAlpha;
	/**
	* Duration of the animation, in seconds.
	* Default Value: 1
	*/
	protected Double startDuration;
	/**
	* Animation effect. Possible values are: easeOutSine, easeInSine, elastic, bounce
	* Default Value: bounce
	*/
	protected StartEffect startEffect;
	/**
	* Name of the field in chart's dataProvider which holds slice's title.
	* Default Value: 
	*/
	protected String titleField;
	/**
	* Name of the field in chart's dataProvider which holds url which would be accessed if the user clicks on a slice.
	* Default Value: 
	*/
	protected String urlField;
	/**
	* If url is specified for a slice, it will be opened when user clicks on it. urlTarget specifies target of this url. Use _blank if you want url to be opened in a new window.
	* Default Value: _self
	*/
	protected String urlTarget;
	/**
	* Name of the field in chart's dataProvider which holds slice's value.
	* Default Value: 
	*/
	protected String valueField;
	/**
	* Name of the field in chart's dataProvider which holds Boolean variable defining whether this data item should have an entry in the legend.
	* Default Value: 
	*/
	protected String visibleInLegendField;
	
	public Double getAlpha() {
		return alpha;
	}
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}
	public String getAlphaField() {
		return alphaField;
	}
	public void setAlphaField(String alphaField) {
		this.alphaField = alphaField;
	}
	public Color getBaseColor() {
		return baseColor;
	}
	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}
	public Double getBrightnessStep() {
		return brightnessStep;
	}
	public void setBrightnessStep(Double brightnessStep) {
		this.brightnessStep = brightnessStep;
	}
	public List<Slice> getChartData() {
		return chartData;
	}
	public void setChartData(List<Slice> chartData) {
		this.chartData = chartData;
	}
	public String getColorField() {
		return colorField;
	}
	public void setColorField(String colorField) {
		this.colorField = colorField;
	}
	public List<Color> getColors() {
		return colors;
	}
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
	public String getDescriptionField() {
		return descriptionField;
	}
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = descriptionField;
	}
	public List<Double> getGradientRatio() {
		return gradientRatio;
	}
	public void setGradientRatio(List<Double> gradientRatio) {
		this.gradientRatio = gradientRatio;
	}
	public Double getGroupedAlpha() {
		return groupedAlpha;
	}
	public void setGroupedAlpha(Double groupedAlpha) {
		this.groupedAlpha = groupedAlpha;
	}
	public Color getGroupedColor() {
		return groupedColor;
	}
	public void setGroupedColor(Color groupedColor) {
		this.groupedColor = groupedColor;
	}
	public String getGroupedDescription() {
		return groupedDescription;
	}
	public void setGroupedDescription(String groupedDescription) {
		this.groupedDescription = groupedDescription;
	}
	public Boolean isGroupedPulled() {
		return groupedPulled;
	}
	public void setGroupedPulled(Boolean groupedPulled) {
		this.groupedPulled = groupedPulled;
	}
	public String getGroupedTitle() {
		return groupedTitle;
	}
	public void setGroupedTitle(String groupedTitle) {
		this.groupedTitle = groupedTitle;
	}
	public Double getGroupPercent() {
		return groupPercent;
	}
	public void setGroupPercent(Double groupPercent) {
		this.groupPercent = groupPercent;
	}
	public Double getHideLabelsPercent() {
		return hideLabelsPercent;
	}
	public void setHideLabelsPercent(Double hideLabelsPercent) {
		this.hideLabelsPercent = hideLabelsPercent;
	}
	public Double getHoverAlpha() {
		return hoverAlpha;
	}
	public void setHoverAlpha(Double hoverAlpha) {
		this.hoverAlpha = hoverAlpha;
	}
	public String getLabelFunction() {
		return labelFunction;
	}
	public void setLabelFunction(String labelFunction) {
		this.labelFunction = labelFunction;
	}
	public Boolean isLabelsEnabled() {
		return labelsEnabled;
	}
	public void setLabelsEnabled(Boolean labelsEnabled) {
		this.labelsEnabled = labelsEnabled;
	}
	public Double getLabelTickAlpha() {
		return labelTickAlpha;
	}
	public void setLabelTickAlpha(Double labelTickAlpha) {
		this.labelTickAlpha = labelTickAlpha;
	}
	public Color getLabelTickColor() {
		return labelTickColor;
	}
	public void setLabelTickColor(Color labelTickColor) {
		this.labelTickColor = labelTickColor;
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
	public Double getMaxLabelWidth() {
		return maxLabelWidth;
	}
	public void setMaxLabelWidth(Double maxLabelWidth) {
		this.maxLabelWidth = maxLabelWidth;
	}
	public Double getOutlineAlpha() {
		return outlineAlpha;
	}
	public void setOutlineAlpha(Double outlineAlpha) {
		this.outlineAlpha = outlineAlpha;
	}
	public Color getOutlineColor() {
		return outlineColor;
	}
	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}
	public Double getOutlineThickness() {
		return outlineThickness;
	}
	public void setOutlineThickness(Double outlineThickness) {
		this.outlineThickness = outlineThickness;
	}
	public String getPatternField() {
		return patternField;
	}
	public void setPatternField(String patternField) {
		this.patternField = patternField;
	}
	public String getPulledField() {
		return pulledField;
	}
	public void setPulledField(String pulledField) {
		this.pulledField = pulledField;
	}
	public Double getPullOutDuration() {
		return pullOutDuration;
	}
	public void setPullOutDuration(Double pullOutDuration) {
		this.pullOutDuration = pullOutDuration;
	}
	public StartEffect getPullOutEffect() {
		return pullOutEffect;
	}
	public void setPullOutEffect(StartEffect pullOutEffect) {
		this.pullOutEffect = pullOutEffect;
	}
	public Boolean isPullOutOnlyOne() {
		return pullOutOnlyOne;
	}
	public void setPullOutOnlyOne(Boolean pullOutOnlyOne) {
		this.pullOutOnlyOne = pullOutOnlyOne;
	}
	public Boolean isSequencedAnimation() {
		return sequencedAnimation;
	}
	public void setSequencedAnimation(Boolean sequencedAnimation) {
		this.sequencedAnimation = sequencedAnimation;
	}
	public Double getStartAlpha() {
		return startAlpha;
	}
	public void setStartAlpha(Double startAlpha) {
		this.startAlpha = startAlpha;
	}
	public Double getStartDuration() {
		return startDuration;
	}
	public void setStartDuration(Double startDuration) {
		this.startDuration = startDuration;
	}
	public StartEffect getStartEffect() {
		return startEffect;
	}
	public void setStartEffect(StartEffect startEffect) {
		this.startEffect = startEffect;
	}
	public String getTitleField() {
		return titleField;
	}
	public void setTitleField(String titleField) {
		this.titleField = titleField;
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
	public String getValueField() {
		return valueField;
	}
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public String getVisibleInLegendField() {
		return visibleInLegendField;
	}
	public void setVisibleInLegendField(String visibleInLegendField) {
		this.visibleInLegendField = visibleInLegendField;
	}

}
