package com.ar.marketanalyzer.ibd50.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50RankingRepository extends JpaRepository<Ibd50Ranking, Integer>{

	public List<Ibd50Ranking> findByRankAndTickerOrderByRankDateAsc(int rank, TickerSymbol ticker);
	public List<Ibd50Ranking> findByModificationTimeAfter(Date date);
}
