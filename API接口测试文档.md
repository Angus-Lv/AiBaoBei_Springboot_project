# 爱宝贝儿孕婴生活馆 - API接口测试文档

## 项目信息

- **项目名称**：爱宝贝儿孕婴生活馆
- **后端地址**：`http://localhost:8080`
- **数据库**：`http://localhost:3306` (aibaobei)
- **测试日期**：2026-04-30

---

## 一、认证模块 API

### 1.1 用户登录

**接口地址**：`POST /api/auth/login`

**请求参数**：
```json
{
  "username": "test001",
  "password": "password123"
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": 1,
      "username": "test001",
      "nickname": "宝宝妈妈",
      "avatar": "https://example.com/avatar1.jpg",
      "phone": "13800138001",
      "vipLevel": "VIP1",
      "points": 500,
      "balance": 100.00
    }
  }
}
```

**测试命令**：
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"test001","password":"password123"}'
```

---

### 1.2 用户注册

**接口地址**：`POST /api/auth/register`

**请求参数**：
```json
{
  "username": "newuser",
  "password": "password123",
  "phone": "13800138099"
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "userId": 4,
    "username": "newuser"
  }
}
```

**测试命令**：
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"newuser","password":"password123","phone":"13800138099"}'
```

---

## 二、用户模块 API

### 2.1 获取用户信息

**接口地址**：`GET /api/user/info`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "username": "test001",
    "nickname": "宝宝妈妈",
    "avatar": "https://example.com/avatar1.jpg",
    "phone": "13800138001",
    "vipLevel": "VIP1",
    "points": 500,
    "balance": 100.00,
    "createdAt": "2024-01-01 10:00:00"
  }
}
```

---

### 2.2 更新用户信息

**接口地址**：`PUT /api/user/info`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "nickname": "新昵称",
  "avatar": "https://example.com/new-avatar.jpg",
  "phone": "13800138001"
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "nickname": "新昵称",
    "avatar": "https://example.com/new-avatar.jpg"
  }
}
```

---

## 三、收货地址模块 API

### 3.1 获取收货地址列表

**接口地址**：`GET /api/addresses`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "张三",
        "phone": "13800138001",
        "province": "河南省",
        "city": "新乡市",
        "district": "红旗区",
        "detail": "某某街道某某小区1号楼101",
        "isDefault": true
      }
    ]
  }
}
```

---

### 3.2 添加收货地址

**接口地址**：`POST /api/addresses`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "name": "李四",
  "phone": "13800138004",
  "province": "河南省",
  "city": "新乡市",
  "district": "牧野区",
  "detail": "某某路某某小区2号楼202",
  "isDefault": false
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "添加成功",
  "data": {
    "id": 2
  }
}
```

---

### 3.3 设置默认收货地址

**接口地址**：`PUT /api/addresses/{id}/default`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "设置成功"
}
```

---

### 3.4 删除收货地址

**接口地址**：`DELETE /api/addresses/{id}`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "删除成功"
}
```

---

## 四、商品模块 API

### 4.1 获取商品分类

**接口地址**：`GET /api/categories`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "奶粉",
        "icon": "🍼"
      },
      {
        "id": 2,
        "name": "纸尿裤",
        "icon": "👶"
      }
    ]
  }
}
```

---

### 4.2 获取商品列表

**接口地址**：`GET /api/products`

**请求参数**：
- `category` (string, optional): 分类ID
- `keyword` (string, optional): 搜索关键词
- `filter` (string, optional): 筛选条件（hot/seckill）
- `sort` (string, optional): 排序方式（price_asc/price_desc/sales）
- `page` (number, optional): 页码，默认1
- `pageSize` (number, optional): 每页数量，默认20

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "爱他美白金版奶粉",
        "image": "https://example.com/product1.jpg",
        "price": 199,
        "originalPrice": 299,
        "sales": 1258,
        "stock": 200,
        "isSeckill": true,
        "isHot": true
      }
    ],
    "total": 100,
    "page": 1,
    "pageSize": 20,
    "totalPages": 5
  }
}
```

