package com.commercecapstone.transactiontriggertracker.service;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.commercecapstone.transactiontriggertracker.domain.NotificationDomain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Service @Data @Slf4j
public class NotificationRowMapper implements RowMapper<NotificationDomain> {
	//@Override //mapRow method is not overridden
    public NotificationDomain mapRow(ResultSet rs, int rowNum) throws SQLException{
        return NotificationDomain.builder()
                                .notificationID(rs.getInt("Notification_ID"))
                                .transactionID(rs.getInt("Transaction_ID"))
                                .notificationType(rs.getString("Notification_Type"))
                                .notificationDescription(rs.getString("Notification_description"))
                                .build();
    }
}
