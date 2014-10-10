package com.ar.marketanalyzer.backtest.models.rules;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Inheritance
@DiscriminatorColumn(name="RULE_NAME") //http://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
@Table(name="backtest_rule")
public abstract class AbstractRule extends PersistableEntityInt{

	private static final long serialVersionUID = 9159243363042551334L;
    
	@ManyToMany(mappedBy="ruleList")
	protected List<AbstractModel> modelList = new ArrayList<AbstractModel>();
	
	@Transient
	protected AbstractModel currentModel;
	
	@Enumerated(EnumType.STRING)
	@Column( name="rule_type", nullable=false)
	protected RuleType ruleType;
	
	@OneToMany(mappedBy = "rule", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	protected List<RuleParameter> ruleParameters = new ArrayList<RuleParameter>();
	
	@OneToMany(mappedBy = "rule", cascade=CascadeType.REMOVE)
	protected List<AbstractRuleResult> ruleResult = new ArrayList<AbstractRuleResult>();

	/*
	 * Constructors
	 */
	public AbstractRule() {
		
	}
	/**
	 * The constructor of the child class should call super(model, RuleType.XXXX)
	 * It should then check the DB for parameters that already exists.
	 * Then it should populate the default parameters for those that don't exist.
	 * 
	 * @param model
	 * @param ruleType
	 */
	public AbstractRule(AbstractModel model, RuleType ruleType) {
		this.currentModel = model;
		if( !this.modelList.contains(model) ) {
			this.modelList.add(model);
		}
		
		this.ruleType = ruleType;
	}
	
	/*
	 * Helper Methods
	 */
	/**
	 * Sets the default parameters for the rule
	 */
	public abstract void setDefaultParameters();
	/**
	 * The brains of the rule. Does all the calculations for the rule
	 */
	public abstract void runRule();
	/**
	 * Populates the ruleResult list that matches the length of the ohlcv data
	 */
	protected abstract void initializeRuleResultList();
	/**
	 * Turns the results from the rule into the triggers to buy or sell,
	 * depending on the rule.
	 */
	protected abstract void findTriggerDays();
	
	protected boolean parametersAlreadyExist() {
		if( ruleParameters == null || ruleParameters.isEmpty() ) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	/*
	 * Getters and Setters
	 */
	public List<AbstractModel> getModelList() {
		return modelList;
	}
	public void setModelList(List<AbstractModel> model) {
		this.modelList = model;
	}

	public RuleType getRuleType() {
		return ruleType;
	}
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public AbstractModel getCurrentModel() {
		return currentModel;
	}
	public void setCurrentModel(AbstractModel currentModel) {
		this.currentModel = currentModel;
	}
	public List<RuleParameter> getRuleParameters() {
		return ruleParameters;
	}
	public void setRuleParameters(List<RuleParameter> ruleParameters) {
		this.ruleParameters = ruleParameters;
	}
	
	public List<AbstractRuleResult> getRuleResult() {
		return ruleResult;
	}
	public void setRuleResult(List<AbstractRuleResult> ruleResult) {
		this.ruleResult = ruleResult;
	}
	
}
