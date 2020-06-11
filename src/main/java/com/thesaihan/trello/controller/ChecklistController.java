package com.thesaihan.trello.controller;

import java.util.List;
import java.util.Map;

import com.thesaihan.trello.model.Checklist;
import com.thesaihan.trello.repository.ChecklistRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("checklist")
public class ChecklistController {

  @Autowired
  ChecklistRepository checklistRepository;

  @PostMapping
  public Checklist save(@RequestBody Checklist checklist) {
    return checklistRepository.saveAndFlush(checklist);
  }
  
  @PutMapping
  public Checklist update(@RequestBody Checklist checklist) {
    Checklist oldCl = checklistRepository.getOne(checklist.getId());
    BeanUtils.copyProperties(checklist, oldCl, "id", "cardId", "position", "title");
    return checklistRepository.saveAndFlush(oldCl);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id) {
    try {
      checklistRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @GetMapping("{cardId}")
  public List<Checklist> getByCardId(@PathVariable Long cardId) {
    return checklistRepository.findByCardId(cardId);
  }

  @PostMapping("{cardId}")
  public List<Checklist> saveAll(@RequestBody List<Checklist> checklists) {
    return checklistRepository.saveAll(checklists);
  }

  @PostMapping("check")
  public Checklist check(@RequestBody Map<String, Long> payload) {
    Checklist chk = checklistRepository.getOne(payload.get("id"));
    return checklistRepository.saveAndFlush(chk);
  }

}