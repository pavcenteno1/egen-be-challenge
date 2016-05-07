package com.egen.code.challenge.mvc.model;

import java.util.List;

import com.egen.code.challenge.entity.Alert;

public interface IAlertsDAO {

	void createAlert(Alert alert);

	List<Alert> findAllAlerts();

	List<Alert> findAlertsByTimeRange(Long initial, Long _final);

}