package com.sgqn.clubonline.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgqn.clubonline.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wspstart
 * @description 针对表【per_menu(权限带单表)】的数据库操作Mapper
 * @createDate 2023-03-01 19:47:21
 * @Entity com.sgqn.clubonline.pojo.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据权限ID 查找对应的权限信息
     *
     * @param menuId 权限ID
     * @return 返回单个权限菜单
     */
    Menu selectByMenuId(@Param("id") Integer menuId);


    /**
     * 根据角色ID 查找对应的权限信息列表
     *
     * @param roleId 角色ID
     * @return 返回角色对应的所有权限
     */
    List<Menu> selectMenuListByRoleId(@Param("roleId") Integer roleId);
}