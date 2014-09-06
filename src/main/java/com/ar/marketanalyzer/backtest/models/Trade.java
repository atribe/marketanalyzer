package com.ar.marketanalyzer.backtest.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_trade")
public class Trade extends PersistableEntityInt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 295920987530245454L;

}
