package com.egen.code.challenge.mvc.controller.service;

import java.util.List;

import com.egen.code.challenge.entity.Alert;

public interface IAlertsService {

	void create(Alert alert);

	List<Alert> read();

	List<Alert> read(Long initial, Long _final);

}