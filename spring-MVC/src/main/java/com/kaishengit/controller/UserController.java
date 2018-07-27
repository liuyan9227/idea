package com.kaishengit.controller;

import com.kaishengit.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/20
 */
//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 内置model对象可向前端传值
     * @param model
     * @return 请求转发
     */
    @GetMapping("/add")
    public String userAdd(Model model){
        model.addAttribute("username","刘岩");
        model.addAttribute("password","123");
        return "/user/add";
    }

    /**
     * 用实体类来接收前端的值
     * @param user
     * @return
     */
    @PostMapping("/add")
    public String  addPost(User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println("/user/add");
        return "redirect:/hello";
    }

    /**
     * 获得地址栏的值,用相同属性值接收
     * @param id 路径中的数字获取
     * @param name 路径中?name=的中文
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String findGet(@PathVariable Integer id, String name){
        System.out.println("id:" + id);
        System.out.println("name:" + name);
        return "hello";
    }

    @GetMapping("/{type:v-.+}/{id:\\d+}")
    public ModelAndView findPost(
            // 将p值默认为1
            @RequestParam(defaultValue = "1") Integer p,
            @PathVariable String type,
            @PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("/hello");
        modelAndView.setViewName("/hello");
        modelAndView.addObject("username", "刘岩");
        modelAndView.addObject("password", "123");
        System.out.println("id:" + id);
        System.out.println("type:" + type);
        System.out.println("p:" + p);
        return modelAndView;
        //return "redirect:/hello";
    }

    // 使用produces属性设置MIME类型
    @GetMapping(value = "/save", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(){
        System.out.println("使用@ResponseBody向视图返回字符串");
        return "成功";
    }

    @GetMapping("/{id:\\d+}.jackson")
    @ResponseBody
    public User jackson(@PathVariable Integer id){
        User user = new User();
        System.out.println("id:" + id);
        user.setUsername("加菲");
        user.setPassword("66666");
        return user;
    }

    @GetMapping("/list.jackson")
    @ResponseBody
    public List<User> jacksonList(){
        List<User> userList = Arrays.asList(
                new User("哈哈", "123"),
                new User("啦啦", "456"),
                new User("吼吼", "789")
        );
        return userList;
    }


    @GetMapping("/cookie")
    public String admin(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user){
        Cookie cookie = new Cookie("username", user.getUsername());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*7);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return "redirect:/hello";
    }



}
