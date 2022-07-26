package com.ar.marketanalyzer.backtest.models.ruleresults;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Sell:D-Days And Churn Days")  // Should match the value from the rule
public class RuleResultsDDaysAndChurnDays extends AbstractRuleResult{

	private static final long serialVersionUID = 5530876944518715723L;

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
	public RuleResultsDDaysAndChurnDays(AbstractRule rule) {
		super(rule);
	}
	public RuleResultsDDaysAndChurnDays( AbstractRule rule, LocalDateTime date) {
		super(rule, date);
	}
	public RuleResultsDDaysAndChurnDays( AbstractRule rule, LocalDateTime date, Boolean result ) {
		super(rule, date, result);
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
