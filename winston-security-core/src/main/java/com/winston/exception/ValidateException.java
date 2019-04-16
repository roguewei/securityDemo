package com.winston.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidateException
 * @Description 针对验证码的异常处理类
 * @Author Winston
 * @Date 2019/4/15 22:01
 * @Version 1.0
 **/
public class ValidateException extends AuthenticationException {

    private static final long seralVersionUID = -7285211528095468156L;

    public ValidateException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateException(String msg) {
        super(msg);
    }
}
