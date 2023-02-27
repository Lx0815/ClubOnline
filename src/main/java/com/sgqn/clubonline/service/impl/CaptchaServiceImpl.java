package com.sgqn.clubonline.service.impl;

import com.sgqn.clubonline.common.captcha.CaptchaCreator;
import com.sgqn.clubonline.dao.redisdao.CaptchaDao;
import com.sgqn.clubonline.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CaptchaCreator captchaCreator;

    @Autowired
    private CaptchaDao captchaDao;



    @Override
    public String getCaptcha(String sessionId) {
        String captcha = captchaCreator.randomString();
        captchaDao.saveCaptcha(captcha, sessionId);
        log.info("验证码获取成功，为：" + captcha);
        return captcha;
    }

    @Override
    public Boolean check(String sessionId, String captcha) {
        String redisCaptcha = captchaDao.getCaptcha(sessionId);
        return captcha.equals(redisCaptcha);
    }

}
