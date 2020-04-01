package com.commercecapstone.transactiontriggertracker.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercecapstone.transactiontriggertracker.dao.NotificationDao;
import com.commercecapstone.transactiontriggertracker.domain.NotificationDomain;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
	
	// Connect to NotificationDao
	@Autowired 
	private NotificationDao notificationDao;
	
	// Get all notification
	@GetMapping("/all")
	public List<NotificationDomain> getAllNotification(){
		return notificationDao.getAllNotifications();
	}
	
	// Add a notification
	@PostMapping("/all")
	public ResponseEntity<Object> addNotification (@RequestBody @Valid @NonNull NotificationDomain notification){
		Map<String, Object> inputParams = new HashMap<String,Object>();
		inputParams.put("inputNotification_ID", notification.getNotificationID());
		inputParams.put("inputTransaction_ID", notification.getTransactionID());
		inputParams.put("inputNotification_type", notification.getNotificationType());
		inputParams.put("inputNotification_description", notification.getNotificatonDescription();
		return notificationDao.addNotification(inputParams);
	
	}
	
	// Delete a notification based on notification ID
	@DeleteMapping("/{notificationID}")
	public ResponseEntity<Object> deleteNotification(@PathVariable int notificationID){
		return notificationDao.deleteNotification(notificationID);
	}
	
	
	
	

}
