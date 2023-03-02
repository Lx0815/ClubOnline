package com.sgqn.clubonline.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgqn.clubonline.pojo.Menu;
import com.sgqn.clubonline.pojo.Role;
import com.sgqn.clubonline.service.MenuService;
import com.sgqn.clubonline.service.RedisInitService;
import com.sgqn.clubonline.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wspstart
 * @create 2023-03-01 23:25
 */
@Service
@Slf4j
public class RedisInitServiceImpl implements RedisInitService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 保存角色权限信息到Redis中
     *
     * @param roles 存储的角色，根据角色ID查询权限信息
     */
    @Override
    public void saveRolePermission(List<Role> roles) {
        log.info("正在Redis存入权限角色信息：格式： 角色ID : 角色权限信息");
        for (Role role : roles) {
            List<Menu> menus = menuService.listMenuByRoleId(role.getId());
            role.setMenuList(menus);
            try {
                redisTemplate.<String, String>opsForHash().
                        put("club:rolePermission", role.getId().toString(), objectMapper.writeValueAsString(menus));
            } catch (JsonProcessingException e) {
                log.error(String.format("向Redis中存入信息 %s : %s失败", role.getId(), menus));
            }
        }
    }

    /**
     * 保存角色的信息到Redis中
     *
     * @return 返回 角色信息 供saveRolePermission()使用
     */
    @Override
    public List<Role> saveRole() {
        List<Role> roleList = roleService.getAllRole();
        log.info("正在向Redis存入角色信息：格式： 角色ID : 角色信息");
        for (Role role : roleList) {
            try {
                redisTemplate.<String, String>opsForHash().put("club:role", role.getId().toString(), objectMapper.writeValueAsString(role));
            } catch (JsonProcessingException e) {
                log.error(String.format("向Redis中存入信息 %s 失败", role));
            }
        }
        return roleList;
    }

    /**
     * 保存Menu的全部信息
     */
    @Override
    public void saveMenu() {
        List<Menu> menuList = menuService.getAll();
        log.info("正在向Redis存入权限菜单信息：格式： 权限菜单ID : 权限菜单信息");
        for (Menu menu : menuList) {
            try {
                redisTemplate.<String, String>opsForHash().put("club:menu", menu.getId().toString(), objectMapper.writeValueAsString(menu));
            } catch (JsonProcessingException e) {
                log.error(String.format("向Redis中存入信息 %s 失败", menu));
            }
        }
    }


}
