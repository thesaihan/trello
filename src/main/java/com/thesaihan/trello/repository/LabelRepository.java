package com.thesaihan.trello.repository;

import com.thesaihan.trello.model.Label;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

}