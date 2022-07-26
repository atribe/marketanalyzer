package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.repo.AbstractModelRepo;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AbstractModelService {

    private final AbstractModelRepo modelRepo;
    private final RuleParameterService paramService;
    private final RuleResultService resultService;
    private final DollarValueService valueService;

    @Transactional
    public AbstractModel create(AbstractModel model) {
        model.convertValueMapToSet();

        for (AbstractRule rule : model.getRuleList()) {
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

    @Transactional
    public AbstractModel update(AbstractModel model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public AbstractModel delete(int id) throws ModelNotFound {
        AbstractModel modelToDelete = findById(id);

        // if the findById method fails, then exception thrown and this code not run
        modelRepo.deleteById(id);

        return modelToDelete;
    }

    @Transactional
    public List<AbstractModel> getAll() throws ModelNotFound {
        List<AbstractModel> modelList = modelRepo.findAll();

        if (modelList.isEmpty()) {
            throw new ModelNotFound();
        }

        return modelList;
    }

    @Transactional
    public List<AbstractModel> findBySymbol(Symbol symbol) throws ModelNotFound {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public List<AbstractModel> findByModelStatus(ModelStatus modelStatus) throws ModelNotFound {
        //List<AbstractModel> modelList = modelRepo.
        return null;
    }

    @Transactional
    public AbstractModel findById(int id) throws ModelNotFound {
        var foundModel = modelRepo.findById(id);

        if (foundModel.isEmpty()) {
            throw new ModelNotFound();
        }

        return foundModel.get();
    }

    @Transactional
    public AbstractModel findBySymbolAndModelStatus(Symbol symbol, ModelStatus modelStatus) throws ModelNotFound {
        AbstractModel foundModel = modelRepo.findBySymbolAndModelStatus(symbol, modelStatus);

        if (foundModel == null) {
            throw new ModelNotFound();
        }

        return foundModel;
    }

    @Transactional
    public AbstractModel findBySymbolAndModelStatusEager(Symbol symbol, ModelStatus modelStatus) throws ModelNotFound {
        AbstractModel foundModel = modelRepo.findBySymbolAndModelStatus(symbol, modelStatus);

        if (foundModel == null) {
            throw new ModelNotFound();
        }

        return foundModel;
    }
}
