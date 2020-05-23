package com.thesaihan.trello.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesaihan.trello.model.Account;
import com.thesaihan.trello.model.Card;
import com.thesaihan.trello.repository.AccountRepository;
import com.thesaihan.trello.repository.CardRepository;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {
	
	@Autowired
	CardRepository cardRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping
	public List<Card> getAll() {
		return cardRepository.findAll();
	}

	@GetMapping("{id}")
	public Card getById(@PathVariable Long id) {
		return cardRepository.getOne(id);
	}

	@PostMapping
	public Card save(@RequestBody Card card) {
		return cardRepository.save(card);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Card update(@RequestBody Card card) {
		Card oldCard = cardRepository.getOne(card.getId());
		BeanUtils.copyProperties(card, oldCard, "id", "position", "status");
		return cardRepository.saveAndFlush(oldCard);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable Long id) {
		cardRepository.deleteById(id);
	}

	@PostMapping(value = "add-member")
	public Card addMember(@RequestBody Map<String, Object> payload) {
		Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
		Set<Account> members = card.getMembers();
		if(members == null){
			members = new HashSet<>();
		}
		members.add(accountRepository.getOne(payload.get("accountUsername").toString()));
		card.setMembers(members);
		return cardRepository.saveAndFlush(card);
	}

}
