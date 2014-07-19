package com.ar.marketanalyzer.ibd50.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50RankRepository extends JpaRepository<Ibd50Rank, Integer>{

	public List<Ibd50Rank> findByRankAndTickerOrderByRankDateAsc(int rank, TickerSymbol ticker);
	public List<Ibd50Rank> findByModificationTimeAfter(Date date);
	public List<Ibd50Rank> findByActiveTrueAndRankBetween(int startRank, int endRank);
}
