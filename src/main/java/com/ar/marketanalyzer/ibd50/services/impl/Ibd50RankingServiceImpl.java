package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Ranking;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50RankingRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankingService;

@Service
public class Ibd50RankingServiceImpl implements Ibd50RankingService{

	@Resource
	private Ibd50RankingRepository ibd50RankingRepository;
	
	@Override
	@Transactional
	public Ibd50Ranking create(Ibd50Ranking ibd50Ranking) {
		Ibd50Ranking createdIbd50Ranking = ibd50Ranking;
		return ibd50RankingRepository.save(createdIbd50Ranking);
	}

	@Override
	@Transactional
	public Ibd50Ranking findById(int id) {
		return ibd50RankingRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50Ranking delete(int id) throws GenericIbd50NotFound {
		Ibd50Ranking deletedIbd50Ranking = ibd50RankingRepository.findOne(id);
		
		if(deletedIbd50Ranking == null) {
			throw new GenericIbd50NotFound();
		} 

		ibd50RankingRepository.delete(id);
		
		return deletedIbd50Ranking;	
	}

	@Override
	public List<Ibd50Ranking> findAll() {
		return ibd50RankingRepository.findAll();
	}

	@Override
	public Ibd50Ranking update(Ibd50Ranking ibd50Ranking) throws GenericIbd50NotFound {
		Ibd50Ranking updatedIbd50Ranking = ibd50RankingRepository.findOne(ibd50Ranking.getRankingId());
		
		if( updatedIbd50Ranking == null) {
			throw new GenericIbd50NotFound();
		}
		
		//TODO Filler until I really write this code
		updatedIbd50Ranking.setRank(ibd50Ranking.getRank());
		
		return updatedIbd50Ranking;
	}
}
