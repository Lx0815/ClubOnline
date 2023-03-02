package com.sgqn.clubonline.web.config.captcha;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sgqn.clubonline.common.Constants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 19:27:55
 * @modify:
 */

@Component
public class CaptchaCreator implements InitializingBean {

    @Autowired
    private CaptchaProperties captchaProperties;

    private char[] allowChars;

    private final Random random = new Random();

    @Override
    public void afterPropertiesSet() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if (captchaProperties.getHasDigits()) {
            stringBuilder.append(Constants.DIGITS_STR);
        }
        if (captchaProperties.getHasLowerAlpha()) {
            stringBuilder.append(Constants.LOWER_ALPHA);
        }
        if (captchaProperties.getHasUpperAlpha()) {
            stringBuilder.append(Constants.UPPER_ALPHA);
        }
        if (StringUtils.isNotBlank(captchaProperties.getOthers())) {
            stringBuilder.append(captchaProperties.getOthers().trim());
        }
        allowChars = stringBuilder.toString().toCharArray();
    }

    public String randomString() {
        return randomString(captchaProperties.getLength());
    }

    public String randomString(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(allowChars[random.nextInt(allowChars.length)]);
        }
        return stringBuilder.toString();
    }


}
