package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.dao.mapper.RoleMapper;
import com.sgqn.clubonline.dao.mapper.UserMapper;
import com.sgqn.clubonline.dao.mapper.UserClubRoleMidMapper;
import com.sgqn.clubonline.dao.redisdao.EmailCacheDao;
import com.sgqn.clubonline.pojo.User;
import com.sgqn.clubonline.service.UserService;
import com.sgqn.clubonline.web.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @author 15074
 * @description 针对表【per_user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-25 16:57:08
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    /**
     * TODO 改成 enum 枚举常量
     */
    private static final String ORDINARY_USER = "ORDINARY_USER";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserClubRoleMidMapper userClubRoleMidMapper;

    @Autowired
    private EmailCacheDao emailCacheDao;

    @Override
    public User saveAndReturn(User user) {
        int row = userMapper.insert(user);
        Assert.isTrue(row == 1, "添加失败。本次添加参数：" + user);
        // 获取普通用户的角色
        Integer roleId = roleMapper.selectIdByCode(ORDINARY_USER);
        // 添加用户信息到 cl_per_user__cl_club__per_role_mid 表
        row = userClubRoleMidMapper.insertByUserIdAndRoleId(user.getId(), roleId);
        Assert.isTrue(row == 1, String.format("添加失败。本次添加参数：userId: %s, roleId: %s", user.getId(), roleId));
        // 删除 email 缓存
        emailCacheDao.remove(user.getEmail());
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

    @Override
    public Boolean checkEmailExisted(String newEmail, String oldEmail) {
        emailCacheDao.remove(oldEmail);
        // 先从缓存查询
        boolean exist = emailCacheDao.putIfAbsent(newEmail);
        if (exist) return Boolean.TRUE;
        int count = userMapper.selectCountByEmail(newEmail);
        if (count > 0) {
            return Boolean.TRUE;
        }
        // 该邮箱可用
        return Boolean.FALSE;
    }
}




