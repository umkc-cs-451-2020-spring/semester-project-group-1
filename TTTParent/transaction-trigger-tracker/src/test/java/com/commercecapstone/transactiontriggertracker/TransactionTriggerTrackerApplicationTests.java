package com.commercecapstone.transactiontriggertracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.commercecapstone.transactiontriggertracker.dao.CategoryTriggerDAO;
import com.commercecapstone.transactiontriggertracker.dao.NotificationDao;
import com.commercecapstone.transactiontriggertracker.dao.StateTriggerDao;
import com.commercecapstone.transactiontriggertracker.dao.TimeRangeTriggerDAO;
import com.commercecapstone.transactiontriggertracker.dao.TransactionDao;
import com.commercecapstone.transactiontriggertracker.domain.CategoryTriggerDomain;
import com.commercecapstone.transactiontriggertracker.domain.NotificationDomain;
import com.commercecapstone.transactiontriggertracker.domain.StateTriggerDomain;
import com.commercecapstone.transactiontriggertracker.domain.TimeRangeTriggerDomain;
import com.commercecapstone.transactiontriggertracker.domain.TransactionDomain;

@SpringBootTest
class TransactionTriggerTrackerApplicationTests {

	//Variables for Testing Notification Methods
	private static NotificationDao NotificationDaoMock;
	private static List<NotificationDomain> initialNotifications;
	private static List<NotificationDomain> finalNotifications;
	private static int accountID1;
	private static int notificationID;
	private static NotificationDomain addedNotification;
	private static ResponseEntity<Object> httpResponse;
	
	//Variables for Testing Transaction Methods
	private static TransactionDao TransactionDaoMock;
	private static List<TransactionDomain> initialTransactions;
	private static List<TransactionDomain> finalTransactions;
	private static int accountID2;
	private static int transactionID;
	private static TransactionDomain addedTransaction;
	private static TransactionDomain updatedTransaction;
	private static ResponseEntity<Object> httpResponse2;
	
	//Variables for Testing CategoryTrigger Methods
	private static CategoryTriggerDAO CategoryTriggerDaoMock;
	private static List<CategoryTriggerDomain> initialCategoryTriggers;
	private static List<CategoryTriggerDomain> finalCategoryTriggers;
	private static int userID1;
	private static int categoryTriggerID;
	private static CategoryTriggerDomain addedCategoryTrigger;
	private static CategoryTriggerDomain updatedCategoryTrigger;
	private static ResponseEntity<Object> httpResponse3;
	
	//Variables for Testing StateTrigger Methods
	private static StateTriggerDao StateTriggerDaoMock;
	private static List<StateTriggerDomain> initialStateTriggers;
	private static List<StateTriggerDomain> finalStateTriggers;
	private static int userID2;
	private static int stateTriggerID;
	private static StateTriggerDomain addedStateTrigger;
	private static StateTriggerDomain updatedStateTrigger;
	private static ResponseEntity<Object> httpResponse4;
	
