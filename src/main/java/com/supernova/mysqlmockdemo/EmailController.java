package com.supernova.mysqlmockdemo;

import com.supernova.mysqlmockdemo.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @RequestMapping("/count")
    @ResponseBody
    public MyResponse count() {
        log.info("/emails/count called.");

        long count = emailRepository.count();

        return new MyResponse(count);
    }
}
