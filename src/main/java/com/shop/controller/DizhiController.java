package com.shop.controller;

import com.shop.entity.Dizhi;
import com.shop.entity.Yonghu;
import com.shop.service.DizhiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 用户地址管理控制器，处理用户收货地址的增删查操作
@Controller
public class DizhiController {

    // 注入用户地址业务层
    private final DizhiService dizhiService;

    // 构造方法注入DizhiService，Spring会自动注入
    public DizhiController(DizhiService dizhiService) {
        this.dizhiService = dizhiService;
    }

    // 查询当前用户的所有收货地址，跳转到地址列表页面
    @GetMapping("/dizhi/list")
    public String list(HttpSession session, Model model) {
        // 从session获取当前登录用户
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            // 未登录，跳转到登录页
            return "redirect:/denglu";
        }
        // 查询当前用户的所有地址
        List<Dizhi> dizhiList = dizhiService.chaxunByYonghuId(user.getId());
        // 将地址列表存入model，供页面展示
        model.addAttribute("list", dizhiList);
        return "dizhi_liebiao";
    }

    // 跳转到新增地址页面，返回dizhi_bianji模板
    @GetMapping("/dizhi/add")
    public String add(Model model) {
        // 创建空地址对象，供页面表单绑定
        model.addAttribute("dizhi", new Dizhi());
        return "dizhi_bianji";
    }

    // 保存地址，id为null时执行新增，否则执行修改
    @PostMapping("/dizhi/save")
    public String save(@ModelAttribute Dizhi dizhi, HttpSession session) {
        // 从session获取当前登录用户，并设置地址所属用户id
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            return "redirect:/denglu";
        }
        dizhi.setUserId(user.getId());
        if (dizhi.getId() == null) {
            // id为空，执行新增；新增时设置创建时间为当前时间字符串
            dizhi.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            // 如果默认地址未设置，默认为非默认
            if (dizhi.getIsDefault() == null) {
                dizhi.setIsDefault(0);
            }
            dizhiService.tianjia(dizhi);
        } else {
            // id不为空，执行修改
            dizhiService.xiugai(dizhi);
        }
        // 重定向到地址列表页面
        return "redirect:/dizhi/list";
    }

    // 根据id删除地址
    @GetMapping("/dizhi/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // 调用业务层根据id删除地址
        dizhiService.shanchuById(id);
        // 重定向到地址列表页面
        return "redirect:/dizhi/list";
    }
}