	//Variables for Testing TimeRangeTrigger Methods
	private static TimeRangeTriggerDAO TimeRangeTriggerDaoMock;
	private static List<TimeRangeTriggerDomain> initialTimeRangeTriggers;
	private static List<TimeRangeTriggerDomain> finalTimeRangeTriggers;
	private static int userID3;
	private static int timeRangeTriggerID;
	private static TimeRangeTriggerDomain addedTimeRangeTrigger;
	private static TimeRangeTriggerDomain updatedTimeRangeTrigger;
	private static ResponseEntity<Object> httpResponse5;
	
	
	@BeforeAll
	public static void setup() {
		
		//Setup for Testing Notification Methods
		NotificationDaoMock = mock (NotificationDao.class);
		
		initialNotifications = new ArrayList<>();
		initialNotifications.add(new NotificationDomain(1,"outOfState",1,2,3));
		initialNotifications.add(new NotificationDomain(2,"outOfTime",2,2,1));
		initialNotifications.add(new NotificationDomain(3,"outOfCategory",3,1,2));
		
		finalNotifications = new ArrayList<>();
		finalNotifications.add(new NotificationDomain(3,"outOfCategory",3,1,2));
		
		accountID1 = 2;
		notificationID = 3;
		addedNotification = new NotificationDomain(4,"outOfState",1,2,3);
		httpResponse = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		
		when(NotificationDaoMock.getAllNotifications()).thenReturn(initialNotifications);
		when(NotificationDaoMock.getAccountNotifications(accountID1)).thenReturn(finalNotifications);
		when(NotificationDaoMock.addNotification(addedNotification)).thenReturn(httpResponse);
		when(NotificationDaoMock.deleteNotification(accountID1, notificationID)).thenReturn(httpResponse);
		
		//Setup for Testing Transaction Methods
		TransactionDaoMock = mock (TransactionDao.class);
		
		initialTransactions = new ArrayList<>();
		initialTransactions.add(new TransactionDomain(1,"withdraw","today","MO","Gas","QT_Gas",2,3));
		initialTransactions.add(new TransactionDomain(2,"withdraw","yesterday","KS","Gas","Shell",2,1));
		initialTransactions.add(new TransactionDomain(3,"withdraw","tomorrow","MO","Grocery","Walmart",1,2));
		
		finalTransactions = new ArrayList<>();
		finalTransactions.add(new TransactionDomain(1,"withdraw","today","MO","Gas","QT_Gas",1,2));
		
		accountID2 = 2;
		transactionID = 1;
		addedTransaction = new TransactionDomain(4,"withdraw","today","MO","Gas","QT_Gas",2,3);
		updatedTransaction = new TransactionDomain(3,"withdraw","today","MO","Grocery","Walmart",1,2);
		httpResponse2 = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		
		when(TransactionDaoMock.getAllTransactions()).thenReturn(initialTransactions);
		when(TransactionDaoMock.getAccountTransactions(accountID2)).thenReturn(finalTransactions);
		when(TransactionDaoMock.addTransaction(addedTransaction)).thenReturn(httpResponse2);
		when(TransactionDaoMock.updateTransaction(updatedTransaction)).thenReturn(httpResponse2);
		when(TransactionDaoMock.deleteTransaction(accountID2, transactionID)).thenReturn(httpResponse2);
		
		//Setup for Testing CategoryTrigger Methods
		CategoryTriggerDaoMock = mock (CategoryTriggerDAO.class);
		
		initialCategoryTriggers = new ArrayList<>();
		initialCategoryTriggers.add(new CategoryTriggerDomain(3,1,"Gas"));
		initialCategoryTriggers.add(new CategoryTriggerDomain(2,2,"Grocery"));
		initialCategoryTriggers.add(new CategoryTriggerDomain(1,3,"Gas"));
		
		finalCategoryTriggers = new ArrayList<>();
		finalCategoryTriggers.add(new CategoryTriggerDomain(1,3,"Gas"));
		
		userID1 = 2;
		categoryTriggerID = 1;
		addedCategoryTrigger = new CategoryTriggerDomain(1,4,"Gas");
		updatedCategoryTrigger = new CategoryTriggerDomain(1,3,"Grocery");
		httpResponse3 = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		
		when(CategoryTriggerDaoMock.getAllCategoryTriggers()).thenReturn(initialCategoryTriggers);
		when(CategoryTriggerDaoMock.getUserCategoryTriggers(userID1)).thenReturn(finalCategoryTriggers);
		when(CategoryTriggerDaoMock.addCategoryTrigger(addedCategoryTrigger)).thenReturn(httpResponse3);
		when(CategoryTriggerDaoMock.updateCategoryTrigger(updatedCategoryTrigger)).thenReturn(httpResponse3);
		when(CategoryTriggerDaoMock.deleteCategoryTrigger(userID1, categoryTriggerID)).thenReturn(httpResponse3);
		
		//Setup for Testing StateTrigger Methods
		StateTriggerDaoMock = mock (StateTriggerDao.class);
		
		initialStateTriggers = new ArrayList<>();
		initialStateTriggers.add(new StateTriggerDomain(1,3,"KS"));
		initialStateTriggers.add(new StateTriggerDomain(2,2,"MO"));
		initialStateTriggers.add(new StateTriggerDomain(3,1,"KS"));
		
		finalStateTriggers = new ArrayList<>();
		finalStateTriggers.add(new StateTriggerDomain(1,3,"KS"));
		
		userID2 = 2;
		stateTriggerID = 1;
		addedStateTrigger = new StateTriggerDomain(4,3,"MO");
		updatedStateTrigger = new StateTriggerDomain(1,3,"MO");
		httpResponse4 = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		
		when(StateTriggerDaoMock.getAllStateTriggers()).thenReturn(initialStateTriggers);
		when(StateTriggerDaoMock.getUserStateTriggers(userID2)).thenReturn(finalStateTriggers);
		when(StateTriggerDaoMock.addStateTrigger(addedStateTrigger)).thenReturn(httpResponse4);
		when(StateTriggerDaoMock.updateStateTrigger(updatedStateTrigger)).thenReturn(httpResponse4);
		when(StateTriggerDaoMock.deleteStateTrigger(userID2, stateTriggerID)).thenReturn(httpResponse4);
				
		//Setup for Testing TimeRangeTrigger Methods
		TimeRangeTriggerDaoMock = mock (TimeRangeTriggerDAO.class);
		
		initialTimeRangeTriggers = new ArrayList<>();
		initialTimeRangeTriggers.add(new TimeRangeTriggerDomain(1,3,"After12:00"));
		initialTimeRangeTriggers.add(new TimeRangeTriggerDomain(2,2,"After01:00"));
		initialTimeRangeTriggers.add(new TimeRangeTriggerDomain(3,1,"After01:30"));
		
		finalTimeRangeTriggers = new ArrayList<>();
		finalTimeRangeTriggers.add(new TimeRangeTriggerDomain(1,3,"After12:00"));
		
		userID3 = 2;
		timeRangeTriggerID = 1;
		addedTimeRangeTrigger = new TimeRangeTriggerDomain(4,3,"After01:00");
		updatedTimeRangeTrigger = new TimeRangeTriggerDomain(1,3,"After01:00");
		httpResponse5 = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		
		when(TimeRangeTriggerDaoMock.getAllTimeRangeTriggers()).thenReturn(initialTimeRangeTriggers);
		when(TimeRangeTriggerDaoMock.getUserTimeRangeTriggers(userID3)).thenReturn(finalTimeRangeTriggers);
		when(TimeRangeTriggerDaoMock.addTimeRangeTrigger(addedTimeRangeTrigger)).thenReturn(httpResponse5);
		when(TimeRangeTriggerDaoMock.updateTimeRangeTrigger(updatedTimeRangeTrigger)).thenReturn(httpResponse5);
		when(TimeRangeTriggerDaoMock.deleteTimeRangeTrigger(userID3, timeRangeTriggerID)).thenReturn(httpResponse5);
				
	}
	//Tests for Notification Methods
	@Test
	public void getAllNotificationsTest() {
		assertEquals(3, NotificationDaoMock.getAllNotifications().size());
	}
	@Test
	public void getAccountNotificationsTest() {
		assertEquals(finalNotifications, NotificationDaoMock.getAccountNotifications(accountID1));
	}
	@Test
	public void addNotificationTest() {
		assertEquals(httpResponse, NotificationDaoMock.addNotification(addedNotification));
	}
	@Test
	public void deleteNotificationTest() {
		assertEquals(httpResponse, NotificationDaoMock.deleteNotification(accountID1, notificationID));
	}
	
	
	//Tests for Transaction Methods
	@Test
	public void getAllTransactionsTest() {
		assertEquals(3, TransactionDaoMock.getAllTransactions().size());
	}
	@Test
	public void getAccountTransactionsTest() {
		assertEquals(finalTransactions, TransactionDaoMock.getAccountTransactions(accountID2));
	}
	@Test
	public void addTransactionTest() {
		assertEquals(httpResponse2, TransactionDaoMock.addTransaction(addedTransaction));
	}
	@Test
	public void updateTransactionTest() {
		assertEquals(httpResponse2, TransactionDaoMock.updateTransaction(updatedTransaction));
	}
	@Test
	public void deleteTransactionTest() {
		assertEquals(httpResponse2, TransactionDaoMock.deleteTransaction(accountID2, transactionID));
	}
	
	
	//Tests for CategoryTrigger Methods
	@Test
	public void getAllCategoryTriggersTest() {
		assertEquals(3, CategoryTriggerDaoMock.getAllCategoryTriggers().size());
	}
	@Test
	public void getUserCategoryTriggersTest() {
		assertEquals(finalCategoryTriggers, CategoryTriggerDaoMock.getUserCategoryTriggers(userID1));
	}
	@Test
	public void addCategoryTriggerTest() {
		assertEquals(httpResponse3, CategoryTriggerDaoMock.addCategoryTrigger(addedCategoryTrigger));
	}
	@Test
	public void updateCategoryTriggerTest() {
		assertEquals(httpResponse3, CategoryTriggerDaoMock.updateCategoryTrigger(updatedCategoryTrigger));
	}
	@Test
	public void deleteCategoryTriggerTest() {
		assertEquals(httpResponse3, CategoryTriggerDaoMock.deleteCategoryTrigger(userID1, categoryTriggerID));
	}
	
	
	//Tests for StateTrigger Methods
	@Test
	public void getAllStateTriggersTest() {
		assertEquals(3, StateTriggerDaoMock.getAllStateTriggers().size());
	}
	@Test
	public void getUserStateTriggersTest() {
		assertEquals(finalStateTriggers, StateTriggerDaoMock.getUserStateTriggers(userID2));
	}
	@Test
	public void addStateTriggerTest() {
		assertEquals(httpResponse4, StateTriggerDaoMock.addStateTrigger(addedStateTrigger));
	}
	@Test
	public void updateStateTriggerTest() {
		assertEquals(httpResponse4, StateTriggerDaoMock.updateStateTrigger(updatedStateTrigger));
	}
	@Test
	public void deleteStateTriggerTest() {
		assertEquals(httpResponse4, StateTriggerDaoMock.deleteStateTrigger(userID2, stateTriggerID));
	}
	
	//Tests for TimeRangeTrigger Methods
	@Test
	public void getAllTimeRangeTriggersTest() {
		assertEquals(3, TimeRangeTriggerDaoMock.getAllTimeRangeTriggers().size());
	}
	@Test
	public void getUserTimeRangeTriggersTest() {
		assertEquals(finalTimeRangeTriggers, TimeRangeTriggerDaoMock.getUserTimeRangeTriggers(userID3));
	}
	@Test
	public void addTimeRangeTriggerTest() {
		assertEquals(httpResponse5, TimeRangeTriggerDaoMock.addTimeRangeTrigger(addedTimeRangeTrigger));
	}
	@Test
	public void updateTimeRangeTriggerTest() {
		assertEquals(httpResponse5, TimeRangeTriggerDaoMock.updateTimeRangeTrigger(updatedTimeRangeTrigger));
	}
	@Test
	public void deleteTimeRangeTriggerTest() {
		assertEquals(httpResponse5, TimeRangeTriggerDaoMock.deleteTimeRangeTrigger(userID3, timeRangeTriggerID));
	}
	
}
