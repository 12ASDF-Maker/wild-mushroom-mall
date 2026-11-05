package com.shop.controller;

import com.shop.entity.Dingzhi;
import com.shop.service.DingzhiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 定制需求管理控制器，处理定制需求的增删改查及回复操作
@Controller
public class DingzhiController {

    // 注入定制需求业务层
    private final DingzhiService dingzhiService;

    // 构造方法注入DingzhiService，Spring会自动注入
    public DingzhiController(DingzhiService dingzhiService) {
        this.dingzhiService = dingzhiService;
    }

    // 查询全部定制需求，跳转到定制需求列表页面
    @GetMapping("/dingzhi/list")
    public String list(Model model) {
        // 查询全部定制需求
        List<Dingzhi> customList = dingzhiService.chaxunQuanbu();
        // 将定制需求列表存入model，供页面展示
        model.addAttribute("list", customList);
        return "dingzhi_liebiao";
    }

    // 跳转到新增定制需求页面
    @GetMapping("/dingzhi/add")
    public String add(Model model) {
        // 创建空定制需求对象，供页面表单绑定
        model.addAttribute("custom", new Dingzhi());
        return "dingzhi_bianji";
    }

    // 跳转到编辑定制需求页面，根据id查询定制需求信息回显
    @GetMapping("/dingzhi/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // 根据id查询定制需求
        Dingzhi custom = dingzhiService.chaxunById(id);
        // 将定制需求信息存入model，供页面回显
        model.addAttribute("custom", custom);
        return "dingzhi_bianji";
    }

    // 保存定制需求，id为null时执行新增，否则执行修改
    @PostMapping("/dingzhi/save")
    public String save(@ModelAttribute Dingzhi custom) {
        if (custom.getId() == null) {
            // id为空，调用新增方法；新增时设置创建时间和默认状态
            custom.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if (custom.getStatus() == null || custom.getStatus().isEmpty()) {
                custom.setStatus("待处理");
            }
            dingzhiService.tianjia(custom);
        } else {
            // id不为空，调用修改方法
            dingzhiService.xiugai(custom);
        }
        // 重定向到定制需求列表页面
        return "redirect:/dingzhi/list";
    }

    // 根据id删除定制需求
    @GetMapping("/dingzhi/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // 调用业务层根据id删除定制需求
        dingzhiService.shanchuById(id);
        // 重定向到定制需求列表页面
        return "redirect:/dingzhi/list";
    }

    // 回复定制需求，根据id查询定制需求，设置回复内容后更新
    @PostMapping("/dingzhi/reply/{id}")
    public String reply(@PathVariable Integer id, @RequestParam String reply) {
        // 根据id查询定制需求
        Dingzhi custom = dingzhiService.chaxunById(id);
        // 设置回复内容
        custom.setReply(reply);
        // 调用业务层更新定制需求
        dingzhiService.xiugai(custom);
        // 重定向到定制需求列表页面
        return "redirect:/dingzhi/list";
    }
}
