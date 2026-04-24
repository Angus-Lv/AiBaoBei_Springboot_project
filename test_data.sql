-- 插入订单数据
INSERT INTO `order` (order_id, member_id, member_nickname, member_phone, total_price, total_quantity, status, pay_method, pay_time, receiver_name, receiver_phone, receiver_address, remark, create_time, update_time) VALUES 
('20260424001', 1, 'user1', '13800138001', 398.00, 2, 'completed', 'wechat', NOW(), '张三', '13800138001', '北京市朝阳区', '尽快发货', NOW(), NOW()),
('20260424002', 2, 'user2', '13800138002', 268.00, 1, 'pending', NULL, NULL, '李四', '13800138002', '上海市浦东新区', NULL, NOW(), NOW()),
('20260424003', 3, 'user3', '13800138003', 587.00, 3, 'shipped', 'alipay', NOW(), '王五', '13800138003', '广州市天河区', '包装好一点', NOW(), NOW()),
('20260424004', 1, 'user1', '13800138001', 199.00, 1, 'completed', 'wechat', NOW(), '张三', '13800138001', '北京市朝阳区', NULL, NOW(), NOW()),
('20260424005', 2, 'user2', '13800138002', 447.00, 2, 'pending', NULL, NULL, '李四', '13800138002', '上海市浦东新区', '周末送货', NOW(), NOW()),
('20260424006', 3, 'user3', '13800138003', 128.00, 1, 'shipped', 'alipay', NOW(), '王五', '13800138003', '广州市天河区', NULL, NOW(), NOW()),
('20260424007', 1, 'user1', '13800138001', 89.00, 1, 'completed', 'wechat', NOW(), '张三', '13800138001', '北京市朝阳区', NULL, NOW(), NOW()),
('20260424008', 2, 'user2', '13800138002', 358.00, 2, 'pending', NULL, NULL, '李四', '13800138002', '上海市浦东新区', NULL, NOW(), NOW()),
('20260424009', 3, 'user3', '13800138003', 299.00, 1, 'shipped', 'alipay', NOW(), '王五', '13800138003', '广州市天河区', '需要发票', NOW(), NOW()),
('20260424010', 1, 'user1', '13800138001', 178.00, 2, 'completed', 'wechat', NOW(), '张三', '13800138001', '北京市朝阳区', NULL, NOW(), NOW());

-- 插入订单商品数据
INSERT INTO order_item (order_id, product_id, product_name, product_image, price, quantity, spec) VALUES 
(1, 33, '经典美白奶粉', 'image1.jpg', 199.00, 1, '标准装'),
(1, 34, '爱贝婴儿纸尿裤', 'image2.jpg', 268.00, 1, 'L码'),
(2, 34, '爱贝婴儿纸尿裤', 'image2.jpg', 268.00, 1, 'L码'),
(3, 33, '经典美白奶粉', 'image1.jpg', 199.00, 1, '标准装'),
(3, 34, '爱贝婴儿纸尿裤', 'image2.jpg', 268.00, 1, 'L码'),
(3, 35, '婴儿连体衣', 'image3.jpg', 120.00, 1, '6-12个月'),
(4, 33, '经典美白奶粉', 'image1.jpg', 199.00, 1, '标准装'),
(5, 34, '爱贝婴儿纸尿裤', 'image2.jpg', 268.00, 1, 'L码'),
(5, 35, '婴儿连体衣', 'image3.jpg', 120.00, 1, '6-12个月'),
(6, 36, '婴儿奶瓶', 'image4.jpg', 128.00, 1, '240ml'),
(7, 37, '婴儿洗护套装', 'image5.jpg', 89.00, 1, '套装'),
(8, 38, '婴儿推车', 'image6.jpg', 299.00, 1, '折叠款'),
(8, 39, '婴儿床', 'image7.jpg', 59.00, 1, '便携式'),
(9, 40, '高端床垫', 'image8.jpg', 299.00, 1, '1.2m'),
(10, 41, '婴儿玩具', 'image9.jpg', 89.00, 1, '益智类'),
(10, 42, '营养米粉', 'image10.jpg', 89.00, 1, '高铁锌钙');