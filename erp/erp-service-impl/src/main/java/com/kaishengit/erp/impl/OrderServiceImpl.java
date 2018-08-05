package com.kaishengit.erp.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.mapper.*;
import com.kaishengit.erp.service.OrderService;
import com.kaishengit.erp.utils.Constant;
import com.kaishengit.erp.vo.OrderVo;
import com.kaishengit.erp.vo.PartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/8/3
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private PartsMapper partsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderEmployeeMapper orderEmployeeMapper;

    @Autowired
    private OrderPartsMapper orderPartsMapper;

    /**
     * 查询所有维修类型
     * @return ServiceTypeList
     */
    @Override
    public List<ServiceType> findServiceTypeAll() {
        ServiceTypeExample serviceTypeExample = new ServiceTypeExample();
        List<ServiceType> serviceTypeList = serviceTypeMapper.selectByExample(serviceTypeExample);
        if(serviceTypeList.isEmpty()){
            throw new ServiceException("未查到项目类型");
        }
        return serviceTypeList;
    }

    /**
     * 查找(部件类型)(部件)信息,两表联查
     * @return
     */
    @Override
    public List<Parts> findTypeAndParts() {
        List<Parts> partsList = partsMapper.findAllPartsAndType();
        if(partsList.isEmpty()){
            throw new ServiceException("未查到部件类型");
        }
        return partsList;
    }

    /**
     * 查找(部件parts)信息,根据(部件类型type_id)
     * @return typeId下所有的部件信息
     */
    @Override
    public List<Parts> findPartsByTypeId(Integer id) {
        PartsExample partsExample = new PartsExample();
        partsExample.createCriteria().andTypeIdEqualTo(id);
        List<Parts> partsList = partsMapper.selectByExample(partsExample);
        if(partsList.isEmpty()){
            throw new ServiceException("未查到部件信息");
        }
        return partsList;
    }

    /**
     * 保存维修表单信息
     * @param employeeId 录入员工的id值(当前登录员工)
     * @param orderVo    前端出入的json数据解析成java对象(详情需查找commons.vo.OrderVo和PartsVo)
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrder(Integer employeeId, OrderVo orderVo) {
        // 保存新增订单信息
        Order order = new Order();
        order.setOrderMoney(orderVo.getFee());
        order.setState("1");
        order.setCarId(orderVo.getCarId());
        order.setServiceTypeId(orderVo.getServiceTypeId());

        orderMapper.insertSelective(order);

        // 保存录入员工的信息
        OrderEmployee orderEmployee = new OrderEmployee();
        orderEmployee.setOrderId(order.getId());
        orderEmployee.setEmployeeId(employeeId);

        orderEmployeeMapper.insert(orderEmployee);
        System.out.println("获取的orderEmployee----"+orderEmployee);

        // 保存订单所有的部件信息
        for(PartsVo parts : orderVo.getPartsLists()){
            OrderParts orderParts = new OrderParts();
            orderParts.setOrderId(order.getId());
            orderParts.setPartsId(parts.getId());
            orderParts.setNum(parts.getNum());

            orderPartsMapper.insert(orderParts);
            System.out.println("获取的orderParts----"+orderParts);
        }

    }

    /**
     * 查询-订单(order),customer(车主),car(车辆)多表联查
     * @param p      分页
     * @param params 查询条件
     */
    @Override
    public PageInfo<Order> findOrderAndCustomerAndCarWithLike(Integer p, Map<String, Object> params) {
        PageHelper.startPage(p, Constant.DEFAULT_PAGE_SIZE);

        // 将map集合对象之间传入进行sql查询,可以之间使用Key值进行传值
        List<Order> orderlist = orderMapper.findOrderAndCustomerAndCarWithLike(params);

        PageInfo<Order> pageInfo = new PageInfo<>(orderlist);
        return pageInfo;
    }
}
