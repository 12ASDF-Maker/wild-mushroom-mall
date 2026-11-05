package com.shop.mapper;

import com.shop.entity.Gouwuche;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// 购物车Mapper接口，用于操作购物车表数据
@Mapper
public interface GouwucheMapper {

    // 查询全部购物车信息
    List<Gouwuche> chaxunQuanbu();

    // 根据购物车id查询购物车信息
    Gouwuche chaxunById(Integer id);

    // 根据用户id查询购物车（关联商品信息）
    List<Gouwuche> chaxunByYonghuId(Integer userId);

    // 根据用户id和商品id查询购物车项（判断是否已存在）
    Gouwuche chaxunByYonghuShangpin(@Param("userId") Integer userId, @Param("productId") Integer productId);

    // 新增购物车信息
    int tianjia(Gouwuche entity);

    // 修改购物车信息
    int xiugai(Gouwuche entity);

    // 更新购物车数量
    int gengxinShuliang(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // 根据购物车id删除购物车信息
    int shanchuById(Integer id);

    // 根据用户id清空购物车（下单后调用）
    int qingkongByYonghuId(Integer userId);
}
