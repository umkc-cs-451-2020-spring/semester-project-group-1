package com.commercecapstone.transactiontriggertracker.dao;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.commercecapstone.transactiontriggertracker.domain.NotificationDomain;
import com.commercecapstone.transactiontriggertracker.domain.TransactionDomain;
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
    public List<NotificationDomain> getAccountNotifications(int inputAccountID) {
    	List<NotificationDomain> accountNotifications = null;
        
        String typeQuery = "select * from Notification where Account_ID = :inAccount_ID";
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("inAccount_ID", inputAccountID);
        
        try {
        	accountNotifications = get().query(typeQuery, params, notificationMapper);
            log.info("Notifications for AccountID: {} successfully retrieved", inputAccountID);
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
        
        return accountNotifications;
        
    }
    
    public ResponseEntity<Object> addNotification(NotificationDomain inputNotification){

        String typeQuery = "INSERT INTO Notification(Notification_type, Trigger_ID, Transaction_ID, Account_ID) " +
                "VALUES (:inNotification_type, :inTrigger_ID, :inTransaction_ID, :inAccount_ID) " + 
                "ON DUPLICATE KEY UPDATE Notification_type = :inNotification_type, Trigger_ID = :inTrigger_ID, Transaction_ID = :inTransaction_ID, Account_ID = :inAccount_ID; " ;
        Map<String, Object> inputNotificationParams = new HashMap<String, Object>();
        inputNotificationParams.put("inNotification_type", inputNotification.getNotificationType());
        inputNotificationParams.put("inTrigger_ID", inputNotification.getTriggerID());
        inputNotificationParams.put("inTransaction_ID", inputNotification.getTransactionID());
        inputNotificationParams.put("inAccount_ID", inputNotification.getAccountID());
        
        
        try {
            get().update(typeQuery, inputNotificationParams);
            log.info("Notification ID: {} successfully added to the Account ID: {}", inputNotificationParams.get("inNotification_ID"), inputNotificationParams.get("inAccount_ID"));
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
    public ResponseEntity<Object> deleteNotification(int notificationID, int accountID) {
        
        String typeQuery = "delete from Notification where Notification_ID = :inNotification_ID and Account_ID = :inAccount_ID;";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inNotification_ID", notificationID);
        params.put("inAccount_ID", accountID);
        
        
        try {
            get().update(typeQuery, params);
            log.info("Deleted Notification ID: {} for Account ID: {}", notificationID, accountID);
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
