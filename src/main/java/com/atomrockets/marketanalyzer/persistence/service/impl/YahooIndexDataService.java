package com.atomrockets.marketanalyzer.persistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atomrockets.marketanalyzer.persistence.service.IYahooIndexDataService;
import com.atomrockets.marketanalyzer.persistence.service.common.AbstractService;
import com.atomrockets.marketanalyzer.persistence.dao.IYahooIndexDataDao;
import com.atomrockets.marketanalyzer.persistence.dao.common.IOperations;
import com.atomrockets.marketanalyzer.persistence.model.YahooIndexData;

@Service(value = "YahooIndexDataService")
@Transactional
public class YahooIndexDataService extends AbstractService<YahooIndexData> implements IYahooIndexDataService {

    @Autowired
    private IYahooIndexDataDao dao;

    public YahooIndexDataService() {
        super();
    }

    // API

    @Override
    protected IOperations<YahooIndexData> getDao() {
        return dao;
    }

}