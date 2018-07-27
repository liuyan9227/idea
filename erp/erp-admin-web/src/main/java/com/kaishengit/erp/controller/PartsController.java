package com.kaishengit.erp.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.Parts;
import com.kaishengit.erp.entity.Type;
import com.kaishengit.erp.service.PartsService;
import com.kaishengit.erp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/24
 */
@Controller
@RequestMapping("/parts")
public class PartsController {

    @Autowired
    private PartsService partsService;

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String findPageList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                               // 前端页面返回的信息,获取name属性的值的名称
                               @RequestParam(required = false) String partsName,
                               @RequestParam(required = false) Integer partsType,
                               Model model){

        // 封装所有属性在Map集合中,将所有需要的属性写入map对象传入参数
        Map<String, Object> params = new HashMap<>();
        params.put("partsName", partsName);
        params.put("partsType", partsType);


        // 根据传进的P值来跳转分页, 获得页面所有信息(拦截)
        PageInfo<Parts> page = partsService.findListByPage(pageNo, params);
        // 查询所有类型,用于下拉选择框
        List<Type> typeList = typeService.findAllTypeList();
        // 将数据传送到视图jsp页面
        model.addAttribute("partsName", partsName);
        model.addAttribute("page", page);
        model.addAttribute("typeList", typeList);
        return "parts/list";
    }

    @GetMapping("/{id:\\d+}")
    public String findById(@PathVariable Integer id, Model model)throws IOException {
        Parts parts = partsService.findById(id);

        if(parts != null){
            model.addAttribute("parts", parts);
        } else {
            throw new IOException();
        }

        return "parts/detail";
    }

    @GetMapping("/new")
    public String addPartsGet(Model model){
        List<Type> typeList = typeService.findAllTypeList();
        model.addAttribute("typeList", typeList);
        return "parts/new";
    }

    @PostMapping("/new")
    public String addPartsPost(Parts parts, RedirectAttributes redirectAttributes){
        partsService.saveParts(parts);
        redirectAttributes.addFlashAttribute("message", "入库成功");
        return "redirect:/parts";
    }

    @GetMapping("/{id:\\d+}/del")
    public String delParts(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        partsService.deleteParts(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/parts";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String editParts(@PathVariable Integer id, Model model){
        Parts parts = partsService.findPartsById(id);
        List<Type> typeList = typeService.findAllTypeList();
        model.addAttribute("parts", parts);
        model.addAttribute("typeList",typeList);
        return "parts/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editPartsPost(Parts parts, RedirectAttributes redirectAttributes){
        partsService.updateParts(parts);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/parts";
    }

    @GetMapping("/type")
    public String typeList(){
        // 重定向到tepe类型的页面, 与parts内容摆脱关系
        return "redirect:/type";
    }



}
