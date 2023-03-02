package com.sgqn.clubonline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.clubonline.pojo.Role;

import java.util.List;

/**
 * @author wspstart
 * @description 针对表【per_role(权限角色表)】的数据库操作Service
 * @createDate 2023-02-27 19:25:03
 */
public interface RoleService extends IService<Role> {


    Role getRolePermissionByRoleId(Integer roleId);


    /**
     * 获取所有的角色
     *
     * @return 返回所有的角色列表
     */
    List<Role> getAllRole();
}
