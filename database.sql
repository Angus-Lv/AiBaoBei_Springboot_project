-- 创建数据库
CREATE DATABASE IF NOT EXISTS aibaobei DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE aibaobei;

-- 管理员表
CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'admin',
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login_time DATETIME,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 会员表
CREATE TABLE IF NOT EXISTS member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    password VARCHAR(255),
    points INT DEFAULT 0,
    balance DECIMAL(10,2) DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login_time DATETIME,
    INDEX idx_phone (phone),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 充值档位表
CREATE TABLE IF NOT EXISTS recharge_tier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(10,2) NOT NULL,
    bonus DECIMAL(10,2) DEFAULT 0.00,
    sort INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 充值记录表
CREATE TABLE IF NOT EXISTS recharge (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_no VARCHAR(50) NOT NULL UNIQUE,
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    amount DECIMAL(10,2) NOT NULL,
    bonus DECIMAL(10,2) DEFAULT 0.00,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    complete_time DATETIME,
    INDEX idx_member_id (member_id),
    INDEX idx_status (status),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 签到设置表
CREATE TABLE IF NOT EXISTS signin_setting (
    id INT PRIMARY KEY AUTO_INCREMENT,
    daily_points INT NOT NULL DEFAULT 10,
    continuous_rewards TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'enabled',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 签到记录表
CREATE TABLE IF NOT EXISTS signin_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    points INT NOT NULL,
    signin_date DATE NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_member_id (member_id),
    INDEX idx_signin_date (signin_date),
    UNIQUE KEY uk_member_date (member_id, signin_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 积分设置表
CREATE TABLE IF NOT EXISTS points_setting (
    id INT PRIMARY KEY AUTO_INCREMENT,
    points_per_yuan DECIMAL(10,2) DEFAULT 1.00,
    signin_points INT DEFAULT 10,
    register_points INT DEFAULT 100,
    point_expiry_days INT DEFAULT 365,
    enable_points BOOLEAN DEFAULT TRUE,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 积分记录表
CREATE TABLE IF NOT EXISTS points_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    points INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    type_detail VARCHAR(50),
    balance INT NOT NULL,
    remark VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_member_id (member_id),
    INDEX idx_type (type),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 礼品表
CREATE TABLE IF NOT EXISTS gift (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    points INT NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    exchanged_count INT DEFAULT 0,
    image VARCHAR(255),
    description TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 兑换记录表
CREATE TABLE IF NOT EXISTS exchange (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_no VARCHAR(50) NOT NULL UNIQUE,
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    gift_id INT NOT NULL,
    gift_name VARCHAR(100),
    gift_points INT NOT NULL,
    receiver_name VARCHAR(50),
    receiver_phone VARCHAR(20),
    receiver_address VARCHAR(255),
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    exchange_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    complete_time DATETIME,
    INDEX idx_member_id (member_id),
    INDEX idx_status (status),
    INDEX idx_exchange_time (exchange_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品分类表
CREATE TABLE IF NOT EXISTS category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    value VARCHAR(50) NOT NULL UNIQUE,
    sort INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_sort (sort)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品表
CREATE TABLE IF NOT EXISTS product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    image VARCHAR(255),
    images TEXT,
    price DECIMAL(10,2) NOT NULL,
    original_price DECIMAL(10,2),
    stock INT NOT NULL DEFAULT 0,
    sales INT DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    is_seckill BOOLEAN DEFAULT FALSE,
    seckill_price DECIMAL(10,2),
    seckill_stock INT,
    spec VARCHAR(100),
    description TEXT,
    detail_images TEXT,
    packages TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_category (category),
    INDEX idx_status (status),
    INDEX idx_is_seckill (is_seckill)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 订单表
CREATE TABLE IF NOT EXISTS `order` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id VARCHAR(50) NOT NULL UNIQUE,
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    member_phone VARCHAR(20),
    total_price DECIMAL(10,2) NOT NULL,
    total_quantity INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    pay_method VARCHAR(50),
    pay_time DATETIME,
    receiver_name VARCHAR(50),
    receiver_phone VARCHAR(20),
    receiver_address VARCHAR(255),
    remark VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_member_id (member_id),
    INDEX idx_status (status),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 订单商品表
CREATE TABLE IF NOT EXISTS order_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    product_name VARCHAR(100),
    product_image VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    spec VARCHAR(100),
    INDEX idx_order_id (order_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 退款表
CREATE TABLE IF NOT EXISTS refund (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id VARCHAR(50) NOT NULL,
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    refund_amount DECIMAL(10,2) NOT NULL,
    reason VARCHAR(255),
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    process_time DATETIME,
    remark VARCHAR(255),
    INDEX idx_order_id (order_id),
    INDEX idx_member_id (member_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 服务类型表
CREATE TABLE IF NOT EXISTS service_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    value VARCHAR(50) NOT NULL UNIQUE,
    sort INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_sort (sort)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 服务表
CREATE TABLE IF NOT EXISTS service (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    duration INT,
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_type (type),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 预约表
CREATE TABLE IF NOT EXISTS booking (
    id INT PRIMARY KEY AUTO_INCREMENT,
    service_id INT NOT NULL,
    service_name VARCHAR(100),
    member_id INT NOT NULL,
    member_nickname VARCHAR(50),
    member_phone VARCHAR(20),
    baby_name VARCHAR(50),
    baby_age VARCHAR(20),
    date DATE NOT NULL,
    time VARCHAR(15) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    remark VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_service_id (service_id),
    INDEX idx_member_id (member_id),
    INDEX idx_date (date),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 系统设置表
CREATE TABLE IF NOT EXISTS system_setting (
    id INT PRIMARY KEY AUTO_INCREMENT,
    setting_key VARCHAR(50) NOT NULL UNIQUE,
    setting_value TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_setting_key (setting_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 操作日志表
CREATE TABLE IF NOT EXISTS operation_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    admin_id INT NOT NULL,
    admin_name VARCHAR(50),
    action VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    ip VARCHAR(50),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_admin_id (admin_id),
    INDEX idx_action (action),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认管理员（密码：admin123，需要加密）
INSERT INTO admin (username, password, role, status) VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'admin', 'active');

-- 插入默认签到设置
INSERT INTO signin_setting (daily_points, continuous_rewards, status) VALUES (10, '[{"days":3,"points":50},{"days":7,"points":100}]', 'enabled');

-- 插入默认积分设置
INSERT INTO points_setting (points_per_yuan, signin_points, register_points, point_expiry_days, enable_points) VALUES (1.00, 10, 100, 365, TRUE);

-- 插入默认系统设置
INSERT INTO system_setting (setting_key, setting_value) VALUES 
('siteName', '爱宝贝儿'),
('siteDescription', '专业母婴用品商城'),
('contactPhone', '400-123-4567'),
('contactEmail', 'service@aibaobei.com'),
('address', '北京市朝阳区'),
('logo', 'logo.png'),
('favicon', 'favicon.ico'),
('registerEnabled', 'true'),
('loginAttempts', '5'),
('lockoutDuration', '30'),
('passwordMinLength', '6');
