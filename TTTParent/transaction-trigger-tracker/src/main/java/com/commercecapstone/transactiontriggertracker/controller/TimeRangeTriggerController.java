package com.commercecapstone.transactiontriggertracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercecapstone.transactiontriggertracker.dao.TimeRangeTriggerDAO;
import com.commercecapstone.transactiontriggertracker.domain.TimeRangeTriggerDomain;


@RestController
@RequestMapping("/api/timeRange")
public class TimeRangeTriggerController {
	
	@Autowired
	private TimeRangeTriggerDAO timeRangeTriggerDao;
	
	// Get all time range triggers
	@GetMapping("/all")
	public List<TimeRangeTriggerDomain> getAllTimeRangeTriggers() {
		return timeRangeTriggerDao.getAllTimeRangeTriggers();
	}
	
	// Get all time range triggers for specific users
	@GetMapping("/{inputUserID")
    public List<TimeRangeTriggerDomain> getUserTimeRangeTriggers(int inputUserID) {
		return timeRangeTriggerDao.getUserTimeRangeTriggers(inputUserID);
	}
	
	// Add a time range trigger
	@PostMapping("/all")
    public ResponseEntity<Object> addTimeRangeTrigger(@RequestBody @Valid @NonNull TimeRangeTriggerDomain inputTrigger){
		return timeRangeTriggerDao.addTimeRangeTrigger(inputTrigger);
	}
	
	// Update a time range trigger
	@PutMapping("/all")
	public ResponseEntity<Object> updateTimeRangeTrigger(TimeRangeTriggerDomain inputTrigger) {
		return timeRangeTriggerDao.updateTimeRangeTrigger(inputTrigger);
	}

	// Delete a time range trigger based on trigger id and user id
	@DeleteMapping("/{triggerID}/{userID}")
    public ResponseEntity<Object> deleteTimeRangeTrigger(int triggerID, int userID) {
		return timeRangeTriggerDao.deleteTimeRangeTrigger(triggerID, userID);
	}


}
