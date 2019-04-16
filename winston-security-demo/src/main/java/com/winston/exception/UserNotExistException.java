package com.winston.exception;

/**
 * @ClassName UserNotExistException
 * @Description 自定义异常
 * @Author Winston
 * @Date 2019/4/14 16:23
 * @Version 1.0
 **/
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6112780192479692859L;

    private String id;

    public UserNotExistException(String id){
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
