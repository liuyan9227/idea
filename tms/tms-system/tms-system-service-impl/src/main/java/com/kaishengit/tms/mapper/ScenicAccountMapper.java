package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.ScenicAccount;
import com.kaishengit.tms.entity.ScenicAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScenicAccountMapper {
    long countByExample(ScenicAccountExample example);

    int deleteByExample(ScenicAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScenicAccount record);

    int insertSelective(ScenicAccount record);

    List<ScenicAccount> selectByExample(ScenicAccountExample example);

    ScenicAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScenicAccount record, @Param("example") ScenicAccountExample example);

    int updateByExample(@Param("record") ScenicAccount record, @Param("example") ScenicAccountExample example);

    int updateByPrimaryKeySelective(ScenicAccount record);

    int updateByPrimaryKey(ScenicAccount record);
}