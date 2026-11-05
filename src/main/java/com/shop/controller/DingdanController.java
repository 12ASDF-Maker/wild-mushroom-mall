package com.shop.controller;

import com.shop.entity.Dingdan;
import com.shop.entity.Dizhi;
import com.shop.entity.Yonghu;
import com.shop.service.DingdanService;
import com.shop.service.DizhiService;
import com.shop.service.GouwucheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 订单管理控制器，处理订单的增删改查及支付流程
@Controller
public class DingdanController {

    // 注入订单业务层
    private final DingdanService dingdanService;
    // 注入购物车业务层，用于下单后清空购物车
    private final GouwucheService gouwucheService;
    // 注入地址业务层，用于保存用户地址
    private final DizhiService dizhiService;

    // 构造方法注入，Spring会自动注入
    public DingdanController(DingdanService dingdanService, GouwucheService gouwucheService, DizhiService dizhiService) {
        this.dingdanService = dingdanService;
        this.gouwucheService = gouwucheService;
        this.dizhiService = dizhiService;
    }

    // ========== 后台管理API ==========

    // 查询全部订单，跳转到后台订单列表页面
    @GetMapping("/dingdan/list")
    public String list(Model model) {
        List<Dingdan> orderList = dingdanService.chaxunQuanbu();
        model.addAttribute("list", orderList);
        return "dingdan_liebiao";
    }

    // 跳转到新增订单页面
    @GetMapping("/dingdan/add")
    public String add(Model model) {
        model.addAttribute("order", new Dingdan());
        return "dingdan_bianji";
    }

    // 跳转到编辑订单页面
    @GetMapping("/dingdan/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Dingdan order = dingdanService.chaxunById(id);
        model.addAttribute("order", order);
        return "dingdan_bianji";
    }

    // 保存订单（后台管理用），id为null时新增，否则修改
    @PostMapping("/dingdan/save")
    public String save(@ModelAttribute Dingdan order) {
        if (order.getId() == null) {
            dingdanService.tianjia(order);
        } else {
            dingdanService.xiugai(order);
        }
        return "redirect:/dingdan/list";
    }

    // 根据id删除订单（后台管理用）
    @GetMapping("/dingdan/delete/{id}")
    public String delete(@PathVariable Integer id) {
        dingdanService.shanchuById(id);
        return "redirect:/dingdan/list";
    }

    // 修改订单状态（后台管理用）
    @GetMapping("/dingdan/status/{id}")
    public String xiugaiZhuangtai(@PathVariable Integer id, @RequestParam String status) {
        dingdanService.xiugaiZhuangtai(id, status);
        return "redirect:/dingdan/list";
    }

    // ========== 前台API ==========

    // 创建订单（前台去结算提交），下单后清空购物车，可选保存地址
    @PostMapping("/dingdan/create")
    public String createOrder(@RequestParam Double totalAmount,
                              @RequestParam String receiverName,
                              @RequestParam String receiverPhone,
                              @RequestParam String receiverAddress,
                              @RequestParam(required = false) String saveAddr,
                              HttpSession session) {
        // 创建订单对象
        Dingdan order = new Dingdan();
        order.setTotalAmount(totalAmount);
        order.setReceiverName(receiverName);
        order.setReceiverPhone(receiverPhone);
        order.setReceiverAddress(receiverAddress);
        order.setStatus("待付款");
        // 从session获取当前登录用户id
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user != null) {
            order.setUserId(user.getId());
        }
        // 调用业务层创建订单
        dingdanService.tianjia(order);
        // 如果用户勾选了"保存此地址"，则保存到地址表
        if (saveAddr != null && user != null) {
            Dizhi dizhi = new Dizhi();
            dizhi.setUserId(user.getId());
            dizhi.setReceiverName(receiverName);
            dizhi.setReceiverPhone(receiverPhone);
            dizhi.setReceiverAddress(receiverAddress);
            dizhi.setIsDefault(0);
            dizhi.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            dizhiService.tianjia(dizhi);
        }
        // 下单成功后清空购物车
        if (user != null) {
            gouwucheService.qingkongByYonghuId(user.getId());
        }
        // 跳转到支付页面，带上订单编号和金额
        return "redirect:/dingdan/pay?orderNo=" + order.getOrderNo() + "&amount=" + totalAmount;
    }

    // 取消订单（前台）
    @GetMapping("/dingdan/cancel/{id}")
    public String cancelOrder(@PathVariable Integer id) {
        dingdanService.xiugaiZhuangtai(id, "已取消");
        return "redirect:/wodeDingdan";
    }

    // 确认收货（前台）
    @GetMapping("/dingdan/confirm/{id}")
    public String confirmReceive(@PathVariable Integer id) {
        dingdanService.xiugaiZhuangtai(id, "已完成");
        return "redirect:/wodeDingdan";
    }

    // 付款成功更新状态（前台），支付成功后状态改为待发货，直接跳转我的订单
    @GetMapping("/dingdan/paySuccess")
    public String paySuccess(@RequestParam String orderNo) {
        Dingdan order = dingdanService.chaxunByDingdanNo(orderNo);
        if (order != null) {
            dingdanService.xiugaiZhuangtai(order.getId(), "待发货");
        }
        // 支付成功后直接跳转到我的订单页面
        return "redirect:/wodeDingdan";
    }
}
