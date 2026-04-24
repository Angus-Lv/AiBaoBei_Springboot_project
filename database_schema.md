# 爱宝贝儿数据库表结构文档

## 1. 管理员表

管理员表存放系统管理员信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | username | VARCHAR(50) | 用户名，唯一 |
| 3 | password | VARCHAR(255) | 密码 |
| 4 | role | VARCHAR(20) | 角色，默认admin |
| 5 | status | VARCHAR(20) | 状态，默认active |
| 6 | create_time | DATETIME | 创建时间，默认当前时间 |
| 7 | last_login_time | DATETIME | 最后登录时间 |

## 2. 会员表

会员表存放系统会员信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | username | VARCHAR(50) | 用户名 |
| 3 | phone | VARCHAR(20) | 手机号，唯一 |
| 4 | password | VARCHAR(255) | 密码 |
| 5 | points | INT | 积分，默认0 |
| 6 | balance | DECIMAL(10,2) | 余额，默认0.00 |
| 7 | status | VARCHAR(20) | 状态，默认active |
| 8 | create_time | DATETIME | 创建时间，默认当前时间 |
| 9 | last_login_time | DATETIME | 最后登录时间 |

## 3. 充值档位表

充值档位表存放充值金额和赠送金额的对应关系，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | amount | DECIMAL(10,2) | 充值金额 |
| 3 | bonus | DECIMAL(10,2) | 赠送金额，默认0.00 |
| 4 | sort | INT | 排序，默认0 |
| 5 | create_time | DATETIME | 创建时间，默认当前时间 |

## 4. 充值记录表

充值记录表存放会员充值记录，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | order_no | VARCHAR(50) | 订单号，唯一 |
| 3 | member_id | INT | 会员ID |
| 4 | member_nickname | VARCHAR(50) | 会员昵称 |
| 5 | amount | DECIMAL(10,2) | 充值金额 |
| 6 | bonus | DECIMAL(10,2) | 赠送金额，默认0.00 |
| 7 | total_amount | DECIMAL(10,2) | 总金额 |
| 8 | status | VARCHAR(20) | 状态，默认pending |
| 9 | create_time | DATETIME | 创建时间，默认当前时间 |
| 10 | complete_time | DATETIME | 完成时间 |

## 5. 签到设置表

签到设置表存放签到相关配置，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | daily_points | INT | 每日签到积分，默认10 |
| 3 | continuous_rewards | TEXT | 连续签到奖励规则 |
| 4 | status | VARCHAR(20) | 状态，默认enabled |
| 5 | create_time | DATETIME | 创建时间，默认当前时间 |
| 6 | update_time | DATETIME | 更新时间，默认当前时间 |

## 6. 签到记录表

签到记录表存放会员签到记录，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | member_id | INT | 会员ID |
| 3 | member_nickname | VARCHAR(50) | 会员昵称 |
| 4 | points | INT | 获得积分 |
| 5 | signin_date | DATE | 签到日期 |
| 6 | create_time | DATETIME | 创建时间，默认当前时间 |

## 7. 积分设置表

积分设置表存放积分相关配置，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | points_per_yuan | DECIMAL(10,2) | 每元获得积分，默认1.00 |
| 3 | signin_points | INT | 签到积分，默认10 |
| 4 | register_points | INT | 注册积分，默认100 |
| 5 | point_expiry_days | INT | 积分有效期(天)，默认365 |
| 6 | enable_points | BOOLEAN | 是否开启积分，默认TRUE |
| 7 | create_time | DATETIME | 创建时间，默认当前时间 |
| 8 | update_time | DATETIME | 更新时间，默认当前时间 |

## 8. 积分记录表

积分记录表存放会员积分变动记录，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | member_id | INT | 会员ID |
| 3 | member_nickname | VARCHAR(50) | 会员昵称 |
| 4 | points | INT | 积分变动数量 |
| 5 | type | VARCHAR(20) | 操作类型 |
| 6 | type_detail | VARCHAR(50) | 操作类型详情 |
| 7 | balance | INT | 积分余额 |
| 8 | remark | VARCHAR(255) | 备注 |
| 9 | create_time | DATETIME | 创建时间，默认当前时间 |

## 9. 礼品表

