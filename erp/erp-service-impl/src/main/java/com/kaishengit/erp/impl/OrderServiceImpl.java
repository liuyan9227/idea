package com.kaishengit.erp.impl;

import com.kaishengit.erp.entity.Parts;
import com.kaishengit.erp.entity.PartsExample;
import com.kaishengit.erp.entity.ServiceType;
import com.kaishengit.erp.entity.ServiceTypeExample;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.mapper.PartsMapper;
import com.kaishengit.erp.mapper.ServiceTypeMapper;
import com.kaishengit.erp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/3
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private PartsMapper partsMapper;

    /**
     * 查询所有维修类型
     * @return ServiceTypeList
     */
    @Override
    public List<ServiceType> findServiceTypeAll() {
        ServiceTypeExample serviceTypeExample = new ServiceTypeExample();
        List<ServiceType> serviceTypeList = serviceTypeMapper.selectByExample(serviceTypeExample);
        if(serviceTypeList.isEmpty()){
            throw new ServiceException("未查到项目类型");
        }
        return serviceTypeList;
    }

    /**
     * 查找(部件类型)(部件)信息,两表联查
     * @return
     */
    @Override
    public List<Parts> findTypeAndParts() {
        List<Parts> partsList = partsMapper.findAllPartsAndType();
        if(partsList.isEmpty()){
            throw new ServiceException("未查到部件类型");
        }
        return partsList;
    }

    /**
     * 查找(部件parts)信息,根据(部件类型type_id)
     * @return typeId下所有的部件信息
     */
    @Override
    public List<Parts> findPartsByTypeId(Integer id) {
        PartsExample partsExample = new PartsExample();
        partsExample.createCriteria().andTypeIdEqualTo(id);
        List<Parts> partsList = partsMapper.selectByExample(partsExample);
        if(partsList.isEmpty()){
            throw new ServiceException("未查到部件信息");
        }
        return partsList;
    }
}
