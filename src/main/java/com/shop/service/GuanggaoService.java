package com.shop.service;

import com.shop.entity.Guanggao;
import com.shop.mapper.GuanggaoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

// 广告业务层，处理广告相关的业务逻辑
@Service
public class GuanggaoService {

    // 注入广告Mapper，用于操作广告表数据
    private final GuanggaoMapper guanggaoMapper;

    // 构造方法注入GuanggaoMapper，Spring会自动注入
    public GuanggaoService(GuanggaoMapper guanggaoMapper) {
        this.guanggaoMapper = guanggaoMapper;
    }

    // 查询全部广告
    public List<Guanggao> chaxunQuanbu() {
        return guanggaoMapper.chaxunQuanbu();
    }

    // 根据id查询广告
    public Guanggao chaxunById(Integer id) {
        return guanggaoMapper.chaxunById(id);
    }

    // 新增广告
    public void tianjia(Guanggao ad) {
        guanggaoMapper.tianjia(ad);
    }

    // 修改广告
    public void xiugai(Guanggao ad) {
        guanggaoMapper.xiugai(ad);
    }

    // 根据id删除广告
    public void shanchuById(Integer id) {
        guanggaoMapper.shanchuById(id);
    }
}
