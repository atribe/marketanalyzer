package com.ar.marketanalyzer.backtest.models.ruleresults;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Sell:D-Days And Churn Days")  // Should match the value from the rule
public class RuleResultsDDaysAndChurnDays extends AbstractRuleResult{

	private static final long serialVersionUID = 5530876944518715723L;

	@Column( name = "pivot_day" )
	Boolean pivotDay;
	
	@Column( name = "d_day" )
	Boolean dday;
	
	@Column( name = "churn_day" )
	Boolean churnDay;
	
	@Column( name = "ddays_in_window")
	int ddaysInWindow;

	/*
	 * Constructors
	 */
	public RuleResultsDDaysAndChurnDays() {
		super();
	}
	public RuleResultsDDaysAndChurnDays( Date date ) {
		super(date);
	}
	
	/*
	 * Helper Methods
	 */
	@Override
	public String toString() {
		return "Date: " + date.toString() + " Result: " + ruleResult.toString();
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
	
	public Boolean getDday() {
		return dday;
	}
	public void setDday(Boolean dday) {
		this.dday = dday;
	}

	public Boolean getChurnDay() {
		return churnDay;
	}
	public void setChurnDay(Boolean churnDay) {
		this.churnDay = churnDay;
	}

	public int getDdaysInWindow() {
		return ddaysInWindow;
	}
	public void setDdaysInWindow(int ddaysInWindow) {
		this.ddaysInWindow = ddaysInWindow;
	}
	
}
