package com.sgqn.clubonline.service;

import com.sgqn.clubonline.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgqn.clubonline.web.response.Response;

/**
* @author Ding
* @description 针对表【per_user(用户表)】的数据库操作Service
* @createDate 2023-02-25 16:57:08
*/
public interface UserService extends IService<User> {

    /**
     * 保存一个新的 User 并返回该对象
     * @param user 新的用户对象
     * @return 返回新增后的用户对象
     */
    User saveAndReturn(User user);


    /**
     * 根据邮箱查找对应的用户
     * @param email
     * @return
     */
    User selectByEmail(String email);




}
