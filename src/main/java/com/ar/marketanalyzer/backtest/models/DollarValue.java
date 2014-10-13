package com.ar.marketanalyzer.backtest.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_value")
public class DollarValue extends PersistableEntityInt {

	private static final long serialVersionUID = -1433482868025063181L;


	@ManyToOne(optional=false)
	@JoinColumn(name="model_id", referencedColumnName="id")
	private AbstractModel model;
	
	private Date date;
	
	@Column(precision=12, scale=2, nullable=false)
	private BigDecimal dollarValue;

	/*
	 * Constructors
	 */
	public DollarValue(Date date) {
		this.date = date;
	}

	/*
	 * Helper Methods
	 */
	@Override
	public String toString() {
		return "Date:" + date.toString() + " Value:" + dollarValue;
	}
	
	/*
	 * Getters and Setters
	 */
	public AbstractModel getModel() {
		return model;
	}

	public void setModel(AbstractModel model) {
		this.model = model;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getDollarValue() {
		return dollarValue;
	}

	public void setDollarValue(BigDecimal dollarValue) {
		this.dollarValue = dollarValue;
	}
}
