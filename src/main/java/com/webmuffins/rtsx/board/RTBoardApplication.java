package com.webmuffins.rtsx.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class RTBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RTBoardApplication.class, args);
    }

}
