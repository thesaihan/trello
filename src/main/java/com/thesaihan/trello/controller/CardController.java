package com.thesaihan.trello.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesaihan.trello.model.Account;
import com.thesaihan.trello.model.Card;
import com.thesaihan.trello.model.Checklist;
import com.thesaihan.trello.model.Label;
import com.thesaihan.trello.repository.AccountRepository;
import com.thesaihan.trello.repository.CardRepository;
import com.thesaihan.trello.repository.ChecklistRepository;
import com.thesaihan.trello.repository.LabelRepository;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {

	@Autowired
	CardRepository cardRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	LabelRepository labelRepository;
	@Autowired
	ChecklistRepository checklistRepository;

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

	@PostMapping(value = "member")
	public Card addMember(@RequestBody Map<String, Object> payload) {
		Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
		Set<Account> members = card.getMembers();
		if (members == null) {
			members = new HashSet<>();
		}
		members.add(accountRepository.getOne(payload.get("accountUsername").toString()));
		card.setMembers(members);
		return cardRepository.saveAndFlush(card);
	}

	@PostMapping(value = "label")
	public Card addLabel(@RequestBody Map<String, Long> payload) {
		Card card = cardRepository.getOne(payload.get("cardId"));
		Set<Label> labels = card.getLabels();
		if (labels == null) {
			labels = new HashSet<>();
		}
		labels.add(labelRepository.getOne(payload.get("labelId")));
		card.setLabels(labels);
		return cardRepository.saveAndFlush(card);
	}

	@DeleteMapping(value = "member")
	public Card removeMember(@RequestBody Map<String, Object> payload) {
		Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
		Set<Account> members = card.getMembers();
		if (members == null) {
			members = new HashSet<>();
		}
		members.removeIf(acc -> acc.getUsername().equals(payload.get("accountUsername").toString()));
		card.setMembers(members);
		return cardRepository.saveAndFlush(card);
	}

	@DeleteMapping(value = "label")
	public Card removeLabel(@RequestBody Map<String, Long> payload) {
		Card card = cardRepository.getOne(payload.get("cardId"));
		Set<Label> labels = card.getLabels();
		if (labels == null) {
			labels = new HashSet<>();
		}
		labels.removeIf(lbl -> lbl.getId().equals(payload.get("labelId")));
		card.setLabels(labels);
		return cardRepository.saveAndFlush(card);
	}

	@PostMapping(value = "reorder-checklist")
	public Card addChecklist(@RequestBody Card payload) {
		Card card = cardRepository.getOne(payload.getId());
		List<Checklist> checklists = payload.getChecklists();
		if (checklists == null) {
			checklists = new ArrayList<>();
		}
		for (int i = 0; i < checklists.size(); i++) {
			checklists.get(i).setPosition((short) i);
		}
		card.setChecklists(checklists);
		return cardRepository.saveAndFlush(card);
	}

	@Transactional
	@DeleteMapping("{cardId}/checklist")
	public Long deleteChecklistByCardId(@PathVariable Long cardId) {
		return checklistRepository.deleteByCardId(cardId);
	}

}
