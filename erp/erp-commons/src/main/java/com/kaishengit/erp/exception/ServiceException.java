package com.kaishengit.erp.exception;

/**
 * @author liuyan
 * @date 2018/7/26
 */
public class ServiceException extends RuntimeException {

    public ServiceException(){

    }

    public ServiceException(String message){
        super(message);
    }

    private ServiceException(Throwable th){
        super(th);
    }

    public ServiceException(String message, Throwable th){
        super(message, th);
    }

}
