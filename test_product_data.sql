-- 清空现有商品数据
DELETE FROM product;

-- 插入商品数据
INSERT INTO product (name, category, image, images, price, original_price, stock, sales, status, is_seckill, seckill_price, seckill_stock, spec, description, detail_images, packages, create_time) VALUES 
-- 奶粉分类
('爱他美白金版奶粉', 'milk', 'https://example.com/images/milk1.jpg', '["https://example.com/images/milk1-1.jpg", "https://example.com/images/milk1-2.jpg"]', 199.00, 299.00, 200, 1258, 'active', 1, 159.00, 50, '800g/罐', '德国进口，含有DHA和ARA，促进大脑和视力发育', '["https://example.com/images/milk1-detail1.jpg", "https://example.com/images/milk1-detail2.jpg"]', '[{"name": "单罐", "price": 199.00, "stock": 200}, {"name": "三罐套装", "price": 569.00, "stock": 50}]', NOW()),
('美赞臣蓝臻奶粉', 'milk', 'https://example.com/images/milk2.jpg', '["https://example.com/images/milk2-1.jpg", "https://example.com/images/milk2-2.jpg"]', 268.00, 368.00, 150, 892, 'active', 0, NULL, NULL, '900g/罐', '含有乳铁蛋白，增强免疫力', '["https://example.com/images/milk2-detail1.jpg", "https://example.com/images/milk2-detail2.jpg"]', '[{"name": "单罐", "price": 268.00, "stock": 150}, {"name": "两罐套装", "price": 518.00, "stock": 30}]', NOW()),

-- 尿裤分类
('花王纸尿裤', 'diaper', 'https://example.com/images/diaper1.jpg', '["https://example.com/images/diaper1-1.jpg", "https://example.com/images/diaper1-2.jpg"]', 89.00, 129.00, 500, 2341, 'active', 1, 69.00, 100, 'M码 64片', '日本进口，超薄透气，防红屁屁', '["https://example.com/images/diaper1-detail1.jpg", "https://example.com/images/diaper1-detail2.jpg"]', '[{"name": "单包", "price": 89.00, "stock": 500}, {"name": "三包套装", "price": 259.00, "stock": 100}]', NOW()),
('帮宝适纸尿裤', 'diaper', 'https://example.com/images/diaper2.jpg', '["https://example.com/images/diaper2-1.jpg", "https://example.com/images/diaper2-2.jpg"]', 109.00, 159.00, 300, 1567, 'active', 0, NULL, NULL, 'L码 52片', '美国进口，干爽舒适', '["https://example.com/images/diaper2-detail1.jpg", "https://example.com/images/diaper2-detail2.jpg"]', '[{"name": "单包", "price": 109.00, "stock": 300}, {"name": "两包套装", "price": 209.00, "stock": 50}]', NOW()),

-- 服装分类
('婴儿连体衣', 'clothes', 'https://example.com/images/clothes1.jpg', '["https://example.com/images/clothes1-1.jpg", "https://example.com/images/clothes1-2.jpg"]', 69.00, 99.00, 250, 1890, 'active', 0, NULL, NULL, '0-6个月', '纯棉材质，柔软舒适', '["https://example.com/images/clothes1-detail1.jpg", "https://example.com/images/clothes1-detail2.jpg"]', '[{"name": "单件", "price": 69.00, "stock": 250}, {"name": "两件套装", "price": 129.00, "stock": 50}]', NOW()),
('婴儿睡袋', 'clothes', 'https://example.com/images/clothes2.jpg', '["https://example.com/images/clothes2-1.jpg", "https://example.com/images/clothes2-2.jpg"]', 159.00, 229.00, 120, 678, 'active', 1, 129.00, 30, '1-3岁', '保暖透气，防踢被子', '["https://example.com/images/clothes2-detail1.jpg", "https://example.com/images/clothes2-detail2.jpg"]', '[{"name": "单件", "price": 159.00, "stock": 120}]', NOW()),

-- 玩具分类
('费雪安抚海马', 'toys', 'https://example.com/images/toy1.jpg', '["https://example.com/images/toy1-1.jpg", "https://example.com/images/toy1-2.jpg"]', 128.00, 168.00, 100, 567, 'active', 1, 99.00, 20, '蓝色', '轻柔音乐，安抚宝宝情绪', '["https://example.com/images/toy1-detail1.jpg", "https://example.com/images/toy1-detail2.jpg"]', '[{"name": "单个", "price": 128.00, "stock": 100}]', NOW()),
('乐高积木', 'toys', 'https://example.com/images/toy2.jpg', '["https://example.com/images/toy2-1.jpg", "https://example.com/images/toy2-2.jpg"]', 299.00, 399.00, 80, 423, 'active', 0, NULL, NULL, '100块', '开发智力，培养创造力', '["https://example.com/images/toy2-detail1.jpg", "https://example.com/images/toy2-detail2.jpg"]', '[{"name": "盒装", "price": 299.00, "stock": 80}]', NOW()),

