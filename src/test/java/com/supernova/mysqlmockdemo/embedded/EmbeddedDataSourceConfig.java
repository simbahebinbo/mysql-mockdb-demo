package com.supernova.mysqlmockdemo.embedded;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Slf4j
@TestConfiguration
public class EmbeddedDataSourceConfig {
    private MysqlDataSource dataSource;

    @PostConstruct
    public void init() {
        log.info("config init");
    }

    @Bean
    public DataSource getDataSource() {
        if (dataSource == null) {
            log.info("getDataSource create bean...");

            EmbeddedMysqlManager mysqlManager = EmbeddedMysqlManager.getInstance();

            dataSource = new MysqlDataSource();
            dataSource.setUrl(mysqlManager.getDbUrl());
            dataSource.setUser(mysqlManager.getDbUser());
            dataSource.setPassword(mysqlManager.getDbPassword());
        }
        return dataSource;
    }

}
