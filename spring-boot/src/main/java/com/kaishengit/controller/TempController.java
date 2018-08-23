package com.kaishengit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Car;
import com.kaishengit.entity.CarExample;
import com.kaishengit.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    @ResponseBody
    public List<Car> cars(){
        PageHelper.startPage(1, 3);
        List<Car> carList = carMapper.selectByExample(new CarExample());
        PageInfo page = new PageInfo(carList);
        return carList;
    }
}
