package com.ar.marketanalyzer.ibd50.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Tracking;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50TrackingRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50TrackingService;

@Service
public class Ibd50TrackingServiceImpl implements Ibd50TrackingService{
	@Resource
	private Ibd50TrackingRepository ibd50TrackingRepo;
	@Autowired
	private SymbolServiceInterface tickerSymbolService;
	
	@Override
	@Transactional
	public Ibd50Tracking create(Ibd50Tracking ibd50Tracking) {
		Ibd50Tracking createdIbd50Tracking = ibd50Tracking;
		return ibd50TrackingRepo.save(createdIbd50Tracking);
	}

	@Override
	@Transactional
	public Ibd50Tracking findById(int id) {
		return ibd50TrackingRepo.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50Tracking delete(int id) throws SecuritiesNotFound {
		Ibd50Tracking deletedIbd50Tracking = ibd50TrackingRepo.findOne(id);
		
		if(deletedIbd50Tracking == null) {
			throw new SecuritiesNotFound();
		} 

		ibd50TrackingRepo.delete(id);
		
		return deletedIbd50Tracking;	
	}

	@Override
	@Transactional
	public List<Ibd50Tracking> findAll() {
		return ibd50TrackingRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50Tracking update(Ibd50Tracking ibd50Tracking) throws SecuritiesNotFound {
		Ibd50Tracking updatedIbd50Tracking = ibd50TrackingRepo.findOne(ibd50Tracking.getId());
		
		if( updatedIbd50Tracking == null) {
			throw new SecuritiesNotFound();
		}
		
		//updatedIbd50Tracking.setRank(Ibd50Tracking.getRank());
		
		return updatedIbd50Tracking;
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50Tracking findByActiveAndTicker(boolean active, Symbol ticker) throws SecuritiesNotFound, Ibd50TooManyFound {
		Symbol foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());
		
		if( foundTickerSymbol == null ) {
			throw new SecuritiesNotFound("By Active and Ticker search for " + ticker.getSymbol() + " failed because the Ticker was not found in the Ticker DB.");
		}
		
		List<Ibd50Tracking> trackingList = ibd50TrackingRepo.findByActiveAndTicker(active, foundTickerSymbol);
		
		if(trackingList.isEmpty()) {
			throw new SecuritiesNotFound("The Ticker '" + foundTickerSymbol.getSymbol() + "' was not found with the active state '" + active + "'.");
		} else if( trackingList.size() > 1 ) {
			throw new Ibd50TooManyFound("The Ticker'" + foundTickerSymbol.getSymbol() + "' had more than one row returned. This is a problem and needs to be debugged.");
		}
				
		return trackingList.get(0);
	}

	@Override
	@Transactional
	public List<Ibd50Tracking> findByActiveTrue() {
		return ibd50TrackingRepo.findByActiveTrue();
		
	}

	@Override
	@Transactional
	public Ibd50Tracking updateActivity(Ibd50Tracking tracker) {
		Ibd50Tracking updatedTracker = ibd50TrackingRepo.findOne(tracker.getId());
		
		updatedTracker.setActive(tracker.getActive());
		updatedTracker.setLastPrice(tracker.getLastPrice());
		updatedTracker.setLeaveDate(tracker.getLeaveDate());
		updatedTracker.setPercentReturn(tracker.getPercentReturn());
		
		return ibd50TrackingRepo.save(updatedTracker);
	}

	@Override
	@Transactional
	public List<Ibd50Tracking> findByActiveFalseAndDateAfter(LocalDate date) throws SecuritiesNotFound {
		List<Ibd50Tracking> deactiveTrackers = null;
		
		deactiveTrackers = ibd50TrackingRepo.findByActiveFalseAndLeaveDateGreaterThanEqual(date.toDate());
		
		if( deactiveTrackers == null ) {
			throw new SecuritiesNotFound();
		}
		
		return deactiveTrackers;
	}

}
