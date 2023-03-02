package com.sgqn.clubonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 16:09:24
 * @modify:
 */

@SpringBootApplication
@MapperScan("com.sgqn.clubonline.dao.mapper")
@EnableScheduling
public class ClubOnlineApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ClubOnlineApplication.class);

    }
}
