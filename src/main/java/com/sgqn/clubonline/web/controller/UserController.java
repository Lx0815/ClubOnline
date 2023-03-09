package com.sgqn.clubonline.web.controller;

import com.sgqn.clubonline.common.utils.ResponseUtil;
import com.sgqn.clubonline.pojo.User;
import com.sgqn.clubonline.service.UserService;
import com.sgqn.clubonline.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static com.sgqn.clubonline.web.response.ResponseCode.*;

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
        return ResponseUtil.createByCodeAndData(RESOURCE_CREATE_SUCCESS, returnUser);
    }

    @GetMapping("/email/check/{newEmail}")
    public Object checkEmail(@PathVariable @Email String newEmail, String oldEmail) {
        Boolean exist = userService.checkEmailExisted(newEmail, oldEmail);
        if (exist) return ResponseUtil.createByCode(RESOURCE_EXIST);
        return ResponseUtil.createByCode(RESOURCE_NOT_EXIST);
    }
}
