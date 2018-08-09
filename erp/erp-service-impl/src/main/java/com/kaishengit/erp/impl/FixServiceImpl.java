package com.kaishengit.erp.impl;

import com.google.gson.Gson;
import com.kaishengit.erp.dto.OrderInfoDto;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.mapper.FixOrderMapper;
import com.kaishengit.erp.mapper.FixOrderPartsMapper;
import com.kaishengit.erp.mapper.OrderEmployeeMapper;
import com.kaishengit.erp.service.FixService;
import com.kaishengit.erp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author liuyan
 * @date 2018/8/8
 */
@Service
public class FixServiceImpl implements FixService {

    @Autowired
    private OrderEmployeeMapper orderEmployeeMapper;

    @Autowired
    private FixOrderMapper fixOrderMapper;

    @Autowired
    private FixOrderPartsMapper fixOrderPartsMapper;


    /**
     * 接收队列中的消息
     * 1.添加事务
     * 2.将json数据转化为对象(需要先创建相应的对象接收)
     * 3.将对象保存到数据库
     * @param json 下单详情信息的json数据
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void comOrderfromFrontMq(String json) {
        Gson gson = new Gson();
        // 用什么封装传值,就用什么封装接收(dto数据传输实体类)
        OrderInfoDto orderInfoDto = gson.fromJson(json, OrderInfoDto.class);

        // 解析orderInfoDto信息并入库
        // 封装维修单
        FixOrder fixOrder = new FixOrder();
        fixOrder.setOrderId(orderInfoDto.getOrder().getId());
        fixOrder.setOrderMoney(orderInfoDto.getOrder().getOrderMoney());
        fixOrder.setOrderTime(orderInfoDto.getOrder().getCreateTime());
        fixOrder.setState(orderInfoDto.getOrder().getState());
        fixOrder.setOrderType(orderInfoDto.getServiceType().getServiceName());
        fixOrder.setOrderServiceHour(orderInfoDto.getServiceType().getServiceHour());
        // 计算工时费
        fixOrder.setOrderServiceHourFee(new BigDecimal(Integer.parseInt(orderInfoDto.getServiceType().getServiceHour()) * Constant.DEFAULT_HOUR_FEE));
        // 计算配件费用
        fixOrder.setOrderPartsFee(fixOrder.getOrderMoney().subtract(fixOrder.getOrderServiceHourFee()));
        // 封装车辆信息
        fixOrder.setCarColor(orderInfoDto.getOrder().getCar().getColor());
        fixOrder.setCarLicence(orderInfoDto.getOrder().getCar().getLicenceNo());
        fixOrder.setCarType(orderInfoDto.getOrder().getCar().getCarType());
        // 封装客户信息
        fixOrder.setCustomerName(orderInfoDto.getOrder().getCustomer().getUserName());
        fixOrder.setCustomerTel(orderInfoDto.getOrder().getCustomer().getTel());

        fixOrderMapper.insert(fixOrder);

        // 配件列表入库
        for(Parts parts : orderInfoDto.getPartsList()) {
            FixOrderParts fixOrderParts = new FixOrderParts();
            fixOrderParts.setOrderId(orderInfoDto.getOrder().getId());
            fixOrderParts.setPartsId(parts.getId());
            fixOrderParts.setPartsName(parts.getPartsName());
            fixOrderParts.setPartsNo(parts.getPartsNo());
            fixOrderParts.setPartsNum(parts.getNum());

            fixOrderPartsMapper.insert(fixOrderParts);
        }
    }

    /**
     * 保存员工与当前领取表单的关联关系表
     * @param order    表单id
     * @param employee 员工id
     */
    @Override
    public void saveOrderFixEmployee(Integer order, Employee employee) {
        OrderEmployee orderEmployee = new OrderEmployee();
        orderEmployee.setOrderId(order);
        orderEmployee.setEmployeeId(employee.getId());
        orderEmployeeMapper.insert(orderEmployee);
    }
}
