package com.supernova.mysqlmockdemo.embedded;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Slf4j
@Configuration
public class EmbeddedDataSourceConfig {
    private MysqlDataSource dataSource;

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
