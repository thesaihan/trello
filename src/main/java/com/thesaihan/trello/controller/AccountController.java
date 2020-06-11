package com.thesaihan.trello.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thesaihan.trello.model.Account;
import com.thesaihan.trello.repository.AccountRepository;

@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping
	public List<Account> getAll() {
		return accountRepository.findAll();
	}
	
	@GetMapping("{username}")
	public Account getById(@PathVariable String username) {
		return accountRepository.getOne(username);
	}
	
	@PostMapping()
	public Account save(@RequestBody Account account) {
		return accountRepository.saveAndFlush(account);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Account update(@RequestBody Account account) {
		Account oldAccount = accountRepository.getOne(account.getUsername());
		BeanUtils.copyProperties(account, oldAccount, "username");
		return accountRepository.saveAndFlush(oldAccount);
	}
	
	@RequestMapping(value = "{username}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String username) {
		accountRepository.deleteById(username);
	}

}
