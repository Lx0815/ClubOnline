package com.sgqn.clubonline.web.controller;

import com.sgqn.clubonline.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 16:44:27
 * @modify:
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        return null;
    }

}
