package com.commercecapstone.transactiontriggertracker.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class BaseDao {
	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	protected DataSource dataSource;
	
	@Autowired
	private DaoManager daoManager;
	
	public NamedParameterJdbcOperations get() {
		return this.namedParameterJdbcOperations;
	}
	
	public DataSource getDataSource() {
        return dataSource;
    }
    
	//TODO Implement DAO Manager
	/*
    public DataSourceTransactionManager getTransactionManager() {
        return this.daoManager.getTransactionManager();
    }
    
    public DaoManager getDaoManager() {
        return this.daoManager;
    }
    */
}
