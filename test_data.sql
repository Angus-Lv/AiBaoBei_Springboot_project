-- 爱宝贝儿孕婴生活馆 - 测试数据初始化脚本
-- 创建日期：2026-04-30

-- 使用数据库
USE aibaobei;

-- 1. 插入用户测试数据
INSERT INTO users (username, password, nickname, avatar, phone, vip_level, points, balance) VALUES
('test001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', '宝宝妈妈', 'https://example.com/avatar1.jpg', '13800138001', 'VIP1', 500, 100.00),
('test002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', '奶爸老王', 'https://example.com/avatar2.jpg', '13800138002', 'VIP2', 1200, 500.00),
('test003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', '宝贝爸比', 'https://example.com/avatar3.jpg', '13800138003', 'VIP0', 0, 0.00);

-- 2. 插入收货地址测试数据
INSERT INTO addresses (user_id, name, phone, province, city, district, detail, is_default) VALUES
(1, '张三', '13800138001', '河南省', '新乡市', '红旗区', '某某街道某某小区1号楼101', 1),
(1, '李四', '13800138004', '河南省', '新乡市', '牧野区', '某某路某某小区2号楼202', 0),
(2, '王五', '13800138002', '河南省', '新乡市', '卫滨区', '某某大道某某小区3号楼303', 1);

-- 3. 插入商品分类测试数据
INSERT INTO category (name, icon, parent_id, sort_order) VALUES
('奶粉', '🍼', 0, 1),
('纸尿裤', '👶', 0, 2),
('服装', '👔', 0, 3),
('玩具', '🧸', 0, 4),
('喂养', '�奶瓶', 0, 5),
('护理', '🧴', 0, 6),
('营养', '💊', 0, 7),
('出行', '🚗', 0, 8);

-- 4. 插入商品测试数据（部分）
INSERT INTO product (name, category, price, original_price, stock, sales, status, is_seckill, is_hot, image) VALUES
('爱他美白金版奶粉', 'milk', 199.00, 299.00, 200, 1258, 1, 1, 1, 'https://example.com/images/milk1.jpg'),
('美赞臣蓝臻奶粉', 'milk', 268.00, 368.00, 150, 892, 1, 0, 1, 'https://example.com/images/milk2.jpg'),
('花王纸尿裤', 'diaper', 89.00, 129.00, 500, 2341, 1, 1, 0, 'https://example.com/images/diaper1.jpg'),
('帮宝适纸尿裤', 'diaper', 109.00, 159.00, 300, 1567, 1, 0, 0, 'https://example.com/images/diaper2.jpg'),
('婴儿连体衣', 'clothes', 69.00, 99.00, 250, 1890, 1, 0, 0, 'https://example.com/images/clothes1.jpg'),
('婴儿睡袋', 'clothes', 159.00, 229.00, 120, 678, 1, 1, 0, 'https://example.com/images/clothes2.jpg'),
('费雪安抚海马', 'toys', 128.00, 168.00, 100, 567, 1, 1, 0, 'https://example.com/images/toy1.jpg'),
('乐高积木', 'toys', 299.00, 399.00, 80, 423, 1, 0, 0, 'https://example.com/images/toy2.jpg');

-- 5. 插入商品套餐测试数据
INSERT INTO product_packages (product_id, name, price, stock) VALUES
(1, '单罐', 199.00, 100),
(1, '两罐套装', 380.00, 50),
(1, '四罐套餐', 750.00, 30),
(2, '单罐', 268.00, 80),
(2, '两罐套装', 520.00, 40),
(3, '小包', 89.00, 200),
(3, '大包', 168.00, 150),
(7, '单个', 128.00, 50),
(7, '两个套装', 240.00, 30);

-- 6. 插入会员档位测试数据
INSERT INTO member_tiers (name, amount, bonus, vip_level, points_bonus, sort_order) VALUES
('VIP1', 100.00, 10.00, 'VIP1', 100, 1),
('VIP2', 500.00, 75.00, 'VIP2', 600, 2),
('VIP3', 1000.00, 200.00, 'VIP3', 1500, 3),
('VIP4', 2000.00, 500.00, 'VIP4', 3500, 4),
('VIP5', 5000.00, 1500.00, 'VIP5', 10000, 5);

