package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.dao.mapper.UserMapper;
import com.sgqn.clubonline.pojo.User;
import com.sgqn.clubonline.service.UserService;
import com.sgqn.clubonline.web.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author 15074
 * @description 针对表【per_user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-25 16:57:08
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveAndReturn(User user) {
        int row = userMapper.insert(user);
        Assert.isTrue(row == 1, "添加失败。本次添加参数：" + user);
        return userMapper.selectById(user.getId());
    }


    @Override
    public User selectByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        log.info("通过邮箱查询到的用户为：" + user);
        if (Objects.isNull(user)) {
            throw new NotFoundException("查询的用户失败");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = selectByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //TODO 根据用户名进行查询权限
        return user;
    }
}




