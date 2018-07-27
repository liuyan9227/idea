package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Parts;
import com.kaishengit.entity.Type;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/24
 */
public interface PartsService {
    /**
     * 根据Id查找配件信息
     * @param id
     * @return
     */
    Parts findById(Integer id);

    /**
     * 根据p值来查找分页信息
     * @param pageNo 分页值,默认为1
     * @return
     */
    PageInfo<Parts> findListByPage(Integer pageNo, Map params);

    /**
     * 根据id删除信息
     * @param id
     */
    void deleteParts(Integer id);

    /**
     * 新增部件
     * @return
     */
    int saveParts(Parts parts);

    /**
     * 根据ID查找对应的parts信息
     * @param id
     * @return
     */
    Parts findPartsById(Integer id);

    /**
     * 修改信息
     * @param parts
     */
    void updateParts(Parts parts);

    /**
     * 根据typeId查询没有使用的类型
     * @param id
     * @return
     */
    Boolean findNotType(Integer id);
}
