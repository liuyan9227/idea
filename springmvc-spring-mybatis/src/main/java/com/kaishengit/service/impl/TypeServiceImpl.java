package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Type;
import com.kaishengit.entity.TypeExample;
import com.kaishengit.mapper.TypeMapper;
import com.kaishengit.service.TypeService;
import com.kaishengit.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/24
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有部件类型
     * @return 部件类型typeList集合
     */
    @Override
    public List<Type> findAllTypeList() {
        TypeExample typeExample = new TypeExample();
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        return typeList;
    }

    /**
     * 删除没有占用的类型
     */
    @Override
    public void delType(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 模糊查询所有类型
     *
     * @param p
     * @param params
     * @return
     */
    @Override
    public PageInfo<Type> findAllTypeListLike(Integer p, Map<String, Object> params) {
        // 分页拦截器
        PageHelper.startPage(p, Constant.DEFAULT_PAGE_SIZE);
        // 写模糊查询
        List<Type> typeList = typeMapper.findTypeListLike(params);
        // 将list集合转化为PageInfo对象,PageInfo对象中自带list结果集,前端页面需要${page.list}进行迭代(PageHelper与PageInfo组合使用,分页插件)
        PageInfo<Type> page = new PageInfo<>(typeList);
        return page;
    }


}
