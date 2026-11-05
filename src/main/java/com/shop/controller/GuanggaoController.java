package com.shop.controller;

import com.shop.entity.Guanggao;
import com.shop.service.GuanggaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 广告管理控制器，处理广告的增删改查操作
@Controller
public class GuanggaoController {

    // 注入广告业务层
    private final GuanggaoService guanggaoService;

    // 构造方法注入GuanggaoService，Spring会自动注入
    public GuanggaoController(GuanggaoService guanggaoService) {
        this.guanggaoService = guanggaoService;
    }

    // 查询全部广告，跳转到广告列表页面
    @GetMapping("/guanggao/list")
    public String list(Model model) {
        // 查询全部广告
        List<Guanggao> adList = guanggaoService.chaxunQuanbu();
        // 将广告列表存入model，供页面展示
        model.addAttribute("list", adList);
        return "guanggao_liebiao";
    }

    // 跳转到新增广告页面
    @GetMapping("/guanggao/add")
    public String add(Model model) {
        // 创建空广告对象，供页面表单绑定
        model.addAttribute("ad", new Guanggao());
        return "guanggao_bianji";
    }

    // 跳转到编辑广告页面，根据id查询广告信息回显
    @GetMapping("/guanggao/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // 根据id查询广告
        Guanggao ad = guanggaoService.chaxunById(id);
        // 将广告信息存入model，供页面回显
        model.addAttribute("ad", ad);
        return "guanggao_bianji";
    }

    // 保存广告，id为null时执行新增，否则执行修改
    @PostMapping("/guanggao/save")
    public String save(@ModelAttribute Guanggao ad) {
        if (ad.getId() == null) {
            // id为空，调用新增方法
            guanggaoService.tianjia(ad);
        } else {
            // id不为空，调用修改方法
            guanggaoService.xiugai(ad);
        }
        // 重定向到广告列表页面
        return "redirect:/guanggao/list";
    }

    // 根据id删除广告
    @GetMapping("/guanggao/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // 调用业务层根据id删除广告
        guanggaoService.shanchuById(id);
        // 重定向到广告列表页面
        return "redirect:/guanggao/list";
    }
}
