package com.shop.entity;

// 广告实体类，对应数据库ad表
public class Guanggao {
    // 广告主键id，自增唯一标识
    private Integer id;
    // 广告标题，展示广告的文字标题
    private String title;
    // 广告图片地址，存储图片的访问路径
    private String imageUrl;
    // 广告跳转链接，点击广告后跳转的地址
    private String linkUrl;
    // 广告展示位置，标识广告显示在哪个位置
    private String position;
    // 广告状态，1表示上架，0表示下架
    private Integer status;

    // 无参构造方法，方便反射创建对象
    public Guanggao() {
    }

    // 获取广告主键id
    public Integer getId() {
        return id;
    }

    // 设置广告主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取广告标题
    public String getTitle() {
        return title;
    }

    // 设置广告标题
    public void setTitle(String title) {
        this.title = title;
    }

    // 获取广告图片地址
    public String getImageUrl() {
        return imageUrl;
    }

    // 设置广告图片地址
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 获取广告跳转链接
    public String getLinkUrl() {
        return linkUrl;
    }

    // 设置广告跳转链接
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    // 获取广告展示位置
    public String getPosition() {
        return position;
    }

    // 设置广告展示位置
    public void setPosition(String position) {
        this.position = position;
    }

    // 获取广告状态
    public Integer getStatus() {
        return status;
    }

    // 设置广告状态
    public void setStatus(Integer status) {
        this.status = status;
    }
}
