-- 爱宝贝儿孕婴生活馆 - 微信小程序后端 - 数据库初始化脚本
-- 创建日期：2026-04-30

-- 使用数据库
USE aibaobei;

-- 1. 用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码（加密存储）',
    nickname VARCHAR(50) COMMENT '昵称',
    avatar VARCHAR(500) COMMENT '头像URL',
    phone VARCHAR(20) COMMENT '手机号',
    vip_level VARCHAR(20) DEFAULT 'VIP0' COMMENT 'VIP等级',
    points INT DEFAULT 0 COMMENT '积分余额',
    balance DECIMAL(10,2) DEFAULT 0.00 COMMENT '账户余额',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 2. 收货地址表
CREATE TABLE IF NOT EXISTS addresses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '地址ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    name VARCHAR(50) NOT NULL COMMENT '收货人姓名',
    phone VARCHAR(20) NOT NULL COMMENT '手机号',
    province VARCHAR(50) NOT NULL COMMENT '省份',
    city VARCHAR(50) NOT NULL COMMENT '城市',
    district VARCHAR(50) NOT NULL COMMENT '区/县',
    detail VARCHAR(200) NOT NULL COMMENT '详细地址',
    is_default TINYINT(1) DEFAULT 0 COMMENT '是否默认地址（0否，1是）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

