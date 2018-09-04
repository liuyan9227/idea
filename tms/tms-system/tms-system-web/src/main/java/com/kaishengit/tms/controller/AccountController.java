package com.kaishengit.tms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 账号管理
 * @author liuyan
 * @date 2018/9/3
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {

    @Reference(version = "1.0")
    private RolePermissionService rolePermissionService;

    @Reference(version = "1.0")
    private AccountService accountService;


    /**
     * 封装登录人员信息
     */
    private void transAccount(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        accountService.transAccount(map);
    }

    /**
     * (跳转页面)accouunt首页,并展示所有账号信息
     */
    @GetMapping
    public String home(Model model,
                       @RequestParam(required = false) Integer rolesId,
                       @RequestParam(required = false) String nameMobile) {

        Map<String,Object> requestParam = Maps.newHashMap();
        requestParam.put("nameMobile",nameMobile);
        requestParam.put("rolesId",rolesId);

        List<Account> accountList = accountService.findAllAccountWithRolesByQueryParam(requestParam);
        List<Roles> rolesList = rolePermissionService.findAllRoles();

        model.addAttribute("accountList", accountList);
        model.addAttribute("rolesList", rolesList);
        return "manage/account/home";
    }


    /**
     * 新增帐号
     */
    @GetMapping("/new")
    public String newAccount(Model model) {
        List<Roles> rolesList = rolePermissionService.findAllRoles();

        model.addAttribute("rolesList",rolesList);
        return "manage/account/new";
    }

    @PostMapping("/new")
    public String newAccount(Account account, Integer[] rolesIds) {
        accountService.saveAccount(account,rolesIds);
        transAccount();
        return "redirect:/manage/account";
    }


    /**
     * 修改账号
     */
    @GetMapping("/{id:\\d+}/edit")
    public String updateAccount(@PathVariable Integer id, Model model) {
        Account account = accountService.findById(id);
        if(account == null) {
            throw new NotFoundException();
        }
        //查询所有的角色
        List<Roles> rolesList = rolePermissionService.findAllRoles();
        //当前账号拥有的角色
        List<Roles> accountRolesList = rolePermissionService.findRolesByAccountId(id);

        model.addAttribute("rolesList",checkRolesListIsChecked(rolesList, accountRolesList));
        model.addAttribute("account",account);
        return "manage/account/edit";
    }

    /**
     * 转换为带是否选中的map集合
     * @param rolesList
     * @param accountRolesList
     * @return
     */
    private Map<Roles,Boolean> checkRolesListIsChecked(List<Roles> rolesList,List<Roles> accountRolesList) {
        Map<Roles,Boolean> map = new LinkedHashMap<>();
        for(Roles roles : rolesList) {
            boolean flag = false;
            for (Roles accountRoles : accountRolesList) {
                if(accountRoles.getId().equals(roles.getId())) {
                    flag = true;
                }
            }
            map.put(roles,flag);
        }
        return map;
    }

    @PostMapping("/{id:\\d+}/edit")
    public String updateAccount(Account account, Integer[] rolesIds, RedirectAttributes redirectAttributes) {
        accountService.updateAccount(account,rolesIds);
        transAccount();
        redirectAttributes.addFlashAttribute("message","修改账号成功");
        return "redirect:/manage/account";
    }

}
