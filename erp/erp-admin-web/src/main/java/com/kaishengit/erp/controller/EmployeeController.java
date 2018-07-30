package com.kaishengit.erp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.Role;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.EmployeeService;
import com.kaishengit.erp.service.RolePermissionService;
import com.kaishengit.erp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String Login(){
        return "index";
    }

    @PostMapping("/")
    public String loginPost(String userTel, String password, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes){
        // 注意 : 所有业务在serviceImpl中完成
        // 获得请求中的ip地址
        String ip = request.getRemoteAddr();

        try {
            // 获取电话和密码查询返回employee对象
            Employee employee = employeeService.findTelAndPassword(userTel, password, ip);
            // 将用户设置在session中
            session.setAttribute("employee", employee);

            redirectAttributes.addFlashAttribute("employee", employee);
            // 重定向到主页面,防止表单重复提交
            return "redirect:/parts/";
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/account/home")
    public String home(@RequestParam(defaultValue = "1", required = false) Integer p, Model model){
        // 分页插件
        PageHelper.startPage(p, Constant.DEFAULT_PAGE_SIZE);
        // 查找所有的员工信息
        List<Employee> employeeList = employeeService.findAll();
        // 将List<employee>对象转换为PageInfo<Employee>对象, 本质还是一个List集合
        PageInfo<Employee> page = new PageInfo<>(employeeList);

        model.addAttribute("employeeList", employeeList);
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

    @GetMapping("/manage/employee/{id:\\d+}/edit")
    public String editEmployee(@PathVariable Integer id, Model model){
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
    public String editEmployeePost(Employee employee, Integer[] id){
        employeeService.save(employee, id);
        return "redirect:/account/home";
    }


}
