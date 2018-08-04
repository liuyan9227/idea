package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Parts;
import com.kaishengit.erp.entity.ServiceType;
import com.kaishengit.erp.entity.Type;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/3
 */
public interface OrderService {


    /**
     * 查询所有维修类型
     * @return ServiceTypeList
     */
    List<ServiceType> findServiceTypeAll();

    /**
     * 查找(部件类型)(部件)信息,两表联查
     * @return
     */
    List<Parts> findTypeAndParts();

    /**
     * 查找(部件parts)信息,根据(部件类型type_id)
     * @return typeId下所有的部件信息
     */
    List<Parts> findPartsByTypeId(Integer id);
}
