package com.shop.entity;

// 站内消息实体类，对应数据库message表
public class Xiaoxi {
    // 消息主键id，自增唯一标识
    private Integer id;
    // 发送者用户id，标识消息发送人
    private Integer senderId;
    // 接收者用户id，标识消息接收人
    private Integer receiverId;
    // 消息内容，存储发送的文字内容
    private String content;
    // 是否已读，1表示已读，0表示未读
    private Integer isRead;
    // 消息发送时间
    private String createTime;

    // 无参构造方法，方便反射创建对象
    public Xiaoxi() {
    }

    // 获取消息主键id
    public Integer getId() {
        return id;
    }

    // 设置消息主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取发送者用户id
    public Integer getSenderId() {
        return senderId;
    }

    // 设置发送者用户id
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    // 获取接收者用户id
    public Integer getReceiverId() {
        return receiverId;
    }

    // 设置接收者用户id
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    // 获取消息内容
    public String getContent() {
        return content;
    }

    // 设置消息内容
    public void setContent(String content) {
        this.content = content;
    }

    // 获取是否已读
    public Integer getIsRead() {
        return isRead;
    }

    // 设置是否已读
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    // 获取消息发送时间
    public String getCreateTime() {
        return createTime;
    }

    // 设置消息发送时间
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
