package com.ar.marketanalyzer.backtest.models.ruleresults;

import com.ar.marketanalyzer.backtest.models.comparables.DateCompImp;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Inheritance
@DiscriminatorColumn(name="RULE_NAME")
@Table(name = "backtest_rule_result")
public class AbstractRuleResult extends PersistableEntityInt implements DateCompImp{

	private static final long serialVersionUID = 6088797692365143508L;

	@ManyToOne
	@JoinColumn(name="rule_id", referencedColumnName = "id")
	protected AbstractRule rule;

	@Column
	protected Date date;
	
	@Column(name = "rule_result")
	protected Boolean ruleResult;
	
	/*
	 * Constructors
	 */
	public AbstractRuleResult() {
		
	}
	public AbstractRuleResult(AbstractRule rule) {
		this.rule = rule;
	}
	public AbstractRuleResult( AbstractRule rule, Date date ) {
		this.rule = rule;
		this.date = date;
	}
	public AbstractRuleResult( AbstractRule rule, Date date, Boolean result) {
		this.rule = rule;
		this.date = date;
		this.ruleResult = result;
	}
	
	@Override
	public String toString() {
		if( ruleResult!=null ) {
			return "Date: " + date.toString() + " Result: " + ruleResult.toString();
		} else {
			return "Date: " + date.toString() + " Result: Not Set"; 
		}
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

	@Override
	public Date getDate() {
		return date;
	}
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getRuleResult() {
		return ruleResult;
	}
	public void setRuleResult(Boolean ruleResult) {
		this.ruleResult = ruleResult;
	}

	@Override
	public int compareTo(DateCompImp o) {
		if(o != null) {
			return this.date.compareTo(o.getDate());
		} else {
			return -1;
		}
	}
}
