package com.ar.marketanalyzer.ibd50.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ar.marketanalyzer.ibd50.models.parents.PersitableEntity;

@Entity
@Table(name = "IBD50_Custom_Index")
public class Ibd50CustomIndex extends PersitableEntity{

	private static final long serialVersionUID = 6291983237483263551L;

	@Column(name="index_name", nullable=false)
	private String indexName;
	
	@Column(name="rank_range_start", nullable=false)
	private Integer rankRangeStart;
	
	@Column(name="rank_range_end", nullable=false)
	private Integer rankRangeEnd;
	
	@OneToMany(mappedBy = "index",cascade = CascadeType.ALL)
	private Collection<Ibd50IndexShares> shareCounts;
	
	public Ibd50CustomIndex() {	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*
	 * Getters and Setters
	 */
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public Integer getRankRangeStart() {
		return rankRangeStart;
	}
	public void setRankRangeStart(Integer rankRangeStart) {
		this.rankRangeStart = rankRangeStart;
	}
	public Integer getRankRangeEnd() {
		return rankRangeEnd;
	}
	public void setRankRangeEnd(Integer rankRangeEnd) {
		this.rankRangeEnd = rankRangeEnd;
	}
}
