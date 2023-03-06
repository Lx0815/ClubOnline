package com.sgqn.clubonline.web.controller;

import com.sgqn.accesslimit.annotation.AccessLimit;
import com.sgqn.clubonline.common.utils.ResponseUtil;
import com.sgqn.clubonline.service.CaptchaService;
import com.sgqn.clubonline.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 17:11:02
 * @modify:
 */

@Validated
@RestController
@RequestMapping("/permission")
public class PermissionController {



}
