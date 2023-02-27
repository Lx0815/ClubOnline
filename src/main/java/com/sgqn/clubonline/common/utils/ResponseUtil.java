package com.sgqn.clubonline.common.utils;

import com.sgqn.clubonline.web.response.Response;

import static com.sgqn.clubonline.web.response.ResponseCode.*;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 16:36:54
 * @modify:
 */

public class ResponseUtil {

    private ResponseUtil() {}

    public static  <T> Response<T> success() {
        return new Response<>(OK);
    }

    public static  <T> Response<T> success(T data) {
        return new Response<>(OK, data);
    }

    public static  <T> Response<T> fail() {
        return new Response<>(FAIL);
    }

}
