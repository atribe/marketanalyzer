package com.atomrockets.marketanalyzer.persistence.dao.impl;

import com.atomrockets.marketanalyzer.persistence.dao.IYahooIndexDataDao;
import com.atomrockets.marketanalyzer.persistence.dao.common.AbstractHibernateDao;
import com.atomrockets.marketanalyzer.persistence.model.YahooIndexData;

import org.springframework.stereotype.Repository;

@Repository
public class YahooIndexDataDao extends AbstractHibernateDao<YahooIndexData> implements IYahooIndexDataDao {

    public YahooIndexDataDao() {
        super();

        setClazz(YahooIndexData.class);
    }

    // API

}