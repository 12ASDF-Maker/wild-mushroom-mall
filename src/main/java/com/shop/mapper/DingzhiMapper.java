package com.shop.mapper;

import com.shop.entity.Dingzhi;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 定制需求Mapper接口，用于操作定制需求表数据
@Mapper
public interface DingzhiMapper {

    // 查询全部定制需求信息
    List<Dingzhi> chaxunQuanbu();

    // 根据定制需求id查询定制需求信息
    Dingzhi chaxunById(Integer id);

    // 新增定制需求信息
    int tianjia(Dingzhi entity);

    // 修改定制需求信息
    int xiugai(Dingzhi entity);

    // 根据定制需求id删除定制需求信息
    int shanchuById(Integer id);
}
