package com.thesaihan.trello.controller;

import java.util.List;

import com.thesaihan.trello.model.Checklist;
import com.thesaihan.trello.repository.ChecklistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("checklist")
public class ChecklistController {

  @Autowired
  ChecklistRepository checklistRepository;

  @GetMapping("{cardId}")
  public List<Checklist> getByCardId(@PathVariable Long cardId) {
    return checklistRepository.findByCardId(cardId);
  }

  @PostMapping
  public List<Checklist> saveAll(@RequestBody List<Checklist> checklists) {
    return checklistRepository.saveAll(checklists);
  }

}