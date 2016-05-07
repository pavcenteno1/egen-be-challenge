package com.egen.code.challenge.mvc.model;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.egen.code.challenge.dbaccess.config.MorphianConfigurationDataStore;
import com.egen.code.challenge.entity.Alert;

@Controller
public class AlertsDAO implements IAlertsDAO {


	@Autowired
	private MorphianConfigurationDataStore dataSource;
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.model.IAlertsDAO#createAlert(com.egen.code.challenge.entity.Alert)
	 */
	@Override
	public void createAlert(Alert alert){
		dataSource.datastore().save(alert);
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.model.IAlertsDAO#findAllAlerts()
	 */
	@Override
	public List<Alert> findAllAlerts(){
		Query<Alert> query = dataSource.datastore().createQuery(Alert.class);
		return query.asList();
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.model.IAlertsDAO#findAlertsByTimeRange(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Alert> findAlertsByTimeRange(Long initial, Long _final){
		
		Query<Alert> query = dataSource.datastore().createQuery(Alert.class);
		query.and( query.criteria("metric.timeStamp").greaterThanOrEq(initial), query.criteria("metric.timeStamp").lessThanOrEq(_final)  );
		return query.asList();
	}	
	
}
