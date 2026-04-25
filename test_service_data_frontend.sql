-- 服务类型测试数据（根据前端提供的服务类型）
INSERT INTO service_type (name, value, sort) VALUES
('婴儿洗澡', 'baby_bath', 1),
('宝宝必看', 'baby_must_see', 2),
('儿童游乐场', 'children_playground', 3),
('早教课程', 'early_education', 4),
('婴儿理发', 'baby_haircut', 5),
('满月照', 'full_moon_photo', 6);

-- 服务测试数据
INSERT INTO service (name, type, price, description, duration, status) VALUES
('婴儿洗澡服务', 'baby_bath', 199.00, '专业婴儿洗澡服务，包括洗澡、抚触、按摩等', 60, 'active'),
('宝宝必看套餐', 'baby_must_see', 299.00, '宝宝发育评估、健康检查等服务', 90, 'active'),
('儿童游乐场体验', 'children_playground', 159.00, '儿童游乐场全天体验，包含各种游乐设施', 240, 'active'),
('早教课程体验', 'early_education', 399.00, '专业早教课程，促进宝宝智力发育', 90, 'active'),
('婴儿理发服务', 'baby_haircut', 89.00, '专业婴儿理发，安全卫生', 30, 'active'),
('满月照套餐', 'full_moon_photo', 599.00, '专业满月照拍摄服务，包含多套服装', 120, 'active');

-- 预约测试数据
INSERT INTO booking (service_id, service_name, member_id, member_nickname, member_phone, baby_name, baby_age, date, time, status, remark) VALUES
(1, '婴儿洗澡服务', 1, 'user1', '13800138001', '宝宝1', '2个月', '2026-04-25', '09:00', 'pending', '需要提前准备好宝宝用品'),
(2, '宝宝必看套餐', 1, 'user1', '13800138001', '宝宝1', '2个月', '2026-04-26', '10:00', 'confirmed', '无特殊要求'),
(3, '儿童游乐场体验', 2, 'user2', '13800138002', '宝宝2', '3岁', '2026-04-27', '14:00', 'completed', '孩子很喜欢游乐场'),
(4, '早教课程体验', 3, 'user3', '13800138003', '宝宝3', '1岁', '2026-04-28', '15:00', 'pending', '希望有适合1岁宝宝的课程'),
(5, '婴儿理发服务', 4, 'user4', '13800138004', '宝宝4', '3个月', '2026-04-29', '09:30', 'confirmed', '第一次理发，希望理发师有耐心'),
(6, '满月照套餐', 5, 'user5', '13800138005', '宝宝5', '1个月', '2026-04-30', '14:30', 'completed', '需要提前沟通拍摄风格');
