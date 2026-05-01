# 爱宝贝儿孕婴生活馆 - 后端项目

## 📋 项目简介

这是一个为微信小程序"爱宝贝儿孕婴生活馆"开发的后端系统，提供孕婴商品销售与门店服务预约功能。

### 核心功能

- 🛒 **商品管理**：商品分类、搜索、详情、秒杀、爆款推荐
- 👤 **用户管理**：用户注册登录、收货地址、会员积分
- 🛍️ **购物车**：添加、删除、更新、清空购物车
- ⭐ **收藏管理**：收藏商品、批量操作
- 📦 **订单管理**：创建订单、支付、取消、确认收货
- 🎁 **会员服务**：会员充值、每日签到、积分兑换
- 📅 **服务预约**：婴儿游泳、理发、满月照、早教课程等
- 📬 **消息通知**：订单通知、活动通知、系统消息
- 📊 **数据统计**：销售趋势、订单统计、会员统计

---

## 🛠️ 技术栈

### 后端技术

- **框架**：Spring Boot 2.x
- **ORM**：MyBatis Plus
- **数据库**：MySQL 8.0
- **认证**：JWT (JSON Web Token)
- **构建工具**：Maven

### 数据库

- **数据库名**：aibaobei
- **连接信息**：
  - 主机：localhost
  - 端口：3306
  - 用户名：root
  - 密码：root

---

## 📁 项目结构

```
src/main/java/com/w1101/
├── entity/              # 实体类
│   ├── User.java        # 用户实体
│   ├── Address.java     # 地址实体
│   ├── Favorite.java    # 收藏实体
│   ├── CartItem.java    # 购物车实体
│   ├── Banner.java      # 轮播图实体
│   ├── Message.java     # 消息实体
│   ├── StoreInfo.java   # 门店信息实体
│   ├── ProductPackage.java   # 商品套餐实体
│   ├── PointsProduct.java    # 积分商品实体
│   ├── MemberTier.java       # 会员档位实体
│   ├── SignRecord.java       # 签到记录实体
│   ├── RechargeRecord.java   # 充值记录实体
│   ├── PointsExchange.java   # 积分兑换实体
│   └── SeckillProduct.java   # 秒杀商品实体
├── mapper/              # Mapper接口
├── service/              # 业务逻辑
│   └── impl/            # 业务实现
├── controller/          # 控制器
├── dto/                 # 数据传输对象
├── utils/               # 工具类
└── config/              # 配置类
```

---

## 🚀 快速开始

### 1. 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 8.0+

### 2. 初始化数据库

```bash
# 创建数据库
mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS aibaobei;"

# 执行初始化SQL
mysql -uroot -proot aibaobei < database_initialization.sql

# 插入测试数据
mysql -uroot -proot aibaobei < test_data.sql
```

### 3. 配置数据库连接

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/aibaobei?useUnicode=true&characterEncoding=utf8
    username: root
    password: root
```

### 4. 启动项目

```bash
mvn spring-boot:run
```

项目将在 `http://localhost:8080` 启动

---

## 📚 API接口文档

详细API接口文档请参考：

- [API接口测试文档](./API接口测试文档.md)

### 主要接口列表

| 模块 | 接口 | 方法 | 说明 |
|------|------|------|------|
| 认证 | `/api/auth/login` | POST | 用户登录 |
| 认证 | `/api/auth/register` | POST | 用户注册 |
| 用户 | `/api/user/info` | GET | 获取用户信息 |
| 地址 | `/api/addresses` | GET/POST | 收货地址管理 |
| 商品 | `/api/products` | GET | 获取商品列表 |
| 商品 | `/api/products/{id}` | GET | 获取商品详情 |
| 购物车 | `/api/cart` | GET/POST | 购物车管理 |
| 收藏 | `/api/favorites` | GET/POST | 收藏管理 |
| 订单 | `/api/orders` | GET/POST | 订单管理 |
| 会员 | `/api/member/info` | GET | 会员信息 |
| 会员 | `/api/member/sign` | POST | 每日签到 |
| 会员 | `/api/member/recharge` | POST | 会员充值 |
| 服务 | `/api/services/types` | GET | 服务类型 |
| 服务 | `/api/services/book` | POST | 预约服务 |
| 消息 | `/api/messages` | GET | 消息列表 |
| 首页 | `/api/home` | GET | 首页数据 |

---

## 🗄️ 数据库表结构

### 核心业务表

| 表名 | 说明 |
|------|------|
| users | 用户表 |
| addresses | 收货地址表 |
| favorites | 收藏表 |
| cart_items | 购物车表 |
| product | 商品表 |
| product_packages | 商品套餐表 |
| `order` | 订单表 |
| order_item | 订单商品表 |
| service | 服务表 |
| booking | 服务预约表 |
| banners | 轮播图表 |
| messages | 消息表 |
| store_info | 门店信息表 |
| member_tiers | 会员档位表 |
| sign_records | 签到记录表 |
| recharge_records | 充值记录表 |
| points_products | 积分商品表 |
| points_exchanges | 积分兑换记录表 |
| seckill_products | 秒杀商品表 |

---

## 🧪 测试数据

### 测试用户

| 用户名 | 密码 | 昵称 | VIP等级 | 积分 |
|--------|------|------|---------|------|
| test001 | password123 | 宝宝妈妈 | VIP1 | 500 |
| test002 | password123 | 奶爸老王 | VIP2 | 1200 |
| test003 | password123 | 宝贝爸比 | VIP0 | 0 |

---

## 📝 开发文档

详细开发文档请参考：

- [需求文档](../my-vue3-project/最终版小程序后端需求实现.md)
- [实现总结](./后端实现总结.md)
- [API测试文档](./API接口测试文档.md)

---

## 📞 联系方式

- **项目作者**：[作者名称]
- **创建日期**：2026-04-30

---

## 📄 许可证

本项目仅供学习参考使用。
