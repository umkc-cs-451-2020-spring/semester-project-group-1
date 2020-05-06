package com.commercecapstone.transactiontriggertracker.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercecapstone.transactiontriggertracker.dao.StateTriggerDao;
import com.commercecapstone.transactiontriggertracker.domain.StateTriggerDomain;

@RestController
@RequestMapping("/api/StateTrigger")
public class StateTriggerController {
	
	@Autowired
	private StateTriggerDao stateTriggerDao;
	
	// Get all state triggers
	@GetMapping("/all")
	public List<StateTriggerDomain> getAllStateTriggers(){
		return stateTriggerDao.getAllStateTriggers();
	}
	
	// Get all state triggers of specific user
	@GetMapping("/{inputUserID}")
	public List<StateTriggerDomain> getUserStateTriggers(@PathVariable int inputUserID){
		return stateTriggerDao.getUserStateTriggers(inputUserID);
	}
	
	// Add a state trigger
	@PostMapping("/add/{inputTriggerRule}")
	public ResponseEntity<Object> addStateTrigger(@PathVariable String inputTriggerRule){
		return stateTriggerDao.addStateTrigger(inputTriggerRule);
	}
	
	// Update a state trigger
	@PutMapping("/update")
	public ResponseEntity<Object> updateStateTrigger(@RequestBody @Valid @NonNull StateTriggerDomain inputTrigger){
		return stateTriggerDao.updateStateTrigger(inputTrigger);
		
	}
	
	// Delete a state trigger based on triggerID and userID
	@DeleteMapping("/delete/{triggerID}")
	public ResponseEntity<Object> deleteStateTrigger(@PathVariable int triggerID) {
		return stateTriggerDao.deleteStateTrigger(triggerID);
	}
	
	
	

}
