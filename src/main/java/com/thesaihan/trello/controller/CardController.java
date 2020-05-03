package com.thesaihan.trello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesaihan.trello.model.Card;
import com.thesaihan.trello.repository.CardRepository;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {
	
	@Autowired
	CardRepository cardRepository;
	
	@GetMapping
	public List<Card> getAll() {
		return cardRepository.findAll();
	}

}
