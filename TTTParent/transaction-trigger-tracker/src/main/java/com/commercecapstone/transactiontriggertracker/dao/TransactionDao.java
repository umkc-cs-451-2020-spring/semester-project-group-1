package com.commercecapstone.transactiontriggertracker.dao;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogFile;
import org.springframework.dao.DataAccessException;

import com.commercecapstone.transactiontriggertracker.domain.TransactionDomain;
import com.commercecapstone.transactiontriggertracker.service.TransactionRowMapper;

import lombok.extern.slf4j.Slf4j;

@Repository @Slf4j
public class TransactionDao extends BaseDao {
    @Autowired
    TransactionRowMapper transactionMapper;// appMapper changed as notificationMapper

    /**
     * Gets all the notifications from the Notification Table
     * 
     * @return List of NotificationDomain
     */
    public List<TransactionDomain> getAllTransactions() {
        List<TransactionDomain> transactionList = new ArrayList<>();

        String typeQuery = "select * from Transaction";

        try {
            transactionList = get().query(typeQuery, transactionMapper);
            log.info("Transaction table successfully retrieved");
        } 
        catch(NullPointerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch(DataAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return transactionList;
            
    }
    /** Gets all of one account's transactions.
     * 
     * @param int transactionId
     * @param int accountId
     * @return TransactionDomain
     */
    public List<TransactionDomain> getAccountTransactions(int inputAccountID) {
    	List<TransactionDomain> accountTransactions = null;
        
        String typeQuery = "select * from Transaction where Account_ID = :inAccount_ID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inAccount_ID", inputAccountID);
        
        try {
        	accountTransactions = get().query(typeQuery, params, transactionMapper);
            log.info("Transactions for AccountID: {} successfully retrieved", inputAccountID);
        }
        catch(NullPointerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch(DataAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        
        return accountTransactions;
        
    }
    
	/*
	 * Probably don't need to grab a single transaction any where in the app, leaving in just in case
	 * Get specific transaction based on transaction ID
	 */
    
    /*
    public TransactionDomain getTransaction(int inputAccountID, int inputTransactionID) {
    	List<TransactionDomain> transactionWrapper = null;
        
        String typeQuery = "select * from Transaction where Transaction_ID = :inTransaction_ID and Account_ID = :inAccount_ID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inTransaction_ID", inputTransactionID);
        params.put("inAccount_ID", inputAccountID);
        
        
        try {
        	transactionWrapper = get().query(typeQuery, params, transactionMapper);
            log.info("TransactionID: {} of Account ID: {} successfully retrieved", inputTransactionID, inputAccountID);
        }
        catch(NullPointerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch(DataAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        
        return transactionWrapper.get(0);
        
    }
    */
    
    public ResponseEntity<Object> addTransaction(TransactionDomain inputTransaction){

        String typeQuery = "INSERT INTO Transaction(Transaction_type, Transaction_time, State, Category, Transaction_description, Ammunt, Account_ID) " +
                "VALUES (:inTransaction_type, :inTransaction_time, :inState, :inCategory, :inTransaction_description, :inAmount, :inAccount_ID) " + 
                "ON DUPLICATE KEY UPDATE Transaction_type = :inTransaction_type, Transaction_time = :inTransaction_time, State = :inState, Category = :inCategory, Transaction_description = :inTransaction_description, Amount = :inAmount, Account_ID = :inAccount_ID; " ;
        Map<String, Object> inputTransactionParams = new HashMap<String, Object>();
        inputTransactionParams.put("inTransaction_type", inputTransaction.getTransactionType());
        inputTransactionParams.put("inTransaction_time", inputTransaction.getTransactionTime());
        inputTransactionParams.put("inState", inputTransaction.getState());
        inputTransactionParams.put("inCategory", inputTransaction.getCategory());
        inputTransactionParams.put("inTransaction_description", inputTransaction.getTransactionDescription());
        inputTransactionParams.put("inAmount", inputTransaction.getAmount());
        inputTransactionParams.put("inAccount_ID", inputTransaction.getAccountID());
        
        
        try {
            get().update(typeQuery, inputTransactionParams);
            log.info("Transaction Amount of {} successfully added to the Account ID: {}", inputTransaction.getAmount(), inputTransaction.getAccountID());
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }  catch(NullPointerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(DataAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        
    
    }
    
    public ResponseEntity<Object> updateTransaction(TransactionDomain inputTransaction) {

        String typeQuery = "update Transaction set Transaction_type = :inTransaction_type, Transaction_time = :inTransaction_time, State = :inState, Category = :inCategory, Transaction_description = :inTransaction_description, Amount = :inAmount "
                + "WHERE Transaction_ID = :inTransaction_ID AND Account_ID = :inAccount_ID;";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inTransaction_ID", inputTransaction.getTransactionID());
        params.put("inTransaction_type", inputTransaction.getTransactionType());
        params.put("inTransaction_time", inputTransaction.getTransactionTime());
        params.put("inState", inputTransaction.getState());
        params.put("inCategory", inputTransaction.getCategory());
        params.put("inTransaction_description", inputTransaction.getTransactionDescription());            
        params.put("inAmount", inputTransaction.getAmount());
        params.put("inAccount_ID", inputTransaction.getAccountID());
    try {
            get().update(typeQuery, params);
            log.info("Transaction Amount of {} successfully updated for the Account ID: {}",  inputTransaction.getAmount(), inputTransaction.getAccountID());
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        } catch(NullPointerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(DataAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        
    }
    
    
    /** Deletes the transaction from the database completely
     * 
     * @param int transactionId
     * @return  ResponseEntity
     */
    public ResponseEntity<Object> deleteTransaction(int transactionID, int accountID) {
        
        String typeQuery = "delete from Transaction where Transaction_ID = :inTransaction_ID and Account_ID = :inAccount_ID;";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inTransaction_ID", transactionID);
        params.put("inAccount_ID", accountID);
                
        
        try {
            get().update(typeQuery, params);
            log.info("Deleted Transaction ID: {} for Account ID: {}", transactionID, accountID);
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        } catch(NullPointerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(DataAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.I_AM_A_TEAPOT);
        }
        
    }
    
}
