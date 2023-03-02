package com.sgqn.clubonline.config;

import com.sgqn.clubonline.pojo.Role;
import com.sgqn.clubonline.service.RedisInitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 项目启动时 读取数据 加载到redis缓存中
 *
 * @author wspstart
 * @create 2023-02-28 22:21
 */
@Configuration
@Slf4j
public class RedisInitDataRunner {


    @Autowired
    private RedisInitService redisInitService;

    /**
     * 初始化数据
     */
    @PostConstruct
    public void initData() {
        log.info("正在准备向Redis中初始化信息：");
        List<Role> roles = redisInitService.saveRole();
        redisInitService.saveRolePermission(roles);
        redisInitService.saveMenu();
    }


}
