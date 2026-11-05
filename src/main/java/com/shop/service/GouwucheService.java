package com.shop.service;

import com.shop.entity.Gouwuche;
import com.shop.mapper.GouwucheMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 购物车业务层，处理购物车相关的业务逻辑
@Service
public class GouwucheService {

    // 注入购物车Mapper，用于操作购物车表数据
    private final GouwucheMapper gouwucheMapper;

    // 构造方法注入GouwucheMapper，Spring会自动注入
    public GouwucheService(GouwucheMapper gouwucheMapper) {
        this.gouwucheMapper = gouwucheMapper;
    }

    // 查询全部购物车
    public List<Gouwuche> chaxunQuanbu() {
        return gouwucheMapper.chaxunQuanbu();
    }

    // 根据id查询购物车
    public Gouwuche chaxunById(Integer id) {
        return gouwucheMapper.chaxunById(id);
    }

    // 根据用户id查询购物车（含商品信息）
    public List<Gouwuche> chaxunByYonghuId(Integer userId) {
        return gouwucheMapper.chaxunByYonghuId(userId);
    }

    // 加入购物车：如果商品已在购物车则数量+1，否则新增
    public void jiaruGouwuche(Integer userId, Integer productId) {
        // 先查询该商品是否已在购物车中
        Gouwuche exist = gouwucheMapper.chaxunByYonghuShangpin(userId, productId);
        if (exist != null) {
            // 已存在，数量+1
            exist.setQuantity(exist.getQuantity() + 1);
            gouwucheMapper.gengxinShuliang(exist.getId(), exist.getQuantity());
        } else {
            // 不存在，新增购物车项
            Gouwuche cart = new Gouwuche();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(1);
            cart.setStatus(1);
            cart.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            gouwucheMapper.tianjia(cart);
        }
    }

    // 更新购物车数量
    public void gengxinShuliang(Integer id, Integer quantity) {
        gouwucheMapper.gengxinShuliang(id, quantity);
    }

    // 新增购物车
    public void tianjia(Gouwuche cart) {
        gouwucheMapper.tianjia(cart);
    }

    // 修改购物车
    public void xiugai(Gouwuche cart) {
        gouwucheMapper.xiugai(cart);
    }

    // 根据id删除购物车
    public void shanchuById(Integer id) {
        gouwucheMapper.shanchuById(id);
    }

    // 根据用户id清空购物车（下单后调用）
    public void qingkongByYonghuId(Integer userId) {
        gouwucheMapper.qingkongByYonghuId(userId);
    }
}
