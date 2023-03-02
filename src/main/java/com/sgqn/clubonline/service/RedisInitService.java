package com.sgqn.clubonline.service;

import com.sgqn.clubonline.pojo.Role;

import java.util.List;

/**
 * Redis启动时获取信息
 *
 * @author wspstart
 * @create 2023-03-01 23:25
 */
public interface RedisInitService {


    /**
     * 保存角色权限信息到Redis中
     *
     * @param roles 存储的角色，根据角色ID查询权限信息
     */
    void saveRolePermission(List<Role> roles);

    /**
     * 保存角色的信息到Redis中
     *
     * @return 返回 角色信息 供saveRolePermission()使用
     */
    List<Role> saveRole();

    /**
     * 保存Menu的全部信息
     */
    void saveMenu();
}
