package com.ar.marketanalyzer.core.securities.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private List<SecuritiesOhlcv> ohlcv;
	
	@Override
	public String toString() {
		return "ID:" + id + ", " +symbol + ", " + name + ", " + type;
	}
	
	/*
	 * =====Constructors=====
	 */
	public Symbol() {};
	
	public Symbol(String symbol, String name, String type) {
		this.symbol = symbol;
		this.name = name;
		this.type = type;
	}
	
	/*
	 * =====Getters and Setters=======
	 */
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
