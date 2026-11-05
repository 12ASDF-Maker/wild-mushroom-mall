package com.shop.entity;

// 商品实体类，对应数据库product表
public class Shangpin {
    // 商品主键id，自增唯一标识
    private Integer id;
    // 商品名称，展示商品的标题
    private String name;
    // 商品价格，单位为元
    private Double price;
    // 商品库存数量，表示当前可售卖数量
    private Integer stock;
    // 商品描述，详细介绍商品信息
    private String description;
    // 商品图片地址，存储商品图片的访问路径
    private String imageUrl;
    // 商品状态，1表示上架，0表示下架
    private Integer status;
    // 商品分类，如：松茸、牛肝菌、香菇等
    private String category;

    // 无参构造方法，方便反射创建对象
    public Shangpin() {
    }

    // 获取商品主键id
    public Integer getId() {
        return id;
    }

    // 设置商品主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取商品名称
    public String getName() {
        return name;
    }

    // 设置商品名称
    public void setName(String name) {
        this.name = name;
    }

    // 获取商品价格
    public Double getPrice() {
        return price;
    }

    // 设置商品价格
    public void setPrice(Double price) {
        this.price = price;
    }

    // 获取商品库存数量
    public Integer getStock() {
        return stock;
    }

    // 设置商品库存数量
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // 获取商品描述
    public String getDescription() {
        return description;
    }

    // 设置商品描述
    public void setDescription(String description) {
        this.description = description;
    }

    // 获取商品图片地址
    public String getImageUrl() {
        return imageUrl;
    }

    // 设置商品图片地址
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 获取商品状态
    public Integer getStatus() {
        return status;
    }

    // 设置商品状态
    public void setStatus(Integer status) {
        this.status = status;
    }

    // 获取商品分类
    public String getCategory() {
        return category;
    }

    // 设置商品分类
    public void setCategory(String category) {
        this.category = category;
    }
}
