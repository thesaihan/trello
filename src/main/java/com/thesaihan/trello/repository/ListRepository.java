package com.thesaihan.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesaihan.trello.model.List;

public interface ListRepository extends JpaRepository<List, Long>{
	
	public java.util.List<List> findByPositionGreaterThanEqual(Integer position);
	
	public java.util.List<List> findByTitleContaining(String searchTerm);
	
}
