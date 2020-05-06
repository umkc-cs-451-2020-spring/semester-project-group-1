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
	
	// Get notification for specific account
	@GetMapping("/{inputAccountID")
    public List<NotificationDomain> getAccountNotifications(int inputAccountID) {
		return notificationDao.getAccountNotifications(inputAccountID);
	}

	// Add a notification
	@PostMapping("/all")
	public ResponseEntity<Object> addNotification (@RequestBody @Valid @NonNull NotificationDomain inputNotification){
		return notificationDao.addNotification(inputNotification);
	}
	
	// Delete a notification based on accountID and notification ID
	@DeleteMapping("/{accountID}/{notificationID}")
	public ResponseEntity<Object> deleteNotification(@PathVariable int accountID, @PathVariable int notificationID){
		return notificationDao.deleteNotification(accountID, notificationID);
	}
	
}
