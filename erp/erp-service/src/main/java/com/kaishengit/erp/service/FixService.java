package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Employee;

/**
 * @author liuyan
 * @date 2018/8/8
 */
public interface FixService {
    /**
     * 保存员工与当前领取表单的关联关系表
     * @param order 表单id
     * @param employee 员工id
     */
    void saveOrderFixEmployee(Integer order, Employee employee);
}
