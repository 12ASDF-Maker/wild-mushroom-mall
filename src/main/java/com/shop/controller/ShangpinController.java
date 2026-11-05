package com.shop.controller;

import com.shop.entity.Shangpin;
import com.shop.service.ShangpinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 商品管理控制器，处理商品的增删改查及详情展示
@Controller
public class ShangpinController {

    // 注入商品业务层
    private final ShangpinService shangpinService;

    // 构造方法注入ShangpinService，Spring会自动注入
    public ShangpinController(ShangpinService shangpinService) {
        this.shangpinService = shangpinService;
    }

    // 查询全部商品，跳转到商品列表页面
    @GetMapping("/shangpin/list")
    public String list(Model model) {
        List<Shangpin> productList = shangpinService.chaxunQuanbu();
        model.addAttribute("list", productList);
        return "shangpin_liebiao";
    }

    // 跳转到新增商品页面
    @GetMapping("/shangpin/add")
    public String add(Model model) {
        model.addAttribute("product", new Shangpin());
        return "shangpin_bianji";
    }

    // 跳转到编辑商品页面
    @GetMapping("/shangpin/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Shangpin product = shangpinService.chaxunById(id);
        model.addAttribute("product", product);
        return "shangpin_bianji";
    }

    // 保存商品，id为null时新增，否则修改
    @PostMapping("/shangpin/save")
    public String save(@ModelAttribute Shangpin product) {
        if (product.getId() == null) {
            shangpinService.tianjia(product);
        } else {
            shangpinService.xiugai(product);
        }
        return "redirect:/shangpin/list";
    }

    // 根据id删除商品
    @GetMapping("/shangpin/delete/{id}")
    public String delete(@PathVariable Integer id) {
        shangpinService.shanchuById(id);
        return "redirect:/shangpin/list";
    }

    // ========== 前台API ==========

    // 商品详情页，根据id查询商品信息并展示
    @GetMapping("/shangpin/xiangqing/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Shangpin product = shangpinService.chaxunById(id);
        model.addAttribute("product", product);
        return "shangpin_xiangqing";
    }
}
