package com.ar.marketanalyzer.backtest.models;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "backtest_rule_parameters")
public class RuleParameter extends PersistableEntityInt{

	private static final long serialVersionUID = -5560880183141014649L;

	@Column(name = "parameter_name")
	private String parameterName;
	
	@Column(name = "parameter_value")
	private String value;
	
	@ManyToOne
	private AbstractRule rule;

	/*
	 * Constructors
	 */
	/**
	 * Optional parameters: String name, whatever value
	 */
	public RuleParameter() {
		
	}
	public RuleParameter(AbstractRule rule) {
		this.rule = rule;
	}
	/**
	 * @param name
	 * @param value
	 */
	public RuleParameter(AbstractRule rule, String name, String value) {
		this.rule = rule;
		setParameterName(name);
		setValue(value);
	}
	public RuleParameter(AbstractRule rule, String name, double value) {
		this.rule = rule;
		setParameterName(name);
		setValue(value);
	}
	public RuleParameter(AbstractRule rule, String name, int value) {
		this.rule = rule;
		setParameterName(name);
		setValue(value);
	}
	public RuleParameter(AbstractRule rule, String name, boolean value) {
		this.rule = rule;
		setParameterName(name);
		setValue(value);
	}
	
	@Override
	public String toString() {
		return parameterName + ": " + value;
	}
	
	/*
	 * Getters and Setters
	 */
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getValue() {
		return value;
	}
	public int getValueInt() {
		return Integer.getInteger(value);
	}
	public double getValueDouble() {
		return Double.parseDouble(value);
	}
	public BigDecimal getValueBigDecimal() {
		return new BigDecimal(value);
	}

	public void setValue(String value) {
		this.value = value;
	}
	public void setValue(boolean value) {
		this.value = String.valueOf(value);
	}
	public void setValue(double value) {
		this.value = String.valueOf(value);
	}
	public void setValue(int value) {
		this.value = String.valueOf(value);
	}

	public AbstractRule getRule() {
		return rule;
	}

	public void setRule(AbstractRule rule) {
		this.rule = rule;
	}
}
