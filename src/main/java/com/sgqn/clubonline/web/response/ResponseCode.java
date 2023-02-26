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

    OK("20000", "成功"),

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