礼品表存放积分兑换礼品信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | name | VARCHAR(100) | 礼品名称 |
| 3 | points | INT | 所需积分 |
| 4 | stock | INT | 库存数量，默认0 |
| 5 | exchanged_count | INT | 兑换次数，默认0 |
| 6 | image | VARCHAR(255) | 礼品图片 |
| 7 | description | TEXT | 礼品描述 |
| 8 | status | VARCHAR(20) | 状态，默认active |
| 9 | create_time | DATETIME | 创建时间，默认当前时间 |
| 10 | update_time | DATETIME | 更新时间，默认当前时间 |

## 10. 兑换记录表

兑换记录表存放会员积分兑换记录，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | order_no | VARCHAR(50) | 订单号，唯一 |
| 3 | member_id | INT | 会员ID |
| 4 | member_nickname | VARCHAR(50) | 会员昵称 |
| 5 | gift_id | INT | 礼品ID |
| 6 | gift_name | VARCHAR(100) | 礼品名称 |
| 7 | gift_points | INT | 礼品所需积分 |
| 8 | receiver_name | VARCHAR(50) | 收货人姓名 |
| 9 | receiver_phone | VARCHAR(20) | 收货人手机号 |
| 10 | receiver_address | VARCHAR(255) | 收货地址 |
| 11 | status | VARCHAR(20) | 状态，默认pending |
| 12 | exchange_time | DATETIME | 兑换时间，默认当前时间 |
| 13 | complete_time | DATETIME | 完成时间 |

## 11. 商品分类表

商品分类表存放商品分类信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | name | VARCHAR(50) | 分类名称 |
| 3 | value | VARCHAR(50) | 分类值，唯一 |
| 4 | sort | INT | 排序，默认0 |
| 5 | create_time | DATETIME | 创建时间，默认当前时间 |

## 12. 商品表

商品表存放商品信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | name | VARCHAR(100) | 商品名称 |
| 3 | category | VARCHAR(50) | 商品分类 |
| 4 | image | VARCHAR(255) | 商品图片 |
| 5 | images | TEXT | 商品图片列表 |
| 6 | price | DECIMAL(10,2) | 商品价格 |
| 7 | original_price | DECIMAL(10,2) | 原价 |
| 8 | stock | INT | 库存，默认0 |
| 9 | sales | INT | 销量，默认0 |
| 10 | status | VARCHAR(20) | 状态，默认active |
| 11 | is_seckill | BOOLEAN | 是否秒杀，默认FALSE |
| 12 | seckill_price | DECIMAL(10,2) | 秒杀价格 |
| 13 | seckill_stock | INT | 秒杀库存 |
| 14 | spec | VARCHAR(100) | 商品规格 |
| 15 | description | TEXT | 商品描述 |
| 16 | detail_images | TEXT | 详情图片 |
| 17 | packages | TEXT | 商品套餐 |
| 18 | create_time | DATETIME | 创建时间，默认当前时间 |
| 19 | update_time | DATETIME | 更新时间，默认当前时间 |

## 13. 订单表

订单表存放订单信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | order_id | VARCHAR(50) | 订单号，唯一 |
| 3 | member_id | INT | 会员ID |
| 4 | member_nickname | VARCHAR(50) | 会员昵称 |
| 5 | member_phone | VARCHAR(20) | 会员手机号 |
| 6 | total_price | DECIMAL(10,2) | 订单总金额 |
| 7 | total_quantity | INT | 商品总数量 |
| 8 | status | VARCHAR(20) | 状态，默认pending |
| 9 | pay_method | VARCHAR(50) | 支付方式 |
| 10 | pay_time | DATETIME | 支付时间 |
| 11 | receiver_name | VARCHAR(50) | 收货人姓名 |
| 12 | receiver_phone | VARCHAR(20) | 收货人手机号 |
| 13 | receiver_address | VARCHAR(255) | 收货地址 |
| 14 | remark | VARCHAR(255) | 备注 |
| 15 | create_time | DATETIME | 创建时间，默认当前时间 |
| 16 | update_time | DATETIME | 更新时间，默认当前时间 |

## 14. 订单商品表

