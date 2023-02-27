package com.sgqn.clubonline.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 19:20:51
 * @modify:
 */

public interface CaptchaService {

    /**
     * 根据 sessionId 获取验证码，获取到的验证码将会存入 Redis 缓存。
     * 验证码的具体配置需要在 application.yml 中进行配置
     * @param sessionId 该用户的会话 ID
     * @return 返回验证码
     */
    String getCaptcha(String sessionId);

    /**
     * 通过 sessionId 校验该用户输入的验证码是否正确
     * @param sessionId 会话 id
     * @return 返回 true 表示输入正确
     */
    Boolean check(String sessionId, String captcha);
}
