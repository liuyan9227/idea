package com.kaishengit.tms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;
import com.kaishengit.tms.shiro.CustomerFilterChainDefinition;
import com.kaishengit.tms.util.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限模块
 * @author liuyan
 * @date 2018/9/2
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Reference(version = "1.0")
    private RolePermissionService rolePermissionService;

    @Autowired
    private CustomerFilterChainDefinition customerFilterChainDefinition;



    /**
     * 封装登录人员信息
     */
    private void transAccount(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        rolePermissionService.transAccount(map);
    }


    /**
     * 权限首页显示
     */
    @GetMapping
    public String home(Model model){
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList", permissionList);
        return "manage/permission/home";
    }


    /**
     * (跳转)新增权限,回显所有权限信息
     */
    @GetMapping("new")
    public String save(Model model){
        List<Permission> permissionList = rolePermissionService.findPermissionByPermissionType(Permission.MENU_TYPE);
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/new";
    }

    /**
     * 新增权限,并刷新权限
     */
    @PostMapping("/new")
    public String savePost(Permission permission, RedirectAttributes redirectAttributes){
        rolePermissionService.savePermission(permission);
        transAccount();
        //刷新Shiro的权限
        customerFilterChainDefinition.updateUrlPermission();
        redirectAttributes.addFlashAttribute("message","新增权限成功");
        return "redirect:/manage/permission";
    }


    /**
     * 删除权限,并刷新权限
     */
    @GetMapping("{id:\\d+}//del")
    @ResponseBody
    public ResponseBean del(@PathVariable Integer id){
        try {
            rolePermissionService.delPermissionById(id);
            transAccount();
            //刷新Shiro的权限
            customerFilterChainDefinition.updateUrlPermission();
            return ResponseBean.success();
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
    }


    /**
     * (修改权限)回显权限信息
     */
    @GetMapping("/{id:\\d+}/edit")
    public String permissionEdit(@PathVariable Integer id, Model model) {
        Permission permission = rolePermissionService.findPermissionById(id);

        if(permission == null) {
            throw new NotFoundException();
        }

        // 封装所有菜单权限列表
        List<Permission> menuPermissionList = rolePermissionService.findPermissionByPermissionType(Permission.MENU_TYPE);
        // 排除当前permission对象及其子权限对象
        remove(menuPermissionList,permission);

        model.addAttribute("menuPermissionList", menuPermissionList);
        model.addAttribute("permission", permission);
        return "manage/permission/edit";
    }

    /**
     * 递归去除所有的子权限
     * @param menuPermissionList 源list
     * @param permission 要去除的权限对象
     */
    private void remove(List<Permission> menuPermissionList, Permission permission) {
        // 通过临时变量来存储所有的list元素防止漏删
        List<Permission> temp = Lists.newArrayList(menuPermissionList);
        for(int i = 0; i < temp.size(); i++) {
            // 判断有没有子权限要去除
            if(temp.get(i).getParentId().equals(permission.getId())) {
                remove(menuPermissionList,temp.get(i));
            }
        }
        // 去除当前权限
        menuPermissionList.remove(permission);
    }

    @PostMapping("/{id:\\d+}/edit")
    public String permissionEdit(Permission permission) {
        rolePermissionService.editPermission(permission);
        transAccount();
        // 刷新权限
        customerFilterChainDefinition.updateUrlPermission();
        return "redirect:/manage/permission";
    }


}
