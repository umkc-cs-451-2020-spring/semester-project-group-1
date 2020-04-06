package com.commercecapstone.transactiontriggertracker.dao;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.commercecapstone.transactiontriggertracker.domain.*;
import com.commercecapstone.transactiontriggertracker.service.*;

import lombok.extern.slf4j.Slf4j;

@Repository @Slf4j
public class CategoryTriggerDAO extends BaseDao{
	
	@Autowired
	CategoryTriggerRowMapper categoryMapper;
	
	 /** Gets all the Category Triggers
     * 
     * @return List of CategoryTriggerDomain
     */
    public List<CategoryTriggerDomain> getAllCategoryTriggers() {
        List<CategoryTriggerDomain> categoryTriggerList = new ArrayList<>();
        
        String typeQuery = "select * from category_trigger";
                
        try {
            categoryTriggerList = get().query(typeQuery, categoryMapper);
            log.info("Category trigger table successfully retrieved");
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

        return categoryTriggerList;
            
    }
    
    /** Gets all the category triggers for a specific user
     * 
     */
    public List<CategoryTriggerDomain> getUserCategoryTriggers(int inputUserID) {
        List<CategoryTriggerDomain> userCategoryTriggers = null;
        
        String typeQuery = "select * from Category_trigger where User_ID = :inUserID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inUserID", inputUserID);
        
        try {
        	userCategoryTriggers = get().query(typeQuery, params, categoryMapper);
            log.info("Category triggers for UserID {} successfully retrieved", inputUserID);
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
        
        return userCategoryTriggers;
     }
    
    /*
     * Adds an inputted user's category trigger
     */
    public ResponseEntity<Object> addCategoryTrigger(CategoryTriggerDomain inputTrigger){

        String typeQuery = "INSERT INTO Category_trigger(User_ID, Trigger_Rule) " +
                "VALUES (:inUserID, :inTriggerRule)";
        Map<String, Object> inputTriggerParams = new HashMap<String, Object>();
        inputTriggerParams.put("inUserID", inputTrigger.getUserID());
        inputTriggerParams.put("inTriggerRule", inputTrigger.getRule());
        
        try {
            get().update(typeQuery, inputTriggerParams);
            log.info("Trigger added for UserID {} with the following state rule: {}", inputTrigger.getUserID(), inputTrigger.getRule());
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
    
	/*
	 * Updates a user's category trigger.
	 */
	public ResponseEntity<Object> updateCategoryTrigger(CategoryTriggerDomain inputTrigger) {
	
	        String typeQuery = "update Category_trigger set trigger_rule = :inStateRule"
	                		+ "WHERE Trigger_ID = :inTriggerID AND User_ID = :inUserID";
	        
	        Map<String, Object> params = new HashMap<String,Object>();
	        params.put("inStateRule", inputTrigger.getRule());
	        params.put("inTriggerID", inputTrigger.getTriggerID());
	        params.put("inUserID", inputTrigger.getUserID());
	        try {
	            get().update(typeQuery, params);
	            log.info("Category Trigger for UserID {} successfully updated with rule: {}", inputTrigger.getUserID(), inputTrigger.getRule());
	            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	        } catch (NullPointerException e) {
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
    /** 
     * Deletes a user's category trigger.
     */
    public ResponseEntity<Object> deleteCategoryTrigger(int triggerID, int userID) {
        
        String typeQuery = "delete from Category_trigger where trigger_ID = :inTriggerID and User_ID = :inUserID";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inTriggerID", triggerID);
        params.put("inUserID", userID);
        
        
        try {
            get().update(typeQuery, params);
            log.info("Deleted Category Trigger with UserID {} and TriggerID {}", userID, triggerID);
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
