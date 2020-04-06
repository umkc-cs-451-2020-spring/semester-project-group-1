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
public class TimeRangeTriggerDAO extends BaseDao{
	
	@Autowired
	TimeRangeTriggerRowMapper timeRangeMapper;
	
	 /** Gets all the Timerange Triggers
     * 
     * @return List of TimeRangeTriggerDomain
     */
    public List<TimeRangeTriggerDomain> getAllTimeRangeTriggers() {
        List<TimeRangeTriggerDomain> timeRangeTriggerList = new ArrayList<>();
        
        String typeQuery = "select * from time_range_trigger";
                
        try {
            timeRangeTriggerList = get().query(typeQuery, timeRangeMapper);
            log.info("Time range trigger table successfully retrieved");
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

        return timeRangeTriggerList;
            
    }
    
    /** Gets all the Timerange triggers for a specific user
     * 
     */
    public List<TimeRangeTriggerDomain> getUserTimeRangeTriggers(int inputUserID) {
        List<TimeRangeTriggerDomain> userTimeRangeTriggers = null;
        
        String typeQuery = "select * from time_range_trigger where User_ID = :inUserID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inUserID", inputUserID);
        
        try {
        	userTimeRangeTriggers = get().query(typeQuery, params, timeRangeMapper);
            log.info("Time range triggers for UserID {} successfully retrieved", inputUserID);
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
        
        return userTimeRangeTriggers;
     }
    
    /*
     * Adds an inputted user's time range trigger
     */
    public ResponseEntity<Object> addTimeRangeTrigger(TimeRangeTriggerDomain inputTrigger){

        String typeQuery = "INSERT INTO time_range_trigger(User_ID, Trigger_Rule) " +
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
	 * Updates a user's time range trigger.
	 */
	public ResponseEntity<Object> updateTimeRangeTrigger(TimeRangeTriggerDomain inputTrigger) {
	
	        String typeQuery = "update time_range_trigger set trigger_rule = :inStateRule"
	                		+ "WHERE Trigger_ID = :inTriggerID AND User_ID = :inUserID";
	        
	        Map<String, Object> params = new HashMap<String,Object>();
	        params.put("inStateRule", inputTrigger.getRule());
	        params.put("inTriggerID", inputTrigger.getTriggerID());
	        params.put("inUserID", inputTrigger.getUserID());
	        try {
	            get().update(typeQuery, params);
	            log.info("Time range Trigger for UserID {} successfully updated with rule: {}", inputTrigger.getUserID(), inputTrigger.getRule());
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
     * Deletes a user's time range trigger.
     */
    public ResponseEntity<Object> deleteTimeRangeTrigger(int triggerID, int userID) {
        
        String typeQuery = "delete from time_range_trigger where trigger_ID = :inTriggerID and User_ID = :inUserID";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inTriggerID", triggerID);
        params.put("inUserID", userID);
        
        
        try {
            get().update(typeQuery, params);
            log.info("Deleted Time range Trigger with UserID {} and TriggerID {}", userID, triggerID);
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
