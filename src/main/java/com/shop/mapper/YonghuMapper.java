package com.shop.mapper;

import com.shop.entity.Yonghu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 用户Mapper接口，用于操作用户表数据
@Mapper
public interface YonghuMapper {

    // 查询全部用户信息
    List<Yonghu> chaxunQuanbu();

    // 根据用户id查询用户信息
    Yonghu chaxunById(Integer id);

    // 根据用户名查询用户信息，用于登录校验
    Yonghu findByUsername(String username);

    // 新增用户信息
    int tianjia(Yonghu entity);

    // 修改用户信息
    int xiugai(Yonghu entity);

    // 根据用户id删除用户信息
    int shanchuById(Integer id);
}
