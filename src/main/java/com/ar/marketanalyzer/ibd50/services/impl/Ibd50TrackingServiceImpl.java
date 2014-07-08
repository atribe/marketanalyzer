package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.GenericIbd50NotFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50TrackingRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50TrackingService;

@Service
public class Ibd50TrackingServiceImpl implements Ibd50TrackingService{
	@Resource
	private Ibd50TrackingRepository ibd50TrackingRepository;
	
	@Override
	@Transactional
	public Ibd50Tracking create(Ibd50Tracking ibd50Tracking) {
		Ibd50Tracking createdIbd50Tracking = ibd50Tracking;
		return ibd50TrackingRepository.save(createdIbd50Tracking);
	}

	@Override
	@Transactional
	public Ibd50Tracking findById(int id) {
		return ibd50TrackingRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=GenericIbd50NotFound.class)
	public Ibd50Tracking delete(int id) throws GenericIbd50NotFound {
		Ibd50Tracking deletedIbd50Tracking = ibd50TrackingRepository.findOne(id);
		
		if(deletedIbd50Tracking == null) {
			throw new GenericIbd50NotFound();
		} 

		ibd50TrackingRepository.delete(id);
		
		return deletedIbd50Tracking;	
	}

	@Override
	public List<Ibd50Tracking> findAll() {
		return ibd50TrackingRepository.findAll();
	}

	@Override
	public Ibd50Tracking update(Ibd50Tracking ibd50Tracking) throws GenericIbd50NotFound {
		Ibd50Tracking updatedIbd50Tracking = ibd50TrackingRepository.findOne(ibd50Tracking.getTrackingId());
		
		if( updatedIbd50Tracking == null) {
			throw new GenericIbd50NotFound();
		}
		
		//TODO Filler until I really write this code
		//updatedIbd50Tracking.setRank(Ibd50Tracking.getRank());
		
		return updatedIbd50Tracking;
	}
}
