package com.kaishengit.service;

import com.kaishengit.entity.Car;
import com.kaishengit.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author liuyan
 * @date 2018/8/24
 */
@Service    // 将此类放入spring容器中管理
@CacheConfig(cacheNames = "car")    // 多个缓存可能重复,用于指定对应的缓存,并放入
public class CacheService {

    @Autowired
    private CarMapper carMapper;

    @Cacheable  //先从缓存查找,再执行此方法查询数据库
    public Car findCar(Integer id){
        Car car = carMapper.selectByPrimaryKey(id);
        return car;
    }

    @CacheEvict //删除缓存
    public void delCache(Integer id){

    }
}
