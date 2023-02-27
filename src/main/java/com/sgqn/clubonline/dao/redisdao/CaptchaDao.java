package com.sgqn.clubonline.dao.redisdao;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-27 15:52:40
 * @modify:
 */

public interface CaptchaDao {

    /**
     * 保存验证码
     * @param captcha 验证码
     * @param sessionId 会话 id
     */
    void saveCaptcha(String captcha, String sessionId);

    /**
     * 通过 会话id 获取验证码
     * @param sessionId 会话 id
     * @return 返回验证码
     */
    String getCaptcha(String sessionId);

}
