package com.sgqn.clubonline.service;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 18:20:02
 * @modify:
 */

public interface EmailService {

    /**
     * 发送指定的验证码到指定的邮箱
     * @param email 接收者邮箱
     * @param captcha 验证码
     */
    void sendCaptcha(String email, String captcha);

}
