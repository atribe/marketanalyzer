package com.ar.marketanalyzer.backtest.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.repo.AbstractModelRepo;
import com.ar.marketanalyzer.backtest.services.interfaces.AbstractModelServiceInterface;
import com.ar.marketanalyzer.core.securities.models.Symbol;

@Service
public class AbstractModelService implements AbstractModelServiceInterface {

	@Resource
	private AbstractModelRepo modelRepo;
	
	@Autowired
	private RuleParameterService paramService;
	
	@Autowired
	private RuleResultService resultService;
	
	@Autowired
	private DollarValueService valueService;

	@Override
	@Transactional
	public AbstractModel create(AbstractModel model) {
		model.convertValueMapToSet();
		
		for(AbstractRule rule: model.getRuleList()) {
			rule.convertRuleResultMapToSet();
		}
		
		AbstractModel createdModel = modelRepo.save(model);
		
		/*
		for(AbstractRule rule: createdModel.getRuleList()) {
			for(int i = 0; i < rule.getRuleParameters().size(); i++) {
				rule.getRuleParameters().get(i).setRule(rule);
			}
			for(int i = 0; i <rule.getRuleResult().size(); i++) {
				rule.getRuleResult().get(i).setRule(rule);
			}
			
			paramService.batchCreate(rule.getRuleParameters());
			
			resultService.batchCreate(rule.getRuleResultSet());
		}
		*/
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
	public List<AbstractModel> getAll() throws ModelNotFound {
		List<AbstractModel> modelList = modelRepo.findAll();
		
		if(modelList == null) {
			throw new ModelNotFound();
		}
		
		return modelList;
	}
	
	@Override
	@Transactional
	public List<AbstractModel> findBySymbol(Symbol symbol) throws ModelNotFound {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public List<AbstractModel> findByModelStatus(ModelStatus modelStatus) throws ModelNotFound{
		//List<AbstractModel> modelList = modelRepo.
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
