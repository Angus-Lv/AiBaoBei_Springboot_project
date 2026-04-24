-- 插入测试商品
INSERT INTO product (name, category, image, price, original_price, stock, sales, status, is_seckill, create_time) VALUES 
('爱他美白金版奶粉', 'milk', 'product1.jpg', 199.00, 299.00, 200, 1258, 'active', 1, NOW()),
('美赞臣蓝臻奶粉', 'milk', 'product2.jpg', 268.00, 368.00, 150, 892, 'active', 0, NOW()),
('花王妙而舒纸尿裤', 'diaper', 'product3.jpg', 89.00, 129.00, 500, 2341, 'active', 1, NOW()),
('帮宝适一级帮纸尿裤', 'diaper', 'product4.jpg', 109.00, 159.00, 300, 1567, 'active', 0, NOW()),
('嘉宝米粉', 'food', 'product5.jpg', 58.00, 78.00, 400, 987, 'active', 0, NOW()),
('贝亲婴儿辅食', 'food', 'product6.jpg', 35.00, 45.00, 600, 1456, 'active', 0, NOW()),
('费雪安抚海马', 'toy', 'product7.jpg', 128.00, 168.00, 100, 567, 'active', 1, NOW()),
('乐高积木', 'toy', 'product8.jpg', 299.00, 399.00, 80, 423, 'active', 0, NOW()),
('婴儿连体衣', 'clothes', 'product9.jpg', 69.00, 99.00, 250, 1890, 'active', 0, NOW()),
('婴儿睡袋', 'clothes', 'product10.jpg', 159.00, 229.00, 120, 678, 'active', 0, NOW());
