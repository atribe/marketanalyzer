package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.Ibd50RankingNotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;

public interface Ibd50RankingService {

	public Ibd50Ranking create(Ibd50Ranking Ibd50Ranking);
	public Ibd50Ranking delete(int id) throws Ibd50RankingNotFound;
	public List<Ibd50Ranking> findAll();
	public Ibd50Ranking update(Ibd50Ranking Ibd50Ranking) throws Ibd50RankingNotFound;
	public Ibd50Ranking findById(int id);
}
