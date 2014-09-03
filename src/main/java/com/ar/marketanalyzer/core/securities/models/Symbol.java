package com.ar.marketanalyzer.core.securities.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "securities_symbols")
public class Symbol extends PersistableEntityInt {

	private static final long serialVersionUID = 8657512556970860218L;

	@Column(name="symbol", length=15, nullable=false)
	private String symbol;

	@Column(name="name", length=50, nullable=false)
	private String name;

	@Column(name="type", length=10, nullable=false)
	private String type; //stock, ETF, Mutual Fund, Index
	
	@OneToMany(mappedBy = "symbol",cascade = CascadeType.ALL)
	private Collection<SecuritiesOhlcv> ohlcv;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