订单商品表存放订单中的商品信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | order_id | INT | 订单ID |
| 3 | product_id | INT | 商品ID |
| 4 | product_name | VARCHAR(100) | 商品名称 |
| 5 | product_image | VARCHAR(255) | 商品图片 |
| 6 | price | DECIMAL(10,2) | 商品价格 |
| 7 | quantity | INT | 商品数量 |
| 8 | spec | VARCHAR(100) | 商品规格 |

## 15. 退款表

退款表存放订单退款信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | order_id | VARCHAR(50) | 订单号 |
| 3 | member_id | INT | 会员ID |
| 4 | member_nickname | VARCHAR(50) | 会员昵称 |
| 5 | refund_amount | DECIMAL(10,2) | 退款金额 |
| 6 | reason | VARCHAR(255) | 退款原因 |
| 7 | status | VARCHAR(20) | 状态，默认pending |
| 8 | create_time | DATETIME | 创建时间，默认当前时间 |
| 9 | process_time | DATETIME | 处理时间 |
| 10 | remark | VARCHAR(255) | 备注 |

## 16. 服务类型表

服务类型表存放服务类型信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | name | VARCHAR(50) | 类型名称 |
| 3 | value | VARCHAR(50) | 类型值，唯一 |
| 4 | sort | INT | 排序，默认0 |
| 5 | create_time | DATETIME | 创建时间，默认当前时间 |

## 17. 服务表

服务表存放服务信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | name | VARCHAR(100) | 服务名称 |
| 3 | type | VARCHAR(50) | 服务类型 |
| 4 | price | DECIMAL(10,2) | 服务价格 |
| 5 | description | TEXT | 服务描述 |
| 6 | duration | INT | 服务时长(分钟) |
| 7 | status | VARCHAR(20) | 状态，默认active |
| 8 | create_time | DATETIME | 创建时间，默认当前时间 |
| 9 | update_time | DATETIME | 更新时间，默认当前时间 |

## 18. 预约表

预约表存放服务预约信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | service_id | INT | 服务ID |
| 3 | service_name | VARCHAR(100) | 服务名称 |
| 4 | member_id | INT | 会员ID |
| 5 | member_nickname | VARCHAR(50) | 会员昵称 |
| 6 | member_phone | VARCHAR(20) | 会员手机号 |
| 7 | baby_name | VARCHAR(50) | 宝宝姓名 |
| 8 | baby_age | VARCHAR(20) | 宝宝年龄 |
| 9 | date | DATE | 预约日期 |
| 10 | time | VARCHAR(10) | 预约时间 |
| 11 | status | VARCHAR(20) | 状态，默认pending |
| 12 | remark | VARCHAR(255) | 备注 |
| 13 | create_time | DATETIME | 创建时间，默认当前时间 |

## 19. 系统设置表

系统设置表存放系统配置信息，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | setting_key | VARCHAR(50) | 设置键，唯一 |
| 3 | setting_value | TEXT | 设置值 |
| 4 | create_time | DATETIME | 创建时间，默认当前时间 |
| 5 | update_time | DATETIME | 更新时间，默认当前时间 |

## 20. 操作日志表

操作日志表存放管理员操作记录，如下表所示：

| 序列 | 列名 | 类型 | 注释 |
| --- | --- | --- | --- |
| 1 | id | INT | 主键，自增 |
| 2 | admin_id | INT | 管理员ID |
| 3 | admin_name | VARCHAR(50) | 管理员名称 |
| 4 | action | VARCHAR(50) | 操作类型 |
| 5 | description | VARCHAR(255) | 操作描述 |
| 6 | ip | VARCHAR(50) | 操作IP |
| 7 | create_time | DATETIME | 创建时间，默认当前时间 |

## 21. 默认数据

### 21.1 默认管理员
- 用户名：admin
- 密码：admin123（已加密）

### 21.2 默认签到设置
- 每日签到积分：10
- 连续签到奖励：3天50积分，7天100积分

### 21.3 默认积分设置
- 每元获得积分：1.00
- 签到积分：10
- 注册积分：100
- 积分有效期：365天
- 开启积分：TRUE

### 21.4 默认系统设置
- 网站名称：爱宝贝儿
- 网站描述：专业母婴用品商城
- 联系电话：400-123-4567
- 联系邮箱：service@aibaobei.com
- 地址：北京市朝阳区
- 注册功能：开启
- 登录尝试次数：5
- 锁定时长：30分钟
- 密码最小长度：6