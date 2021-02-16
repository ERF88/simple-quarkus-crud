package com.github.erf88;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DataBaseLifeCycle implements QuarkusTestResourceLifecycleManager {
	
	@Override
	public Map<String, String> start() {
		
		Map<String, String> propriedades = new HashMap<>();
		propriedades.put("quarkus.datasource.url", "jdbc:mysql://127.0.0.1:3306/test");
		propriedades.put("quarkus.datasource.driver", "com.mysql.cj.jdbc.Driver");
		propriedades.put("quarkus.datasource.username", "root");
		propriedades.put("quarkus.datasource.password", "root");
		return propriedades;
	}

	@Override
	public void stop() {

	}

}
