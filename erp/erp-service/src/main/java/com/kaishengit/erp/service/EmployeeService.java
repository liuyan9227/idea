package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Employee;

/**
 * @author liuyan
 * @date 2018/7/26
 */
public interface EmployeeService {
    /**
     * 登录页面根据电话和密码查找是否存在
     * @param userTel 用户电话
     * @param password 用户密码(采用md5Hex加密)
     * @return 对象用作前端显示
     */
    Employee findTelAndPassword(String userTel, String password, String ip);
}
