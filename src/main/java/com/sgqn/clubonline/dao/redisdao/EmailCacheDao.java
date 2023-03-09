package com.sgqn.clubonline.dao.redisdao;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-03-09 8:31:39
 * @modify:
 */

public interface EmailCacheDao {

    boolean putIfAbsent(String email);

    void remove(String email);
}
