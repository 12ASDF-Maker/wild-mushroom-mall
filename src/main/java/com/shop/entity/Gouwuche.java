package com.shop.entity;

// 购物车实体类，对应数据库gouwuche表
public class Gouwuche {
    // 购物车主键id，自增唯一标识
    private Integer id;
    // 用户id，标识购物车属于哪个用户
    private Integer userId;
    // 商品id，标识购物车中的商品
    private Integer productId;
    // 商品数量，用户加入购物车的数量
    private Integer quantity;
    // 购物车项状态，1表示有效，0表示已移除
    private Integer status;
    // 创建时间
    private String createTime;
    // 以下字段用于关联查询展示，不对应数据库列
    // 商品名称（关联shangpin表）
    private String productName;
    // 商品价格（关联shangpin表）
    private Double productPrice;
    // 商品图片（关联shangpin表）
    private String productImage;

    // 无参构造方法，方便反射创建对象
    public Gouwuche() {
    }

    // 获取购物车主键id
    public Integer getId() {
        return id;
    }

    // 设置购物车主键id
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

    // 获取商品id
    public Integer getProductId() {
        return productId;
    }

    // 设置商品id
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    // 获取商品数量
    public Integer getQuantity() {
        return quantity;
    }

    // 设置商品数量
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // 获取购物车项状态
    public Integer getStatus() {
        return status;
    }

    // 设置购物车项状态
    public void setStatus(Integer status) {
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

    // 获取商品名称（关联查询）
    public String getProductName() {
        return productName;
    }

    // 设置商品名称（关联查询）
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // 获取商品价格（关联查询）
    public Double getProductPrice() {
        return productPrice;
    }

    // 设置商品价格（关联查询）
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    // 获取商品图片（关联查询）
    public String getProductImage() {
        return productImage;
    }

    // 设置商品图片（关联查询）
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
