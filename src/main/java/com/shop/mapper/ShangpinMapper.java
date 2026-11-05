package com.shop.mapper;

import com.shop.entity.Shangpin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// 商品Mapper接口，用于操作商品表数据
@Mapper
public interface ShangpinMapper {

    // 查询全部商品信息
    List<Shangpin> chaxunQuanbu();

    // 查询上架的商品
    List<Shangpin> chaxunShangjia();

    // 根据分类查询上架商品
    List<Shangpin> chaxunByFenlei(String category);

    // 根据商品id查询商品信息
    Shangpin chaxunById(Integer id);

    // 新增商品信息
    int tianjia(Shangpin entity);

    // 修改商品信息
    int xiugai(Shangpin entity);

    // 根据商品id删除商品信息
    int shanchuById(Integer id);

    // 减少库存
    int jianshaoKucun(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // 增加库存
    int zengjiaKucun(@Param("id") Integer id, @Param("quantity") Integer quantity);
}
