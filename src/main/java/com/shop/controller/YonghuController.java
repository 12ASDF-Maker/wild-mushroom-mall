package com.shop.controller;

import com.shop.entity.Yonghu;
import com.shop.service.YonghuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 用户管理控制器，处理用户的增删改查操作
@Controller
public class YonghuController {

    // 注入用户业务层
    private final YonghuService yonghuService;

    // 构造方法注入YonghuService，Spring会自动注入
    public YonghuController(YonghuService yonghuService) {
        this.yonghuService = yonghuService;
    }

    // 查询全部用户，跳转到用户列表页面
    @GetMapping("/yonghu/list")
    public String list(Model model) {
        // 查询全部用户
        List<Yonghu> userList = yonghuService.chaxunQuanbu();
        // 将用户列表存入model，供页面展示
        model.addAttribute("list", userList);
        return "yonghu_liebiao";
    }

    // 跳转到新增用户页面
    @GetMapping("/yonghu/add")
    public String add(Model model) {
        // 创建空用户对象，供页面表单绑定
        model.addAttribute("user", new Yonghu());
        return "yonghu_bianji";
    }

    // 跳转到编辑用户页面，根据id查询用户信息回显
    @GetMapping("/yonghu/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // 根据id查询用户
        Yonghu user = yonghuService.chaxunById(id);
        // 将用户信息存入model，供页面回显
        model.addAttribute("user", user);
        return "yonghu_bianji";
    }

    // 保存用户，id为null时执行新增，否则执行修改
    @PostMapping("/yonghu/save")
    public String save(@ModelAttribute Yonghu user) {
        if (user.getId() == null) {
            // id为空，调用新增方法
            yonghuService.tianjia(user);
        } else {
            // id不为空，调用修改方法
            yonghuService.xiugai(user);
        }
        // 重定向到用户列表页面
        return "redirect:/yonghu/list";
    }

    // 根据id删除用户
    @GetMapping("/yonghu/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // 调用业务层根据id删除用户
        yonghuService.shanchuById(id);
        // 重定向到用户列表页面
        return "redirect:/yonghu/list";
    }
}
