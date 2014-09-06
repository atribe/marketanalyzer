package com.ar.marketanalyzer.backtest.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_value")
public class DollarValue extends PersistableEntityInt {

	private static final long serialVersionUID = -1433482868025063181L;

}
