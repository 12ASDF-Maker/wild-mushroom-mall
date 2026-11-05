package com.shop.service;

import com.shop.entity.Yonghu;
import com.shop.mapper.YonghuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

// 用户业务层，处理用户相关的业务逻辑
@Service
public class YonghuService {

    // 注入用户Mapper，用于操作用户表数据
    private final YonghuMapper yonghuMapper;

    // 构造方法注入YonghuMapper，Spring会自动注入
    public YonghuService(YonghuMapper yonghuMapper) {
        this.yonghuMapper = yonghuMapper;
    }

    // 查询全部用户
    public List<Yonghu> chaxunQuanbu() {
        return yonghuMapper.chaxunQuanbu();
    }

    // 根据id查询用户
    public Yonghu chaxunById(Integer id) {
        return yonghuMapper.chaxunById(id);
    }

    // 新增用户
    public void tianjia(Yonghu user) {
        yonghuMapper.tianjia(user);
    }

    // 修改用户
    public void xiugai(Yonghu user) {
        yonghuMapper.xiugai(user);
    }

    // 根据id删除用户
    public void shanchuById(Integer id) {
        yonghuMapper.shanchuById(id);
    }

    // 根据用户名查询用户
    public Yonghu findByUsername(String username) {
        return yonghuMapper.findByUsername(username);
    }

    // 用户注册，返回true表示注册成功，false表示用户名已存在
    public boolean register(Yonghu user) {
        // 先根据用户名查询，检查用户名是否已存在
        Yonghu existUser = yonghuMapper.findByUsername(user.getUsername());
        if (existUser != null) {
            // 用户名已存在，注册失败
            return false;
        }
        // 设置默认角色为普通用户
        user.setRole("user");
        // 设置默认状态为正常启用，1表示正常
        user.setStatus(1);
        // 调用Mapper插入用户数据
        yonghuMapper.tianjia(user);
        // 注册成功
        return true;
    }

    // 用户登录，验证用户名和密码，成功返回User对象，失败返回null
    public Yonghu login(String username, String password) {
        // 根据用户名查询用户
        Yonghu user = yonghuMapper.findByUsername(username);
        // 用户不存在，登录失败
        if (user == null) {
            return null;
        }
        // 密码不匹配，登录失败
        if (!password.equals(user.getPassword())) {
            return null;
        }
        // 用户名和密码都正确，返回用户对象
        return user;
    }
}
