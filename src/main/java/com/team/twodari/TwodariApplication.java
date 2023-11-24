package com.team.twodari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableJpaAuditing //작성자 수정자 작성일자 수정일자
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TwodariApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwodariApplication.class, args);
    }

}
