package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.pojo.Role;
import com.sgqn.clubonline.service.MenuService;
import com.sgqn.clubonline.service.RoleService;
import com.sgqn.clubonline.dao.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wspstart
* @description 针对表【per_role(权限角色表)】的数据库操作Service实现
* @createDate 2023-02-27 19:25:03
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRolePermissionByRoleId(Integer roleId) {
        return roleMapper.selectRolePermissionByRoleId(roleId);
    }


    @Override
    public List<Role> getAllRole() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>(null);
        return roleMapper.selectList(queryWrapper);
    }

}




