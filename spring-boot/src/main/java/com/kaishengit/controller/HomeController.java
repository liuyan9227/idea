package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/22
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model){
        List<String> nameList = Arrays.asList("max,mini");

        model.
        model.addAttribute("age", 26);
        model.addAttribute("userName", "mini");
        model.addAttribute("name", "max");
        model.addAttribute("nameList", nameList);
        return "home";
    }
}
