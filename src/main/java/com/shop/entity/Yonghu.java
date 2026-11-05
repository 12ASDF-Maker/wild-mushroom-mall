package com.shop.entity;

// 用户实体类，对应数据库user表
public class Yonghu {
    // 用户主键id，自增唯一标识
    private Integer id;
    // 用户名，用于登录的账号
    private String username;
    // 登录密码，存储加密后的密码
    private String password;
    // 用户昵称，用于页面展示名称
    private String nickname;
    // 联系电话，方便商家联系用户
    private String phone;
    // 用户角色，标识是管理员还是普通用户
    private String role;
    // 账号状态，1表示正常，0表示禁用
    private Integer status;

    // 无参构造方法，方便反射创建对象
    public Yonghu() {
    }

    // 获取用户主键id
    public Integer getId() {
        return id;
    }

    // 设置用户主键id
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取用户名
    public String getUsername() {
        return username;
    }

    // 设置用户名
    public void setUsername(String username) {
        this.username = username;
    }

    // 获取登录密码
    public String getPassword() {
        return password;
    }

    // 设置登录密码
    public void setPassword(String password) {
        this.password = password;
    }

    // 获取用户昵称
    public String getNickname() {
        return nickname;
    }

    // 设置用户昵称
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // 获取联系电话
    public String getPhone() {
        return phone;
    }

    // 设置联系电话
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 获取用户角色
    public String getRole() {
        return role;
    }

    // 设置用户角色
    public void setRole(String role) {
        this.role = role;
    }

    // 获取账号状态
    public Integer getStatus() {
        return status;
    }

    // 设置账号状态
    public void setStatus(Integer status) {
        this.status = status;
    }
}
