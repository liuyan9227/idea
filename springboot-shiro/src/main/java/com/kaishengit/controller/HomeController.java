package com.kaishengit.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author liuyan
 * @date 2018/8/25
 */
@Controller
public class HomeController {

    /**
     * 跳转到登录页面
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 业务:登录页面进行post提交,将登录信息进行认证
     */
    @PostMapping("/home")
    public String shiro(String name, String password, RedirectAttributes redirectAttributes){
        // 1.获得安全的主题对象,用于验证账号密码
        Subject subject = SecurityUtils.getSubject();
        // 3.创建token(固定),并将信息封装在token中进行shiro验证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        try {
            // 2.将信息进行验证,需要传入token
            subject.login(usernamePasswordToken);
            // 4.验证成功跳转/home
            return "redirect:/home";
        } catch (AuthenticationException e){
            e.printStackTrace();
            // 将错误信息展示在页面
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            // 5.验证失败跳转/登录页面
            return "redirect:/temp";
        }
    }


}
