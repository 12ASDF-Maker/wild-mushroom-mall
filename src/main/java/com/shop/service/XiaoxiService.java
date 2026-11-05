package com.shop.service;

import com.shop.entity.Xiaoxi;
import com.shop.mapper.XiaoxiMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 站内消息业务层，处理消息相关的业务逻辑
@Service
public class XiaoxiService {

    // 注入消息Mapper，用于操作消息表数据
    private final XiaoxiMapper xiaoxiMapper;

    // 构造方法注入XiaoxiMapper，Spring会自动注入
    public XiaoxiService(XiaoxiMapper xiaoxiMapper) {
        this.xiaoxiMapper = xiaoxiMapper;
    }

    // 查询全部消息
    public List<Xiaoxi> chaxunQuanbu() {
        return xiaoxiMapper.chaxunQuanbu();
    }

    // 根据id查询消息
    public Xiaoxi chaxunById(Integer id) {
        return xiaoxiMapper.chaxunById(id);
    }

    // 查询两个用户之间的聊天记录
    public List<Xiaoxi> chaxunLiaotian(Integer senderId, Integer receiverId) {
        return xiaoxiMapper.chaxunLiaotian(senderId, receiverId);
    }

    // 新增消息，自动设置创建时间
    public void tianjia(Xiaoxi message) {
        // 设置创建时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setCreateTime(sdf.format(new Date()));
        // 如果未读状态为空，默认设为未读0
        if (message.getIsRead() == null) {
            message.setIsRead(0);
        }
        xiaoxiMapper.tianjia(message);
    }

    // 修改消息
    public void xiugai(Xiaoxi message) {
        xiaoxiMapper.xiugai(message);
    }

    // 根据id删除消息
    public void shanchuById(Integer id) {
        xiaoxiMapper.shanchuById(id);
    }
}
