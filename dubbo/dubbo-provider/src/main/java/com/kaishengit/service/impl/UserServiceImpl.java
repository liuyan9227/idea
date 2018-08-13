package com.kaishengit.service.impl;

import com.kaishengit.service.UserService;

/**
 * @author liuyan
 * @date 2018/8/11
 */
public class UserServiceImpl implements UserService {
    public String findById(Integer id) {

        if(id.equals(1100)){
            return "success";
        } else {
            return "error";
        }
    }
}
