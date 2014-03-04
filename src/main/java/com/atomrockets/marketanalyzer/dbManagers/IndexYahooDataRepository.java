package com.atomrockets.marketanalyzer.dbManagers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atomrockets.marketanalyzer.beans.YahooDataObject;

@Transactional
@Repository
public class IndexYahooDataRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<YahooDataObject> getAllYahooDataObjects() {
		return this.hibernateTemplate.loadAll(YahooDataObject.class);
	}
	
	public Integer createYahooDataObject(YahooDataObject yahooDataObject)
	{
		YahooDataObject mergeYahooDataObject = this.hibernateTemplate.merge(yahooDataObject);
		return mergeYahooDataObject.getYd_id();
	}
}
