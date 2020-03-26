package com.commercecapstone.transactiontriggertracker.dao;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.commercecapstone.transactiontriggertracker.domain.TransactionDomain;
import com.commercecapstone.transactiontriggertracker.service.TransactionRowMapper;

import lombok.extern.slf4j.Slf4j;
@Repository @Slf4j
public class TransactionDao extends BaseDao{
	@Autowired
    TransactionRowMapper transactionMapper;//appMapper changed as notificationMapper
    
    /** Gets all the notifications from the Notification Table
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
    /** Gets a specific Transaction
     * 
     * @param int transactionId
     * @param int accountId
     * @return TransactionDomain
     */
    public TransactionDomain getTransaction(int transactionID) {
    	TransactionDomain transaction = null;
        List<TransactionDomain> transactionWrapper = null;
        
        String typeQuery = "select * from Transaction where Transaction_ID = :inputTransaction_ID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inputTransaction_ID", transactionID);
        
        try {
        	transactionWrapper = get().query(typeQuery, params, transactionMapper);
            log.info("Application of TransactionID: {} successfully retrieved", transactionID);
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
    
    public ResponseEntity<Object> addTransaction(Map<String, Object> inputParams){

        String typeQuery = "INSERT INTO Transaction(Transaction_ID, Transaction_type, Transaction_time, State, Category, Transaction_description, Ammount, Account_ID) " +
                "VALUES (:inputTransaction_ID, :inputTransaction_type, :inputTransaction_time, :inputState, :inputCategory, :inputTransaction_description, :inputAmmount, :inputAccount_ID) " + 
                "ON DUPLICATE KEY UPDATE Transaction_ID = :inputTransaction_ID, Transaction_type = :inputTransaction_type, Transaction_time = :inputTransaction_time, State = :inputState, Category = :inputCategory, Transaction_description = :inputTransaction_description, Ammount = :inputAmmount, Account_ID = :inputAccount_ID; " ;
        try {
            get().update(typeQuery, inputParams);
            log.info("Transaction ID: {} successfully added", inputParams.get("inputTransaction_ID"));
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
    
    public ResponseEntity<Object> updateBuildEntry(TransactionDomain transaction) {

        String typeQuery = "update Transaction set Transaction_type = :inputTransaction_type, Transaction_time = :inputTransaction_time, State = :inputState, Category = :inputCategory, Transaction_description = :inputTransaction_description, Ammount = :inputAmmount, Account_ID = :inputAccount_ID "
                + "WHERE TransactionID = :inputTransactionID;";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inputTransaction_ID", transaction.getTransactionID());
        params.put("inputTransaction_type", transaction.getTransactionType());
        params.put("inputTransaction_time", transaction.getTransactionTime());
        params.put("inputState", transaction.getState());
        params.put("inputCategory", transaction.getCategory());
        params.put("inputTransaction_description", transaction.getTransactionDescription());            
        params.put("inputAmmount", transaction.getAmount());
        params.put("inputAccount_ID", transaction.getAccountID());
    try {
            get().update(typeQuery, params);
            log.info("Entry for Transaction ID: {} successfully updated", transaction.getTransactionID());
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
    public ResponseEntity<Object> deleteTransaction(int transactionID) {
        
        String delTransaction = "delete from Transaction where Transaction_ID = :inputTransaction_ID";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inputTransaction_ID", transactionID);
        
        
        try {
            get().update(delTransaction, params);
            log.info("Deleted Transaction ID: {} ", transactionID);
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
