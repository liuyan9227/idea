package com.kaishengit.erp.vo;

import com.kaishengit.erp.entity.*;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/6
 */
public class OrderInfoVo {

    private Order order;
    private Car car;
    private Customer customer;
    private ServiceType serviceType;
    private List<Parts> partsList;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Parts> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Parts> partsList) {
        this.partsList = partsList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderInfoVo{" +
                "order=" + order +
                ", car=" + car +
                ", customer=" + customer +
                ", serviceType=" + serviceType +
                ", partsList=" + partsList +
                '}';
    }
}
