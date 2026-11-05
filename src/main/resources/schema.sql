-- ============================================
-- 山野珍菌商城 数据库建表语句
-- 数据库名：mall
-- 直接在HeidiSQL中执行此文件即可自动建库建表
-- 表名采用汉语拼音命名
-- ============================================

-- 创建数据库（如果不存在）

-- 选择数据库

-- ============================================
-- 建表语句（如果表已存在会先删除再创建）
-- ============================================

-- 1. 用户表（yonghu）
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    `role` VARCHAR(20) DEFAULT 'user' COMMENT '角色：admin管理员/user普通用户',
    `status` INT DEFAULT 1 COMMENT '状态：1正常/0禁用',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 2. 商品表（shangpin）
DROP TABLE IF EXISTS `shangpin`;
CREATE TABLE `shangpin` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '商品主键id',
    `name` VARCHAR(100) NOT NULL COMMENT '商品名称',
    `price` DECIMAL(10,2) NOT NULL COMMENT '商品价格',
    `stock` INT DEFAULT 0 COMMENT '库存数量',
    `description` TEXT COMMENT '商品描述',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '商品图片地址',
    `status` INT DEFAULT 1 COMMENT '状态：1上架/0下架',
    `category` VARCHAR(50) DEFAULT '其他' COMMENT '商品分类',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 3. 订单表（dingdan）
