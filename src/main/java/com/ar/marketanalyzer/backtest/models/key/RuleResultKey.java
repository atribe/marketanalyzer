package com.ar.marketanalyzer.backtest.models.key;

import java.sql.Date;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.backtest.models.AbstractRule;

public class RuleResultKey {
	private AbstractRule rule;
	private Date date;
	
	public RuleResultKey() {
		
	}
	public RuleResultKey(AbstractRule rule, Date date) {
		this.rule = rule;
		this.date = date;
	}
	public RuleResultKey(AbstractRule rule, LocalDate date) {
		this.rule = rule;
		this.date = new Date( date.toDateTimeAtStartOfDay().getMillis() );
	}
	
	public AbstractRule getRule() {
		return rule;
	}
	public void setRule(AbstractRule rule) {
		this.rule = rule;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
