package com.shop.mapper;

import com.shop.entity.Guanggao;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 广告Mapper接口，用于操作广告表数据
@Mapper
public interface GuanggaoMapper {

    // 查询全部广告信息
    List<Guanggao> chaxunQuanbu();

    // 根据广告id查询广告信息
    Guanggao chaxunById(Integer id);

    // 新增广告信息
    int tianjia(Guanggao entity);

    // 修改广告信息
    int xiugai(Guanggao entity);

    // 根据广告id删除广告信息
    int shanchuById(Integer id);
}