---

### 4.3 获取商品详情

**接口地址**：`GET /api/products/{id}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "name": "爱他美白金版奶粉",
    "images": [
      "https://example.com/product1.jpg",
      "https://example.com/product1_2.jpg"
    ],
    "detailImages": [
      "https://example.com/detail1.jpg",
      "https://example.com/detail2.jpg"
    ],
    "price": 199,
    "originalPrice": 299,
    "spec": "800g/罐",
    "stock": 200,
    "sales": 1258,
    "isSeckill": true,
    "isFavorite": false,
    "packages": [
      {"id": 1, "name": "单罐", "price": 199},
      {"id": 2, "name": "两罐套装", "price": 380}
    ]
  }
}
```

---

### 4.4 获取热门商品

**接口地址**：`GET /api/products/hot`

**请求参数**：
- `page` (number, optional): 页码
- `pageSize` (number, optional): 每页数量

**响应示例**：同商品列表

---

## 五、购物车模块 API

### 5.1 获取购物车列表

**接口地址**：`GET /api/cart`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "productId": 1,
        "productName": "爱他美白金版奶粉",
        "productImage": "https://example.com/product1.jpg",
        "packageId": 1,
        "packageName": "单罐",
        "price": 199,
        "quantity": 2,
        "stock": 200
      }
    ],
    "totalPrice": 398,
    "totalQuantity": 2
  }
}
```

---

### 5.2 添加商品到购物车

**接口地址**：`POST /api/cart/add`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "productId": 1,
  "packageId": 1,
  "quantity": 1
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "添加成功",
  "data": {
    "cartId": 1
  }
}
```

---

### 5.3 更新购物车商品数量

**接口地址**：`PUT /api/cart/{id}`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "quantity": 3
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "更新成功"
}
```

---

### 5.4 删除购物车商品

**接口地址**：`DELETE /api/cart/{id}`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "删除成功"
}
```

---

### 5.5 清空购物车

**接口地址**：`DELETE /api/cart/clear`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "清空成功"
}
```

---

## 六、收藏模块 API

### 6.1 获取收藏列表

**接口地址**：`GET /api/favorites`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
- `page` (number, optional): 页码
- `pageSize` (number, optional): 每页数量

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "productId": 1,
        "productName": "爱他美白金版奶粉",
        "productImage": "https://example.com/product1.jpg",
        "price": 199,
        "originalPrice": 299,
        "sales": 1258,
        "createTime": "2024-01-15 10:30:00"
      }
    ],
    "total": 10,
    "page": 1,
    "pageSize": 20
  }
}
```

---

### 6.2 添加收藏

**接口地址**：`POST /api/favorites`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "productId": 1
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "收藏成功",
  "data": {
    "favoriteId": 1
  }
}
```

---

### 6.3 取消收藏

**接口地址**：`DELETE /api/favorites/{id}`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "取消收藏成功"
}
```

---

### 6.4 批量取消收藏

**接口地址**：`DELETE /api/favorites/batch-delete`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "ids": [1, 2, 3]
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "取消收藏成功"
}
```

---

## 七、订单模块 API

### 7.1 创建订单

**接口地址**：`POST /api/orders`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "addressId": 1,
  "remark": "请尽快发货",
  "items": [
    {
      "productId": 1,
      "packageId": 1,
      "quantity": 2
    }
  ]
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "订单创建成功",
  "data": {
    "orderId": "ORD202401291200001",
    "orderNo": "ORD202401291200001",
    "totalPrice": 398,
    "status": "pending_payment",
    "createTime": "2024-01-29 12:00:00"
  }
}
```

---

### 7.2 获取订单列表

