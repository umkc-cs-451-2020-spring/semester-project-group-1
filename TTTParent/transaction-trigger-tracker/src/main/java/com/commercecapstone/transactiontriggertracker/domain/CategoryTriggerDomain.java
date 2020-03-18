package com.commercecapstone.transactiontriggertracker.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @AllArgsConstructor @Builder
public class CategoryTriggerDomain {
	private int userID;
	private int triggerID;
	private String categoryRule;
	private String description;
}
