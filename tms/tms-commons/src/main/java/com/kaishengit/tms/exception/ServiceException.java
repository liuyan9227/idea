package com.kaishengit.tms.exception;

/**
 * 手动抛出异常
 * @author liuyan
 * @date 2018/8/31
 */
public class ServiceException extends RuntimeException {

    private ServiceException(){}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable th){
        super(th);
    }

    public ServiceException(String message, Throwable th){
        super(message, th);
    }


}
