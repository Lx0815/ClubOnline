package com.sgqn.clubonline.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 17:11:02
 * @modify:
 */

@Controller
public class CaptchaController {


    @GetMapping("/captcha")
    public Object captcha(@RequestParam String email) {
        
    }

}
