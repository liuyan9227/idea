package com.kaishengit.erp.dto;

/**
 * @author liuyan
 * @date 2018/8/10
 */
public class FixStateDto {

    private Integer orderId;
    private Integer employeeId;
    private String state;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FixStateDto{" +
                "orderId=" + orderId +
                ", employeeId=" + employeeId +
                ", state='" + state + '\'' +
                '}';
    }
}
