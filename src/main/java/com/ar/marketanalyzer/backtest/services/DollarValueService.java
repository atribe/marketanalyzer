package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.DollarValue;
import com.ar.marketanalyzer.backtest.repo.DollarValueRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.SortedSet;

@RequiredArgsConstructor
@Service
public class DollarValueService {

	private final DollarValueRepo DollarValueRepo;

	@Transactional
	public DollarValue create(DollarValue dollarValue) {

		return DollarValueRepo.save(dollarValue);
	}

	@Transactional
	public void batchCreate(SortedSet<DollarValue> dollarValueList) {
		DollarValueRepo.saveAll(dollarValueList);
	}

	@Transactional
	public DollarValue update(DollarValue dollarValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public DollarValue delete(int id) throws ModelNotFound {
		DollarValue resultToDelete = findById(id);

		// if the findById method fails, then exception thrown and this code not run
		DollarValueRepo.deleteById(id);

		return resultToDelete;
	}

	@Transactional
	public DollarValue findById(int id) throws ModelNotFound {
		var foundDollarValue = DollarValueRepo.findById(id);

		if (foundDollarValue.isEmpty()) {
			throw new ModelNotFound();
		}

		return foundDollarValue.get();
	}
}
