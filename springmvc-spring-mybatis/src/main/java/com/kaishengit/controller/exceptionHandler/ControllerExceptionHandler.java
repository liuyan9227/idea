package com.kaishengit.controller.exceptionHandler;

import com.kaishengit.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author liuyan
 * @date 2018/7/24
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String ioExceptionHandler(){
        return "error/500";
    }

    @ExceptionHandler(NotFoundException.class)
    public String NotFountException(){
        return "error/404";
    }
}
