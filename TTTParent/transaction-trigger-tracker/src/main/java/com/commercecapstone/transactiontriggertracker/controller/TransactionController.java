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

import com.commercecapstone.transactiontriggertracker.dao.TransactionDao;
import com.commercecapstone.transactiontriggertracker.domain.TransactionDomain;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	// Connect to TransactionDAO
	@Autowired
	private TransactionDao transactionDAO;
	
	// Get all transaction
	@GetMapping("/all")
	public List<TransactionDomain> getAllTransactions() {
		return transactionDAO.getAllTransactions();
	}
	
	// Get all Account transaction based on Account ID
	@GetMapping("/{inputAccountID}")
	public List<TransactionDomain> getAccountTransaction(@PathVariable int inputAccountID) {
		return transactionDAO.getAccountTransactions(inputAccountID);
	}
	
	// Get specific transaction based on Account ID and Transaction ID
	@GetMapping("/{inputAccountID}/{inputTransactionID}")
	public TransactionDomain getTRansaction(@PathVariable int inputAccountID, @PathVariable int inputTransactionID){
		return transactionDAO.getTransaction(inputAccountID, inputTransactionID);
	}
	
	// Add a Transaction
	@PostMapping("/all")
	public ResponseEntity<Object> addTransaction(@RequestBody @Valid @NonNull TransactionDomain inputTransaction){
		return transactionDAO.addTransaction(inputTransaction);
	}
	
	// Update a Transaction
	@PutMapping("/all")
	public ResponseEntity<Object> updateTransaction(TransactionDomain inputTransaction){
		return transactionDAO.updateTransaction(inputTransaction);
	}
	
	// Delete a Transaction based on transaction ID
	@DeleteMapping("/{transactionID}/{accountID")
    public ResponseEntity<Object> deleteTransaction(@PathVariable int transactionID, @PathVariable int accountID) {
		return transactionDAO.deleteTransaction(transactionID, accountID);
	}

}
