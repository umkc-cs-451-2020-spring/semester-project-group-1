package com.commercecapstone.transactiontriggertracker.domain;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


//@Data @AllArgsConstructor @Builder
public class StateTriggerDomain {
	private int User_ID;
	private int Trigger_ID;
	private int Account_ID;
	private int Transactions_ID;
	private int Notification_ID;
	
}
