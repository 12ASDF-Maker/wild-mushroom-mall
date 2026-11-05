# 🍄 山野珍菌商城（Wild Mushroom Mall）

基于 **Spring Boot + MyBatis + Thymeleaf** 的云南野生菌电商商城，包含用户前台与商家后台，代码结构简单清晰，适合作为 Java Web 课程设计、毕业设计或入门项目参考。

[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![JDK](https://img.shields.io/badge/JDK-1.8-blue.svg)](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)](https://www.mysql.com/)

## ✨ 功能特性

### 用户前台

- 🏠 商城首页：轮播广告、横幅推广、商品展示
- 🍄 商品浏览：按分类浏览（松茸、牛肝菌、鸡枞菌、竹荪、香菇、礼盒专区）、商品详情
- 🛒 购物车：加入购物车、修改数量、移除、结算
- 📦 下单流程：订单确认 → 模拟支付 → 支付成功
- 📋 我的订单：查看订单、取消订单、确认收货
- 📍 收货地址：新增、删除、默认地址管理
- 🎁 菌菇定制：提交定制需求、查看商家回复
- 💬 站内消息：与客服在线聊天、查看历史消息
- 👤 账号：注册、登录、退出

### 商家后台

- 后台首页（数据导航）
- 用户管理（增删改查、禁用/启用）
- 商品管理（增删改查、分类、上下架）
- 广告管理（首页轮播/横幅配置）
- 订单管理（增删改查、状态流转：待付款 → 已付款 → 已发货 → 已完成 / 已取消）
- 定制需求管理（查看、回复处理）
- 购物车管理
- 站内消息管理

## 🛠 技术栈

| 技术 | 版本 / 说明 |
| --- | --- |
| Java | 1.8 |
| Spring Boot | 2.7.18 |
| MyBatis | mybatis-spring-boot-starter 2.3.2 |
| Thymeleaf | 服务端模板引擎 |
| MySQL | 8.0（mysql-connector-j 8.0.33） |
| 前端 | HTML + CSS + 原生 JS |
| 构建工具 | Maven |

## 📁 项目结构

```
山野珍菌商城
├── pom.xml                             # Maven 依赖配置
├── schema.sql                          # 建库建表 + 演示数据 SQL
├── README.md
├── LICENSE
└── src/main
    ├── java/com/shop
    │   ├── ShopApplication.java        # 启动类
    │   ├── controller/                 # 控制器层（10 个）
    │   │   ├── DengluController.java   # 登录 / 注册 / 退出
    │   │   ├── ShouyeController.java   # 前台首页
    │   │   ├── YonghuController.java   # 用户管理
    │   │   ├── ShangpinController.java # 商品管理
    │   │   ├── DingdanController.java  # 订单管理
    │   │   ├── GouwucheController.java # 购物车管理
    │   │   ├── DingzhiController.java  # 定制需求管理
    │   │   ├── DizhiController.java    # 收货地址管理
    │   │   ├── XiaoxiController.java   # 站内消息管理
    │   │   └── GuanggaoController.java # 广告管理
    │   ├── entity/                     # 实体类（8 个）
    │   ├── mapper/                     # MyBatis Mapper 接口（8 个）
    │   └── service/                    # 业务层（8 个）
    └── resources
        ├── application.yml             # 配置文件
        ├── schema.sql                  # 建库建表 + 演示数据
        ├── mapper/                     # MyBatis XML 映射文件
        ├── templates/                  # Thymeleaf 页面（26 个）
        └── static/                     # css / 商品图片
```

## 🚀 快速开始

### 环境要求

- JDK 1.8
- Maven 3.6+
- MySQL 8.0（本地或远程均可）

### 启动步骤

1. 克隆项目：

   ```bash
   git clone https://github.com/12ASDF-Maker/wild-mushroom-mall.git
   ```

2. 初始化数据库：用 HeidiSQL / Navicat 等工具执行根目录的 `schema.sql`，会自动创建数据库 `mall`、全部 8 张数据表，并插入演示商品、广告、定制需求和管理员账号（密码明文 `admin`，仅作演示用）。

   也可以命令行执行：

   ```bash
   mysql -u root -p < schema.sql
   ```

3. 修改 `src/main/resources/application.yml` 中的数据库账号密码：

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
       username: root
       password: 你的MySQL密码
   ```

4. 启动项目（二选一）：

   - IDEA：打开项目根目录，确认 Project SDK 为 JDK 1.8，运行 `ShopApplication.java`
   - 命令行：`mvn spring-boot:run`

5. 浏览器访问 <http://localhost:8080/> 进入商城首页

6. 登录：

   | 角色 | 账号 | 密码 | 入口 |
   | --- | --- | --- | --- |
   | 管理员 | admin | admin | 首页点击登录 |
   | 普通用户 | 首页注册或后台创建 | — | 首页点击注册 |

   登录后访问 <http://localhost:8080/houtai> 进入商家后台。

## 🧭 页面路由说明

### 前台页面

| 路径 | 说明 |
| --- | --- |
| `/` | 商城首页 |
| `/denglu` | 登录页 |
| `/zhuce` | 注册页 |
| `/tuichu` | 退出登录 |
| `/fenlei` | 商品分类浏览 |
| `/shangpin/xiangqing/{id}` | 商品详情 |
| `/gouwuche/view` | 我的购物车 |
| `/gouwuche/jiaru` | 加入购物车 |
| `/gouwuche/yichu/{id}` | 移除购物车商品 |
| `/gouwuche/gengxin` | 更新购物车数量 |
| `/dingdan/confirm` | 订单确认 |
| `/dingdan/pay` | 模拟支付 |
| `/dingdan/paySuccess` | 支付成功 |
| `/dingdan/create` | 创建订单（POST） |
| `/wodeDingdan` | 我的订单 |
| `/dingdan/cancel/{id}` | 取消订单 |
| `/dingdan/confirm/{id}` | 确认收货 |
| `/dizhi/list` · `/dizhi/add` · `/dizhi/save` · `/dizhi/delete/{id}` | 收货地址管理 |
| `/dingzhi/add` · `/dingzhi/save` | 提交定制需求 |
| `/xiaoxi/liaotian` | 与客服聊天 |
| `/xiaoxi/send` | 发送消息（POST） |
| `/xiaoxi/history` | 历史消息 |

### 后台页面（/houtai）

| 模块 | 路径 |
| --- | --- |
| 用户管理 | `/yonghu/list` · `/yonghu/add` · `/yonghu/edit/{id}` · `/yonghu/save`(POST) · `/yonghu/delete/{id}` |
| 商品管理 | `/shangpin/list` · `/shangpin/add` · `/shangpin/edit/{id}` · `/shangpin/save`(POST) · `/shangpin/delete/{id}` |
| 广告管理 | `/guanggao/list` · `/guanggao/add` · `/guanggao/edit/{id}` · `/guanggao/save`(POST) · `/guanggao/delete/{id}` |
| 订单管理 | `/dingdan/list` · `/dingdan/add` · `/dingdan/edit/{id}` · `/dingdan/save`(POST) · `/dingdan/delete/{id}` · `/dingdan/status/{id}` |
| 购物车管理 | `/gouwuche/list` · `/gouwuche/add` · `/gouwuche/edit/{id}` · `/gouwuche/save`(POST) · `/gouwuche/delete/{id}` |
| 定制管理 | `/dingzhi/list` · `/dingzhi/add` · `/dingzhi/edit/{id}` · `/dingzhi/save`(POST) · `/dingzhi/delete/{id}` · `/dingzhi/reply/{id}`(POST) |
| 消息管理 | `/xiaoxi/list` · `/xiaoxi/add` · `/xiaoxi/edit/{id}` · `/xiaoxi/save`(POST) · `/xiaoxi/delete/{id}` |

## 🗄 数据库表结构

| 表名 | 说明 | 主要字段 |
| --- | --- | --- |
| `yonghu` | 用户表 | id, username, password, nickname, phone, role, status |
| `shangpin` | 商品表 | id, name, price, stock, description, image_url, status, category |
| `dingdan` | 订单表 | id, order_no, user_id, total_amount, status, receiver_name, receiver_phone, receiver_address, create_time |
| `gouwuche` | 购物车表 | id, user_id, product_id, quantity, status, create_time |
| `xiaoxi` | 站内消息表 | id, sender_id, receiver_id, content, is_read, create_time |
| `guanggao` | 广告表 | id, title, image_url, link_url, position, status |
| `dingzhi` | 定制表 | id, user_id, product_name, description, phone, status, create_time, reply |
| `dizhi` | 用户地址表 | id, user_id, receiver_name, receiver_phone, receiver_address, is_default, create_time |

`schema.sql` 中附带 16 款演示商品（含对应商品图片）、7 条演示广告、2 条演示定制需求，手机号等均为虚构示例数据，可放心使用或自行修改。

## ⚠️ 注意事项

- 本项目为课程设计/学习用途的演示项目，密码为明文存储、接口未做权限校验，**请勿直接用于生产环境**。
- 数据库默认账号 `root/root` 仅适用于本地开发，请按需修改。
- 图片保存在本地 `src/main/resources/static/images/`，商品图片通过 `/images/xxx.jpg` 路径引用。

## 📄 开源许可

本项目基于 [MIT License](LICENSE) 开源，欢迎 Star、Fork 与提交 PR。
