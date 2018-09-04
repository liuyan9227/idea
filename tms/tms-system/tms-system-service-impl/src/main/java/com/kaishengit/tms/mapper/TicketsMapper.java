package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Tickets;
import com.kaishengit.tms.entity.TicketsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketsMapper {
    long countByExample(TicketsExample example);

    int deleteByExample(TicketsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tickets record);

    int insertSelective(Tickets record);

    List<Tickets> selectByExample(TicketsExample example);

    Tickets selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tickets record, @Param("example") TicketsExample example);

    int updateByExample(@Param("record") Tickets record, @Param("example") TicketsExample example);

    int updateByPrimaryKeySelective(Tickets record);

    int updateByPrimaryKey(Tickets record);
}