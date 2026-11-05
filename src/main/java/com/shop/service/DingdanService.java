package com.shop.service;

import com.shop.entity.Dingdan;
import com.shop.mapper.DingdanMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 订单业务层，处理订单相关的业务逻辑
@Service
public class DingdanService {

    // 注入订单Mapper，用于操作订单表数据
    private final DingdanMapper dingdanMapper;

    // 构造方法注入DingdanMapper，Spring会自动注入
    public DingdanService(DingdanMapper dingdanMapper) {
        this.dingdanMapper = dingdanMapper;
    }

    // 查询全部订单
    public List<Dingdan> chaxunQuanbu() {
        return dingdanMapper.chaxunQuanbu();
    }

    // 根据用户id查询订单
    public List<Dingdan> chaxunByYonghuId(Integer userId) {
        return dingdanMapper.chaxunByYonghuId(userId);
    }

    // 根据订单编号查询订单
    public Dingdan chaxunByDingdanNo(String orderNo) {
        return dingdanMapper.chaxunByDingdanNo(orderNo);
    }

    // 根据id查询订单
    public Dingdan chaxunById(Integer id) {
        return dingdanMapper.chaxunById(id);
    }

    // 新增订单，自动生成订单编号和创建时间
    public void tianjia(Dingdan order) {
        // 生成订单编号，格式为OD+年月日时分秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = "OD" + sdf.format(new Date());
        order.setOrderNo(orderNo);
        // 设置创建时间
        SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(timeSdf.format(new Date()));
        // 如果状态为空，默认设为待付款
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("待付款");
        }
        // 调用Mapper插入订单数据
        dingdanMapper.tianjia(order);
    }

    // 修改订单
    public void xiugai(Dingdan order) {
        dingdanMapper.xiugai(order);
    }

    // 根据id删除订单
    public void shanchuById(Integer id) {
        dingdanMapper.shanchuById(id);
    }

    // 修改订单状态，根据订单id更新订单状态
    public void xiugaiZhuangtai(Integer id, String status) {
        dingdanMapper.xiugaiZhuangtai(id, status);
    }
}
