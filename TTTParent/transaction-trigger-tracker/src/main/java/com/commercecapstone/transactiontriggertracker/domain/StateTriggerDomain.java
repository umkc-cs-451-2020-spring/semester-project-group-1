package com.commercecapstone.transactiontriggertracker.domain;


	
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;




@Data @AllArgsConstructor @Builder
public class StateTriggerDomain {
	private int triggerID;
	private int userID;
	private String rule;
}