-- 7. 插入积分商品测试数据
INSERT INTO points_products (name, image, points, stock, status) VALUES
('婴儿湿巾', 'https://example.com/images/wetwipes.jpg', 100, 50, 1),
('儿童牙刷', 'https://example.com/images/toothbrush.jpg', 200, 30, 1),
('婴儿护臀膏', 'https://example.com/images/cream.jpg', 150, 40, 1),
('儿童水杯', 'https://example.com/images/cup.jpg', 300, 20, 1),
('婴儿睡袋', 'https://example.com/images/sleepbag.jpg', 500, 10, 1);

-- 8. 插入轮播图测试数据
INSERT INTO banners (title, image, link_type, link_id, sort_order, status) VALUES
('春季优惠', 'https://example.com/images/banner1.jpg', 'product', 1, 1, 1),
('奶粉专场', 'https://example.com/images/banner2.jpg', 'product', 2, 2, 1),
('婴儿游泳', 'https://example.com/images/banner3.jpg', 'service', 1, 3, 1),
('满月照特惠', 'https://example.com/images/banner4.jpg', 'service', 3, 4, 1),
('新会员专享', 'https://example.com/images/banner5.jpg', 'product', 5, 5, 1);

-- 9. 插入服务类型测试数据
INSERT INTO service (type, name, icon, price, description, duration, status) VALUES
('bath', '婴儿游泳', '🏊', 58.00, '专业婴儿游泳服务，水温恒定37度，配有专业抚触师', 30, 1),
('hair', '婴儿理发', '✂️', 30.00, '专业婴儿理发服务，使用安全理发工具', 15, 1),
('photo', '满月照拍摄', '📷', 199.00, '专业满月照拍摄，赠送精修照片', 60, 1),
('playground', '游乐园', '🎠', 38.00, '婴儿游乐园，安全卫生', 120, 1),
('education', '早教课程', '📚', 88.00, '专业早教课程，开发智力', 45, 1),
('dad', '奶爸服务', '👨', 68.00, '专为奶爸提供的育儿指导服务', 30, 1);

-- 10. 插入门店信息测试数据
INSERT INTO store_info (name, address, phone, business_hours, latitude, longitude) VALUES
('爱宝贝儿孕婴生活馆', '河南省新乡市马庄乡常兴集村', '13273721553', '07:30-21:00', 35.3018, 113.8656);

-- 11. 插入签到记录测试数据
INSERT INTO sign_records (user_id, sign_date, points) VALUES
(1, CURDATE() - INTERVAL 5 DAY, 10),
(1, CURDATE() - INTERVAL 4 DAY, 10),
(1, CURDATE() - INTERVAL 3 DAY, 10),
(1, CURDATE() - INTERVAL 2 DAY, 10),
(1, CURDATE() - INTERVAL 1 DAY, 10),
(2, CURDATE() - INTERVAL 3 DAY, 10),
(2, CURDATE() - INTERVAL 2 DAY, 10),
(2, CURDATE() - INTERVAL 1 DAY, 10);

-- 12. 插入收藏测试数据
INSERT INTO favorites (user_id, product_id) VALUES
(1, 1),
(1, 3),
(1, 7),
(2, 2),
(2, 5);

-- 13. 插入购物车测试数据
INSERT INTO cart_items (user_id, product_id, package_id, quantity) VALUES
(1, 1, 1, 2),
(1, 3, 6, 1),
(1, 7, 8, 1),
(2, 2, 4, 1),
(2, 5, NULL, 3);

-- 14. 插入消息测试数据
INSERT INTO messages (user_id, title, content, type, icon, is_read) VALUES
(0, '欢迎使用爱宝贝儿孕婴生活馆', '感谢您使用我们的服务，祝您购物愉快！', 'system', '🎉', 0),
(1, '订单已发货', '您的订单 ORD202401291200001 已发货，请注意查收', 'order', '📦', 0),
(1, '积分即将过期', '您有500积分将于30天后过期，请尽快使用', 'activity', '🎁', 1),
(2, '新商品上架', '爱他美最新配方奶粉已上架，欢迎选购', 'activity', '🆕', 0);

-- 15. 插入秒杀商品测试数据
INSERT INTO seckill_products (product_id, seckill_price, stock, start_time, end_time, status) VALUES
(1, 199.00, 50, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1),
(3, 79.00, 100, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1),
(6, 139.00, 30, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1),
(7, 99.00, 40, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1);

-- 输出完成信息
SELECT '测试数据插入完成！' AS message;
SELECT COUNT(*) AS user_count FROM users;
SELECT COUNT(*) AS product_count FROM product;
SELECT COUNT(*) AS service_count FROM service;
