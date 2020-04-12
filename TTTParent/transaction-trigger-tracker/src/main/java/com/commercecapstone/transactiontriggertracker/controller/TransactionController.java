package com.commercecapstone.transactiontriggertracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// Really just for testing purposes
	@GetMapping("/all")
	public List<TransactionDomain> getAllTransactions() {
		return transactionDAO.getAllTransactions();
	}
	
	/*
	 * TODO: Is this best practice? Maybe pass in a response body with account ID instead?
	 * Returns all transactions for a specific account
	 */
	@GetMapping("/account/{accountID}")
	public List<TransactionDomain> getAccountTransactions(@PathVariable int accountID) {
		return transactionDAO.getAccountTransactions(accountID);
	}
	
	/*
	 * Probably don't need to grab a single transaction any where in the app, leaving in just in case
	 * Get specific transaction based on transaction ID
	 */
	
	/*
	@GetMapping("/{transactionID}")
	public TransactionDomain getTransaction(@PathVariable int transactionID) {
		return transactionDAO.getTransaction(transactionID);
	}
	*/
	
	// Add a Transaction
	@PostMapping("/add")
	public ResponseEntity<Object> addTransaction(@RequestBody @Valid @NonNull TransactionDomain transaction){
		return transactionDAO.addTransaction(transaction);
	}
	
	// Update a Transaction
	@PutMapping("/update")
	public ResponseEntity<Object> updateTransactionEntry(@RequestBody TransactionDomain transaction){
		return transactionDAO.updateTransaction(transaction);
	}
	
	// Delete a Transaction based on transaction ID
	@DeleteMapping("/delete/{transactionID}/{accountID}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable int transactionID, @PathVariable int accountID) {
		return transactionDAO.deleteTransaction(transactionID, accountID);
	}
	
	

	
}
