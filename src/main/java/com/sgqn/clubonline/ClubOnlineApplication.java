package com.sgqn.clubonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 16:09:24
 * @modify:
 */

@SpringBootApplication
@MapperScan("com.sgqn.clubonline.dao.mapper")
public class ClubOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubOnlineApplication.class);
    }
}
