package com.sgqn.clubonline.service;

import com.sgqn.clubonline.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wspstart
* @description 针对表【per_menu(权限带单表)】的数据库操作Service
* @createDate 2023-03-01 20:22:24
*/
public interface MenuService extends IService<Menu> {


    Menu getByMenuId(Integer menuId);

    List<Menu> getAll();

    List<Menu> listMenuByRoleId(Integer roleId);
}
