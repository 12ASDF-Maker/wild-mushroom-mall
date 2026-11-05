package com.shop.mapper;

import com.shop.entity.Xiaoxi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// 站内消息Mapper接口，用于操作消息表数据
@Mapper
public interface XiaoxiMapper {

    // 查询全部消息信息
    List<Xiaoxi> chaxunQuanbu();

    // 根据消息id查询消息信息
    Xiaoxi chaxunById(Integer id);

    // 查询两个用户之间的聊天记录
    List<Xiaoxi> chaxunLiaotian(@Param("senderId") Integer senderId, @Param("receiverId") Integer receiverId);

    // 新增消息信息
    int tianjia(Xiaoxi entity);

    // 修改消息信息
    int xiugai(Xiaoxi entity);

    // 根据消息id删除消息信息
    int shanchuById(Integer id);
}
