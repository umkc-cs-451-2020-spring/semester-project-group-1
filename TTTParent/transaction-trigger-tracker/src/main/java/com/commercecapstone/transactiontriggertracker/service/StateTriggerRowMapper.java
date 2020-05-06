package com.commercecapstone.transactiontriggertracker.service;

import java.sql.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.commercecapstone.transactiontriggertracker.domain.StateTriggerDomain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Service @Data @Slf4j
public class StateTriggerRowMapper implements RowMapper<StateTriggerDomain>{
	@Override
    public StateTriggerDomain mapRow(ResultSet rs, int rowNum) throws SQLException{
        return StateTriggerDomain.builder()
                                .triggerID(rs.getInt("Trigger_ID"))
                                .userID(rs.getInt("User_ID"))
                                .rule(rs.getString("Trigger_rule"))
                                .build();
    }

}