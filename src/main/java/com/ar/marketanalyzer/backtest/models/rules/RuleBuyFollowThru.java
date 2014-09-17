package com.ar.marketanalyzer.backtest.models.rules;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;

@Entity
@DiscriminatorValue("Buy:Follow Thru")
public class RuleBuyFollowThru extends AbstractRule {

	private static final long serialVersionUID = 6794964611277097413L;

	/*
	 * Constructors
	 */
	public RuleBuyFollowThru() {
		super();
	}
	public RuleBuyFollowThru(AbstractModel model) {
		super(model, RuleType.BUY);
	}
}
