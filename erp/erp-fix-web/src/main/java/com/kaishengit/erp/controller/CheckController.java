package com.kaishengit.erp.controller;

import com.kaishengit.erp.entity.FixOrder;
import com.kaishengit.erp.service.FixService;
import com.kaishengit.erp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/10
 */
@Controller
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private FixService fixService;

    @GetMapping
    public String checkList(Model model){
        List<String> state = new ArrayList<>();
        state.add(Constant.ORDER_STATE_FIXED);
        state.add(Constant.ORDER_STATE_CHECKING);

        List<FixOrder> fixOrderList = fixService.findOrderByState(state);

        model.addAttribute("fixOrderList", fixOrderList);
        return "check/list";
    }


}
