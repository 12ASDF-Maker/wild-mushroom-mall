package com.shop.service;

import com.shop.entity.Shangpin;
import com.shop.mapper.ShangpinMapper;
import org.springframework.stereotype.Service;

import java.util.List;

// 商品业务层，处理商品相关的业务逻辑
@Service
public class ShangpinService {

    // 注入商品Mapper，用于操作商品表数据
    private final ShangpinMapper shangpinMapper;

    // 构造方法注入ShangpinMapper，Spring会自动注入
    public ShangpinService(ShangpinMapper shangpinMapper) {
        this.shangpinMapper = shangpinMapper;
    }

    // 查询全部商品
    public List<Shangpin> chaxunQuanbu() {
        return shangpinMapper.chaxunQuanbu();
    }

    // 查询上架商品
    public List<Shangpin> chaxunShangjia() {
        return shangpinMapper.chaxunShangjia();
    }

    // 根据分类查询上架商品
    public List<Shangpin> chaxunByFenlei(String category) {
        return shangpinMapper.chaxunByFenlei(category);
    }

    // 根据id查询商品
    public Shangpin chaxunById(Integer id) {
        return shangpinMapper.chaxunById(id);
    }

    // 新增商品
    public void tianjia(Shangpin product) {
        shangpinMapper.tianjia(product);
    }

    // 修改商品
    public void xiugai(Shangpin product) {
        shangpinMapper.xiugai(product);
    }

    // 根据id删除商品
    public void shanchuById(Integer id) {
        shangpinMapper.shanchuById(id);
    }

    // 减少库存
    public boolean jianshaoKucun(Integer id, Integer quantity) {
        int rows = shangpinMapper.jianshaoKucun(id, quantity);
        return rows > 0;
    }

    // 增加库存
    public void zengjiaKucun(Integer id, Integer quantity) {
        shangpinMapper.zengjiaKucun(id, quantity);
    }
}
