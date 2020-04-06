package com.commercecapstone.transactiontriggertracker.dao;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.commercecapstone.transactiontriggertracker.domain.NotificationDomain;
import com.commercecapstone.transactiontriggertracker.service.NotificationRowMapper;

import lombok.extern.slf4j.Slf4j;
@Repository @Slf4j
public class NotificationDao extends BaseDao{
	@Autowired
    NotificationRowMapper notificationMapper;//appMapper changed as notificationMapper
    
    /** Gets all the notifications from the Notification Table
     * 
     * @return List of NotificationDomain
     */
    public List<NotificationDomain> getAllNotifications() {
        List<NotificationDomain> notificationList = new ArrayList<>();
        
        String typeQuery = "select * from Notification";
                
        try {
            notificationList = get().query(typeQuery, notificationMapper);
            log.info("Notification table successfully retrieved");
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

        return notificationList;
            
    }
    //get and update methods were not implemented for notifications
    
    public ResponseEntity<Object> addNotification(Map<String, Object> inputParams){

        String typeQuery = "INSERT INTO Notification(Notification_ID, Transaction_ID, Notification_type, Trigger_ID) " +
                "VALUES (:inputNotification_ID, :inputTransaction_ID, :inputNotification_type, :inputTrigger_ID) " + 
                "ON DUPLICATE KEY UPDATE Notification_ID = :inputNotification_ID, Transaction_ID = :inputTransaction_ID, Notification_type = :inputNotification_type, Trigger_ID = :inputTrigger_ID; " ;
        try {
            get().update(typeQuery, inputParams);
            log.info("Notification ID: {} successfully added", inputParams.get("inputNotification_ID"));
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
    /** Deletes the notification from the database completely
     * 
     * @param int notificationId
     * @return  ResponseEntity
     */
    public ResponseEntity<Object> deleteNotification(int notificationID) {
        
        String delNotification = "delete from Notification where Notification_ID = :inputNotification_ID";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inputNotification_ID", notificationID);
        
        
        try {
            get().update(delNotification, params);
            log.info("Deleted Notification ID: {} ", notificationID);
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
