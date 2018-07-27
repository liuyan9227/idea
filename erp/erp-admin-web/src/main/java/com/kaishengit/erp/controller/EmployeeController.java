package com.kaishengit.erp.controller;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liuyan
 * @date 2018/7/26
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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




}
