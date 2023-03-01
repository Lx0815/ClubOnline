package com.sgqn.clubonline.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 项目启动时 读取数据 加载到redis缓存中
 *
 * @author wspstart
 * @create 2023-02-28 22:21
 */
@Configuration
public class RedisInitDataRunner {



    /**
     * 初始化数据
     */
    @PostConstruct
    public void initData(){

    }

}
