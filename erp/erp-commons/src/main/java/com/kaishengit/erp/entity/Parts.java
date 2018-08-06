package com.kaishengit.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class Parts implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 备件编号
     */
    private String partsNo;

    /**
     * 备件名称
     */
    private String partsName;

    /**
     * 进价
     */
    private BigDecimal inPrice;

    /**
     * 售价
     */
    private BigDecimal salePrice;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 类型
     */
    private Integer typeId;

    /**
     * 进货地址
     */
    private String address;

    /**
     * 类型
     */
    private Type type;

    /**
     * 订单与部件关系表
     */
    private OrderParts orderParts;

    /**
     * 数量
     */
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public OrderParts getOrderParts() {
        return orderParts;
    }

    public void setOrderParts(OrderParts orderParts) {
        this.orderParts = orderParts;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Parts other = (Parts) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPartsNo() == null ? other.getPartsNo() == null : this.getPartsNo().equals(other.getPartsNo()))
            && (this.getPartsName() == null ? other.getPartsName() == null : this.getPartsName().equals(other.getPartsName()))
            && (this.getInPrice() == null ? other.getInPrice() == null : this.getInPrice().equals(other.getInPrice()))
            && (this.getSalePrice() == null ? other.getSalePrice() == null : this.getSalePrice().equals(other.getSalePrice()))
            && (this.getInventory() == null ? other.getInventory() == null : this.getInventory().equals(other.getInventory()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPartsNo() == null) ? 0 : getPartsNo().hashCode());
        result = prime * result + ((getPartsName() == null) ? 0 : getPartsName().hashCode());
        result = prime * result + ((getInPrice() == null) ? 0 : getInPrice().hashCode());
        result = prime * result + ((getSalePrice() == null) ? 0 : getSalePrice().hashCode());
        result = prime * result + ((getInventory() == null) ? 0 : getInventory().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "id=" + id +
                ", partsNo='" + partsNo + '\'' +
                ", partsName='" + partsName + '\'' +
                ", inPrice=" + inPrice +
                ", salePrice=" + salePrice +
                ", inventory=" + inventory +
                ", typeId=" + typeId +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", orderParts=" + orderParts +
                ", num=" + num +
                '}';
    }
}