**接口地址**：`GET /api/orders`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
- `status` (string, optional): 订单状态
- `page` (number, optional): 页码
- `pageSize` (number, optional): 每页数量

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "orderNo": "ORD202401291200001",
        "status": "pending_payment",
        "statusText": "待付款",
        "totalPrice": 398,
        "totalQuantity": 2,
        "payTime": null,
        "createTime": "2024-01-29 12:00:00",
        "items": [
          {
            "id": 1,
            "productId": 1,
            "productName": "爱他美白金版奶粉",
            "productImage": "https://example.com/product1.jpg",
            "packageName": "单罐",
            "price": 199,
            "quantity": 2
          }
        ]
      }
    ],
    "total": 5,
    "page": 1,
    "pageSize": 20
  }
}
```

---

### 7.3 获取订单详情

**接口地址**：`GET /api/orders/{id}`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "orderNo": "ORD202401291200001",
    "status": "pending_payment",
    "statusText": "待付款",
    "totalPrice": 398,
    "payMethod": null,
    "payTime": null,
    "remark": "请尽快发货",
    "createTime": "2024-01-29 12:00:00",
    "address": {
      "name": "张三",
      "phone": "13800138001",
      "province": "河南省",
      "city": "新乡市",
      "district": "红旗区",
      "detail": "某某街道某某小区1号楼101"
    },
    "items": [
      {
        "id": 1,
        "productId": 1,
        "productName": "爱他美白金版奶粉",
        "productImage": "https://example.com/product1.jpg",
        "packageName": "单罐",
        "price": 199,
        "quantity": 2
      }
    ]
  }
}
```

---

### 7.4 取消订单

**接口地址**：`PUT /api/orders/{id}/cancel`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "订单已取消"
}
```

---

### 7.5 支付订单

**接口地址**：`POST /api/orders/{id}/pay`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "payMethod": "wechat"
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "支付成功",
  "data": {
    "orderId": 1,
    "orderNo": "ORD202401291200001",
    "status": "paid",
    "payTime": "2024-01-29 12:30:00"
  }
}
```

---

### 7.6 确认收货

**接口地址**：`PUT /api/orders/{id}/confirm`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "确认收货成功"
}
```

---

## 八、会员服务模块 API

### 8.1 获取会员信息

**接口地址**：`GET /api/member/info`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "vipLevel": "VIP1",
    "points": 500,
    "balance": 100.00,
    "nextLevelPoints": 500,
    "memberBenefits": [
      "享受9.5折优惠",
      "生日双倍积分",
      "专属客服"
    ]
  }
}
```

---

### 8.2 获取充值档位列表

**接口地址**：`GET /api/member/tiers`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "VIP1",
        "amount": 100,
        "bonus": 10,
        "vipLevel": "VIP1",
        "pointsBonus": 100
      },
      {
        "id": 2,
        "name": "VIP2",
        "amount": 500,
        "bonus": 75,
        "vipLevel": "VIP2",
        "pointsBonus": 600
      }
    ]
  }
}
```

---

### 8.3 会员充值

**接口地址**：`POST /api/member/recharge`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "tierId": 1
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "充值成功",
  "data": {
    "transactionNo": "RC202401291200001",
    "amount": 100,
    "bonus": 10,
    "totalAmount": 110,
    "balance": 210,
    "points": 600,
    "vipLevel": "VIP1"
  }
}
```

---

### 8.4 获取签到状态

**接口地址**：`GET /api/member/sign/status`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "todaySigned": false,
    "continuousDays": 5,
    "totalPoints": 500,
    "signCalendars": [
      {"date": "2024-01-25", "signed": true, "points": 10},
      {"date": "2024-01-26", "signed": true, "points": 10},
      {"date": "2024-01-27", "signed": true, "points": 10},
      {"date": "2024-01-28", "signed": true, "points": 10},
      {"date": "2024-01-29", "signed": false, "points": 10}
    ]
  }
}
```

---

### 8.5 每日签到

**接口地址**：`POST /api/member/sign`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "签到成功",
  "data": {
    "points": 10,
    "continuousDays": 6,
    "totalPoints": 510
  }
}
```

