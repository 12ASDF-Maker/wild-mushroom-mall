package com.shop.controller;

import com.shop.entity.Yonghu;
import com.shop.service.YonghuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

// 登录注册控制器，处理用户登录、注册、退出登录操作
@Controller
public class DengluController {

    // 注入用户业务层
    private final YonghuService yonghuService;

    // 构造方法注入YonghuService，Spring会自动注入
    public DengluController(YonghuService yonghuService) {
        this.yonghuService = yonghuService;
    }

    // 跳转到登录页面
    @GetMapping("/denglu")
    public String login() {
        return "denglu";
    }

    // 处理登录表单提交
    @PostMapping("/denglu")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        // 调用业务层验证用户名和密码
        Yonghu user = yonghuService.login(username, password);
        if (user != null) {
            // 登录成功，将用户信息存入session
            session.setAttribute("user", user);
            // 如果是管理员，跳到后台用户管理页面，否则跳回首页
            if ("admin".equals(user.getRole())) {
                return "redirect:/yonghu/list";
            }
            return "redirect:/";
        }
        // 登录失败，添加错误提示信息到model
        model.addAttribute("msg", "用户名或密码错误");
        // 返回登录页面
        return "denglu";
    }

    // 跳转到注册页面
    @GetMapping("/zhuce")
    public String register() {
        return "zhuce";
    }

    // 处理注册表单提交
    @PostMapping("/zhuce")
    public String doRegister(@ModelAttribute Yonghu user, Model model) {
        // 调用业务层注册用户
        boolean success = yonghuService.register(user);
        if (success) {
            // 注册成功，重定向到登录页面
            return "redirect:/denglu";
        }
        // 注册失败，用户名已存在，添加错误提示信息到model
        model.addAttribute("msg", "用户名已存在");
        // 返回注册页面
        return "zhuce";
    }

    // 退出登录，清除session后跳转到登录页面
    @GetMapping("/tuichu")
    public String logout(HttpSession session) {
        // 清除session中的用户信息
        session.removeAttribute("user");
        // 重定向到登录页面
        return "redirect:/denglu";
    }
}
