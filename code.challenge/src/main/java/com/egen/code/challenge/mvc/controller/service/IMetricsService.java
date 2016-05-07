package com.egen.code.challenge.mvc.controller.service;

import java.util.List;

import com.egen.code.challenge.entity.Metric;

public interface IMetricsService {

	void create(Metric metric);

	List<Metric> read();

	List<Metric> read(Long initial, Long _final);

}