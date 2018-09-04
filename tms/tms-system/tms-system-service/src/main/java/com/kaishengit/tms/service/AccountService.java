package com.kaishengit.tms.service;


import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;

import java.util.List;
import java.util.Map;


/**
 * @author liuyan
 * @date 2018/8/31
 */
public interface AccountService {

    /**
     * 封装登录人员信息
     * @param map 登录人员信息
     */
    void transAccount(Map<String,Object> map);

    /**
     * 查询用户,根据用户名
     * @param accountMobile 用户名
     * @return 用户对象
     */
    Account findAccountByModel(String accountMobile);

    /**
     * 保存system模块登录日志
     * @param accountLoginLog 登陆日志
     */
    void saveAccountLoginlog(AccountLoginLog accountLoginLog);


    /**
     * account首页,查看所有帐号和角色权限信息
     * @param requestParam nameMobile & rolesId
     * @return accountList
     */
    List<Account> findAllAccountWithRolesByQueryParam(Map<String,Object> requestParam);


    /**
     * 新增帐号:post表单提交
     * @param account 员工对象
     * @param rolesIds 对应的角色信息
     */
    void saveAccount(Account account, Integer[] rolesIds);

    /**
     * 查找账户信息,根据id查找
     * @param id accountId
     * @return 账户对象
     */
    Account findById(Integer id);

    /**
     * 修改账户信息,提交post表单信息
     * @param account 修改的账户对象
     * @param rolesIds 修改的角色信息
     */
    void updateAccount(Account account, Integer[] rolesIds);
}
