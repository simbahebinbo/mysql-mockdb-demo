package com.supernova.mysqlmockdemo;

import com.supernova.mysqlmockdemo.embedded.EmbeddedMysqlManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
