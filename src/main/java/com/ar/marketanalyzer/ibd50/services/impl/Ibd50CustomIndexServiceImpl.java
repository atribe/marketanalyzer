package com.ar.marketanalyzer.ibd50.services.impl;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50CustomIndex;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50CustomIndexRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50CustomIndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
	public Ibd50CustomIndex delete(int id) throws SecuritiesNotFound {
		var deletedIbd50CustomIndex = ibd50CustomIndexRepo.findById(id);
		
		if(deletedIbd50CustomIndex.isEmpty()) {
			throw new SecuritiesNotFound();
		}
		
		ibd50CustomIndexRepo.deleteById(id);
		
		return deletedIbd50CustomIndex.get();
	}

	@Override
	@Transactional
	public List<Ibd50CustomIndex> findAll() {
		return ibd50CustomIndexRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50CustomIndex update(Ibd50CustomIndex ibd50CustomIndex) throws SecuritiesNotFound {
		var updatedIbd50CustomIndex = ibd50CustomIndexRepo.findById(ibd50CustomIndex.getId())
				.orElseThrow(SecuritiesNotFound::new);

		updatedIbd50CustomIndex.setIndexName(ibd50CustomIndex.getIndexName());
		updatedIbd50CustomIndex.setRankRangeStart(ibd50CustomIndex.getRankRangeStart());
		updatedIbd50CustomIndex.setRankRangeEnd(ibd50CustomIndex.getRankRangeEnd());
		
		return ibd50CustomIndexRepo.save(updatedIbd50CustomIndex);
	}

	@Override
	@Transactional
	public Ibd50CustomIndex findById(int id) throws SecuritiesNotFound {
		return ibd50CustomIndexRepo.findById(id).orElseThrow(SecuritiesNotFound::new);
	}
}
