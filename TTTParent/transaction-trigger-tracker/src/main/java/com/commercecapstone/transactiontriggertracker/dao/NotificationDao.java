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
    NotificationRowMapper notificationMapper;//appMapper -> notificationMapper
    
    /** Gets all the notifications from the Notification Table
     * 
     * @return List of NotificationDomain
     */
    public List<NotificationDomain> getAllNotifications() {
        List<NotificationDomain> notificationList = new ArrayList<>();
        
        String typeQuery = "select * from Notification";//Check table name?
                
        try {
            notificationList = get().query(typeQuery, notificationMapper);
            log.info("Application table successfully retrieved");
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
    
    
    public ResponseEntity<Object> addNotification(Map<String, Object> inputParams){

        String typeQuery = "INSERT INTO Notification(NotificationID, TransactionID, NotificationType, NotificationDescripton) " +
                "VALUES (:inputNotificationID, :inputTransactionID, :inputNotificationType, :inputNotificationDescripton)" + 
                "ON DUPLICATE KEY UPDATE NotificationID = :inputNotificationID, TransactionID = :inputTransactionID, NotificationType = :inputNotificationType, NotificationDescripton = :inputNotificationDescripton; " ;
        try {
            get().update(typeQuery, inputParams);
            log.info("Notification ID: {} successfully added", inputParams.get("inputNotificationID"));
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
        
        String delNotification = "delete from Notification where NotificationID = :inNotificationID";
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inNotificationID", notificationID);
        
        
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
