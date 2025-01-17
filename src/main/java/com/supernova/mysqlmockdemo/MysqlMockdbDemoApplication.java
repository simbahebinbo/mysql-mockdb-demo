package com.supernova.mysqlmockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.supernova.mysqlmockdemo")
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "com.supernova.mysqlmockdemo")
public class MysqlMockdbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlMockdbDemoApplication.class, args);
    }
}
