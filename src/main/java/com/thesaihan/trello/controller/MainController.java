package com.thesaihan.trello.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MainController {
	
	@GetMapping
	public Map<String, String> getVersion() {
		Map<String, String> app = new HashMap<>();
		app.put("name", "Trello (Clone) REST API");
		app.put("description", "Backend REST API for a copy version of Trello using Spring Boot and Spring Data JPA");
		app.put("version", "v1.2");
		return app;
	}

}