---

### 8.6 获取积分商品列表

**接口地址**：`GET /api/member/points-products`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
- `page` (number, optional): 页码
- `pageSize` (number, optional): 每页数量

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "婴儿湿巾",
        "image": "https://example.com/wetwipes.jpg",
        "points": 100,
        "stock": 50
      }
    ],
    "total": 20,
    "page": 1,
    "pageSize": 20
  }
}
```

---

### 8.7 积分兑换

**接口地址**：`POST /api/member/points/exchange`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "productId": 1,
  "addressId": 1
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "兑换成功",
  "data": {
    "exchangeId": 1,
    "productName": "婴儿湿巾",
    "points": 100,
    "remainingPoints": 400,
    "status": "pending"
  }
}
```

---

### 8.8 获取积分记录

**接口地址**：`GET /api/member/points/records`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "type": "sign",
        "points": 10,
        "description": "每日签到",
        "balance": 510,
        "createdAt": "2024-01-29 08:00:00"
      }
    ]
  }
}
```

---

## 九、服务预约模块 API

### 9.1 获取服务类型列表

**接口地址**：`GET /api/services/types`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "婴儿游泳",
        "icon": "🏊",
        "url": "/pages/service/bath/bath"
      },
      {
        "id": 2,
        "name": "婴儿理发",
        "icon": "✂️",
        "url": "/pages/service/hair/hair"
      }
    ]
  }
}
```

---

### 9.2 获取服务详情

**接口地址**：`GET /api/services/{id}`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "name": "婴儿游泳",
    "icon": "🏊",
    "price": 58,
    "description": "专业婴儿游泳服务，水温恒定37度，配有专业抚触师",
    "duration": 30,
    "images": [
      "https://example.com/swim1.jpg",
      "https://example.com/swim2.jpg"
    ],
    "packages": [
      {"id": 1, "name": "单次", "price": 58},
      {"id": 2, "name": "10次卡", "price": 520},
      {"id": 3, "name": "月卡", "price": 880}
    ],
    "availableTimes": [
      {"time": "09:00", "available": true},
      {"time": "10:00", "available": true},
      {"time": "11:00", "available": false}
    ]
  }
}
```

---

### 9.3 提交服务预约

**接口地址**：`POST /api/services/book`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
```json
{
  "serviceId": 1,
  "packageId": 1,
  "babyName": "宝宝",
  "babyAge": "1岁",
  "bookingDate": "2024-02-01",
  "bookingTime": "10:00",
  "remark": "需要自带游泳纸尿裤"
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "预约成功",
  "data": {
    "bookingId": 1,
    "bookingNo": "BK202401291200001",
    "serviceName": "婴儿游泳",
    "packageName": "单次",
    "babyName": "宝宝",
    "bookingDate": "2024-02-01",
    "bookingTime": "10:00",
    "status": "pending"
  }
}
```

---

### 9.4 获取预约记录

**接口地址**：`GET /api/services/bookings`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
- `status` (string, optional): 状态
- `page` (number, optional): 页码
- `pageSize` (number, optional): 每页数量

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "bookingNo": "BK202401291200001",
        "serviceId": 1,
        "serviceName": "婴儿游泳",
        "serviceIcon": "🏊",
        "packageName": "单次",
        "babyName": "宝宝",
        "babyAge": "1岁",
        "bookingDate": "2024-02-01",
        "bookingTime": "10:00",
        "status": "pending",
        "statusText": "待消费",
        "price": 58,
        "createTime": "2024-01-29 12:00:00"
      }
    ],
    "total": 5,
    "page": 1,
    "pageSize": 20
  }
}
```

---

### 9.5 取消预约

