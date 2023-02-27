package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.dao.mapper.UserMapper;
import com.sgqn.clubonline.pojo.User;
import com.sgqn.clubonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author 15074
 * @description 针对表【per_user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-25 16:57:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveAndReturn(User user) {
        int row = userMapper.insert(user);
        Assert.isTrue(row == 1, "添加失败。本次添加参数：" + user);
        return userMapper.selectById(user.getId());
    }
}




