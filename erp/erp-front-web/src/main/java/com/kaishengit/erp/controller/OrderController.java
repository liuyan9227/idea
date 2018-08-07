package com.kaishengit.erp.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.OrderService;
import com.kaishengit.erp.service.TypeService;
import com.kaishengit.erp.vo.OrderInfoVo;
import com.kaishengit.erp.vo.OrderVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单信息
 * @author liuyan
 * @date 2018/8/3
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TypeService typeService;

    /**
     * 查询所有维修项目信息
     */
    @GetMapping("/service/types")
    @ResponseBody
    public ResponseBean findServiceType(){
        List<ServiceType> serviceTypeList = orderService.findServiceTypeAll();
        return ResponseBean.success(serviceTypeList);
    }

    /**
     * 前端页面内容:在页面加载的时候,发起ajax请求,回显下拉选择框的内容(type),将查到的type信息返回页面用于回显
     */
    @GetMapping("/parts/types")
    @ResponseBody
    public ResponseBean mountedType(){
        List<Type> typeList = typeService.findAllTypeList();
        if(typeList == null){
            throw new ServiceException("未能查到该类型");
        }
        return ResponseBean.success(typeList);
    }


    /**
     * 查询(所有部件)根据前端页面传过来的类型的id值(typeId)
     */
    @GetMapping("/{id:\\d+}/parts")
    @ResponseBody
    public ResponseBean findPartsType(@PathVariable Integer id){
        System.out.println("类型信息---------"+id);
        List<Parts> partsType = orderService.findPartsByTypeId(id);
        return ResponseBean.success(partsType);
    }

    /**
     * 用于页面跳转
     */
    @GetMapping("/new")
    public String orderNew(){
        return "order/new";
    }

    /**
     * 回看提示 : 转化json数据(创建实体类解析json数据), 获取登录员工信息(securityUtils中提取);
     * 订单提交:将表单所有信息提交保存到数据库
     * 获得前端传入的json数组,本质是字符串,用String接收值:{"carId":1,"serviceTypeId":3,"fee":230,"partsLists":[{"id":1001,"num":1}]}
     * 将json数据转化为对象在获取信息的值(需要gson的jar包)
     * (carId : 根据车辆的id值能获得车辆信息以及车主信息
     * serviceTypeId : 提交服务信息
     * partsList : 用户会选择更换多个部件所以会以集合的方式保存到数据库
     * fee(费用) : 在维修保养中的所有产生的费用报讯导数据库)信息
     * 提交并保存表单信息:要获取当前登录员工的信息,操作人员的信息从securityUtils中获得,(注意选对subject),在获得员工信息和id值
     */
    @PostMapping("/new")
    @ResponseBody
    public ResponseBean orderSubmit(String json){
        // 将json数据转化为对象数组
        Gson gson = new Gson();
        // 使用fromJson()将json数据转化为对象,参数传(json数据, 解析json数据的类.class)
        OrderVo orderVo = gson.fromJson(json, OrderVo.class);
        // 获取当前登录员工信息
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        Integer employeeId = employee.getId();
        // 将对象传入后台保存到数据库
        orderService.saveOrder(employeeId, orderVo);
        return ResponseBean.success();
    }

    /**
     * 已完成页面
     */
    @GetMapping("/done/list")
    public String orderDoneList(){
        return "order/hisList";
    }

    /**
     * 页面显示(未完成)所有订单信息
     * 分页:PageHelper, PageInfo
     * 订单号:orderId, 状态:state 订单金额:orderMoney, 订单日期:create_time
     * 车主信息:customer
     * 车辆信息:car
     * 模糊查询-根据(车牌)和(车主电话)和(下单日期)
     */
    @GetMapping("/undone/list")
    public String listNo(@RequestParam(defaultValue = "1", required = false) Integer p,
                         Model model){
        // 创建一个map集合进行传值
        Map<String, Object> params = new HashMap<>();

        // 查询所有表单信息
        PageInfo<Order> pageInfo = orderService.findOrderAndCustomerAndCarWithLike(p, params);
        model.addAttribute("pageInfo", pageInfo);
        return "order/list";
    }

    /**
     * 页面需求:回显当前订单orderId(order, car, customer, parts)
     * 返回的是:order对象,将其他信息都封装在order中
     */
    @GetMapping("/{id:\\d+}/detail")
    public String orderDetail(@PathVariable Integer id, Model model){
        // 查询当前(order,car,customer)的详情信息,根据订单id
        Order order = orderService.findOrderAndCustomerAndCarById(id);
        // 查询当前订单的(serviceType)服务类型详情信息,根据订单id
        ServiceType serviceType = orderService.findServiceTypeByOrderServiceTypeId(order.getServiceTypeId());
        // 查询当前订单的(parts)使用部件的详情信息,
        List<Parts> partsList = orderService.findPartsListByOrderId(order.getId());

        model.addAttribute("order", order);
        model.addAttribute("serviceType", serviceType);
        model.addAttribute("partsList", partsList);
        return "order/detail";
    }

    /**
     * 保养维修单的下发订单功能:改变订单当前状态(1:等待, 2:维修中, 3:质检中 4:结算中 5:完成)
     * 获取当前订单的id值
     */
    @GetMapping("/{id:\\d+}/trans")
    @ResponseBody
    public ResponseBean orderState(@PathVariable Integer id){
        orderService.updateStateByOrderId(id);
        return ResponseBean.success();
    }

    /**
     * 业务:删除当前订单根据orderId
     * 注意:删除表单需要删除表单内容以及关联关系表谨防脏数据留存
     */
    @GetMapping("{id:\\d+}/del")
    public String orderDel(@PathVariable Integer id){
        orderService.delOrderById(id);
        return "order/hisList";
    }

    /**
     * 更新页面
     */
    @GetMapping("/{id:\\d+}/edit")
    public String orderEdit(@PathVariable Integer id, Model model){
        model.addAttribute("orderId", id);
        return "order/edit";
    }

    /**
     * 修改当前订单,需要回显信息根据(orderId)
     * 回调函数在页面加载时会发起ajax请求来展示前端详情 (mounted:)
     * 回显car, customer, serviceType, parts和orderParts
     */
    @GetMapping("/{id:\\d+}/info")
    @ResponseBody
    public ResponseBean orderInfo(@PathVariable Integer id){
        try {
            // 根据orderId返回order对象, order对象中包含car, customer对象信息
            Order order = orderService.findOrderAndCustomerAndCarById(id);
            // 获得car对象
            Car car = order.getCar();
            // 获得customer对象
            Customer customer = order.getCustomer();
            // 查询订单的serviceType获得服务类型对象
            ServiceType serviceType = orderService.findServiceTypeByOrderServiceTypeId(order.getServiceTypeId());
            // 查询parts部件对象
            List<Parts> partsList = orderService.findPartsListByOrderId(order.getId());

            OrderInfoVo orderInfoVo = new OrderInfoVo();
            orderInfoVo.setOrder(order);
            orderInfoVo.setServiceType(serviceType);
            orderInfoVo.setPartsList(partsList);

            return ResponseBean.success(orderInfoVo);

        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }

    }

    @PostMapping("/{id:\\d+}/edit")
    @ResponseBody
    public ResponseBean orderEditPost(String json){
        // 将json数据转化为对象数组
        Gson gson = new Gson();
        // 使用fromJson()将json数据转化为对象,参数传(json数据, 解析json数据的类.class)
        OrderVo orderVo = gson.fromJson(json, OrderVo.class);
        // 获取当前登录员工信息
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        Integer employeeId = employee.getId();
        // 将对象传入后台保存到数据库
        orderService.saveOrder(employeeId, orderVo);
        return ResponseBean.success();
    }




}
