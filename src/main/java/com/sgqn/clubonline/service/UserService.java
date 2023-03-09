package com.sgqn.clubonline.service;

import com.sgqn.clubonline.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

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


    /**
     * 检查该电子邮件是否已经被占用
     *
     * @param newEmail 新的电子邮件
     * @param oldEmail 旧的电子邮件
     * @return 返回 ture 表示已经有人使用了本电子邮件
     */
    Boolean checkEmailExisted(String newEmail, String oldEmail);
}
