package com.thesaihan.trello.controller;

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

import java.util.Map;

import com.thesaihan.trello.model.List;
import com.thesaihan.trello.repository.ListRepository;

@RestController
@CrossOrigin
@RequestMapping("list")
public class ListController {
	
	@Autowired
	ListRepository listRepository;
	
	@GetMapping
	public java.util.List<List> getAll() {
		return listRepository.findAll();
	}
	
	@GetMapping("{id}")
	public List getById(@PathVariable Long id) {
		return listRepository.getOne(id);
	}

	@PostMapping
	public List save(@RequestBody List list) {
		return listRepository.saveAndFlush(list);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public List update(@RequestBody List list) {
		List oldList = listRepository.getOne(list.getId());
		BeanUtils.copyProperties(list, oldList, "id", "status", "position");
		return listRepository.saveAndFlush(oldList);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable Long id) {
		listRepository.deleteById(id);
	}
	
	@GetMapping("position/{position}")
	public java.util.List<List> getByPositionGTE(@PathVariable Integer position) {
		return listRepository.findByPositionGreaterThanEqual(position);
	}
	
	@GetMapping("title/{searchTerm}")
	public java.util.List<List> getByPositionGTE(@PathVariable String searchTerm) {
		return listRepository.findByTitleContaining(searchTerm);
	}

	@PostMapping("change-status")
	public List changeStatus(@RequestBody Map<String, Long> payload) {
		List li = listRepository.getOne(payload.get("id"));
		li.setStatus((int) payload.get("status").longValue());
		return listRepository.saveAndFlush(li);
	}

}
