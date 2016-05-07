package com.egen.code.challenge.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.mongodb.morphia.Key;

import com.egen.code.challenge.dbaccess.config.MorphianConfigurationDataStore;
import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.entity.AlertType;
import com.egen.code.challenge.mvc.controller.MetricsController;
import com.egen.code.challenge.mvc.controller.service.IAlertsService;

public class OverWeightRule extends BaseWeightRule {

	public OverWeightRule(IAlertsService service) {
		super("OveerWeightRule", "Check if base weight ramps up 10%");
		this.alertService = service;
	}

	@Condition
	public boolean evaluate() {
		if (metric.getValue() >= MetricsController.baseValue + metric.getValue() * .1) {
			return true;
		}
		return false;
	}
	
	@Action
	public void execute(){
		Alert alert = new Alert();
		alert.setMetric( metric );
		alert.setType(AlertType.Over_Weight);		
		alertService.create(alert);
    	
	}

}
