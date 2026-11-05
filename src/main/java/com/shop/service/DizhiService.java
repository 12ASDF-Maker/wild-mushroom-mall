package com.shop.service;

import com.shop.entity.Dizhi;
import com.shop.mapper.DizhiMapper;
import org.springframework.stereotype.Service;

import java.util.List;

// 用户地址业务层，处理用户地址相关的业务逻辑
@Service
public class DizhiService {

    // 注入用户地址Mapper，用于操作用户地址表数据
    private final DizhiMapper dizhiMapper;

    // 构造方法注入DizhiMapper，Spring会自动注入
    public DizhiService(DizhiMapper dizhiMapper) {
        this.dizhiMapper = dizhiMapper;
    }

    // 根据用户id查询该用户的所有地址
    public List<Dizhi> chaxunByYonghuId(Integer userId) {
        return dizhiMapper.chaxunByYonghuId(userId);
    }

    // 根据地址id查询地址信息
    public Dizhi chaxunById(Integer id) {
        return dizhiMapper.chaxunById(id);
    }

    // 新增地址
    public void tianjia(Dizhi dizhi) {
        dizhiMapper.tianjia(dizhi);
    }

    // 修改地址
    public void xiugai(Dizhi dizhi) {
        dizhiMapper.xiugai(dizhi);
    }

    // 根据id删除地址
    public void shanchuById(Integer id) {
        dizhiMapper.shanchuById(id);
    }
}
