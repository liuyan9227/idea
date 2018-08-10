package com.kaishengit.erp.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuyan
 * @date 2018/8/2
 */
@Controller
public class HomeController {

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
            String url = "/fix";
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
    public String unauthorizedUrl() {
        return "error/401";
    }

}
