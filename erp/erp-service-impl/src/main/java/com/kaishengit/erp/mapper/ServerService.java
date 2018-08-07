package com.kaishengit.erp.mapper;

import com.kaishengit.erp.entity.Employee;

/**
 * @author liuyan
 * @date 2018/8/7
 */
public interface ServerService {



    /**
     * 添加维修人员领取表单的关联关系表
     * @param orderId 领取表单ID
     * @param employee 当前登录员工
     */
    void saveOrderWithEmployee(Integer orderId, Employee employee);
}
