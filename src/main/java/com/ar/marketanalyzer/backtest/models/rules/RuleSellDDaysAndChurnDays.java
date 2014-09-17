package com.ar.marketanalyzer.backtest.models.rules;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;

@Entity
@DiscriminatorValue("Sell:D-Days And Churn Days")
public class RuleSellDDaysAndChurnDays extends AbstractRule {

	private static final long serialVersionUID = 8090046696312115053L;

	/*
	 * Constructors
	 */
	public RuleSellDDaysAndChurnDays() {
		super();
	}
	public RuleSellDDaysAndChurnDays(AbstractModel model) {
		super(model, RuleType.SELL);
	}
}
