package com.shop.service;

import com.shop.entity.Dingzhi;
import com.shop.mapper.DingzhiMapper;
import org.springframework.stereotype.Service;

import java.util.List;

// 定制需求业务层，处理定制需求相关的业务逻辑
@Service
public class DingzhiService {

    // 注入定制需求Mapper，用于操作定制需求表数据
    private final DingzhiMapper dingzhiMapper;

    // 构造方法注入DingzhiMapper，Spring会自动注入
    public DingzhiService(DingzhiMapper dingzhiMapper) {
        this.dingzhiMapper = dingzhiMapper;
    }

    // 查询全部定制需求
    public List<Dingzhi> chaxunQuanbu() {
        return dingzhiMapper.chaxunQuanbu();
    }

    // 根据id查询定制需求
    public Dingzhi chaxunById(Integer id) {
        return dingzhiMapper.chaxunById(id);
    }

    // 新增定制需求
    public void tianjia(Dingzhi custom) {
        dingzhiMapper.tianjia(custom);
    }

    // 修改定制需求
    public void xiugai(Dingzhi custom) {
        dingzhiMapper.xiugai(custom);
    }

    // 根据id删除定制需求
    public void shanchuById(Integer id) {
        dingzhiMapper.shanchuById(id);
    }
}
