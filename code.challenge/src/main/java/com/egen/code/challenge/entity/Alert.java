package com.egen.code.challenge.entity;

import org.mongodb.morphia.annotations.Embedded;

public class Alert extends BaseEntity {
	private AlertType type;
	
	@Embedded
	private Metric metric;

	public AlertType getType() {
		return type;
	}

	public void setType(AlertType type) {
		this.type = type;
	}

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}
	
	
}
