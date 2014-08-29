package com.ar.marketanalyzer.securities.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.securities.models.parents.PersistableEntityInt;

/**
 * @author Allan
 *
 */
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
	
	/**
	 * Constructor that takes the following arguments
	 * 
	 * @param symbol - String symbol
	 * @param name - String company name
	 * @param type - String type of security (Stock, ETF, Mutual Fund, Index)
	 */
	public Symbol(String symbol, String name, String type) {
		this.symbol = symbol;
		this.name = name;
		this.type = type;
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
