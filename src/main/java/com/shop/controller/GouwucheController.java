package com.shop.controller;

import com.shop.entity.Gouwuche;
import com.shop.entity.Yonghu;
import com.shop.service.GouwucheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

// 购物车管理控制器，处理购物车的增删改查及前台操作
@Controller
public class GouwucheController {

    // 注入购物车业务层
    private final GouwucheService gouwucheService;

    // 构造方法注入GouwucheService，Spring会自动注入
    public GouwucheController(GouwucheService gouwucheService) {
        this.gouwucheService = gouwucheService;
    }

    // ========== 后台管理API ==========

    // 查询全部购物车，跳转到购物车列表页面
    @GetMapping("/gouwuche/list")
    public String list(Model model) {
        List<Gouwuche> cartList = gouwucheService.chaxunQuanbu();
        model.addAttribute("list", cartList);
        return "gouwuche_liebiao";
    }

    // 跳转到新增购物车页面
    @GetMapping("/gouwuche/add")
    public String add(Model model) {
        model.addAttribute("cart", new Gouwuche());
        return "gouwuche_bianji";
    }

    // 跳转到编辑购物车页面
    @GetMapping("/gouwuche/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Gouwuche cart = gouwucheService.chaxunById(id);
        model.addAttribute("cart", cart);
        return "gouwuche_bianji";
    }

    // 保存购物车，id为null时新增，否则修改
    @PostMapping("/gouwuche/save")
    public String save(@ModelAttribute Gouwuche cart) {
        if (cart.getId() == null) {
            gouwucheService.tianjia(cart);
        } else {
            gouwucheService.xiugai(cart);
        }
        return "redirect:/gouwuche/list";
    }

    // 根据id删除购物车（后台管理用）
    @GetMapping("/gouwuche/delete/{id}")
    public String delete(@PathVariable Integer id) {
        gouwucheService.shanchuById(id);
        return "redirect:/gouwuche/list";
    }

    // ========== 前台API ==========

    // 前台购物车页面，从数据库加载当前用户的购物车
    @GetMapping("/gouwuche/view")
    public String cartView(HttpSession session, Model model) {
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            // 未登录跳转到登录页
            return "redirect:/denglu";
        }
        // 查询当前用户的购物车（含商品信息）
        List<Gouwuche> cartList = gouwucheService.chaxunByYonghuId(user.getId());
        model.addAttribute("cartList", cartList);
        return "gouwuche_view";
    }

    // 加入购物车（前台）
    @GetMapping("/gouwuche/jiaru")
    public String jiaruGouwuche(@RequestParam Integer productId, HttpSession session) {
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            // 未登录跳转到登录页
            return "redirect:/denglu";
        }
        // 调用业务层加入购物车
        gouwucheService.jiaruGouwuche(user.getId(), productId);
        // 重定向到购物车页面
        return "redirect:/gouwuche/view";
    }

    // 从购物车移除商品（前台）
    @GetMapping("/gouwuche/yichu/{id}")
    public String yichuGouwuche(@PathVariable Integer id) {
        gouwucheService.shanchuById(id);
        return "redirect:/gouwuche/view";
    }

    // 更新购物车数量（前台）
    @GetMapping("/gouwuche/gengxin")
    public String gengxinShuliang(@RequestParam Integer id, @RequestParam Integer quantity) {
        if (quantity != null && quantity > 0) {
            gouwucheService.gengxinShuliang(id, quantity);
        }
        return "redirect:/gouwuche/view";
    }
}
