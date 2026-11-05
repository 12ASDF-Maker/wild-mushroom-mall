package com.shop.mapper;

import com.shop.entity.Dingdan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// 订单Mapper接口，用于操作订单表数据
@Mapper
public interface DingdanMapper {

    // 查询全部订单信息
    List<Dingdan> chaxunQuanbu();

    // 根据用户id查询订单
    List<Dingdan> chaxunByYonghuId(Integer userId);

    // 根据订单编号查询订单
    Dingdan chaxunByDingdanNo(String orderNo);

    // 根据订单id查询订单信息
    Dingdan chaxunById(Integer id);

    // 新增订单信息
    int tianjia(Dingdan entity);

    // 修改订单信息
    int xiugai(Dingdan entity);

    // 根据订单id删除订单信息
    int shanchuById(Integer id);

    // 修改订单状态，根据订单id更新订单状态
    int xiugaiZhuangtai(@Param("id") Integer id, @Param("status") String status);
}
