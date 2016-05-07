package com.egen.code.challenge.mvc.model;

import java.util.List;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.egen.code.challenge.dbaccess.config.MorphianConfigurationDataStore;
import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.entity.Metric;

@Controller
public class MetricsDAO implements IMetricsDAO {

	@Autowired
	private MorphianConfigurationDataStore dataSource;
	
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.model.IMetricsDAO#createMetric(com.egen.code.challenge.entity.Metric)
	 */
	@Override
	public void createMetric(Metric metric){
		Key<Metric> savedMetric = dataSource.datastore().save(metric);
		System.out.println("Saved Metric!! " + savedMetric);
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.model.IMetricsDAO#findAllMetrics()
	 */
	@Override
	public List<Metric> findAllMetrics(){
		
		Query<Metric> query = dataSource.datastore().createQuery(Metric.class);
    	return query.asList();
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.model.IMetricsDAO#findMetricsByTimeRange(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Metric> findMetricsByTimeRange(Long initial, Long _final){
		Query<Metric> query = dataSource.datastore().createQuery(Metric.class);
    	query.and( query.criteria("timeStamp").greaterThanOrEq(initial), query.criteria("timeStamp").lessThanOrEq(_final) );
    	return query.asList();
	}
	
}
