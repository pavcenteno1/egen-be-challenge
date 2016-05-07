package com.egen.code.challenge.mvc.controller;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.code.challenge.dbaccess.config.MorphianConfigurationDataStore;
import com.egen.code.challenge.entity.Alert;
import com.egen.code.challenge.mvc.controller.service.IAlertsService;

@RestController
public class AlertsController {

	@Autowired
	private IAlertsService service;
	
	
	@RequestMapping(method=RequestMethod.GET, path="/alert/read")
	private List<Alert> read(){		
		return service.read();		
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/alert/readByTimeRange")
	private List<Alert> readByTimeRange(@RequestParam(value="initial") Long initial, @RequestParam(value="final") Long _final){		
		return service.read(initial, _final);		
	}
	
}