**接口地址**：`PUT /api/services/bookings/{id}/cancel`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "取消成功"
}
```

---

## 十、消息通知模块 API

### 10.1 获取消息列表

**接口地址**：`GET /api/messages`

**请求头**：`Authorization: Bearer {token}`

**请求参数**：
- `type` (string, optional): 消息类型
- `page` (number, optional): 页码
- `pageSize` (number, optional): 每页数量

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "title": "订单已发货",
        "content": "您的订单 ORD202401291200001 已发货，请注意查收",
        "type": "order",
        "icon": "📦",
        "isRead": false,
        "createTime": "2024-01-29 14:00:00"
      },
      {
        "id": 2,
        "title": "积分即将过期",
        "content": "您有500积分将于30天后过期，请尽快使用",
        "type": "activity",
        "icon": "🎁",
        "isRead": true,
        "createTime": "2024-01-28 10:00:00"
      }
    ],
    "total": 10,
    "unreadCount": 3,
    "page": 1,
    "pageSize": 20
  }
}
```

---

### 10.2 标记消息已读

**接口地址**：`PUT /api/messages/{id}/read`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "标记成功"
}
```

---

### 10.3 标记全部已读

**接口地址**：`PUT /api/messages/read-all`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "标记成功"
}
```

---

### 10.4 删除消息

**接口地址**：`DELETE /api/messages/{id}`

**请求头**：`Authorization: Bearer {token}`

**响应示例**：
```json
{
  "code": 200,
  "message": "删除成功"
}
```

---

## 十一、首页数据模块 API

### 11.1 获取首页数据

**接口地址**：`GET /api/home`

