package com.shop.mapper;

import com.shop.entity.Dizhi;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 用户地址Mapper接口，用于操作用户地址表数据
@Mapper
public interface DizhiMapper {

    // 根据用户id查询该用户的所有地址
    List<Dizhi> chaxunByYonghuId(Integer userId);

    // 根据地址id查询地址信息
    Dizhi chaxunById(Integer id);

    // 新增地址信息
    int tianjia(Dizhi entity);

    // 修改地址信息，根据id更新
    int xiugai(Dizhi entity);

    // 根据地址id删除地址信息
    int shanchuById(Integer id);
}
