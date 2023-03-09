package com.sgqn.clubonline.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgqn.clubonline.common.Constants;
import com.sgqn.clubonline.web.response.Response;
import com.sgqn.clubonline.web.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.sgqn.clubonline.web.response.ResponseCode.*;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 16:36:54
 * @modify:
 */
@Slf4j
public class ResponseUtil {

    private ResponseUtil() {}

    private static <T> Response<T> create(ResponseCode code, T data, String message) {
        return new Response<>(code, data, message);
    }

    public static <T> Response<T> createByCode(ResponseCode code) {
        return create(code, null, code.getDescription());
    }

    public static <T> Response<T> createByCodeAndData(ResponseCode code, T data) {
        return create(code, data, Constants.EMPTY_STRING);
    }

    public static <T> Response<T> createByCodeAndMessage(ResponseCode code, String message) {
        return create(code, null, message);
    }

    public static void out(HttpServletResponse response, Response<Object> responseModel){
        ObjectMapper objectMapper = new ObjectMapper();
        // 封装response的状态码的格式内容
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            // 使用jackson，在响应体中写入ResponseModel中的数据
            objectMapper.writeValue(response.getOutputStream(),responseModel.getMessage());
            log.info(String.format("向响应体 %s 写入内容 %s",response,responseModel));
        } catch (IOException e) {
            log.error(String.format("向响应体 %s 写入内容 %s 失败！！！",response,responseModel.getMessage()));
            throw new RuntimeException(e);
        }

    }
}
