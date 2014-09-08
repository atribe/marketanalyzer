package com.ar.marketanalyzer.backtest.models.rules.key;

import java.io.Serializable;
import java.sql.Date;

import org.joda.time.LocalDate;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;

public class RuleResultKey implements Serializable{
	
	private static final long serialVersionUID = 8500186321606923666L;
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
