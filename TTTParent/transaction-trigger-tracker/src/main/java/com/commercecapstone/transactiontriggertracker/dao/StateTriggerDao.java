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
public class StateTriggerDao extends BaseDao{
	
	@Autowired
	StateTriggerRowMapper stateMapper;
	
	 /** Gets all the State Triggers
     * 
     * @return List of State Triggers
     */
    public List<StateTriggerDomain> getAllStateTriggers() {
        List<StateTriggerDomain> stateTriggerList = new ArrayList<>();
        
        String typeQuery = "select * from state_trigger";
                
        try {
            stateTriggerList = get().query(typeQuery, stateMapper);
            log.info("State trigger table successfully retrieved");
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

        return stateTriggerList;
            
    }
    
    /** Gets all the triggers for a specific user
     * 
     */
    public List<StateTriggerDomain> getUserStateTriggers(int inputUserID) {
        List<StateTriggerDomain> userStateTriggers = null;
        
        String typeQuery = "select * from State_trigger where User_ID = :inUserID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inUserID", inputUserID);
        
        try {
        	userStateTriggers = get().query(typeQuery, params, stateMapper);
            log.info("State triggers for UserID {} successfully retrieved", inputUserID);
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
        
        return userStateTriggers;
     }
    
    /*
     * Adds an inputted user's state trigger
     */
    public ResponseEntity<Object> addStateTrigger(String inputTrigger){

        String typeQuery = "INSERT INTO State_Trigger(User_ID, Trigger_Rule) " +
                "VALUES (1, :inTriggerRule)";
        Map<String, Object> inputTriggerParams = new HashMap<String, Object>();
        //inputTriggerParams.put("inUserID", inputTrigger.getUserID());
        inputTriggerParams.put("inTriggerRule", inputTrigger);
        
        try {
            get().update(typeQuery, inputTriggerParams);
            log.info("Trigger added with the following state rule: {}", inputTrigger);
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
	 * Updates a user's state trigger.
	 */
	public ResponseEntity<Object> updateStateTrigger(StateTriggerDomain inputTrigger) {
	
	        String typeQuery = "update State_trigger set trigger_rule = :inStateRule"
	                		+ "WHERE Trigger_ID = :inTriggerID AND User_ID = :inUserID";
	        
	        Map<String, Object> params = new HashMap<String,Object>();
	        params.put("inStateRule", inputTrigger.getRule());
	        params.put("inTriggerID", inputTrigger.getTriggerID());
	        params.put("inUserID", inputTrigger.getUserID());
	        try {
	            get().update(typeQuery, params);
	            log.info("State Trigger for UserID {} successfully updated with rule: {}", inputTrigger.getUserID(), inputTrigger.getRule());
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
     * Deletes a user's state trigger.
     */
    public ResponseEntity<Object> deleteStateTrigger(int triggerID) {
        
        String typeQuery = "delete from State_trigger where trigger_ID = :inTriggerID";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inTriggerID", triggerID);
        //params.put("inUserID", userID);
        
        
        try {
            get().update(typeQuery, params);
            log.info("Deleted State Trigger with TriggerID {}", triggerID);
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
