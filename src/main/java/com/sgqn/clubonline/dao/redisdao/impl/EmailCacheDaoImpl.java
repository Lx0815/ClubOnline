package com.sgqn.clubonline.dao.redisdao.impl;

import com.sgqn.clubonline.common.Constants;
import com.sgqn.clubonline.dao.redisdao.EmailCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-03-09 8:32:23
 * @modify:
 */

@Repository
public class EmailCacheDaoImpl implements EmailCacheDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String EMAIL_CACHE_REDIS_PREFIX = "email-cache";

    @Override
    public boolean putIfAbsent(String email) {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(
                EMAIL_CACHE_REDIS_PREFIX + ":" + email,
                Constants.EMPTY_STRING,
                2,
                TimeUnit.MINUTES
        );
        return Objects.nonNull(flag) && flag;
    }

    @Override
    public void remove(String email) {
        redisTemplate.delete(EMAIL_CACHE_REDIS_PREFIX + ":" + email);
    }
}
