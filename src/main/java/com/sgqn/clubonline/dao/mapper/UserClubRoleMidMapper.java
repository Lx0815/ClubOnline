package com.sgqn.clubonline.dao.mapper;

import com.sgqn.clubonline.pojo.UserClubRoleMid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
* @author 15074
* @description 针对表【cl_per_user__cl_club__per_role_mid(社团、用户、角色三表的关联表)】的数据库操作Mapper
* @createDate 2023-03-02 20:08:12
* @Entity com.sgqn.clubonline.pojo.UserClubRoleMid
*/
public interface UserClubRoleMidMapper extends BaseMapper<UserClubRoleMid> {


    default int insertByUserIdAndRoleId(Integer userId, Integer roleId) {
        UserClubRoleMid userClubRoleMid = new UserClubRoleMid();
        userClubRoleMid.setUserId(userId);
        userClubRoleMid.setRoleId(roleId);
        return insert(userClubRoleMid);
    }
}




