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


@Data @AllArgsConstructor @Builder
public class TransactionDomain {
	private int transactionID;
	private String transactionType;
	private String transactionTime;
	private String state;
	private String category;
	private String transactionDescription;
	private int amount;
	private int accountID;
	
}
