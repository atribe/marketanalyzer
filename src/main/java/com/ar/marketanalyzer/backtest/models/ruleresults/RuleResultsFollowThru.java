package com.ar.marketanalyzer.backtest.models.ruleresults;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Buy:Follow Thru")
public class RuleResultsFollowThru extends AbstractRuleResult {

	private static final long serialVersionUID = -8340994568617972660L;

	@Column( name = "pivot_day" )
	Boolean pivotDay;
	
	@Column( name = "follow_thru_day" )
	Boolean followThruDay;
	
	/*
	 * Constructors
	 */
	public RuleResultsFollowThru() {
		super();
	}
	public RuleResultsFollowThru( Date date ) {
		super(date);
	}
	
	/*
	 * Getters and Setters
	 */
	public Boolean getPivotDay() {
		return pivotDay;
	}
	public void setPivotDay(Boolean pivotDay) {
		this.pivotDay = pivotDay;
	}
	
	public Boolean getFollowThruDay() {
		return followThruDay;
	}
	public void setFollowThruDay(Boolean followThruDay) {
		this.followThruDay = followThruDay;
	}
}
