package com.ar.marketanalyzer.backtest.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_rule_result")
public class RuleResult extends PersistableEntityInt{

	private static final long serialVersionUID = 6088797692365143508L;

	@ManyToOne(optional=false)
	@JoinColumn(name="rule_id", referencedColumnName = "id")
	private AbstractRule rule;

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
