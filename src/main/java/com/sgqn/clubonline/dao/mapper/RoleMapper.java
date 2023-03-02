package com.sgqn.clubonline.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.clubonline.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wpsstart
 * @description 针对表【per_role(权限角色表)】的数据库操作Mapper
 * @createDate 2023-02-27 19:25:03
 * @Entity com.sgqn.clubonline.pojo.Role
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 存在问题
     * 根据角色ID查询角色以及权限
     *
     * @param roleId 角色ID
     * @return
     */
    Role selectRolePermissionByRoleId(@Param("roleId") Integer roleId);



}




