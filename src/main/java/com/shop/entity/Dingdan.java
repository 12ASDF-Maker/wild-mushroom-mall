package com.shop.entity;

// 订单实体类，对应数据库orders表
public class Dingdan {
    // 订单主键id，自增唯一标识
    private Integer id;
    // 订单编号，系统生成的唯一订单号
    private String orderNo;
    // 下单用户id，关联用户表
    private Integer userId;
    // 订单总金额，单位为元
    private Double totalAmount;
    // 订单状态，如待付款、已付款、已发货等
    private String status;
    // 收货人姓名
    private String receiverName;
    // 收货人联系电话
    private String receiverPhone;
    // 收货人详细地址
    private String receiverAddress;
    // 订单创建时间
    private String createTime;

    // 无参构造方法，方便反射创建对象
    public Dingdan() {
    }

    // 获取订单主键id
    public Integer getId() {
        return id;
    }

    // 设置订单主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取订单编号
    public String getOrderNo() {
        return orderNo;
    }

    // 设置订单编号
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    // 获取下单用户id
    public Integer getUserId() {
        return userId;
    }

    // 设置下单用户id
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // 获取订单总金额
    public Double getTotalAmount() {
        return totalAmount;
    }

    // 设置订单总金额
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // 获取订单状态
    public String getStatus() {
        return status;
    }

    // 设置订单状态
    public void setStatus(String status) {
        this.status = status;
    }

    // 获取收货人姓名
    public String getReceiverName() {
        return receiverName;
    }

    // 设置收货人姓名
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    // 获取收货人联系电话
    public String getReceiverPhone() {
        return receiverPhone;
    }

    // 设置收货人联系电话
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    // 获取收货人详细地址
    public String getReceiverAddress() {
        return receiverAddress;
    }

    // 设置收货人详细地址
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    // 获取订单创建时间
    public String getCreateTime() {
        return createTime;
    }

    // 设置订单创建时间
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