-- 喂养分类
('贝亲奶瓶', 'feeding', 'https://example.com/images/feeding1.jpg', '["https://example.com/images/feeding1-1.jpg", "https://example.com/images/feeding1-2.jpg"]', 89.00, 129.00, 300, 1234, 'active', 0, NULL, NULL, '240ml', '防胀气，宽口径设计', '["https://example.com/images/feeding1-detail1.jpg", "https://example.com/images/feeding1-detail2.jpg"]', '[{"name": "单个", "price": 89.00, "stock": 300}, {"name": "两个套装", "price": 169.00, "stock": 50}]', NOW()),
('嘉宝米粉', 'feeding', 'https://example.com/images/feeding2.jpg', '["https://example.com/images/feeding2-1.jpg", "https://example.com/images/feeding2-2.jpg"]', 58.00, 78.00, 400, 987, 'active', 1, 45.00, 50, '227g', '高铁配方，易于消化', '["https://example.com/images/feeding2-detail1.jpg", "https://example.com/images/feeding2-detail2.jpg"]', '[{"name": "单罐", "price": 58.00, "stock": 400}, {"name": "三罐套装", "price": 169.00, "stock": 100}]', NOW()),

-- 护理分类
('婴儿润肤霜', 'care', 'https://example.com/images/care1.jpg', '["https://example.com/images/care1-1.jpg", "https://example.com/images/care1-2.jpg"]', 45.00, 69.00, 200, 876, 'active', 0, NULL, NULL, '100ml', '天然成分，滋润保湿', '["https://example.com/images/care1-detail1.jpg", "https://example.com/images/care1-detail2.jpg"]', '[{"name": "单瓶", "price": 45.00, "stock": 200}]', NOW()),
('婴儿洗发沐浴露', 'care', 'https://example.com/images/care2.jpg', '["https://example.com/images/care2-1.jpg", "https://example.com/images/care2-2.jpg"]', 59.00, 89.00, 150, 654, 'active', 1, 45.00, 30, '200ml', '二合一配方，无泪配方', '["https://example.com/images/care2-detail1.jpg", "https://example.com/images/care2-detail2.jpg"]', '[{"name": "单瓶", "price": 59.00, "stock": 150}]', NOW()),

-- 营养分类
('儿童维生素D', 'nutrition', 'https://example.com/images/nutrition1.jpg', '["https://example.com/images/nutrition1-1.jpg", "https://example.com/images/nutrition1-2.jpg"]', 99.00, 139.00, 100, 432, 'active', 0, NULL, NULL, '60粒', '促进钙吸收，增强免疫力', '["https://example.com/images/nutrition1-detail1.jpg", "https://example.com/images/nutrition1-detail2.jpg"]', '[{"name": "单瓶", "price": 99.00, "stock": 100}]', NOW()),
('DHA藻油', 'nutrition', 'https://example.com/images/nutrition2.jpg', '["https://example.com/images/nutrition2-1.jpg", "https://example.com/images/nutrition2-2.jpg"]', 199.00, 269.00, 80, 234, 'active', 1, 159.00, 20, '90粒', '促进大脑发育', '["https://example.com/images/nutrition2-detail1.jpg", "https://example.com/images/nutrition2-detail2.jpg"]', '[{"name": "单瓶", "price": 199.00, "stock": 80}]', NOW()),

-- 出行分类
('婴儿推车', 'travel', 'https://example.com/images/travel1.jpg', '["https://example.com/images/travel1-1.jpg", "https://example.com/images/travel1-2.jpg"]', 599.00, 799.00, 50, 123, 'active', 0, NULL, NULL, '可折叠', '轻便折叠，避震设计', '["https://example.com/images/travel1-detail1.jpg", "https://example.com/images/travel1-detail2.jpg"]', '[{"name": "标配", "price": 599.00, "stock": 50}]', NOW()),
('婴儿安全座椅', 'travel', 'https://example.com/images/travel2.jpg', '["https://example.com/images/travel2-1.jpg", "https://example.com/images/travel2-2.jpg"]', 899.00, 1199.00, 30, 89, 'active', 1, 799.00, 10, '0-4岁', 'ISOFIX接口，安全可靠', '["https://example.com/images/travel2-detail1.jpg", "https://example.com/images/travel2-detail2.jpg"]', '[{"name": "标配", "price": 899.00, "stock": 30}]', NOW());
