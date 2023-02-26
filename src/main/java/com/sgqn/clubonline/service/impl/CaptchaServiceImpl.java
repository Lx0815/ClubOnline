package com.sgqn.clubonline.service.impl;

import com.sgqn.clubonline.common.captcha.CaptchaCreator;
import com.sgqn.clubonline.common.captcha.CaptchaProperties;
import com.sgqn.clubonline.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 19:21:19
 * @modify:
 */

@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CaptchaCreator captchaCreator;

    @Autowired
    private CaptchaProperties captchaProperties;

    @Override
    public String getCaptcha(String sessionId) {
        String captcha = captchaCreator.randomString();
        redisTemplate.opsForValue().set(String.format("captcha:%s", sessionId), captcha, captchaProperties.getTimeout());
        log.info("验证码获取成功，为：" + captcha);
        return captcha;
    }

}
