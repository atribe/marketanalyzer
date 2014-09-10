package com.ar.marketanalyzer.backtest.models.rules;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Inheritance
@DiscriminatorColumn(name="RULE_NAME") //http://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
@Table(name="backtest_rule")
public class AbstractRule extends PersistableEntityInt {

	private static final long serialVersionUID = 9159243363042551334L;
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="model_id", referencedColumnName="id")
	private AbstractModel model;
	
	//private List<RuleResult> ruleResult;
	
	//private String ruleParameters; //probably a StringList converted into a String
	
}
