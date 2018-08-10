package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.FixOrder;

import java.util.List;

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

    /**
     * 查询维修中与维修完成订单根据State=2,3
     * @param state 订单状态
     * @return 返回条件相符的订单
     */
    List<FixOrder> findOrderByState(List<String> state);

    /**
     * 查询当前员工是否有正在维修的订单
     * @param id 员工id
     * @return 是否存在
     */
    boolean isOrderByEmployee(Integer id);

    /**
     * 保存当前(登录员工和订单的关联关系表)
     * @param id 订单id
     * @param employee 当前员工信息
     */
    void saveOrderAndEmployee(Integer id, Employee employee);

    /**
     * 更改领取的订单状态
     * @param id orderId
     */
    void updateStateByOrderId(Integer id);
}
