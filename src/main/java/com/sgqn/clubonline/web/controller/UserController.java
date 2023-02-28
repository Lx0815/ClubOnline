package com.sgqn.clubonline.web.controller;

import com.sgqn.clubonline.common.utils.ResponseUtil;
import com.sgqn.clubonline.pojo.User;
import com.sgqn.clubonline.service.UserService;
import com.sgqn.clubonline.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-25 16:44:27
 * @modify:
 */

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Object register(@RequestBody @NotNull User user) {
        User returnUser = userService.saveAndReturn(user);
        if (Objects.isNull(returnUser))
            throw new NotFoundException("插入 User 成功后读取失败。User: " + user);
        return ResponseUtil.success(returnUser);
    }


}
