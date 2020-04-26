package com.thesaihan.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesaihan.trello.model.List;
import com.thesaihan.trello.repository.ListRepository;

@RestController
@CrossOrigin
@RequestMapping("list")
public class ListController {
	
	@Autowired
	ListRepository listRepository;
	
	@GetMapping
	public java.util.List<List> getAll() {
		return listRepository.findAll();
	}
	
	@GetMapping("position/{position}")
	public java.util.List<List> getByPositionGTE(@PathVariable Integer position) {
		return listRepository.findByPositionGreaterThanEqual(position);
	}

}
