package com.kaishengit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Car;
import com.kaishengit.entity.CarExample;
import com.kaishengit.mapper.CarMapper;
import com.kaishengit.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/23
 */
@Controller
@RequestMapping("/car")
public class TempController {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CacheService cacheService;

    @GetMapping
    @ResponseBody
    public List<Car> cars(){
        PageHelper.startPage(1, 3);
        List<Car> carList = carMapper.selectByExample(new CarExample());
        PageInfo page = new PageInfo(carList);
        return carList;
    }

    /**
     * 测试carService是否缓存
     */
    @GetMapping("/cache")
    @ResponseBody
    public Car cache(){
        Car car = cacheService.findCar(62);
        return car;
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public String delCache(@PathVariable Integer id){
        try {
            cacheService.delCache(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
