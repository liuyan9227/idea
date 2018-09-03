package com.kaishengit.tms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;
import com.kaishengit.tms.util.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.获取登录人员信息并传到impl做日志记录
 * 2.对controller获取thymeleaf的参数首先进行校验
 * 3.不做任何业务逻辑
 * @author liuyan
 * @date 2018/8/31
 */
@Controller
@RequestMapping("/manage/roles")
public class RolesController {

    @Reference(version = "1.0")
    private RolePermissionService rolePermissionService;


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
     * (角色)页面显示所有角色信息和对应的权限信息
     */
    @GetMapping
    public String home(Model model){
        List<Roles> rolesList = rolePermissionService.findAllRolesWithPermission();
        model.addAttribute("rolesList", rolesList);
        return "manage/roles/home";
    }

    /**
     * 跳转->添加角色页面
     * 1.展示所有权限信息
     */
    @GetMapping("/new")
    public String save(Model model){
        transAccount();
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList", permissionList);
        return "manage/roles/new";
    }

    /**
     * 保存新增角色信息
     */
    @PostMapping("/new")
    public String savePost(Roles roles, Integer[] permissionId, RedirectAttributes redirectAttributes){
        rolePermissionService.saveRoles(roles, permissionId);
        redirectAttributes.addFlashAttribute("message", "保存成功");
        return "redirect:/manage/roles";
    }

    /**
     * 删除角色信息
     */
    @ResponseBody
    @GetMapping("/{id:\\d+}/del")
    public ResponseBean rolesDel(@PathVariable Integer id){
        if(id == null || id < 0){
            throw new ServiceException("没有获取的角色id");
        }

        transAccount();

        try {
            rolePermissionService.delRolesById(id);
            return ResponseBean.success();
        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }

    /**
     * 跳转->修改页面
     * 1.回显修改信息 roles, permissionMap
     */
    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){
        Roles roles = rolePermissionService.findRolesWithPermissionByRoleId(id);

        if(roles == null){
            throw new NotFoundException();
        }

        List<Permission> permissionList = rolePermissionService.findAllPermission();

        Map<Permission,Boolean> permissionMap = checkdPermissionList(roles.getPermissionList(),permissionList);

        model.addAttribute("roles", roles);
        model.addAttribute("permissionMap", permissionMap);
        return "manage/roles/edit";
    }

    /**
     * 根据状态来显示选中的权限信息
     * @param permissionList 选中的权限信息
     * @param permissionListAll 全部的权限信息
     * @return 有顺序的Map,选中的:true
     */
    private Map<Permission,Boolean> checkdPermissionList(List<Permission> permissionList, List<Permission> permissionListAll) {
        Map<Permission, Boolean> permissionMap = Maps.newLinkedHashMap();

        // 将所有的权限包裹循环
        for(Permission permissionAll : permissionListAll){
            boolean flag = false;
            for(Permission permission : permissionList){
                if(permissionAll.getId().equals(permission.getId())){
                    flag = true;
                    break;
                }
            }
            permissionMap.put(permissionAll, flag);
        }
        return permissionMap;
    }

    /**
     * 修改角色信息,提交表单
     * @param roles
     * @param permissionId
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/{id:\\d+}/edit")
    public String deitPost(Roles roles,Integer[] permissionId, RedirectAttributes redirectAttributes) {
        rolePermissionService.updateRoles(roles,permissionId);

        transAccount();

        redirectAttributes.addFlashAttribute("message","角色修改成功");
        return "redirect:/manage/roles";
    }

}
