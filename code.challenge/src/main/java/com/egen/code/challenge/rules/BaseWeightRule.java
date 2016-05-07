package com.egen.code.challenge.rules;

import org.easyrules.core.BasicRule;

import com.egen.code.challenge.entity.Metric;
import com.egen.code.challenge.mvc.controller.service.IAlertsService;

public abstract class BaseWeightRule extends BasicRule {

	protected IAlertsService alertService;
	
	protected Metric metric;
		
	
	
	
	public BaseWeightRule(String string, String string2) {
		super(string,string2);
	}

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

}
