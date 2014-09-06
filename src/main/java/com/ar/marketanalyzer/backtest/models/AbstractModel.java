package com.ar.marketanalyzer.backtest.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Inheritance
@DiscriminatorColumn(name="MODEL_NAME") //http://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
@Table(name="backtest_model")
public abstract class AbstractModel extends PersistableEntityInt {

	private static final long serialVersionUID = 5829380092032471186L;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	private Symbol symbol;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<AbstractRule> ruleList;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<Trade> tradeList;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<DollarValue> valueList;
}
