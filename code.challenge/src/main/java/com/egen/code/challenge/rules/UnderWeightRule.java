package com.egen.code.challenge.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.core.BasicRule;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;

import com.egen.code.challenge.dbaccess.config.MorphianConfigurationDataStore;
import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.entity.AlertType;
import com.egen.code.challenge.entity.Metric;
import com.egen.code.challenge.mvc.controller.MetricsController;
import com.egen.code.challenge.mvc.controller.service.IAlertsService;

public class UnderWeightRule extends BaseWeightRule {

	public UnderWeightRule(IAlertsService service){
		super("UnderWeightRule", "Check if base weight drops 10%");
		this.alertService = service;
	}
	
	@Condition
	public boolean evaluate(){
		if(  metric.getValue() <= MetricsController.baseValue - metric.getValue() * .1  ){
			
			return true;
    		
    	}
		return false;
	}
	
	@Action
	public void execute(){
		Alert alert = new Alert();
		alert.setMetric( metric );
		alert.setType(AlertType.Under_Weight);
		
		alertService.create(alert);
    	
	}
}
