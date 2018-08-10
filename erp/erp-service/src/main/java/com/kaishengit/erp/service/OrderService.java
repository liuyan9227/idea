package com.kaishengit.erp.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.vo.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/8/3
 */
public interface OrderService {


    /**
     * 查询所有维修类型
     * @return ServiceTypeList
     */
    List<ServiceType> findServiceTypeAll();

    /**
     * 查找(部件类型)(部件)信息,两表联查
     * @return
     */
    List<Parts> findTypeAndParts();

    /**
     * 查找(部件parts)信息,根据(部件类型type_id)
     * @return typeId下所有的部件信息
     */
    List<Parts> findPartsByTypeId(Integer id);

    /**
     * 保存维修表单信息
     * @param employeeId 录入员工的id值(当前登录员工)
     * @param orderVo 前端出入的json数据解析成java对象(详情需查找commons.vo.OrderVo和PartsVo)
     */
    void saveOrder(Integer employeeId, OrderVo orderVo);

    /**
     * 查询-订单(order),customer(车主),car(车辆)多表联查
     * @param p 分页
     * @param params 查询条件
     */
    PageInfo<Order> findOrderAndCustomerAndCarWithLike(Integer p, Map<String,Object> params);

    /**
     * 查询(order,car,customer)详情根据orderId
     * @param id 订单的id值
     * @return 返回订单详情
     */
    Order findOrderAndCustomerAndCarById(Integer id);

    /**
     * 查询(ServiceType)服务类型,根据ServiceTypeId
     * @param serviceTypeId order表中的serviceTypeId
     * @return 服务详情信息
     */
    ServiceType findServiceTypeByOrderServiceTypeId(Integer serviceTypeId);

    /**
     * 查询-(orderParts)订单与部件的关联表, (parts)部件表, 根据orderId
     * @param orderId 当前订单的id值
     * @return 返回所有部件的集合
     */
    List<Parts> findPartsListByOrderId(Integer orderId);

    /**
     * 修改订单状态
     * @param id 订单id
     */
    void updateStateByOrderId(Integer id);

    /**
     * 删除订单根据(orderId)
     * @param id 订单id
     */
    void delOrderById(Integer id);

    /**
     * 查询订单根据状态为2
     * @return 符合条件的所有订单
     */
    List<Order> findOrderWhitState();

    /**
     * 获取维修信息的json数据
     * @param json 维修信息
     */
    void comFixStateMq(String json);
}
