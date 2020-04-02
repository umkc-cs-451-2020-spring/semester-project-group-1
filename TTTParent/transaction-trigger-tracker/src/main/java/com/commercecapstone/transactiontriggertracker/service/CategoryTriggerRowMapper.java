package com.commercecapstone.transactiontriggertracker.service;

import java.sql.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.commercecapstone.transactiontriggertracker.domain.CategoryTriggerDomain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Service @Data @Slf4j
public class CategoryTriggerRowMapper implements RowMapper<CategoryTriggerDomain>{
	@Override
    public CategoryTriggerDomain mapRow(ResultSet rs, int rowNum) throws SQLException{
        return CategoryTriggerDomain.builder()
                                .triggerID(rs.getInt("Trigger_ID"))
                                .userID(rs.getInt("User_ID"))
                                .rule(rs.getString("Trigger_rule"))
                                .build();
	}
}