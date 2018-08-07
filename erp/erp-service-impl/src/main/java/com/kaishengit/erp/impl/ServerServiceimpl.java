package com.kaishengit.erp.impl;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.mapper.ServerService;
import org.springframework.stereotype.Service;

/**
 * @author liuyan
 * @date 2018/8/7
 */
@Service
public class ServerServiceimpl implements ServerService {


    /**
     * 添加维修人员领取表单的关联关系表
     *
     * @param orderId  领取表单ID
     * @param employee 当前登录员工
     */
    @Override
    public void saveOrderWithEmployee(Integer orderId, Employee employee) {

    }
}
