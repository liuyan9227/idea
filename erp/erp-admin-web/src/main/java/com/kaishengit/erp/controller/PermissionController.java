package com.kaishengit.erp.controller;

import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Permission;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * Get方法作为页面跳转作用,也需要回显数据,返回的是jsp页面路径
 * Post方法作为表单提交作用,需要大部分用于业务处理,并且返回的是重定向请求,防止表单重复提交
 * 如果排序出现乱序要采用递归排序处理,来自于guava技术支持
 * 在删除业务中,主要的是判断是否还有其他使用此项,判断条件要精确,不能出现又是null又是空的判断,切记
 * @author liuyan
 * @date 2018/8/1
*/

@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model){
        // TODO 重点(递归排序)
        // 主页显示所有权限
        List<Permission> permissionList = rolePermissionService.findPermissionAll();
        model.addAttribute("permissionList", permissionList);
        return "manage/permission/home";
    }

    @GetMapping("/new")
    public String save(Model model){
        // 在新增页面<checkbox>显示所有权限
        List<Permission> menuPermissionList = rolePermissionService.findPermissionAll();
        model.addAttribute(menuPermissionList);
        return "manage/permission/new";
    }

    @PostMapping("/new")
    public String savePost(Permission permission, RedirectAttributes redirectAttributes){
        // 将新增信息保存到数据库
        rolePermissionService.savePermission(permission);
        return "redirect:/manage/permission";
    }

    @ResponseBody
    @GetMapping("/{id:\\d+}/del")
    public ResponseBean del(@PathVariable Integer id){
        // TODO 路径中获取的值必须加上注解!!!在删除的判断中注意判断条件
        try {
            rolePermissionService.delPermission(id);
        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
            return ResponseBean.success();
    }

    @GetMapping("{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){
        // 修改要回显权限信息给前端页面
        Permission permission = rolePermissionService.findPermissionById(id);
        // 回显复选框
        List<Permission> menuPermissionList = rolePermissionService.findPermissionPid(id);
        model.addAttribute("menuPermissionList", menuPermissionList);
        model.addAttribute("permission", permission);
        return "manage/permission/edit";
    }

    @PostMapping("{id:\\d+}/edit")
    public String editPost(Permission permission){
        rolePermissionService.updetePermission(permission);
        return "redirect:/manage/permission";
    }
}
