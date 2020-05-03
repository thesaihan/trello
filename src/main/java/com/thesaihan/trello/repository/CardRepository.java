package com.thesaihan.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesaihan.trello.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
