package com.sgqn.clubonline.web.response;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 16:38:39
 * @modify:
 */

public enum ResponseCode {

    /*====================================
    200XX ： 请求被成功处理
        ====================================*/
    OK("20000", "成功"),
    CERTIFICATION_SUCCEEDED("20001","认证成功"),

    /*====================================
    250XX ： 由于用户错误导致请求处理失败
        ====================================*/
    CERTIFICATION_FAIL("25001","认证失败"),

    /*====================================
    300XX ： TODO 表示资源是否存在？？？？
        ====================================*/
    RESOURCE_NOT_EXIST("30001", "资源不存在"),

    RESOURCE_EXIST("30002", "资源已存在"),

    RESOURCE_CREATE_SUCCESS("30003", "资源创建成功"),

    RESOURCE_CREATE_FAIL("30004", "资源创建失败"),


    FAIL("50000", "失败");

    private final String code;

    private final String description;

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
