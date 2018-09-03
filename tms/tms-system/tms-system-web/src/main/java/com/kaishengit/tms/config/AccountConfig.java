package com.kaishengit.tms.config;

import com.kaishengit.tms.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 用于记录日志时对登录人员信息的封装
 * @author liuyan
 * @date 2018/9/1
 */
public class AccountConfig {
    public Account getAccount(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return account;
    }
}
