package com.ar.marketanalyzer.backtest.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.models.rules.key.RuleResultKey;

@Entity
@IdClass(RuleResultKey.class)
@Table(name = "backtest_rule_result")
public class RuleResult {

	@Id
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="rule_id", referencedColumnName="rule_id")
	private AbstractRule rule;

	@Id
	@Column
	private Date date;
	
	@Column(name = "rule_result")
	private Boolean ruleResult;
	
	/*
	 * Constructors
	 */
	public RuleResult() {
		
	}
	public RuleResult( Date date, Boolean result) {
		this.date = date;
		this.ruleResult = result;
	}
	
	@Override
	public String toString() {
		return "Date: " + date.toString() + " Result: " + ruleResult.toString();
	}
	
	/*
	 * Getters and Setters
	 */
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

	public Boolean getRuleResult() {
		return ruleResult;
	}
	public void setRuleResult(Boolean ruleResult) {
		this.ruleResult = ruleResult;
	}
}