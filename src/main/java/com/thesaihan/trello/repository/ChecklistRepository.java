package com.thesaihan.trello.repository;

import java.util.List;

import com.thesaihan.trello.model.Checklist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

  public List<Checklist> findByCardId(Long cardId);

}