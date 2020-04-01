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
	@GetMapping("/all")
	public List<TransactionDomain> getAllTransactions() {
		return transactionDAO.getAllTransactions();
	}
	
	// Get specific transaction based on transaction ID
	@GetMapping("/{transactionID}")
	public TransactionDomain getTransaction(@PathVariable int transactionID) {
		return transactionDAO.getTransaction(transactionID);
	}
	
	// Add a Transaction
	@PostMapping("/all")
	public ResponseEntity<Object> addTransaction(@RequestBody @Valid @NonNull TransactionDomain transacion){
		Map<String, Object> inputParams = new HashMap<String,Object>();
		inputParams.put("inputTransaction_ID", transaction.getTransactionID());
		inputParams.put("inputTransaction_type", transaction.getTransactionType());
		inputParams.put("inputTransaction_time", transaction.getTransactionTime());
		inputParams.put("inputState", transaction.getState());
		inputParams.put("inputCategory", transaction.getCategory());
		inputParams.put("inputTransaction_description", transaction.getTransactionDescription());            
		inputParams.put("inputAmmount", transaction.getAmount());
		inputParams.put("inputAccount_ID", transaction.getAccountID());
		return transactionDAO.addTransaction(inputParams);
	}
	
	// Update a Transaction
	@PutMapping("/all")
	public ResponseEntity<Object> updateBuildEntry(TransactionDomain transaction){
		return transactionDAO.updateBuildEntry(transaction);
	}
	
	// Delete a Transaction
	@DeleteMapping("/{transactionID")
    public ResponseEntity<Object> deleteTransaction(@PathVariable int transactionID) {
		return transactionDAO.deleteTransaction(transactionID);
	}

	
}
