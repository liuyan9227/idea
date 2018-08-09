package com.kaishengit.erp.controller;

import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Car;
import com.kaishengit.erp.entity.Customer;
import com.kaishengit.erp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author liuyan
 * @date 2018/8/2
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/new")
    public String home(){
        return "order/new";
    }

    /**
     * 业务: 保存模态框(car, customer)
     * 返回: json数据,需要回显存入的数据在页面, 根据前端页面显示需要把customer封装在car对象中
     */
    @PostMapping("/new")
    @ResponseBody
    public ResponseBean home(Car car, Customer customer){
        // 保存车辆和客户信息
        carService.saveCarAndCustomer(car, customer);
        car.setCustomer(customer);
        return ResponseBean.success(car);
    }

    @ResponseBody
    @GetMapping("/check")
    public ResponseBean findCar(String licenceNo){
        // 查询此车牌号是否存在
        Car car = carService.findCarByLicenseNo(licenceNo);
        if(car != null){
            return ResponseBean.success(car);
        } else {
            return ResponseBean.error("未找到此车辆");
        }
    }




}
