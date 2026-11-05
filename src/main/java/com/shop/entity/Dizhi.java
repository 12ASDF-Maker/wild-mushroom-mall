package com.shop.entity;

// 用户地址实体类，对应数据库dizhi表
public class Dizhi {
    // 地址主键id，自增唯一标识
    private Integer id;
    // 用户id，标识地址所属用户
    private Integer userId;
    // 收货人姓名
    private String receiverName;
    // 收货人电话
    private String receiverPhone;
    // 收货详细地址
    private String receiverAddress;
    // 是否默认地址：1默认/0非默认
    private Integer isDefault;
    // 创建时间
    private String createTime;

    // 无参构造方法，方便反射创建对象
    public Dizhi() {
    }

    // 获取地址主键id
    public Integer getId() {
        return id;
    }

    // 设置地址主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取用户id
    public Integer getUserId() {
        return userId;
    }

    // 设置用户id
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // 获取收货人姓名
    public String getReceiverName() {
        return receiverName;
    }

    // 设置收货人姓名
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    // 获取收货人电话
    public String getReceiverPhone() {
        return receiverPhone;
    }

    // 设置收货人电话
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    // 获取收货详细地址
    public String getReceiverAddress() {
        return receiverAddress;
    }

    // 设置收货详细地址
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    // 获取是否默认地址
    public Integer getIsDefault() {
        return isDefault;
    }

    // 设置是否默认地址
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    // 获取创建时间
    public String getCreateTime() {
        return createTime;
    }

    // 设置创建时间
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
