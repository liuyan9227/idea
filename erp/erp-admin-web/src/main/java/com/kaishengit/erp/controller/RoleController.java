package com.kaishengit.erp.controller;

import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Permission;
import com.kaishengit.erp.entity.Role;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/27
 */
@Controller
@RequestMapping("/manage/role")
public class RoleController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model){
        // 查找内容显示在页面
        List<Role> roleList = rolePermissionService.findRoleAll();
        model.addAttribute("roleList", roleList);
        return "manage/role/home";
    }

    @GetMapping("/new")
    public String newRole(Model model){
        // 查询并显示所有权限类型,并显示在new页面上
        List<Permission> permissionList = rolePermissionService.findPermissionAll();
        model.addAttribute("permissionList", permissionList);
        return "manage/role/new";
    }

    @PostMapping("/new")
    public String newRolePost(Role role, Integer[] permissionId){
        // 获取表单提交的值, 复选框可以选择多个按钮, 所以用数组来接收
        rolePermissionService.save(role, permissionId);
        // 表单提交都需要重定向到home页面,谨防表单重复提交
        return "redirect:/manage/role";
    }

    /*@GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean delPermission(@PathVariable Integer id){
        try{
            // 将权限id进行查询是否在使用中,再删除
            rolePermissionService.delPermission(id);
            return ResponseBean.success();
        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }*/


    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean delRole(@PathVariable Integer id){
        try{
            // 删除角色信息,获取的是roleId
            rolePermissionService.delRole(id);
            return ResponseBean.success();
        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edirRole(@PathVariable Integer id, Model model){
        // 回显需要role和permission信息
        Role role = rolePermissionService.findRoleById(id);
        if(role == null){
            throw new ServiceException("未找到角色信息");
        }
        // 回显一个Map集合,存放所有权限信息,将此roleId选中过的权限的状态改为true,传入所有已选中的权限
        Map<Permission, Boolean> permissionBooleanMap = rolePermissionService.findPermissionWithBoolean(role.getPermissionList());

        model.addAttribute("role", role);
        model.addAttribute("permissionBooleanMap", permissionBooleanMap);
        return "manage/role/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edirRole(Role roleEdit , Integer[] permissionId){
        // 获取需要更改的role的信息,返回更改后的对象传入前端
        rolePermissionService.editRole(roleEdit);
        // 删除原有的permission的信息
        rolePermissionService.delPermissionByRoleId(roleEdit);
        // 获取选中的需要更改的permission的信息
        rolePermissionService.updetePermissionMap(roleEdit, permissionId);

        return "redirect:/manage/role";
    }

}
