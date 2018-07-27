package com.kaishengit.erp.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.Type;
import com.kaishengit.erp.service.PartsService;
import com.kaishengit.erp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/25
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private PartsService partsService;

    @GetMapping
    public String home(@RequestParam(defaultValue = "1", required = false) Integer p,
                       @RequestParam(required = false) String typeName,
                       Model model){
        Map<String, Object> params = new HashMap<>();
        params.put("typeName", typeName);
        // 查询所有type信息显示在页面
        PageInfo<Type> page = typeService.findAllTypeListLike(p, params);
        // 将page对象传入前端做分页显示
        model.addAttribute("page", page);
        model.addAttribute("typeName", typeName);
        return "type/list";
    }



    @GetMapping("/new")
    public String newType(){

        return "";
    }

    @GetMapping("/{id:\\d+}/del")
    public String delType(@PathVariable Integer id, RedirectAttributes redirectAttributes)throws IOException{
        // 判断是否存在此类型
        List<Type> typeList = typeService.findAllTypeList();
        // 此类型是否在使用中
        if (typeList != null){
            // 缺少判断是否被使用
            Boolean typeNo = partsService.findNotType(id);
            // 是否没被占用,true 没有占用可以删除 : false 占用中不能删除
            if (typeNo){
                typeService.delType(id);
            } else {
                redirectAttributes.addFlashAttribute("message", "此类型被占用不能删除");
                return "redirect:/type";
            }
        } else {
            throw new IOException();
        }
        // 将删除的信息返回在前端显示
        redirectAttributes.addFlashAttribute("message", "删除成功");
        // 从定向到主页面
        return "redirect:/type";
    }

    @GetMapping("/edit")
    public String updateType(){

        return "";
    }




}
