package com.kaishengit.erp.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.Order;
import com.kaishengit.erp.entity.Parts;
import com.kaishengit.erp.entity.ServiceType;
import com.kaishengit.erp.entity.Type;
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
}
