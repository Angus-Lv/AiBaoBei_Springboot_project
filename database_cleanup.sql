USE aibaobei;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sign_records;
DROP TABLE IF EXISTS recharge_records;

SELECT '数据库清理完成！' AS message;
SELECT COUNT(*) AS remaining_tables FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'aibaobei';