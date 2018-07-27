package com.kaishengit.erp.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.Type;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/24
 */
public interface TypeService {
    /**
     * 查询所有部件类型
     * @return
     */
    List<Type> findAllTypeList();

    /**
     * 删除没有占用的类型
     */
    void delType(Integer id);


    /**
     * 模糊查询所有类型
     * @param p
     * @param params
     * @return
     */
    PageInfo<Type> findAllTypeListLike(Integer p, Map<String, Object> params);
}
