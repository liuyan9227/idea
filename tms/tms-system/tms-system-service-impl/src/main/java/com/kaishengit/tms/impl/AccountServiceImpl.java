package com.kaishengit.tms.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/31
 */
@Service(version = "1.0", timeout = 5000)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    /**
     * shiro验证用户信息
     */
    @Override
    public Account findAccountByName(String username) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountNameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        if(accounts.size() != 0){
            return accounts.get(0);
        }
        return null;
    }

    /**
     * shiro保存登录信息
     */
    @Override
    public void saveAccountLoginlog(AccountLoginLog accountLoginLog) {
        accountLoginLogMapper.insertSelective(accountLoginLog);
    }
}
