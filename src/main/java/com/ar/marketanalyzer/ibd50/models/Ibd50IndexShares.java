package com.ar.marketanalyzer.ibd50.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ar.marketanalyzer.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "ibd50_index_shares")
public class Ibd50IndexShares extends PersistableEntityInt{

	private static final long serialVersionUID = 8404969144499301634L;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="custom_index_id", referencedColumnName="id")
	private Ibd50CustomIndex index;
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="ranking_id", referencedColumnName="id")
	private Ibd50Rank ranking;
	
	@OneToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="ohlcv_id", referencedColumnName="id")
	private StockOhlcv ohlcv;
	
	private Double shareCount;
	
	public Ibd50IndexShares() {}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Ibd50CustomIndex getIndex() {
		return index;
	}
	public void setIndex(Ibd50CustomIndex index) {
		this.index = index;
	}
	public Ibd50Rank getRanking() {
		return ranking;
	}
	public void setRanking(Ibd50Rank ranking) {
		this.ranking = ranking;
	}
	public StockOhlcv getOhlcv() {
		return ohlcv;
	}
	public void setOhlcv(StockOhlcv ohlcv) {
		this.ohlcv = ohlcv;
	}
	public Double getShareCount() {
		return shareCount;
	}
	public void setShareCount(Double shareCount) {
		this.shareCount = shareCount;
	}
}
