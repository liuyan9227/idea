package com.kaishengit.erp.vo;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/5
 */
public class OrderVo {

    private Integer id;
    private Integer carId;
    private Integer serviceTypeId;
    private BigDecimal fee;
    private List<PartsVo> partsLists;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<PartsVo> getPartsLists() {
        return partsLists;
    }

    public void setPartsLists(List<PartsVo> partsLists) {
        this.partsLists = partsLists;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", carId=" + carId +
                ", serviceTypeId=" + serviceTypeId +
                ", fee=" + fee +
                ", partsLists=" + partsLists +
                '}';
    }
}
