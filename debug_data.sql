-- 清空现有数据
DELETE FROM product;
DELETE FROM category;

-- 插入分类数据
INSERT INTO category (id, name, value, sort, create_time) VALUES 
(1, 'All', 'all', 0, NOW()),
(2, 'Milk Powder', 'milk', 1, NOW()),
(3, 'Diaper', 'diaper', 2, NOW()),
(4, 'Clothes', 'clothes', 3, NOW()),
(5, 'Toys', 'toys', 4, NOW()),
(6, 'Feeding', 'feeding', 5, NOW()),
(7, 'Care', 'care', 6, NOW()),
(8, 'Nutrition', 'nutrition', 7, NOW()),
(9, 'Travel', 'travel', 8, NOW());

-- 插入商品数据
INSERT INTO product (name, category, image, price, original_price, stock, sales, status, is_seckill, create_time) VALUES 
('Aptamil Platinum Milk Powder', 'milk', 'https://example.com/images/milk1.jpg', 199.00, 299.00, 200, 1258, 'active', 1, NOW()),
('MeadJohnson Enfinitas Milk Powder', 'milk', 'https://example.com/images/milk2.jpg', 268.00, 368.00, 150, 892, 'active', 0, NOW()),
('Merries Diapers', 'diaper', 'https://example.com/images/diaper1.jpg', 89.00, 129.00, 500, 2341, 'active', 1, NOW()),
('Pampers Premium Diapers', 'diaper', 'https://example.com/images/diaper2.jpg', 109.00, 159.00, 300, 1567, 'active', 0, NOW()),
('Gerber Rice Cereal', 'feeding', 'https://example.com/images/feeding1.jpg', 58.00, 78.00, 400, 987, 'active', 0, NOW()),
('Pigeon Baby Food', 'feeding', 'https://example.com/images/feeding2.jpg', 35.00, 45.00, 600, 1456, 'active', 0, NOW()),
('Fisher Price Soothing Seahorse', 'toys', 'https://example.com/images/toy1.jpg', 128.00, 168.00, 100, 567, 'active', 1, NOW()),
('Lego Building Blocks', 'toys', 'https://example.com/images/toy2.jpg', 299.00, 399.00, 80, 423, 'active', 0, NOW()),
('Baby Romper', 'clothes', 'https://example.com/images/clothes1.jpg', 69.00, 99.00, 250, 1890, 'active', 0, NOW()),
('Baby Sleeping Bag', 'clothes', 'https://example.com/images/clothes2.jpg', 159.00, 229.00, 120, 678, 'active', 0, NOW());
