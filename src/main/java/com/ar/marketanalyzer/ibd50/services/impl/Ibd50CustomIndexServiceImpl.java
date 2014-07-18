package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50CustomIndex;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50CustomIndexRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50CustomIndexService;

@Service
public class Ibd50CustomIndexServiceImpl implements Ibd50CustomIndexService{

	@Resource
	private Ibd50CustomIndexRepository ibd50CustomIndexRepo;

	
	/*
	 * Database Functions
	 */
	
	@Override
	@Transactional
 	public Ibd50CustomIndex create(Ibd50CustomIndex ibd50CustomIndex) {
		Ibd50CustomIndex createdIbd50CustomIndex = ibd50CustomIndex;
		return ibd50CustomIndexRepo.save(createdIbd50CustomIndex);
	}

	@Override
	@Transactional
	public Ibd50CustomIndex delete(int id) throws GenericIbd50NotFound {
		Ibd50CustomIndex deletedIbd50CustomIndex = ibd50CustomIndexRepo.findOne(id);
		
		if(deletedIbd50CustomIndex == null) {
			throw new GenericIbd50NotFound();
		}
		
		ibd50CustomIndexRepo.delete(id);
		
		return deletedIbd50CustomIndex;
	}

	@Override
	@Transactional
	public List<Ibd50CustomIndex> findAll() {
		return ibd50CustomIndexRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50CustomIndex update(Ibd50CustomIndex ibd50CustomIndex) throws GenericIbd50NotFound {
		Ibd50CustomIndex updatedIbd50CustomIndex = ibd50CustomIndexRepo.findOne(ibd50CustomIndex.getId());
		
		if(updatedIbd50CustomIndex == null) {
			throw new GenericIbd50NotFound();
		}

		updatedIbd50CustomIndex.setIndexName(ibd50CustomIndex.getIndexName());
		updatedIbd50CustomIndex.setRankRangeStart(ibd50CustomIndex.getRankRangeStart());
		updatedIbd50CustomIndex.setRankRangeEnd(ibd50CustomIndex.getRankRangeEnd());
		
		return ibd50CustomIndexRepo.save(updatedIbd50CustomIndex);
	}

	@Override
	@Transactional
	public Ibd50CustomIndex findById(int id) throws GenericIbd50NotFound {
		return ibd50CustomIndexRepo.findOne(id);
	}
}
