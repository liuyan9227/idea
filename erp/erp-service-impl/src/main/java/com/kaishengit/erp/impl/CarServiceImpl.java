package com.kaishengit.erp.impl;

import com.kaishengit.erp.entity.Car;
import com.kaishengit.erp.entity.Customer;
import com.kaishengit.erp.entity.CustomerExample;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.mapper.CarMapper;
import com.kaishengit.erp.mapper.CustomerMapper;
import com.kaishengit.erp.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/2
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 保存车辆与客户信息
     * @param car      车辆对象
     * @param customer 客户信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveCarAndCustomer(Car car, Customer customer) {
        // 查询客户信息是否存在
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andIdCardEqualTo(customer.getIdCard());
        List<Customer> customerList = customerMapper.selectByExample(customerExample);
        // 初始化用户id值(customerId)
        Integer customerId = null;
        if(customerList == null || customerList.size() == 0){
            // 如果不存在,存该用户,获取用户id值
            customerMapper.insertSelective(customer);
            customerId = customer.getId();
        } else {
            // 如果存在获取用户id值
            customerId = customerList.get(0).getId();
        }
        // 人对车是1对多的关系,关联关系在car表中,所以要获取customerId设置在car对象中
        car.setCustomerId(customerId);
        // 保存车辆信息
        carMapper.insertSelective(car);
    }

    /**
     * 查询(车辆信息,车主信息)根据(车牌号)
     * @param licenceNo 车牌号(唯一)
     * @return car车辆对象
     */
    @Override
    public Car findCarByLicenseNo(String licenceNo) {
        if(StringUtils.isEmpty(licenceNo)){
            throw new ServiceException("参数异常");
        }
        // 如果报异常可能是输数据库被重复提交表单,存在多个车牌号相同的对象
        Car car = carMapper.findCarAndCustomerByLicenseNo(licenceNo);
        return car;
    }



}
