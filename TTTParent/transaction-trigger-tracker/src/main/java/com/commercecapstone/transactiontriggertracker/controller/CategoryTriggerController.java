package com.commercecapstone.transactiontriggertracker.controller;

import java.util.*;

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

import com.commercecapstone.transactiontriggertracker.dao.CategoryTriggerDAO;
import com.commercecapstone.transactiontriggertracker.domain.CategoryTriggerDomain;

@RestController
@RequestMapping("/api/categoryTrigger")
public class CategoryTriggerController {
	
	@Autowired
	private CategoryTriggerDAO categoryTriggerDao;
	
	// Get all category triggers domain
	@GetMapping("/all")
	public List<CategoryTriggerDomain> getAllCategoryTrigger(){
		return categoryTriggerDao.getAllCategoryTriggers();
	}
	
	// Get all category triggers for specific user ID
	@GetMapping("/{inputUserID")
	public List<CategoryTriggerDomain> getUserCategoryTriggers(int inputUserID){
		return categoryTriggerDao.getUserCategoryTriggers(inputUserID);
	}
	
	// Add a category trigger domain
	@PostMapping("/all")
	public ResponseEntity<Object> addCategoryTrigger(@RequestBody @Valid @NonNull CategoryTriggerDomain inputTrigger){
		return categoryTriggerDao.addCategoryTrigger(inputTrigger);
	}
	
	// Update a category trigger domain
	@PutMapping("/all")
	public ResponseEntity<Object> updateCategoryTrigger(CategoryTriggerDomain inputTrigger) {
		return categoryTriggerDao.updateCategoryTrigger(inputTrigger);
	}
	
	// Delete a category trigger based on trigger ID and user ID
	@DeleteMapping("/{triggerID}/{userID}")
	public ResponseEntity<Object> deleteCategoryTrigger(int triggerID, int userID) {
		return categoryTriggerDao.deleteCategoryTrigger(triggerID, userID);
	}
	
	

}
