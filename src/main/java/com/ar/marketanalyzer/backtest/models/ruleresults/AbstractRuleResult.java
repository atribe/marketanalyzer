package com.ar.marketanalyzer.backtest.models.ruleresults;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Inheritance
@DiscriminatorColumn(name="RULE_NAME")
@Table(name = "backtest_rule_result")
public class AbstractRuleResult extends PersistableEntityInt{

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
	public AbstractRuleResult( Date date ) {
		this.date = date;
	}
	public AbstractRuleResult( Date date, Boolean result) {
		this.date = date;
		this.ruleResult = result;
	}
	
	@Override
	public String toString() {
		return "Date: " + date.toString() + " Result: " + ruleResult.toString();
	}
	
	static public boolean listContainsDate(List<AbstractRuleResult> resultList, Date dateInQuestion) {
		boolean exists = false;								// Start with the assumption the date is not in the list
		
		for(AbstractRuleResult result: resultList) {				// Loop through the list
			if(result.getDate().equals(dateInQuestion)) {	// Check if the date of the result is equal to the date in question
				exists = true;								// If the date is in the list set exists to true
				break;										// Stop the loop
			}
		}
		
		return exists;
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
