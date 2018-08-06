package com.kaishengit.erp.mapper;

import java.util.List;
import java.util.Map;

import com.kaishengit.erp.entity.Order;
import com.kaishengit.erp.entity.OrderExample;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findOrderAndCustomerAndCarWithLike(Map<String,Object> params);

    Order findOrderAndCustomerAndCarById(Integer id);
}