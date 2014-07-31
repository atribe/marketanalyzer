package com.ar.marketanalyzer.ibd50.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface Ibd50RankRepository extends JpaRepository<Ibd50Rank, Integer>{

	public List<Ibd50Rank> findByRankAndTickerOrderByRankDateAsc(int rank, TickerSymbol ticker);
	public List<Ibd50Rank> findByModificationTimeAfter(Date date);
	public List<Ibd50Rank> findByTickerAndActiveRankingTrue(TickerSymbol ticker);
	
	/* Keeping this query as an example
	 * @Query(	  "SELECT ir FROM Ibd50Rank ir "
			+ "LEFT OUTER JOIN ir.tracker t "
			+ "WHERE t.active = true "
			+ "AND ir.rank BETWEEN :startRank AND :endRank")
	public List<Ibd50Rank> findByRankBetweenAndActiveTrue(@Param("startRank")int startRank, @Param("endRank")int endRank);
	*/
	
	//public List<Ibd50Rank> findByActiveRankingTrue();

	@Modifying
	@Transactional
	@Query( "Update Ibd50Rank AS r SET r.activeRanking = false "
			+ "WHERE r.activeRanking = true")
	public void deactivateActiveRanking();
}