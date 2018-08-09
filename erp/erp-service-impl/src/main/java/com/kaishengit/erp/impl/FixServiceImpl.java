package com.kaishengit.erp.impl;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.OrderEmployee;
import com.kaishengit.erp.mapper.OrderEmployeeMapper;
import com.kaishengit.erp.service.FixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyan
 * @date 2018/8/8
 */
@Service
public class FixServiceImpl implements FixService {

    @Autowired
    private OrderEmployeeMapper orderEmployeeMapper;

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