**响应示例**：
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "banners": [
      {
        "id": 1,
        "title": "春季优惠",
        "image": "https://example.com/banner1.jpg",
        "linkType": "product",
        "linkId": 1
      }
    ],
    "seckillProducts": [
      {
        "id": 1,
        "name": "爱他美白金版奶粉",
        "image": "https://example.com/product1.jpg",
        "originalPrice": 299,
        "currentPrice": 199,
        "sales": 1258,
        "stock": 200
      }
    ],
    "categories": [
      {
        "id": 1,
        "name": "奶粉",
        "icon": "🍼"
      },
      {
        "id": 2,
        "name": "纸尿裤",
        "icon": "👶"
      }
    ],
    "hotProducts": [
      {
        "id": 1,
        "name": "婴儿手推车",
        "image": "https://example.com/product2.jpg",
        "price": 599,
        "sales": 3560
      }
    ],
    "services": [
      {
        "id": 1,
        "name": "婴儿游泳",
        "icon": "🏊",
        "price": 58,
        "url": "/pages/service/bath/bath"
      },
      {
        "id": 2,
        "name": "婴儿理发",
        "icon": "✂️",
        "price": 30,
        "url": "/pages/service/hair/hair"
      }
    ],
    "storeInfo": {
      "name": "爱宝贝儿孕婴生活馆",
      "address": "河南省新乡市马庄乡常兴集村",
      "phone": "13273721553",
      "businessHours": "07:30-21:00"
    }
  }
}
```

---

## 十二、统计模块 API

### 12.1 仪表盘统计数据

**接口地址**：`GET /api/admin/dashboard/statistics`

**响应示例**：
```json
{
  "code": 200,
  "data": {
    "todaySales": 1878.00,
    "todayOrders": 10,
    "todayMembers": 2,
    "todayBookings": 5,
    "totalProducts": 17,
    "lowStockAlerts": 4,
    "pendingPayments": 3,
    "completedOrders": 7,
    "refundOrders": 3,
    "totalMembers": 50,
    "activeMembers": 35,
    "totalServices": 6,
    "recentOrders": [...]
  },
  "message": "success"
}
```

---

### 12.2 销售趋势数据

**接口地址**：`GET /api/admin/statistics/sales-trend`

**请求参数**：
- `timeRange` (string, optional): 时间范围（7d/30d/90d），默认7d

**响应示例**：
```json
{
  "code": 200,
  "data": {
    "dates": ["2024-01-23", "2024-01-24", "2024-01-25", "2024-01-26", "2024-01-27", "2024-01-28", "2024-01-29"],
    "sales": [1250.00, 1380.00, 1450.00, 1520.00, 1680.00, 1750.00, 1878.00],
    "orders": [8, 9, 10, 11, 12, 13, 10]
  },
  "message": "success"
}
```

---

### 12.3 订单状态分布

**接口地址**：`GET /api/admin/statistics/order-status`

**请求参数**：
- `timeRange` (string, optional): 时间范围（7d/30d/90d），默认7d

**响应示例**：
```json
{
  "code": 200,
  "data": [
    {"status": "pending_payment", "count": 3, "percentage": 30.0},
    {"status": "paid", "count": 2, "percentage": 20.0},
    {"status": "shipped", "count": 1, "percentage": 10.0},
    {"status": "completed", "count": 4, "percentage": 40.0}
  ],
  "message": "success"
}
```

---

## 十三、订单统计 API

### 13.1 订单统计

**接口地址**：`GET /api/admin/orders/statistics`

**请求参数**：
- `timeRange` (string, optional): 时间范围（7d/30d/90d），默认7d

**响应示例**：
```json
{
  "code": 200,
  "data": {
    "totalOrders": 10,
    "totalAmount": 1878.00,
    "completedOrders": 7,
    "refundOrders": 3,
    "completionRate": 70.0,
    "refundRate": 30.0,
    "orderTrend": [10, 0, 0, 0, 0, 0, 0],
    "amountTrend": [1878.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    "orderStatusDistribution": [...],
    "recentOrders": [...]
  },
  "message": "success"
}
```

---

## 十四、测试数据说明

### 14.1 测试用户

| 用户名 | 密码 | 昵称 | VIP等级 | 积分 | 余额 |
|--------|------|------|---------|------|------|
| test001 | password123 | 宝宝妈妈 | VIP1 | 500 | 100.00 |
| test002 | password123 | 奶爸老王 | VIP2 | 1200 | 500.00 |
| test003 | password123 | 宝贝爸比 | VIP0 | 0 | 0.00 |

### 14.2 测试商品

| ID | 商品名称 | 分类 | 价格 | 库存 | 销量 |
|----|---------|------|------|------|------|
| 1 | 爱他美白金版奶粉 | milk | 199.00 | 200 | 1258 |
| 2 | 美赞臣蓝臻奶粉 | milk | 268.00 | 150 | 892 |
| 3 | 花王纸尿裤 | diaper | 89.00 | 500 | 2341 |
| 7 | 费雪安抚海马 | toys | 128.00 | 100 | 567 |

### 14.3 测试服务

| ID | 服务名称 | 图标 | 价格 | 时长 |
|----|---------|------|------|------|
| 1 | 婴儿游泳 | 🏊 | 58.00 | 30分钟 |
| 2 | 婴儿理发 | ✂️ | 30.00 | 15分钟 |
| 3 | 满月照拍摄 | 📷 | 199.00 | 60分钟 |

---

## 十五、Postman测试建议

### 15.1 环境配置

创建Postman环境，配置变量：
- `baseUrl`: `http://localhost:8080`
- `token`: （登录后获取）

### 15.2 测试流程

1. **先登录获取token**
   - 调用 `POST /api/auth/login`
   - 将返回的 `token` 复制到环境变量的 `token` 中

2. **测试需要认证的接口**
   - 在请求头的 `Authorization` 字段中填写 `Bearer {{token}}`

3. **测试业务流程**
   - 添加购物车 → 创建订单 → 支付订单 → 查看订单列表 → 确认收货

---

## 文档信息

- **创建日期**：2026-04-30
- **最后更新**：2026-04-30
- **版本**：1.0
