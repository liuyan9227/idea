package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Employee;

/**
 * @author liuyan
 * @date 2018/8/8
 */
public interface FixService {

    /**
     * 接收队列中的消息
     * @param json 下单详情信息的json数据
     */
    void comOrderfromFrontMq(String json);

    /**
     * 保存员工与当前领取表单的关联关系表
     * @param order 表单id
     * @param employee 员工id
     */
    void saveOrderFixEmployee(Integer order, Employee employee);
}
