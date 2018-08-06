package com.kaishengit.erp.mapper;

import com.kaishengit.erp.entity.OrderState;
import com.kaishengit.erp.entity.OrderStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderStateMapper {
    long countByExample(OrderStateExample example);

    int deleteByExample(OrderStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderState record);

    int insertSelective(OrderState record);

    List<OrderState> selectByExample(OrderStateExample example);

    OrderState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderState record, @Param("example") OrderStateExample example);

    int updateByExample(@Param("record") OrderState record, @Param("example") OrderStateExample example);

    int updateByPrimaryKeySelective(OrderState record);

    int updateByPrimaryKey(OrderState record);
}