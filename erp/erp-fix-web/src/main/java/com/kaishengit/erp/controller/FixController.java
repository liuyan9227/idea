package com.kaishengit.erp.controller;

import com.google.gson.Gson;
import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.service.CarService;
import com.kaishengit.erp.service.FixService;
import com.kaishengit.erp.service.OrderService;
import com.kaishengit.erp.service.TypeService;
import com.kaishengit.erp.utils.Constant;
import com.kaishengit.erp.vo.OrderVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/8/8
 */
@Controller
@RequestMapping("/fix")
public class FixController {

    @Autowired
    private FixService fixService;

    /**
     * 业务: 跳转到维修领单页面,回显订单信息
     * 查询: order,car,serviceType,parts
     * 1.显示所有信息封装在fixOrder中
     * 2.orderList: 显示状态为2和3的列表
     * 3.partsList: 封装在fixOrder中
     */
    @GetMapping
    public String fixOrderHome(Model model){

        List<String> state = new ArrayList<>();
        state.add(Constant.ORDER_STATE_FIXING);
        state.add(Constant.ORDER_STATE_CHECKING);

        List<FixOrder> fixOrderList = fixService.findOrderByState(state);

        model.addAttribute("fixOrderList", fixOrderList);
        return "fix/list";
    }

    /**
     * 业务: 维修人员接单维修, 添加状态为2的订单,并添加登录员工与订单的关联关系表
     * 传值: 前端发起json数据,返回success
     * 获取orderId,从json数据中
     * 获取登录员工的详情信息Employee,从security中
     * 添加维修员工与领取表单的关联关系表
     */
    @GetMapping("/order")
    public ResponseBean fix(String json){
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(json, OrderVo.class);
        Integer order = orderVo.getId();

        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        fixService.saveOrderFixEmployee(order, employee);
        return ResponseBean.success();
    }

    /**
     * 业务:查询是否接单人员是否已有订单正在维修
     */
    @GetMapping("/{id:\\d+}/receive")
    @ResponseBody
    public ResponseBean fixComeOrder(@PathVariable Integer id){

        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        boolean isOrder = fixService.isOrderByEmployee(employee.getId());

        return ResponseBean.success(isOrder);
    }

    /**
     * 业务:维修人员接收订单根据orderId
     * 注意:
     * 获取当前登录员工信息
     * 创建维修员工与当前表单的关联关系表
     * 领取后更改订单当前状态并返回给前台状态信息
     */
    @GetMapping("/{id:\\d+}/make")
    public String fixOrderMake(@PathVariable Integer id){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        fixService.saveOrderAndEmployee(id, employee);

        fixService.updateStateByOrderId(id);
        return "fix/detail";
    }


}
