USE aibaobei;

SELECT 'admin' as tbl, COUNT(*) as cnt FROM admin
UNION ALL SELECT 'addresses', COUNT(*) FROM addresses
UNION ALL SELECT 'banners', COUNT(*) FROM banners
UNION ALL SELECT 'booking', COUNT(*) FROM booking
UNION ALL SELECT 'cart_items', COUNT(*) FROM cart_items
UNION ALL SELECT 'category', COUNT(*) FROM category
UNION ALL SELECT 'exchange', COUNT(*) FROM exchange
UNION ALL SELECT 'favorites', COUNT(*) FROM favorites
UNION ALL SELECT 'gift', COUNT(*) FROM gift
UNION ALL SELECT 'member', COUNT(*) FROM member
UNION ALL SELECT 'member_tiers', COUNT(*) FROM member_tiers
UNION ALL SELECT 'messages', COUNT(*) FROM messages
UNION ALL SELECT 'operation_log', COUNT(*) FROM operation_log
UNION ALL SELECT 'order', COUNT(*) FROM `order`
UNION ALL SELECT 'order_item', COUNT(*) FROM order_item
UNION ALL SELECT 'points_exchanges', COUNT(*) FROM points_exchanges
UNION ALL SELECT 'points_products', COUNT(*) FROM points_products
UNION ALL SELECT 'points_record', COUNT(*) FROM points_record
UNION ALL SELECT 'points_setting', COUNT(*) FROM points_setting
UNION ALL SELECT 'product', COUNT(*) FROM product
UNION ALL SELECT 'product_packages', COUNT(*) FROM product_packages
UNION ALL SELECT 'recharge', COUNT(*) FROM recharge
UNION ALL SELECT 'recharge_tier', COUNT(*) FROM recharge_tier
UNION ALL SELECT 'refund', COUNT(*) FROM refund
UNION ALL SELECT 'seckill_products', COUNT(*) FROM seckill_products
UNION ALL SELECT 'service', COUNT(*) FROM service
UNION ALL SELECT 'service_type', COUNT(*) FROM service_type
UNION ALL SELECT 'signin_record', COUNT(*) FROM signin_record
UNION ALL SELECT 'signin_setting', COUNT(*) FROM signin_setting
UNION ALL SELECT 'store_info', COUNT(*) FROM store_info
UNION ALL SELECT 'system_setting', COUNT(*) FROM system_setting;