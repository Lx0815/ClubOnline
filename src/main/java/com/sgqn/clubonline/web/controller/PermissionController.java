package com.sgqn.clubonline.web.controller;

import cloud.agileframework.kaptcha.kaptcha.KaptchaContextHolder;
import com.sgqn.clubonline.common.utils.ResponseUtil;
import com.sgqn.clubonline.service.CaptchaService;
import com.sgqn.clubonline.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 17:11:02
 * @modify:
 */

@Valid
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CaptchaService captchaService;


    @GetMapping("/email/captcha")
    public Object captcha(@RequestParam @Email String email, HttpServletRequest request) {
        String captcha = captchaService.getCaptcha(request.getSession().getId());
        emailService.sendCaptcha(email, captcha);
        return ResponseUtil.success();
    }

}
