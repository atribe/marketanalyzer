package com.ar.marketanalyzer.ibd50.repositories;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface Ibd50RankRepository extends JpaRepository<Ibd50Rank, Integer> {

    public List<Ibd50Rank> findByRankingAndTickerOrderByRankDateAsc(int rank, Symbol ticker);

    public List<Ibd50Rank> findByModificationTimeAfter(LocalDateTime date);

    public List<Ibd50Rank> findByTickerAndActiveRankingTrue(Symbol ticker);
	
	/* Keeping this query as an example
	 * @Query(	  "SELECT ir FROM Ibd50Rank ir "
			+ "LEFT OUTER JOIN ir.tracker t "
			+ "WHERE t.active = true "
			+ "AND ir.rank BETWEEN :startRank AND :endRank")
	public List<Ibd50Rank> findByRankBetweenAndActiveTrue(@Param("startRank")int startRank, @Param("endRank")int endRank);
	*/

    //public List<Ibd50Rank> findByActiveRankingTrue();

    public List<Ibd50Rank> findByRankingBetweenAndActiveRankingTrue(int startRank, int endRank);

    @Modifying
    @Transactional
    @Query("Update Ibd50Rank AS r SET r.activeRanking = false "
            + "WHERE r.activeRanking = true")
    public void deactivateActiveRanking();
}
