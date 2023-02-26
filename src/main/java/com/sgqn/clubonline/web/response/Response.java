package com.sgqn.clubonline.web.response;

import com.sgqn.clubonline.common.Constants;
import lombok.Data;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 16:37:40
 * @modify:
 */

@Data
public class Response<T> {

    private ResponseCode code;

    private T data;

    private String message;

    public Response(ResponseCode code) {
        this(code, null, code.getDescription());
    }

    public Response(ResponseCode code, T data) {
        this(code, data, Constants.EMPTY_STRING);
    }

    public Response(ResponseCode code, String message) {
        this(code, null, message);
    }

    public Response(ResponseCode code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return "\nResponse: \n" +
                "Code: " + code + "\n" +
                "Data: " + data + "\n" +
                "Message: " + message + "\n";
    }
}
