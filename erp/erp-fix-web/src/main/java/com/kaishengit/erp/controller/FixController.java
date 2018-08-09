package com.kaishengit.erp.controller;

import com.google.gson.Gson;
import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Car;
import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.Order;
import com.kaishengit.erp.service.CarService;
import com.kaishengit.erp.service.FixService;
import com.kaishengit.erp.service.OrderService;
import com.kaishengit.erp.vo.OrderVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/8
 */
@Controller
@RequestMapping("/fix")
public class FixController {

    @Autowired
    private FixService fixService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;


    /**
     * 业务: 跳转到维修领单页面,回显订单信息
     * 查询: order,car,serviceType,parts
     */
    @GetMapping("/sss")
    public String fixOrderHome(Model model){
        List<Order> orderList = orderService.findOrderWhitState();

        for(Order order : orderList){
            List<Car> carList = carService.findCarByid(order.getId());

        }

        return "";
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

}
