package com.sgqn.clubonline.dao.redisdao.impl;

import com.sgqn.clubonline.web.config.captcha.CaptchaProperties;
import com.sgqn.clubonline.dao.redisdao.CaptchaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-27 15:37:54
 * @modify:
 */

@Repository
public class CaptchaDaoImpl implements CaptchaDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CaptchaProperties captchaProperties;

    private static final String CAPTCHA_REDIS_PREFIX = "captcha";

    @Override
    public void saveCaptcha(String captcha, String sessionId) {
        redisTemplate.opsForValue().set(String.format("%s:%s", CAPTCHA_REDIS_PREFIX, sessionId), captcha, captchaProperties.getTimeout(), TimeUnit.SECONDS);
    }

    @Override
    public String getCaptcha(String sessionId) {
        return redisTemplate.opsForValue().get(String.format("%s:%s", CAPTCHA_REDIS_PREFIX, sessionId));
    }

}
