package com.ar.marketanalyzer.ibd50.services.impl;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50IndexShares;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50IndexSharesRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50IndexSharesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Ibd50IndexSharesServiceImpl implements Ibd50IndexSharesService{

	@Resource
	private Ibd50IndexSharesRepository ibd50IndexSharesRepo;
	
	@Override
	@Transactional
	public Ibd50IndexShares create(Ibd50IndexShares ibd50IndexShares) {
		Ibd50IndexShares createdIbd50IndexShares = ibd50IndexShares;
		return ibd50IndexSharesRepo.save(createdIbd50IndexShares);
	}

	@Override
	@Transactional
	public Ibd50IndexShares delete(int id) throws SecuritiesNotFound {
		var deletedIbd50IndexShares = ibd50IndexSharesRepo.findById(id);
		
		if(deletedIbd50IndexShares.isEmpty()) {
			throw new SecuritiesNotFound();
		}
		
		ibd50IndexSharesRepo.deleteById(id);
		
		return deletedIbd50IndexShares.get();
	}

	@Override
	@Transactional
	public List<Ibd50IndexShares> findAll() {
		return ibd50IndexSharesRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50IndexShares update(Ibd50IndexShares ibd50IndexShares) throws SecuritiesNotFound {
		var updatedIbd50IndexShares = ibd50IndexSharesRepo.findById(ibd50IndexShares.getId())
				.orElseThrow(SecuritiesNotFound::new);
		
		updatedIbd50IndexShares.setShareCount(ibd50IndexShares.getShareCount());
		
		return ibd50IndexSharesRepo.save(updatedIbd50IndexShares);
	}

	@Override
	@Transactional
	public Ibd50IndexShares findById(int id) throws SecuritiesNotFound {
		return ibd50IndexSharesRepo.findById(id).orElseThrow(SecuritiesNotFound::new);
	}

}
