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


    @PostMapping("/new")
    public String home(Car car, Customer customer, Model model){
        System.out.println("获得的车辆" + car);
        System.out.println("获得的客户" + customer);
        // 保存车辆和客户信息
        carService.saveCarAndCustomer(car, customer);
        model.addAttribute("car", car);
        model.addAttribute("customer", customer);
        return "order/new";
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
