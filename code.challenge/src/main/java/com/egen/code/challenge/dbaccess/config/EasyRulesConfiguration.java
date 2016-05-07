package com.egen.code.challenge.dbaccess.config;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EasyRulesConfiguration {

	
	
	public RulesEngine getRulesEngine(){
		return RulesEngineBuilder.aNewRulesEngine().named("Rules Engine").build();
	}
	
}
