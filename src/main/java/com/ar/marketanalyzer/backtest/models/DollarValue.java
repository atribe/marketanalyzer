package com.ar.marketanalyzer.backtest.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.comparables.DateCompImp;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_value")
public class DollarValue extends PersistableEntityInt implements DateCompImp{

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

	public DollarValue(AbstractModel model, Date date) {
		this.model = model;
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

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getDollarValue() {
		return dollarValue;
	}

	public void setDollarValue(BigDecimal dollarValue) {
		this.dollarValue = dollarValue;
	}

	@Override
	public int compareTo(DateCompImp o) {
		if(o != null) {
			return this.date.compareTo(o.getDate());
		} else {
			return -1;
		}
	}
}
