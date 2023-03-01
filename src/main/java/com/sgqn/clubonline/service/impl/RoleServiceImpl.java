package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.pojo.Role;
import com.sgqn.clubonline.service.RoleService;
import com.sgqn.clubonline.dao.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author wspstart
* @description 针对表【per_role(权限角色表)】的数据库操作Service实现
* @createDate 2023-02-27 19:25:03
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




