package com.sgqn.clubonline.web.config.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 19:28:22
 * @modify:
 */

@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    /**
     * 是否包含数字
     */
    private Boolean hasDigits = Boolean.TRUE;

    /**
     * 是否包含小写字母
     */
    private Boolean hasLowerAlpha = Boolean.TRUE;

    /**
     * 是否包含大写字母
     */
    private Boolean hasUpperAlpha = Boolean.TRUE;

    /**
     * 需要包含的其他字符
     */
    private String others;

    /**
     * 验证码长度
     */
    private Integer length = 5;

    /**
     * 验证码有效期
     */
    private Long timeout = 120L;

}
