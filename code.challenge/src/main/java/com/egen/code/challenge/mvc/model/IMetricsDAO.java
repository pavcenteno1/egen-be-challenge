package com.egen.code.challenge.mvc.model;

import java.util.List;

import com.egen.code.challenge.entity.Metric;

public interface IMetricsDAO {

	void createMetric(Metric metric);

	List<Metric> findAllMetrics();

	List<Metric> findMetricsByTimeRange(Long initial, Long _final);

}