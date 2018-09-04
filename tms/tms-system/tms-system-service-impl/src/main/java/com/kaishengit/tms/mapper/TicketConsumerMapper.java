package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.TicketConsumer;
import com.kaishengit.tms.entity.TicketConsumerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketConsumerMapper {
    long countByExample(TicketConsumerExample example);

    int deleteByExample(TicketConsumerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TicketConsumer record);

    int insertSelective(TicketConsumer record);

    List<TicketConsumer> selectByExample(TicketConsumerExample example);

    TicketConsumer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TicketConsumer record, @Param("example") TicketConsumerExample example);

    int updateByExample(@Param("record") TicketConsumer record, @Param("example") TicketConsumerExample example);

    int updateByPrimaryKeySelective(TicketConsumer record);

    int updateByPrimaryKey(TicketConsumer record);
}