-- 3. 收藏表
CREATE TABLE IF NOT EXISTS favorites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_user_product (user_id, product_id),
    INDEX idx_user_id (user_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- 4. 购物车表
CREATE TABLE IF NOT EXISTS cart_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '购物车ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    package_id BIGINT COMMENT '套餐ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '数量',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 5. 轮播图表
CREATE TABLE IF NOT EXISTS banners (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '轮播图ID',
    title VARCHAR(100) NOT NULL COMMENT '标题',
    image VARCHAR(500) NOT NULL COMMENT '图片URL',
    link_type VARCHAR(20) COMMENT '链接类型（product/service）',
    link_id BIGINT COMMENT '链接ID',
    sort_order INT DEFAULT 0 COMMENT '排序',
    status TINYINT(1) DEFAULT 1 COMMENT '状态（0禁用，1启用）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_status (status),
    INDEX idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';

-- 6. 消息表
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '消息ID',
    user_id BIGINT DEFAULT 0 COMMENT '用户ID（0为系统消息）',
    title VARCHAR(100) NOT NULL COMMENT '标题',
    content TEXT COMMENT '内容',
    type VARCHAR(20) DEFAULT 'system' COMMENT '类型（system/order/activity）',
    icon VARCHAR(50) COMMENT '图标',
    is_read TINYINT(1) DEFAULT 0 COMMENT '是否已读（0否，1是）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_type (type),
    INDEX idx_is_read (is_read)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- 7. 门店信息表
CREATE TABLE IF NOT EXISTS store_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '门店ID',
    name VARCHAR(100) NOT NULL COMMENT '门店名称',
    address VARCHAR(200) NOT NULL COMMENT '地址',
    phone VARCHAR(20) NOT NULL COMMENT '电话',
    business_hours VARCHAR(100) COMMENT '营业时间',
    latitude DECIMAL(10,8) COMMENT '纬度',
    longitude DECIMAL(11,8) COMMENT '经度',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='门店信息表';

-- 8. 商品套餐表
CREATE TABLE IF NOT EXISTS product_packages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '套餐ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    name VARCHAR(50) NOT NULL COMMENT '套餐名称',
    price DECIMAL(10,2) NOT NULL COMMENT '套餐价格',
    stock INT DEFAULT 0 COMMENT '库存',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品套餐表';

-- 9. 积分商品表
CREATE TABLE IF NOT EXISTS points_products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '积分商品ID',
    name VARCHAR(100) NOT NULL COMMENT '商品名称',
    image VARCHAR(500) COMMENT '图片URL',
    points INT NOT NULL COMMENT '所需积分',
    stock INT DEFAULT 0 COMMENT '库存',
    status TINYINT(1) DEFAULT 1 COMMENT '状态（0下架，1上架）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分商品表';

-- 10. 会员档位表
CREATE TABLE IF NOT EXISTS member_tiers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '档位ID',
    name VARCHAR(50) NOT NULL COMMENT '档位名称',
    amount DECIMAL(10,2) NOT NULL COMMENT '充值金额',
    bonus DECIMAL(10,2) DEFAULT 0 COMMENT '赠送金额',
    vip_level VARCHAR(20) NOT NULL COMMENT '升级后的VIP等级',
    points_bonus INT DEFAULT 0 COMMENT '赠送积分',
    sort_order INT DEFAULT 0 COMMENT '排序',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员档位表';

-- 11. 签到记录表
CREATE TABLE IF NOT EXISTS sign_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '签到ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    sign_date DATE NOT NULL COMMENT '签到日期',
    points INT DEFAULT 0 COMMENT '获得积分',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_user_date (user_id, sign_date),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='签到记录表';

-- 12. 充值记录表
CREATE TABLE IF NOT EXISTS recharge_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '充值记录ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    tier_id BIGINT COMMENT '档位ID',
    amount DECIMAL(10,2) NOT NULL COMMENT '充值金额',
    bonus DECIMAL(10,2) DEFAULT 0 COMMENT '赠送金额',
    transaction_no VARCHAR(50) COMMENT '交易流水号',
    status VARCHAR(20) DEFAULT 'completed' COMMENT '状态',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_transaction_no (transaction_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值记录表';

-- 13. 积分兑换记录表
CREATE TABLE IF NOT EXISTS points_exchanges (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '兑换记录ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    points INT NOT NULL COMMENT '消耗积分',
    address_id BIGINT COMMENT '收货地址ID',
    status VARCHAR(20) DEFAULT 'pending' COMMENT '状态',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分兑换记录表';

-- 14. 秒杀商品表
CREATE TABLE IF NOT EXISTS seckill_products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '秒杀ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    seckill_price DECIMAL(10,2) NOT NULL COMMENT '秒杀价',
    stock INT NOT NULL COMMENT '秒杀库存',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME NOT NULL COMMENT '结束时间',
    status TINYINT(1) DEFAULT 1 COMMENT '状态（0禁用，1启用）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_product_id (product_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';

-- 15. 服务表（扩展原有service表）
ALTER TABLE service ADD COLUMN icon VARCHAR(100) COMMENT '图标' AFTER type;
ALTER TABLE service ADD COLUMN images TEXT COMMENT '服务图片（JSON数组）' AFTER duration;

-- 16. 服务预约表（扩展原有booking表）
ALTER TABLE booking ADD COLUMN package_id BIGINT COMMENT '套餐ID' AFTER service_id;
ALTER TABLE booking ADD COLUMN remark VARCHAR(200) COMMENT '备注' AFTER booking_time;

-- 17. 订单表（扩展原有order表）
ALTER TABLE `order` ADD COLUMN pay_method VARCHAR(20) COMMENT '支付方式' AFTER status;
ALTER TABLE `order` ADD COLUMN address_id BIGINT COMMENT '收货地址ID' AFTER pay_time;
ALTER TABLE `order` ADD COLUMN remark VARCHAR(200) COMMENT '备注' AFTER address_id;

-- 18. 订单商品表（扩展原有order_item表）
ALTER TABLE order_item ADD COLUMN package_id BIGINT COMMENT '套餐ID' AFTER product_id;
ALTER TABLE order_item ADD COLUMN package_name VARCHAR(50) COMMENT '套餐名称' AFTER package_id;

-- 输出完成信息
SELECT '数据库表创建完成！' AS message;
SELECT '共创建/修改表：users, addresses, favorites, cart_items, banners, messages, store_info, product_packages, points_products, member_tiers, sign_records, recharge_records, points_exchanges, seckill_products' AS tables_created;