DROP TABLE IF EXISTS `dingdan`;
CREATE TABLE `dingdan` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '订单主键id',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单编号',
    `user_id` INT DEFAULT NULL COMMENT '下单用户id',
    `total_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '订单总金额',
    `status` VARCHAR(20) DEFAULT '待付款' COMMENT '订单状态',
    `receiver_name` VARCHAR(50) DEFAULT NULL COMMENT '收货人姓名',
    `receiver_phone` VARCHAR(20) DEFAULT NULL COMMENT '收货人电话',
    `receiver_address` VARCHAR(255) DEFAULT NULL COMMENT '收货地址',
    `create_time` VARCHAR(30) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 4. 购物车表（gouwuche）
DROP TABLE IF EXISTS `gouwuche`;
CREATE TABLE `gouwuche` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '购物车主键id',
    `user_id` INT DEFAULT NULL COMMENT '用户id',
    `product_id` INT DEFAULT NULL COMMENT '商品id',
    `quantity` INT DEFAULT 1 COMMENT '数量',
    `status` INT DEFAULT 1 COMMENT '状态：1有效/0已移除',
    `create_time` VARCHAR(30) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 5. 站内消息表（xiaoxi）
DROP TABLE IF EXISTS `xiaoxi`;
CREATE TABLE `xiaoxi` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '消息主键id',
    `sender_id` INT DEFAULT NULL COMMENT '发送者用户id',
    `receiver_id` INT DEFAULT NULL COMMENT '接收者用户id，0代表客服系统',
    `content` TEXT COMMENT '消息内容',
    `is_read` INT DEFAULT 0 COMMENT '是否已读：1已读/0未读',
    `create_time` VARCHAR(30) DEFAULT NULL COMMENT '发送时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站内消息表';

-- 6. 广告表（guanggao）
DROP TABLE IF EXISTS `guanggao`;
CREATE TABLE `guanggao` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '广告主键id',
    `title` VARCHAR(100) NOT NULL COMMENT '广告标题',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '广告图片地址',
    `link_url` VARCHAR(255) DEFAULT NULL COMMENT '广告跳转链接',
    `position` VARCHAR(50) DEFAULT NULL COMMENT '展示位置',
    `status` INT DEFAULT 1 COMMENT '状态：1上架/0下架',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告表';

-- 7. 定制表（dingzhi）
DROP TABLE IF EXISTS `dingzhi`;
CREATE TABLE `dingzhi` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '定制主键id',
    `user_id` INT DEFAULT NULL COMMENT '用户id',
    `product_name` VARCHAR(100) DEFAULT NULL COMMENT '定制商品名',
    `description` TEXT COMMENT '定制需求描述',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `status` VARCHAR(20) DEFAULT '待处理' COMMENT '处理状态',
    `create_time` VARCHAR(30) DEFAULT NULL COMMENT '创建时间',
    `reply` VARCHAR(500) DEFAULT NULL COMMENT '商家回复',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定制表';

-- 8. 用户地址表（dizhi）
DROP TABLE IF EXISTS `dizhi`;
CREATE TABLE `dizhi` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '地址主键id',
    `user_id` INT DEFAULT NULL COMMENT '用户id',
    `receiver_name` VARCHAR(50) DEFAULT NULL COMMENT '收货人姓名',
    `receiver_phone` VARCHAR(20) DEFAULT NULL COMMENT '收货人电话',
    `receiver_address` VARCHAR(255) DEFAULT NULL COMMENT '收货详细地址',
    `is_default` INT DEFAULT 0 COMMENT '是否默认地址：1默认/0非默认',
    `create_time` VARCHAR(30) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户地址表';

-- ============================================
-- 初始数据：插入管理员账号（密码admin）
-- ============================================
INSERT INTO `yonghu` (`username`, `password`, `nickname`, `phone`, `role`, `status`)
VALUES ('admin', 'admin', '管理员', '13800000000', 'admin', 1);

-- ============================================
-- 插入示例商品数据（图片路径使用 /images/产品名.jpg）
-- 商品分类覆盖：松茸、牛肝菌、鸡枞菌、竹荪、香菇、礼盒专区
-- 商品描述均加长到20个字左右
-- ============================================
INSERT INTO `shangpin` (`name`, `price`, `stock`, `description`, `image_url`, `status`, `category`)
VALUES
('新鲜松茸', 128.00, 50, '云南香格里拉野生松茸，新鲜采摘个大肉厚，500g精品装', '/images/新鲜松茸.jpg', 1, '松茸'),
('松茸干货', 268.00, 20, '云南香格里拉松茸干货，精选大片营养醇厚，250g装', '/images/松茸干货.jpg', 1, '松茸'),
('冻干松茸片', 198.00, 30, '冻干松茸片锁鲜加工，泡发即食方便美味，150g便携装', '/images/冻干松茸片.jpg', 1, '松茸'),
('牛肝菌干货', 89.00, 80, '云南美味牛肝菌切片，干货精选肉厚味鲜，250g装', '/images/牛肝菌干货.jpg', 1, '牛肝菌'),
('美味牛肝菌鲜品', 158.00, 40, '云南新鲜美味牛肝菌，当日采摘肉质肥厚，500g装', '/images/美味牛肝菌鲜品.jpg', 1, '牛肝菌'),
('鸡枞菌干货', 78.00, 60, '云南鸡枞菌干货，精选品质鲜香浓郁，200g装', '/images/鸡枞菌干货.jpg', 1, '鸡枞菌'),
('油鸡枞罐头', 68.00, 70, '云南油鸡枞罐头，传统工艺开盖即食，下饭佳品', '/images/油鸡枞罐头.jpg', 1, '鸡枞菌'),
('干品竹荪', 98.00, 40, '精选干品竹荪，朵形完整脆嫩爽口，100g装', '/images/干品竹荪.jpg', 1, '竹荪'),
('竹荪礼盒装', 168.00, 25, '优质竹荪礼盒装，精挑细选煲汤鲜美，高端礼品', '/images/竹荪礼盒装.jpg', 1, '竹荪'),
('精选香菇', 58.00, 100, '云南精选香菇，肉厚味浓干货500g，煲汤炒菜', '/images/精选香菇.jpg', 1, '香菇'),
('茶树菇干货', 45.00, 120, '云南茶树菇干货，口感筋道煲汤佳品，250g装', '/images/茶树菇干货.jpg', 1, '香菇'),
('杏鲍菇干货', 38.00, 90, '精选杏鲍菇干货，口感鲜嫩炖煮皆宜，250g装', '/images/杏鲍菇干货.jpg', 1, '香菇'),
('羊肚菌干货', 168.00, 25, '精选羊肚菌干货，营养滋补个大肉厚，150g装', '/images/羊肚菌干货.jpg', 1, '香菇'),
('松茸礼盒', 388.00, 15, '高端松茸礼盒，含新鲜松茸和精品干货，送礼佳品', '/images/松茸礼盒.jpg', 1, '礼盒专区'),
('菌菇礼盒', 158.00, 30, '8种野生菌组合礼盒，精选搭配送礼佳品，高端包装', '/images/菌菇礼盒.jpg', 1, '礼盒专区'),
('野生菌组合装', 198.00, 35, '5种云南野生菌组合，家庭装实惠美味，一盒搞定', '/images/野生菌组合装.jpg', 1, '礼盒专区');

-- ============================================
-- 插入广告数据，每条对应一个商品
-- ============================================
INSERT INTO `guanggao` (`title`, `image_url`, `link_url`, `position`, `status`)
VALUES
('新鲜松茸上市', '/images/新鲜松茸.jpg', '/shangpin/xiangqing/1', '首页轮播', 1),
('松茸干货精选大片', '/images/松茸干货.jpg', '/shangpin/xiangqing/2', '首页轮播', 1),
('牛肝菌季热销中', '/images/牛肝菌干货.jpg', '/shangpin/xiangqing/4', '首页横幅', 1),
('鸡枞菌鲜香浓郁', '/images/鸡枞菌干货.jpg', '/shangpin/xiangqing/6', '首页横幅', 1),
('菌菇礼盒8折特惠', '/images/菌菇礼盒.jpg', '/shangpin/xiangqing/15', '首页横幅', 1),
('满200减20全场满减', '', '/fenlei', '首页横幅', 1),
('油鸡枞新品上市', '', '/shangpin/xiangqing/7', '首页轮播', 1);

-- ============================================
-- 插入示例定制需求数据
-- ============================================
INSERT INTO `dingzhi` (`user_id`, `product_name`, `description`, `phone`, `status`, `create_time`, `reply`)
VALUES
(1, '松茸定制礼盒', '需要定制一盒含新鲜松茸和干货的高端礼盒，用于企业送礼', '13800000001', '已处理', '2026-09-01 10:00:00', '已安排，礼盒内含新鲜松茸500g及干货250g，请到店自提'),
(1, '牛肝菌大包装', '需要5斤装牛肝菌干货，用于饭店采购', '13800000001', '待处理', '2026-09-02 09:30:00', NULL);

-- ============================================
-- 插入示例用户地址数据
-- ============================================
INSERT INTO `dizhi` (`user_id`, `receiver_name`, `receiver_phone`, `receiver_address`, `is_default`, `create_time`)
VALUES
(1, '管理员', '13800000000', '云南省昆明市盘龙区北京路100号', 1, '2026-09-01 08:00:00'),
(1, '张三', '13900000000', '云南省大理白族自治州下关镇苍山路20号', 0, '2026-09-01 12:00:00');
