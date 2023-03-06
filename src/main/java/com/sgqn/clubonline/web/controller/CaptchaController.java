package com.sgqn.clubonline.web.controller;

import com.sgqn.accesslimit.annotation.AccessLimit;
import com.sgqn.clubonline.common.utils.ResponseUtil;
import com.sgqn.clubonline.service.CaptchaService;
import com.sgqn.clubonline.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-03-06 13:28:37
 * @modify:
 */

@RestController
public class CaptchaController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CaptchaService captchaService;


    @AccessLimit
    @GetMapping("/email/captcha")
    public Object captcha(@RequestParam @Email String email, HttpServletRequest request) {
        String captcha = captchaService.getCaptcha(request.getSession().getId());
        emailService.sendCaptcha(email, captcha);
        return ResponseUtil.success();
    }

    @GetMapping("/email/captcha/check")
    public Object checkCaptcha(@RequestParam @NotBlank String captcha, HttpServletRequest request) {
        Boolean flag = captchaService.check(request.getSession().getId(), captcha);
        return ResponseUtil.success(flag);
    }

}
