package com.supernova.mysqlmockdemo;

import com.supernova.mysqlmockdemo.embedded.EmbeddedMysqlManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Slf4j
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.supernova.mysqlmockdemo"})
public class EmailControllerTest {

    private final EmbeddedMysqlManager mysqlManager = EmbeddedMysqlManager.getInstance();

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        // this is to show that we CAN reset db before each test.
        mysqlManager.reloadSchema();
    }

    @Test
    public void testCountEmail() {
        ResponseEntity<MyResponse> entity = restTemplate.getForEntity("/emails/count", MyResponse.class);

        Assertions.assertTrue(entity.getStatusCode().is2xxSuccessful());

        MyResponse response = entity.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getCount(), 2);
    }
}
