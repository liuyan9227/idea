package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Parts;
import com.kaishengit.entity.PartsExample;
import com.kaishengit.mapper.PartsMapper;
import com.kaishengit.service.PartsService;
import com.kaishengit.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/24
 */
@Service
public class PartsServiceImpl implements PartsService {

    Logger logger = LoggerFactory.getLogger(PartsServiceImpl.class);

    @Autowired
    private PartsMapper partsMapper;

    /**
     * 根据Id查找配件信息
     * @param id
     * @return
     */
    @Override
    public Parts findById(Integer id) {
        return partsMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据p值来查找分页信息
     * @param pageNo 分页值,默认为1
     * @return
     */
    @Override
    public PageInfo<Parts> findListByPage(Integer pageNo, Map params) {
        // 分页
        PageHelper.startPage(pageNo, Constant.DEFAULT_PAGE_SIZE);
        // 多表联查,获取List<Parts>信息
        List<Parts> partsList = partsMapper.findListPage(params);
        // 封装分页对象
        PageInfo<Parts> pageInfo = new PageInfo<>(partsList);
        return pageInfo;
    }

    /**
     * 根据id删除信息
     * @param id 获得的a标签的id值
     */
    @Override
    public void deleteParts(Integer id) {
        partsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增部件
     *
     * @param parts
     * @return
     */
    @Override
    public int saveParts(Parts parts) {
        int num = partsMapper.insertSelective(parts);
        logger.debug("新增零件:{}", parts);
        return num;
    }

    /**
     * 根据ID查找对应的parts信息
     *
     * @param id
     * @return
     */
    @Override
    public Parts findPartsById(Integer id) {
        Parts parts = partsMapper.selectByPrimaryKey(id);
        return parts;
    }

    /**
     * 修改信息
     *
     * @param parts
     */
    @Override
    public void updateParts(Parts parts) {
        partsMapper.updateByPrimaryKeySelective(parts);
    }

    /**
     * 根据typeId查询没有使用的类型
     * @param id
     * @return
     */
    @Override
    public Boolean findNotType(Integer id) {
        PartsExample partsExample = new PartsExample();
        partsExample.createCriteria().andTypeIdEqualTo(id);

        List<Parts> partsList = partsMapper.selectByExample(partsExample);
        int count = partsList.size();

        return count == 0;
    }



}
