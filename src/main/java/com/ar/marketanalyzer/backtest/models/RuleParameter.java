package com.ar.marketanalyzer.backtest.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

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
	/**
	 * @param name
	 * @param value
	 */
	public RuleParameter(String name, String value) {
		setParameterName(name);
		setValue(value);
	}
	public RuleParameter(String name, double value) {
		setParameterName(name);
		setValue(value);
	}
	public RuleParameter(String name, int value) {
		setParameterName(name);
		setValue(value);
	}
	public RuleParameter(String name, boolean value) {
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
