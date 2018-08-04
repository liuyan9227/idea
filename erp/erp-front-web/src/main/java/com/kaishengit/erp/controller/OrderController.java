package com.kaishengit.erp.controller;

import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Parts;
import com.kaishengit.erp.entity.ServiceType;
import com.kaishengit.erp.entity.Type;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.OrderService;
import com.kaishengit.erp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 订单提交:将表单所有信息提交保存到数据库
     * 获得前端传入的json数组,本质是字符串,用String[]接收值,将json数据转化为对象在获取信息的值
     * (carId : 根据车辆的id值能获得车辆信息以及车主信息
     * serviceTypeId : 提交服务信息
     * partsList : 用户会选择更换多个部件所以会以集合的方式保存到数据库
     * fee(费用) : 在维修保养中的所有产生的费用报讯导数据库)信息
     */
    @PostMapping("/new")
    @ResponseBody
    // public ResponseBean orderSubmit(Integer carId, Integer serviceTypeId, Float fee, List<Parts> psrtsList){
    public ResponseBean orderSubmit(String[] json){
        System.out.println("json数组为"+ json.toString());
        // 将json数据转化为对象数组

        System.out.println("获取的车辆ID" );
        System.out.println("获取的服务类型ID" );
        System.out.println("获取所有费用" );
        System.out.println("获取所有的部件信息");
        return ResponseBean.success();
    }




}
