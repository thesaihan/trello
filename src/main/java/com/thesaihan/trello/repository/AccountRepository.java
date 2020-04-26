package com.thesaihan.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesaihan.trello.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
