package com.shop.controller;

import com.shop.entity.Guanggao;
import com.shop.entity.Dingdan;
import com.shop.entity.Dizhi;
import com.shop.entity.Gouwuche;
import com.shop.entity.Shangpin;
import com.shop.entity.Yonghu;
import com.shop.service.GuanggaoService;
import com.shop.service.DingdanService;
import com.shop.service.DizhiService;
import com.shop.service.GouwucheService;
import com.shop.service.ShangpinService;
import com.shop.service.YonghuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

// 首页控制器，处理首页、后台管理首页和前台页面跳转
@Controller
public class ShouyeController {

    private final ShangpinService shangpinService;
    private final DingdanService dingdanService;
    private final GuanggaoService guanggaoService;
    private final GouwucheService gouwucheService;
    private final YonghuService yonghuService;
    private final DizhiService dizhiService;

    public ShouyeController(ShangpinService shangpinService, DingdanService dingdanService,
                           GuanggaoService guanggaoService, GouwucheService gouwucheService,
                           YonghuService yonghuService, DizhiService dizhiService) {
        this.shangpinService = shangpinService;
        this.dingdanService = dingdanService;
        this.guanggaoService = guanggaoService;
        this.gouwucheService = gouwucheService;
        this.yonghuService = yonghuService;
        this.dizhiService = dizhiService;
    }

    // 查询后台管理首页所需的统计数据，并存入model
    private void chaxunTongji(Model model) {
        // 用户总数
        List<Yonghu> yonghuList = yonghuService.chaxunQuanbu();
        int yonghuZongshu = yonghuList.size();

        // 商品总数
        List<Shangpin> shangpinList = shangpinService.chaxunQuanbu();
        int shangpinZongshu = shangpinList.size();

        // 订单总数
        List<Dingdan> dingdanList = dingdanService.chaxunQuanbu();
        int dingdanZongshu = dingdanList.size();

        // 销售总额 = 所有订单的total_amount之和
        double xiaoshouZonge = 0.0;
        for (Dingdan order : dingdanList) {
            if (order.getTotalAmount() != null) {
                xiaoshouZonge += order.getTotalAmount();
            }
        }

        // 上架商品数 = status=1的商品数
        int shangjiaShangpin = 0;
        // 库存总量 = 所有商品的stock之和
        int kucunZongliang = 0;
        for (Shangpin product : shangpinList) {
            if (product.getStatus() != null && product.getStatus() == 1) {
                shangjiaShangpin++;
            }
            if (product.getStock() != null) {
                kucunZongliang += product.getStock();
            }
        }

        // 将统计数据存入model，属性名与模板变量一致
        model.addAttribute("userCount", yonghuZongshu);
        model.addAttribute("productCount", shangpinZongshu);
        model.addAttribute("orderCount", dingdanZongshu);
        model.addAttribute("totalAmount", xiaoshouZonge);
        model.addAttribute("onlineCount", shangjiaShangpin);
        model.addAttribute("totalStock", kucunZongliang);
    }

    // 访问首页，返回shouye模板页面
    @GetMapping("/")
    public String index(Model model) {
        // 从数据库查询上架商品，首页热门推荐最多显示12个
        List<Shangpin> products = shangpinService.chaxunShangjia();
        if (products.size() > 12) {
            products = products.subList(0, 12);
        }
        model.addAttribute("products", products);
        // 查询上架广告
        List<Guanggao> ads = guanggaoService.chaxunQuanbu();
        model.addAttribute("ads", ads);
        // 查询后台管理首页所需的统计数据（首页也可显示统计）
        chaxunTongji(model);
        return "shouye";
    }

    // 跳转到商品分类页
    @GetMapping("/fenlei")
    public String category(@RequestParam(required = false) String cat, Model model) {
        List<Shangpin> products;
        String currentCat = "全部野生菌";
        if (cat != null && !cat.isEmpty() && !"全部野生菌".equals(cat)) {
            products = shangpinService.chaxunByFenlei(cat);
            currentCat = cat;
        } else {
            products = shangpinService.chaxunShangjia();
        }
        model.addAttribute("products", products);
        model.addAttribute("currentCat", currentCat);
        return "shangpin_fenlei";
    }

    // 跳转到我的订单页，从数据库读取当前用户的订单
    @GetMapping("/wodeDingdan")
    public String myOrders(HttpSession session, Model model) {
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user != null) {
            List<Dingdan> orders = dingdanService.chaxunByYonghuId(user.getId());
            model.addAttribute("orders", orders);
        }
        return "wode_dingdan";
    }

    // 跳转到订单确认页（去结算），加载购物车数据和用户地址
    @GetMapping("/dingdan/confirm")
    public String orderConfirm(HttpSession session, Model model) {
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            return "redirect:/denglu";
        }
        // 查询当前用户购物车数据
        List<Gouwuche> cartList = gouwucheService.chaxunByYonghuId(user.getId());
        model.addAttribute("cartList", cartList);
        // 查询当前用户已保存的收货地址
        List<Dizhi> addressList = dizhiService.chaxunByYonghuId(user.getId());
        model.addAttribute("addressList", addressList);
        return "dingdan_queren";
    }

    // 跳转到支付页，将订单号和金额传入页面
    @GetMapping("/dingdan/pay")
    public String orderPay(@RequestParam(required = false) String orderNo,
                           @RequestParam(required = false) String amount,
                           Model model) {
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("amount", amount);
        return "dingdan_zhifu";
    }

    // 跳转到私信聊天页（联系卖家）
    @GetMapping("/xiaoxi/liaotian")
    public String messageChat() {
        return "xiaoxi_liaotian";
    }

    // 后台管理首页，返回houtai_shouye模板，并展示统计数据
    @GetMapping("/houtai")
    public String houtai(Model model) {
        // 查询后台管理首页所需的统计数据并存入model
        chaxunTongji(model);
        return "houtai_shouye";
    }
}
