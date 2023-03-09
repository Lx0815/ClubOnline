package com.sgqn.clubonline.dao.mapper;

import com.sgqn.clubonline.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;

/**
* @author 15074
* @description 针对表【per_user(用户表)】的数据库操作Mapper
* @createDate 2023-02-25 16:57:08
* @Entity {@link User}
*/
public interface UserMapper extends BaseMapper<User> {

    Integer selectCountByEmail(@Param("email") String email);

}




