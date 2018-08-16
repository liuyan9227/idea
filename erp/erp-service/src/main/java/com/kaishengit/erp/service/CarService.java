package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Car;
import com.kaishengit.erp.entity.Customer;

/**
 * @author liuyan
 * @date 2018/8/2
 */
public interface CarService {

    /**
     * 保存(车辆与客户信息)
     * @param car 车辆对象
     * @param customer 客户信息
     */
    void saveCarAndCustomer(Car car, Customer customer);

    /**
     * 查询(车辆信息,车主信息)根据(车牌号)
     * @param licenceNo 车牌号(唯一)
     * @return car车辆对象
     */
    Car findCarByLicenseNo(String licenceNo);

}
