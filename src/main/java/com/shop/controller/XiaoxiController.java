package com.shop.controller;

import com.shop.entity.Xiaoxi;
import com.shop.entity.Yonghu;
import com.shop.service.XiaoxiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 站内消息管理控制器，处理消息的增删改查操作
@Controller
public class XiaoxiController {

    // 注入消息业务层
    private final XiaoxiService xiaoxiService;

    // 构造方法注入XiaoxiService，Spring会自动注入
    public XiaoxiController(XiaoxiService xiaoxiService) {
        this.xiaoxiService = xiaoxiService;
    }

    // 查询全部消息，跳转到消息列表页面
    @GetMapping("/xiaoxi/list")
    public String list(Model model) {
        List<Xiaoxi> messageList = xiaoxiService.chaxunQuanbu();
        model.addAttribute("list", messageList);
        return "xiaoxi_liebiao";
    }

    // 跳转到新增消息页面
    @GetMapping("/xiaoxi/add")
    public String add(Model model) {
        model.addAttribute("message", new Xiaoxi());
        return "xiaoxi_bianji";
    }

    // 跳转到编辑消息页面
    @GetMapping("/xiaoxi/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Xiaoxi message = xiaoxiService.chaxunById(id);
        model.addAttribute("message", message);
        return "xiaoxi_bianji";
    }

    // 保存消息
    @PostMapping("/xiaoxi/save")
    public String save(@ModelAttribute Xiaoxi message) {
        if (message.getId() == null) {
            xiaoxiService.tianjia(message);
        } else {
            xiaoxiService.xiugai(message);
        }
        return "redirect:/xiaoxi/list";
    }

    // 根据id删除消息
    @GetMapping("/xiaoxi/delete/{id}")
    public String delete(@PathVariable Integer id) {
        xiaoxiService.shanchuById(id);
        return "redirect:/xiaoxi/list";
    }

    // ========== 前台聊天API ==========

    // 加载聊天记录，返回JSON格式
    @GetMapping("/xiaoxi/history")
    @ResponseBody
    public Map<String, Object> loadHistory(@RequestParam Integer receiverId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        // 查询当前用户和对方之间的聊天记录
        List<Xiaoxi> messages = xiaoxiService.chaxunLiaotian(user.getId(), receiverId);
        result.put("success", true);
        result.put("messages", messages);
        // 返回当前用户id，前端用来判断消息方向
        result.put("currentUserId", user.getId());
        return result;
    }

    // 发送消息，保存到数据库并返回JSON
    @PostMapping("/xiaoxi/send")
    @ResponseBody
    public Map<String, Object> sendMessage(@RequestParam Integer receiverId,
                                           @RequestParam String content,
                                           HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Yonghu user = (Yonghu) session.getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        if (content == null || content.trim().isEmpty()) {
            result.put("success", false);
            result.put("msg", "消息内容不能为空");
            return result;
        }
        // 创建消息对象
        Xiaoxi message = new Xiaoxi();
        message.setSenderId(user.getId());
        message.setReceiverId(receiverId);
        message.setContent(content.trim());
        message.setIsRead(0);
        // 保存到数据库
        xiaoxiService.tianjia(message);
        // 返回成功结果和时间
        result.put("success", true);
        result.put("createTime", message.getCreateTime());
        result.put("currentUserId", user.getId());
        return result;
    }
}
