package com.commercecapstone.transactiontriggertracker.dao;

import java.util.List;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;

@Configuration
@EnableTransactionManagement
@Validated
@Primary
public class DaoManager
{
    private DataSource                   dataSource;
    private NamedParameterJdbcOperations jdbcOperations;
    
    /**
     * Constructs a DAO Manager with the given data source and JDBC operator. 
     * 
     * @param dataSource
     * @param jdbcOperations
     */
    public DaoManager( @NotNull DataSource dataSource,
                           @NotNull NamedParameterJdbcOperations jdbcOperations )
    {
        this.dataSource = dataSource;
        this.jdbcOperations = jdbcOperations;
    }
    
    /**
     * Returns the Spring Transaction Manager with a reference to the data source known to <code>this</code> DaoManager.
     * Typically invoked automatically for use on classes and methods decorated with the 
     * {@link org.springframework.transaction.annotation.Transactional} annotation.
     * 
     * @return the configured transaction manager.
     */
    @Bean("transactionManager")
    public DataSourceTransactionManager getTransactionManager()
    {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager( this.dataSource );
        return transactionManager;
    }
    
    public <T> List<T> getSelected( String query,
                                    SqlParameterSource parameters,
                                    RowMapper<T> mapper )
    {
        return jdbcOperations.query( query, parameters, mapper );
    }
   public int[] updateSelected( String query, SqlParameterSource[] parameters )
    {
        return jdbcOperations.batchUpdate( query, parameters );
    }
    
    public int updateRecord( String query, SqlParameterSource parameters )
    {
        return jdbcOperations.update( query, parameters );
    }
    
    public <T> T getSelectedType( String query,
                                  SqlParameterSource parameters,
                                  RowMapper<T> mapper )
    {
        return jdbcOperations.queryForObject( query, parameters, mapper );
    }
    
    public <T> T getSelectedAsSingleObject( String query,
                                            SqlParameterSource parameters,
                                            Class<T> resultClassType )
    {
        
        return jdbcOperations.queryForObject( query, parameters, resultClassType );
    }
    
   
}