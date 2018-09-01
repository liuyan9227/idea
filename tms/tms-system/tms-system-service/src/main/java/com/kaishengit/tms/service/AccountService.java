package com.kaishengit.tms.service;


import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;

/**
 * @author liuyan
 * @date 2018/8/31
 */
public interface AccountService {
    /**
     * 查询用户,根据用户名
     * @param username 用户名
     * @return 用户对象
     */
    Account findAccountByName(String username);

    /**
     * 保存system模块登录日志
     * @param accountLoginLog 登陆日志
     */
    void saveAccountLoginlog(AccountLoginLog accountLoginLog);
}
