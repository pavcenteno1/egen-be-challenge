package com.egen.code.challenge.mvc.controller;

import java.util.List;

import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.code.challenge.dbaccess.config.EasyRulesConfiguration;
import com.egen.code.challenge.dbaccess.config.MorphianConfigurationDataStore;
import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.entity.AlertType;
import com.egen.code.challenge.entity.Metric;
import com.egen.code.challenge.mvc.controller.service.IMetricsService;
import com.egen.code.challenge.rules.OverWeightRule;
import com.egen.code.challenge.rules.UnderWeightRule;

@RestController
public class MetricsController {

	
	@Autowired
	private IMetricsService service;
	
	public static  Long baseValue = 0L;
	
    
    @RequestMapping(method=RequestMethod.POST, path="/create")
    public void create(@RequestBody Metric data){    	
    	if( baseValue == 0 ){
    		baseValue = data.getValue();
    	}
    	service.create(data);
    }
    
    @RequestMapping(method=RequestMethod.GET, path="/read")
    public List<Metric> read(){
    	return service.read();
    }
   
    @RequestMapping(method=RequestMethod.GET, path="/readByTimeRange")
    public List<Metric> readByTimeRange( @RequestParam(value="initial") Long initial, @RequestParam(value="final") Long _final){
    	return service.read(initial, _final);
    }
	
}
