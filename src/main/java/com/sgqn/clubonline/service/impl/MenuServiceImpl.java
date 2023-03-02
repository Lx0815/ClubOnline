package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.dao.mapper.MenuMapper;
import com.sgqn.clubonline.pojo.Menu;
import com.sgqn.clubonline.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wspstart
 * @description 针对表【per_menu(权限带单表)】的数据库操作Service实现
 * @createDate 2023-03-01 20:22:24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Menu getByMenuId(Integer menuId) {
        return menuMapper.selectByMenuId(menuId);
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectList(new QueryWrapper<>(null));
    }

    @Override
    public List<Menu> listMenuByRoleId(Integer roleId) {
        return menuMapper.selectMenuListByRoleId(roleId);
    }
}




