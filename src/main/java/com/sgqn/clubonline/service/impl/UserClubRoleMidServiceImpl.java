package com.sgqn.clubonline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgqn.clubonline.pojo.UserClubRoleMid;
import com.sgqn.clubonline.service.UserClubRoleMidService;
import com.sgqn.clubonline.dao.mapper.UserClubRoleMidMapper;
import org.springframework.stereotype.Service;

/**
* @author 15074
* @description 针对表【cl_per_user__cl_club__per_role_mid(社团、用户、角色三表的关联表)】的数据库操作Service实现
* @createDate 2023-03-02 20:08:12
*/
@Service
public class UserClubRoleMidServiceImpl extends ServiceImpl<UserClubRoleMidMapper, UserClubRoleMid>
    implements UserClubRoleMidService{

}




