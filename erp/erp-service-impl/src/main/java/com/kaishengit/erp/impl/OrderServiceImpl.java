package com.kaishengit.erp.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaishengit.erp.dto.FixStateDto;
import com.kaishengit.erp.dto.OrderInfoDto;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.mapper.*;
import com.kaishengit.erp.service.OrderService;
import com.kaishengit.erp.utils.Constant;
import com.kaishengit.erp.vo.OrderVo;
import com.kaishengit.erp.vo.PartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;
import java.util.Map;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

/**
 * @author liuyan
 * @date 2018/8/3
 */
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

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

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
        order.setState(Constant.ORDER_STATE_NEW);
        order.setCarId(orderVo.getCarId());
        order.setServiceTypeId(orderVo.getServiceTypeId());

        orderMapper.insertSelective(order);

        // 保存录入员工的信息
        OrderEmployee orderEmployee = new OrderEmployee();
        orderEmployee.setOrderId(order.getId());
        orderEmployee.setEmployeeId(employeeId);

        orderEmployeeMapper.insert(orderEmployee);

        // 保存订单所有的部件信息
        for(PartsVo parts : orderVo.getPartsLists()){
            OrderParts orderParts = new OrderParts();
            orderParts.setOrderId(order.getId());
            orderParts.setPartsId(parts.getId());
            orderParts.setNum(parts.getNum());

            orderPartsMapper.insert(orderParts);
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

        for(Order order : orderlist){
            if(Constant.ORDER_STATE_DONE.equals(order.getState())){
                PageInfo<Order> pageInfo = new PageInfo<>(orderlist);
                return pageInfo;
            }
        }
        PageInfo<Order> pageInfo = new PageInfo<>(orderlist);
        return pageInfo;
    }

    /**
     * 查询(order,car,customer)详情根据orderId
     * @param id 订单的id值
     * @return 返回订单详情
     */
    @Override
    public Order findOrderAndCustomerAndCarById(Integer id) {
        Order order = orderMapper.findOrderAndCustomerAndCarById(id);
        return order;
    }

    /**
     * 查询(ServiceType)服务类型,根据ServiceTypeId
     * @param serviceTypeId order表中的serviceTypeId
     * @return              服务的详情信息
     */
    @Override
    public ServiceType findServiceTypeByOrderServiceTypeId(Integer serviceTypeId) {
        ServiceType serviceType = serviceTypeMapper.selectByPrimaryKey(serviceTypeId);
        return serviceType;
    }

    /**
     * 查询-(orderParts)订单与部件的关联表, (parts)部件表, 根据orderId
     * @param orderId 当前订单的id值
     * @return 返回所有部件的集合
     */
    @Override
    public List<Parts> findPartsListByOrderId(Integer orderId) {
        List<Parts> partsList = partsMapper.findPartsListByOrderId(orderId);
        return partsList;
    }

    /**
     * 修改订单状态(下单)
     * @param id 订单id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateStateByOrderId(Integer id) throws ServiceException {
        Order order = orderMapper.selectByPrimaryKey(id);
        // 防止篡改orderId
        if(id == null){
            throw new ServiceException("订单错误");
        }
        // 防止表单重复提交, 状态不等于1时,不能重复提交
        if(!order.getState().equals(Constant.ORDER_STATE_NEW)){
            throw new ServiceException("该订单已经提交");
        }
        order.setState(Constant.ORDER_STATE_TRANS);
        orderMapper.updateByPrimaryKeySelective(order);

        sendOrderToFixMq(id);
    }


    /**
     * 业务: 将front下单消息放到消息队列中(将消息封装为json数据)fix接收
     * 注意:
     * 1.获取表单所有信息
     * 2.获取表单所有服务信息
     * 3.获取表单所有配件信息
     * 4.将所有的订单信息封装为json数据(下单页面)
     */
    public void sendOrderToFixMq(Integer id){
        // 多表联查order,car,customer
        Order order = orderMapper.findOrderAndCustomerAndCarById(id);

        ServiceType serviceType = serviceTypeMapper.selectByPrimaryKey(order.getServiceTypeId());

        List<Parts> partsList = partsMapper.findPartsListByOrderId(id);

        // 封装下单页面的所有数据与一个实体类中(dto数据传输实体类),将此实体类转化为json数据
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setOrder(order);
        orderInfoDto.setServiceType(serviceType);
        orderInfoDto.setPartsList(partsList);

        // 将实体类转化为json数据
        Gson gson = new Gson();
        String json = gson.toJson(orderInfoDto);

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });
    }

    /**
     * 删除订单根据(orderId)
     * car, ServiceType,
     * @param id 订单id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delOrderById(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        // 删除车辆信息根据表单的carId值
        carMapper.deleteByPrimaryKey(order.getCarId());
        // 删除订单与员工的关联关系表
        OrderEmployeeExample orderEmployeeExample = new OrderEmployeeExample();
        orderEmployeeExample.createCriteria().andOrderIdEqualTo(id);
        orderEmployeeMapper.deleteByExample(orderEmployeeExample);
        // 删除订单与部件的关联关系表
        OrderPartsExample orderPartsExample = new OrderPartsExample();
        orderPartsExample.createCriteria().andOrderIdEqualTo(id);
        orderPartsMapper.deleteByExample(orderPartsExample);
        // 删除订单
        orderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询订单根据状态为2
     * @return 符合条件的所有订单
     */
    @Override
    public List<Order> findOrderWhitState() {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andStateEqualTo(Constant.ORDER_STATE_FIXING);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        return orderList;
    }

    /**
     * 获取维修信息的json数据
     * @param json 维修信息
     */
    @Override
    public void comFixStateMq(String json) {
        Gson gson = new Gson();
        FixStateDto fixStateDto = gson.fromJson(json, FixStateDto.class);

        Order order = new Order();
        order.setId(fixStateDto.getOrderId());
        order.setState(fixStateDto.getState());
        orderMapper.updateByPrimaryKeySelective(order);
    }


}
