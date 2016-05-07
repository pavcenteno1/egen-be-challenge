package com.egen.code.challenge.mvc.controller.service;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.egen.code.challenge.dbaccess.config.EasyRulesConfiguration;
import com.egen.code.challenge.entity.Metric;
import com.egen.code.challenge.mvc.model.IMetricsDAO;
import com.egen.code.challenge.rules.OverWeightRule;
import com.egen.code.challenge.rules.UnderWeightRule;

@Controller
public class MetricsService implements IMetricsService {

	@Autowired
	private IMetricsDAO dao;
	
	@Autowired
	private EasyRulesConfiguration easyRulesConfiguration;
	
	@Autowired
	private IAlertsService alertService;
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.controller.service.IMetricsService#create(com.egen.code.challenge.entity.Metric)
	 */
	@Override
	public void create(Metric metric){
		Long baseValue = 150L;
		
		dao.createMetric(metric);    	
    	
    	UnderWeightRule underWeight = new UnderWeightRule(alertService);
    	OverWeightRule overWeight = new OverWeightRule(alertService);    	
    	
    	underWeight.setMetric(metric);
    	overWeight.setMetric(metric);
    	
    	RulesEngine rulesEngine = easyRulesConfiguration.getRulesEngine(); 
    	
    	rulesEngine.registerRule(underWeight);
    	rulesEngine.registerRule(overWeight);
    	
    	rulesEngine.fireRules();
		
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.controller.service.IMetricsService#read()
	 */
	@Override
	public List<Metric> read(){
		return dao.findAllMetrics();
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.controller.service.IMetricsService#read(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Metric> read(Long initial, Long _final){
		return dao.findMetricsByTimeRange(initial, _final);
	}
	
}
