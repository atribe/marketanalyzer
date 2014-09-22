package com.ar.marketanalyzer.backtest.models.rules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.models.RuleResult;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;

@Entity
@Inheritance
@DiscriminatorColumn(name="RULE_NAME") //http://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
@Table(name="backtest_rule")
public abstract class AbstractRule implements Serializable{

	private static final long serialVersionUID = 9159243363042551334L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rule_id", nullable=false, unique=true)
	protected Integer ruleId;
    
	@ManyToMany(mappedBy="ruleList")
	protected List<AbstractModel> modelList = new ArrayList<AbstractModel>();
	@Transient
	protected AbstractModel currentModel;
	
	@Enumerated(EnumType.STRING)
	@Column( name="rule_type", nullable=false)
	protected RuleType ruleType;
	
	@OneToMany(mappedBy = "rule",cascade = CascadeType.ALL)
	protected List<RuleParameter> ruleParameters = new ArrayList<RuleParameter>();
	
	@OneToMany(mappedBy="rule", cascade=CascadeType.ALL)
	protected List<RuleResult> ruleResult = new ArrayList<RuleResult>();

	/*
	 * Constructors
	 */
	public AbstractRule() {
		
	}
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
	public abstract void setDefaultParameters();
	public abstract void runRule();
	
	/*
	 * Getters and Setters
	 */
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public void setId(Long ruleId) {
		this.ruleId = (Integer)ruleId.intValue();
	}
	
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
	public List<RuleResult> getRuleResult() {
		return ruleResult;
	}
	public void setRuleResult(List<RuleResult> ruleResult) {
		this.ruleResult = ruleResult;
	}
}
