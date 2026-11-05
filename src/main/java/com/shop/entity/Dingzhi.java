package com.shop.entity;

// 定制需求实体类，对应数据库dingzhi表
public class Dingzhi {
    // 定制需求主键id，自增唯一标识
    private Integer id;
    // 提交需求的用户id，关联用户表
    private Integer userId;
    // 定制商品名，描述用户需要定制的商品
    private String productName;
    // 定制需求描述，描述用户的具体定制要求
    private String description;
    // 联系电话，方便商家联系用户
    private String phone;
    // 处理状态，如待处理、已处理等
    private String status;
    // 创建时间，提交定制需求的时间
    private String createTime;
    // 商家回复内容，针对定制需求的答复
    private String reply;

    // 无参构造方法，方便反射创建对象
    public Dingzhi() {
    }

    // 获取定制需求主键id
    public Integer getId() {
        return id;
    }

    // 设置定制需求主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取提交需求的用户id
    public Integer getUserId() {
        return userId;
    }

    // 设置提交需求的用户id
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // 获取定制商品名
    public String getProductName() {
        return productName;
    }

    // 设置定制商品名
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // 获取定制需求描述
    public String getDescription() {
        return description;
    }

    // 设置定制需求描述
    public void setDescription(String description) {
        this.description = description;
    }

    // 获取联系电话
    public String getPhone() {
        return phone;
    }

    // 设置联系电话
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 获取处理状态
    public String getStatus() {
        return status;
    }

    // 设置处理状态
    public void setStatus(String status) {
        this.status = status;
    }

    // 获取创建时间
    public String getCreateTime() {
        return createTime;
    }

    // 设置创建时间
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    // 获取商家回复内容
    public String getReply() {
        return reply;
    }

    // 设置商家回复内容
    public void setReply(String reply) {
        this.reply = reply;
    }
}
