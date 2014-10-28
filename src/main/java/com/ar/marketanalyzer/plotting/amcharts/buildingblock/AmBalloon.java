package com.ar.marketanalyzer.plotting.amcharts.buildingblock;

import java.io.Serializable;

import com.ar.marketanalyzer.plotting.amcharts.enums.Color;
import com.ar.marketanalyzer.plotting.amcharts.enums.TextAlignment;
import com.ar.marketanalyzer.plotting.amcharts.serializers.JacksonObjectToListSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JacksonObjectToListSerializer.class)
public class AmBalloon implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1090699400466102935L;
	private boolean adjustBorderColor;
    private double animationDuration;
    private double borderAlpha;
    private Color borderColor;
    private double borderThickness;
    private Color color;
    private double cornerRadius;
    private double fadeOutDuration;
    private double fillAlpha;
    private Color fillColor;
    private Boolean fixedPosition;
    private double fontSize;
    private double horizontalPadding;
    private double maxWidth; 
    private double offsetX;
    private double offsetY;
    private double pointerWidth;
    private double shadowAlpha;
    private Color shadowColor;
    private boolean showBullet;
	private TextAlignment textAlign;
    private double verticalPadding;
    
    public double getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}
	public void setFixedPosition(Boolean fixedPosition) {
		this.fixedPosition = fixedPosition;
	}
	
    /**
     * If this is set to true, border color instead of background color will be changed
     * when user rolls-over the slice, graph, etc.
     **/
    public Boolean getAdjustBorderColor() {
        return adjustBorderColor;
    }
    public AmBalloon setAdjustBorderColor(boolean adjustBorderColor) {
        this.adjustBorderColor = adjustBorderColor;
        return this;
    }

    /**
     * Duration of balloon movement from previous point to current point, in seconds.
     **/
    public double getAnimationDuration() {
        return animationDuration;
    }
    public AmBalloon setAnimationDuration(double animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    /**
     * Balloon border opacity. Value range is 0 - 1.
     **/
    public double getBorderAlpha() {
        return borderAlpha;
    }
    public AmBalloon setBorderAlpha(double borderAlpha) {
        this.borderAlpha = borderAlpha;
        return this;
    }

    /**
     * Balloon border color. Will only be used of adjustBorderColor is false.
     **/
    public Color getBorderColor() {
        return borderColor;
    }
    public AmBalloon setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    /**
     * Balloon border thickness.
     **/
    public double getBorderThickness() {
        return borderThickness;
    }
    public AmBalloon setBorderThickness(double borderThickness) {
        this.borderThickness = borderThickness;
        return this;
    }

    /**
     * Color of text in the balloon.
     **/
    public Color getColor() {
        return color;
    }
    public AmBalloon setColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     * Balloon corner radius.
     **/
    public double getCornerRadius() {
        return cornerRadius;
    }
    public AmBalloon setCornerRadius(double cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    /**
     * Duration of a fade out animation, in seconds.
     **/
    public double getFadeOutDuration() {
        return fadeOutDuration;
    }
    public AmBalloon setFadeOutDuration(double fadeOutDuration) {
        this.fadeOutDuration = fadeOutDuration;
        return this;
    }

    /**
     * Balloon background opacity.
     **/
    public double getFillAlpha() {
        return fillAlpha;
    }
    public AmBalloon setFillAlpha(double fillAlpha) {
        this.fillAlpha = fillAlpha;
        return this;
    }

    /**
     * Balloon background color. Usually balloon background color is set by the chart. Only
     * if "adjustBorderColor" is "true" this color will be used.
     **/
    public Color getFillColor() {
        return fillColor;
    }
    public AmBalloon setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    /**
     * Specifies if balloon should follow mouse when hovering the slice/column/bullet or
     * stay in fixed position (this does not affect balloon behavior if ChartCursor is used).
     **/
    public Boolean getFixedPosition() {
        return fixedPosition;
    }
    public AmBalloon setFixedPosition(boolean fixedPosition) {
        this.fixedPosition = fixedPosition;
        return this;
    }

    /**
     * Size of text in the balloon. Chart's fontSize is used by default.
     **/
    public double getFontSize() {
        return fontSize;
    }
    public AmBalloon setFontSize(double fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    /**
     * Horizontal padding of the balloon.
     **/
    public double getHorizontalPadding() {
        return horizontalPadding;
    }
    public AmBalloon setHorizontalPadding(double horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
        return this;
    }

    /**
     * Defines horizontal distance from mouse pointer to balloon pointer. If you set it
     * to a small value, the balloon might flicker, as mouse might lose focus on hovered
     * object.
     **/
    public double getOffsetX() {
        return offsetX;
    }
    public AmBalloon setOffsetX(double offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    /**
     * Defines vertical distance from mouse pointer to balloon pointer. If you set it to
     * a small value, the balloon might flicker, as mouse might lose focus on hovered object.
     **/
    public double getOffsetY() {
        return offsetY;
    }
    public AmBalloon setOffsetY(double offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    /**
     * The width of the pointer (arrow) "root". Only used if cornerRadius is 0.
     **/
    public double getPointerWidth() {
        return pointerWidth;
    }
    public AmBalloon setPointerWidth(double pointerWidth) {
        this.pointerWidth = pointerWidth;
        return this;
    }

    /**
     * Opacity of a shadow.
     **/
    public double getShadowAlpha() {
        return shadowAlpha;
    }
    public AmBalloon setShadowAlpha(double shadowAlpha) {
        this.shadowAlpha = shadowAlpha;
        return this;
    }

    /**
     * Color of a shadow.
     **/
    public Color getShadowColor() {
        return shadowColor;
    }
    public AmBalloon setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    /**
     * If cornerRadius of a balloon is >0, showBullet is set to true for value balloons
     * when ChartCursor is used. If you don't want the bullet near the balloon, set it to
     * false: chart.balloon.showBullet = false
     **/
    public Boolean getShowBullet() {
        return showBullet;
    }
    public AmBalloon setShowBullet(boolean showBullet) {
        this.showBullet = showBullet;
        return this;
    }

    /**
     * Text alignment, possible values "left", "middle" and "right"
     **/
    public TextAlignment getTextAlign() {
        return textAlign;
    }
    public AmBalloon setTextAlign(TextAlignment textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    /**
     * Vertical padding of the balloon.
     **/
    public double getVerticalPadding() {
        return verticalPadding;
    }
    public AmBalloon setVerticalPadding(double verticalPadding) {
        this.verticalPadding = verticalPadding;
        return this;
    }


}