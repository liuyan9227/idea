package com.kaishengit.tms.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountExample;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
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
    public Account findAccountByModel(String accountMobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(accountMobile);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList != null && !accountList.isEmpty()){
            return accountList.get(0);
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
