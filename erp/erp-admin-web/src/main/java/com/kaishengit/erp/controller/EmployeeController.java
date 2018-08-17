package com.kaishengit.erp.controller;

import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.Role;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.EmployeeService;
import com.kaishengit.erp.service.RolePermissionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/26
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @GetMapping("/")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        // 判断是否通过认证,通过认证则退出登录(项目要求登录页需要重新登录)
        if(subject.isAuthenticated()){
            // 根据业务需要进行此项
            subject.logout();
        }
        // 如果没有通过认证,则需要判断是否记住了密码
        if(subject.isRemembered()){
            // 被记住密码后直接跳到登录主页不需要跳转登录页面
            return "redirect:/";
        }
        return "index";
    }

    @PostMapping("/")
    public String loginPost(HttpServletRequest request, String userTel, String password, RedirectAttributes redirectAttributes){
        // 获取主体帐号
        Subject subject =  SecurityUtils.getSubject();
        // 获取登录IP
        String loginIp = request.getRemoteAddr();
        // 通过userTel、password封装UsernamePasswordToken对象进行登录
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userTel, DigestUtils.md5Hex(password),loginIp);
        try {
            // 进行登录,根据token对象
            subject.login(usernamePasswordToken);

            // 将请求封装在savedRequest对象中,可以提取request中的信息
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            // 设置页面跳转的默认值,斜杠要配在url前面,不能写在redirect后面,获取url时前面会自动带上/斜杠
            String url = "/parts";
            // 如果saveRequest路径不为null时,可能是过期等原因,需要登录跳转到之前访问的页面中
            if(savedRequest != null){
                // 从savedRequest对象中获取访问的路径,重新赋值给URL
                url = savedRequest.getRequestUrl();
            }
            return "redirect:" + url;
        }catch (UnknownAccountException |IncorrectCredentialsException e) {
            redirectAttributes.addFlashAttribute("message", "用户名或者密码错误");
        } catch (LockedAccountException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message", "登录失败");
        }
        return "redirect:/";
    }

    @GetMapping("/401")
    public String error(){
        return "error/401";
    }

    /**
     * 员工信息主页面,搜索功能在本野种使用,采用(电话和帐号混输)和根据(角色信息)联合查找
     * 自写sql语句,采用模糊查询,使用if标签
     */
    @GetMapping("/account/home")
    public String home(@RequestParam(defaultValue = "1", required = false) Integer p,
                       @RequestParam(required = false) Integer nameMobile,
                       @RequestParam(required = false) Integer roleId,
                       Model model){

        Map<String, Object> param = new HashMap<>();
        param.put("nameMobile", nameMobile);
        param.put("roleId", roleId);

        List<Employee> employeeList = employeeService.findEmployeeByLike(p, param);
        List<Role> roleList = rolePermissionService.findRoleAll();

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("roleList", roleList);

        return "manage/account/home";
    }

    @GetMapping("/manage/employee/new")
    public String save(Model model){
        // 回显角色列表
        List<Role> roleList = rolePermissionService.findRoleAll();
        model.addAttribute("roleList", roleList);
        return "manage/account/new";
    }

    @PostMapping("/manage/employee/new")
    public String savePost(Employee employee, Integer[] roleIds){
        // 保存新增页面信息
        employeeService.save(employee, roleIds);
        return "redirect:/account/home";
    }

    /**
     * 业务:修改员工信息
     * 1.回显修改的员工个人信息
     * 2.回显员工的角色信息
     */
    @GetMapping("/manage/employee/{id:\\d+}/edit")
    public String editEmployee(@PathVariable Integer id, Model model){
        System.out.println("获得的员工ID为-=-="+ id);
        // 回显员工信息
        Employee employee = employeeService.findEmployee(id);
        // 查找所有角色信息
        List<Role> roleList = rolePermissionService.findRoleAll();
        // 回显员工的角色信息
        List<Role> employeeRoleList = rolePermissionService.findRoleByEmployeeId(id);
        model.addAttribute("roleList", roleList);
        model.addAttribute("employeeRoleList", employeeRoleList);
        model.addAttribute("employee", employee);
        return "manage/account/edit";
    }

    @PostMapping("/manage/employee/{id:\\d+}/edit")
    public String editEmployeePost(Employee employee, Integer[] roleIds){
        employeeService.edit(employee, roleIds);
        return "redirect:/account/home";
    }

    @ResponseBody
    @GetMapping("/manage/employee/{id:\\d+}/del")
    public ResponseBean del(@PathVariable Integer id){
        try {
            // 删除员工需要删除员工的关联关系表
            employeeService.del(id);
            return ResponseBean.success();
        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }




}
