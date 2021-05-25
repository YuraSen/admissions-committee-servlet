package com.senin.demo.model.DAO.mysql;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger log = LoggerFactory.getLogger(ConnectionPoolHolder.class);
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/demo-servlet?serverTimezone=UTC");
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
