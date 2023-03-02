package com.sgqn.clubonline.service.impl;

import com.sgqn.clubonline.web.config.captcha.CaptchaProperties;
import com.sgqn.clubonline.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 18:20:16
 * @modify:
 */

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Autowired
    private CaptchaProperties captchaProperties;

    @Override
    public void sendCaptcha(String email, String captcha) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(emailSender);
        message.setSubject("ClubOnline 验证码");
        message.setText(String.format("您的验证码为：%s 。\n有效时间为 %d 秒。", captcha, captchaProperties.getTimeout()));
        mailSender.send(message);
    }
}
