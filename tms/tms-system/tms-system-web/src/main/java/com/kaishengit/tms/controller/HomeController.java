package com.kaishengit.tms.controller;

import com.kaishengit.tms.util.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuyan
 * @date 2018/8/30
 */
@Controller
public class HomeController {

    /**
     * 进入登录页面
     */
    @GetMapping("/")
    public String login(){
        Subject subject = SecurityUtils.getSubject();

        /*System.out.println("isAuthenticated()?" + subject.isAuthenticated());
        System.out.println("isRemembered()?" + subject.isRemembered());*/

        //判断当前是否有已经认证的账号，如果有，则退出该账号
        if(subject.isAuthenticated()) {
            subject.logout();
        }

        if(subject.isRemembered()) {
            //如果当前为被记住（通过rememberMe认证），则直接跳转到登录成功页面 home
            return "redirect:/home";
        }

        return "index";
    }

    /**
     * 登录页面提交表单
     * 1.重连跳转功能
     */
    @PostMapping("/")
    @ResponseBody
    public ResponseBean homePost(String accountMobile, String password, String rememberMe, HttpServletRequest request){
        // 获得对象用于验证
        Subject subject = SecurityUtils.getSubject();
        // 获取登录人员的远程地址,从请求中获得
        String remoteAddr = request.getRemoteAddr();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile,password,rememberMe!=null,remoteAddr);

        try {
            subject.login(usernamePasswordToken);

            SavedRequest savedRequest = WebUtils.getSavedRequest(request);

            String url = "/home";
            if(savedRequest != null){
                url = savedRequest.getRequestURI();
            }
            return ResponseBean.success(url);

        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            return ResponseBean.error("账号或密码错误");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            return ResponseBean.error("账号被锁定");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ResponseBean.error("账号或密码错误");
        }
    }

    /**
     * 登录后的首页
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }


    @GetMapping("/401")
    public String unauthorizedUrl() {
        return "error/401";
    }



}
