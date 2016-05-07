package com.egen.code.challenge.mvc.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.mvc.model.IAlertsDAO;

@Controller
public class AlertsServices implements IAlertsService {

	@Autowired
	private IAlertsDAO dao;
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.controller.service.IAlertsService#create(com.egen.code.challenge.entity.Alert)
	 */
	@Override
	public void create(Alert alert){
		dao.createAlert(alert);
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.controller.service.IAlertsService#read()
	 */
	@Override
	public List<Alert> read(){
		return dao.findAllAlerts();
	}
	
	/* (non-Javadoc)
	 * @see com.egen.code.challenge.mvc.controller.service.IAlertsService#read(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Alert> read(Long initial, Long _final){
		return dao.findAllAlerts();
	}
	
}
