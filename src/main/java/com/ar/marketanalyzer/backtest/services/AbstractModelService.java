package com.ar.marketanalyzer.backtest.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.repo.AbstractModelRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.AbstractModelServiceInterface;
import com.ar.marketanalyzer.core.securities.models.Symbol;

@Service
public class AbstractModelService implements AbstractModelServiceInterface {

	@Resource
	private AbstractModelRepo modelRepo;

	@Override
	@Transactional
	public AbstractModel create(AbstractModel model) {
		AbstractModel createdModel = modelRepo.save(model);
		
		System.out.print("WAIT!" + createdModel.getRuleList().get(0).getId());
		
		return createdModel;
	}

	@Override
	@Transactional
	public AbstractModel update(AbstractModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AbstractModel delete(int id) throws ModelNotFound {
		AbstractModel modelToDelete = findById(id);
		
		// if the findById method fails, then exception thrown and this code not run
		modelRepo.delete(id);
		
		return modelToDelete;
	}

	@Override
	@Transactional
	public List<AbstractModel> findBySymbol(Symbol symbol) throws ModelNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AbstractModel findById(int id) throws ModelNotFound {
		AbstractModel foundModel = modelRepo.findOne(id);
		
		if( foundModel == null ) {
			throw new ModelNotFound();
		}
		
		return foundModel;
	}
	
	@Override
	@Transactional
	public AbstractModel findBySymbolAndModelStatus(Symbol symbol, ModelStatus modelStatus) throws ModelNotFound {
		AbstractModel foundModel = modelRepo.findBySymbolAndModelStatus(symbol, modelStatus);
		
		if( foundModel == null ) {
			throw new ModelNotFound();
		}
		
		return foundModel;
	}
}
