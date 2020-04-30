package com.commercecapstone.transactiontriggertracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.commercecapstone.transactiontriggertracker.dao.NotificationDao;
import com.commercecapstone.transactiontriggertracker.domain.NotificationDomain;
import com.commercecapstone.transactiontriggertracker.service.NotificationRowMapper;

@SpringBootTest
class TransactionTriggerTrackerApplicationTests {


	@MockBean
	private NotificationDao NotificationDaoMock;
	
	@Test
	public void getAllNotificationsTest() {
		List<NotificationDomain> initialNotifications = new ArrayList<>();
		initialNotifications.add(new NotificationDomain(1,"outOfState",1,2,3));
		initialNotifications.add(new NotificationDomain(2,"outOfTime",2,2,1));
		initialNotifications.add(new NotificationDomain(3,"outOfCategory",3,1,2));
		
		when(NotificationDaoMock.getAllNotifications()).thenReturn(initialNotifications);
		assertEquals(3, NotificationDaoMock.getAllNotifications().size());
	}
	
	@Test
	public void getAccountNotificationsTest() {
		int accountID = 2 ;
		
		List<NotificationDomain> initialNotifications = new ArrayList<>();
		initialNotifications.add(new NotificationDomain(1,"outOfState",1,2,3));
		initialNotifications.add(new NotificationDomain(2,"outOfTime",2,2,1));
		initialNotifications.add(new NotificationDomain(3,"outOfCategory",3,1,2));
		
		List<NotificationDomain> finalNotifications = new ArrayList<>();
		finalNotifications.add(new NotificationDomain(3,"outOfCategory",3,1,2));
		
		when(NotificationDaoMock.getAccountNotifications(accountID)).thenReturn(finalNotifications);
		assertEquals(finalNotifications, NotificationDaoMock.getAccountNotifications(accountID));
	}
	
	
	
}
