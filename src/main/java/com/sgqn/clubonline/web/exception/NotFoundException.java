package com.sgqn.clubonline.web.exception;

/**
 * @description: 没有找到资源异常。意思是资源应该存在，而不存在
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-27 20:29:13
 * @modify:
 */

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        this(message, null);
    }

    public NotFoundException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
