# 后台管理系统API对接文档

## 1. 商品管理 API

### 1.1 商品列表
- **接口路径**: `/api/admin/goods/list`
- **请求方法**: `GET`
- **请求参数**:
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
  - `categoryId`: 分类ID，可选
  - `keyword`: 搜索关键词，可选
  - `isSeckill`: 是否秒杀商品，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "爱他美白金版奶粉",
          "image": "/static/alice.png",
          "price": "199",
          "originalPrice": "299",
          "spec": "800g/罐",
          "categoryId": 1,
          "categoryName": "奶粉",
          "sales": 1258,
          "stock": 200,
          "isSeckill": true,
          "createdAt": "2024-01-01T00:00:00Z",
          "updatedAt": "2024-01-01T00:00:00Z"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 1.2 商品详情
- **接口路径**: `/api/admin/goods/:id`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1,
      "name": "爱他美白金版奶粉",
      "image": "/static/alice.png",
      "images": ["/static/alice.png", "/static/alice.png"],
      "detailImages": ["/static/alice.png"],
      "price": "199",
      "originalPrice": "299",
      "spec": "800g/罐",
      "categoryId": 1,
      "categoryName": "奶粉",
      "sales": 1258,
      "stock": 200,
      "isSeckill": true,
      "description": "商品详细描述",
      "packages": [
        { "name": "单罐", "price": "199" },
        { "name": "两罐套装", "price": "380" }
      ],
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-01T00:00:00Z"
    }
  }
  ```

### 1.3 添加商品
- **接口路径**: `/api/admin/goods`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "爱他美白金版奶粉",
    "image": "/static/alice.png",
    "images": ["/static/alice.png"],
    "detailImages": ["/static/alice.png"],
    "price": "199",
    "originalPrice": "299",
    "spec": "800g/罐",
    "categoryId": 1,
    "stock": 200,
    "isSeckill": true,
    "description": "商品详细描述",
    "packages": [
      { "name": "单罐", "price": "199" },
      { "name": "两罐套装", "price": "380" }
    ]
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1
    }
  }
  ```

### 1.4 更新商品
- **接口路径**: `/api/admin/goods/:id`
- **请求方法**: `PUT`
- **请求参数**: 同添加商品
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1
    }
  }
  ```

### 1.5 删除商品
- **接口路径**: `/api/admin/goods/:id`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 1.6 商品分类列表
- **接口路径**: `/api/admin/categories`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": [
      { "id": 1, "name": "奶粉" },
      { "id": 2, "name": "尿裤" },
      { "id": 3, "name": "童装" },
      { "id": 4, "name": "玩具" },
      { "id": 5, "name": "喂养" },
      { "id": 6, "name": "洗护" },
      { "id": 7, "name": "营养" },
      { "id": 8, "name": "出行" }
    ]
  }
  ```

## 2. 用户管理 API

### 2.1 用户列表
- **接口路径**: `/api/admin/users/list`
- **请求方法**: `GET`
- **请求参数**:
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
  - `keyword`: 搜索关键词，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "username": "user1",
          "nickname": "用户1",
          "phone": "13800138000",
          "avatar": "/static/avatar.png",
          "gender": 1,
          "birthday": "2000-01-01",
          "address": "北京市朝阳区",
          "isVip": true,
          "vipLevel": 2,
          "points": 1000,
          "createdAt": "2024-01-01T00:00:00Z"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 2.2 用户详情
- **接口路径**: `/api/admin/users/:id`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1,
      "username": "user1",
      "nickname": "用户1",
      "phone": "13800138000",
      "avatar": "/static/avatar.png",
      "gender": 1,
      "birthday": "2000-01-01",
      "address": "北京市朝阳区",
      "isVip": true,
      "vipLevel": 2,
      "points": 1000,
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-01T00:00:00Z"
    }
  }
  ```

### 2.3 更新用户信息
- **接口路径**: `/api/admin/users/:id`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "nickname": "用户1",
    "phone": "13800138000",
    "avatar": "/static/avatar.png",
    "gender": 1,
    "birthday": "2000-01-01",
    "address": "北京市朝阳区",
    "isVip": true,
    "vipLevel": 2,
    "points": 1000
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 2.4 删除用户
- **接口路径**: `/api/admin/users/:id`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 3. 订单管理 API

### 3.1 订单列表
- **接口路径**: `/api/admin/orders/list`
- **请求方法**: `GET`
- **请求参数**:
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
  - `status`: 订单状态，可选
  - `keyword`: 搜索关键词，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": "20240129001",
          "userId": 1,
          "userName": "用户1",
          "totalPrice": "288",
          "totalQuantity": 2,
          "status": "已完成",
          "statusClass": "completed",
          "paymentMethod": "微信支付",
          "paymentTime": "2024-01-29T10:00:00Z",
          "shippingAddress": "北京市朝阳区",
          "createdAt": "2024-01-29T09:00:00Z",
          "updatedAt": "2024-01-29T10:00:00Z",
          "products": [
            {
              "id": 1,
              "name": "爱他美白金版奶粉",
              "price": "199",
              "quantity": 1,
              "image": "/static/alice.png"
            },
            {
              "id": 2,
              "name": "花王纸尿裤",
              "price": "89",
              "quantity": 1,
              "image": "/static/alice.png"
            }
          ]
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 3.2 订单详情
- **接口路径**: `/api/admin/orders/:id`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": "20240129001",
      "userId": 1,
      "userName": "用户1",
      "totalPrice": "288",
      "totalQuantity": 2,
      "status": "已完成",
      "statusClass": "completed",
      "paymentMethod": "微信支付",
      "paymentTime": "2024-01-29T10:00:00Z",
      "shippingAddress": "北京市朝阳区",
      "remark": "请尽快发货",
      "createdAt": "2024-01-29T09:00:00Z",
      "updatedAt": "2024-01-29T10:00:00Z",
      "products": [
        {
          "id": 1,
          "name": "爱他美白金版奶粉",
          "price": "199",
          "quantity": 1,
          "image": "/static/alice.png"
        },
        {
          "id": 2,
          "name": "花王纸尿裤",
          "price": "89",
          "quantity": 1,
          "image": "/static/alice.png"
        }
      ]
    }
  }
  ```

### 3.3 更新订单状态
- **接口路径**: `/api/admin/orders/:id/status`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "status": "已完成"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 3.4 删除订单
- **接口路径**: `/api/admin/orders/:id`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 4. 服务管理 API

### 4.1 服务列表
- **接口路径**: `/api/admin/services/list`
- **请求方法**: `GET`
- **请求参数**:
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
  - `keyword`: 搜索关键词，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "婴儿洗澡",
          "icon": "🛁",
          "description": "专业婴儿洗澡服务",
          "price": "58",
          "duration": "30分钟",
          "createdAt": "2024-01-01T00:00:00Z",
          "updatedAt": "2024-01-01T00:00:00Z"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 4.2 服务详情
- **接口路径**: `/api/admin/services/:id`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1,
      "name": "婴儿洗澡",
      "icon": "🛁",
      "description": "专业婴儿洗澡服务",
      "price": "58",
      "duration": "30分钟",
      "details": "详细服务说明",
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-01T00:00:00Z"
    }
  }
  ```

### 4.3 添加服务
- **接口路径**: `/api/admin/services`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "婴儿洗澡",
    "icon": "🛁",
    "description": "专业婴儿洗澡服务",
    "price": "58",
    "duration": "30分钟",
    "details": "详细服务说明"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1
    }
  }
  ```

### 4.4 更新服务
- **接口路径**: `/api/admin/services/:id`
- **请求方法**: `PUT`
- **请求参数**: 同添加服务
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.5 删除服务
- **接口路径**: `/api/admin/services/:id`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.6 服务预约列表
- **接口路径**: `/api/admin/service-bookings/list`
- **请求方法**: `GET`
- **请求参数**:
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
  - `serviceId`: 服务ID，可选
  - `status`: 预约状态，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "serviceId": 1,
          "serviceName": "婴儿洗澡",
          "userId": 1,
          "userName": "用户1",
          "phone": "13800138000",
          "bookingTime": "2024-01-29T14:00:00Z",
          "status": "已预约",
          "createdAt": "2024-01-28T09:00:00Z",
          "updatedAt": "2024-01-28T09:00:00Z"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 4.7 更新预约状态
- **接口路径**: `/api/admin/service-bookings/:id/status`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "status": "已完成"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 5. 会员管理 API

### 5.1 会员列表
- **接口路径**: `/api/admin/members/list`
- **请求方法**: `GET`
- **请求参数**:
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
  - `keyword`: 搜索关键词，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "userId": 1,
          "userName": "用户1",
          "phone": "13800138000",
          "vipLevel": 2,
          "points": 1000,
          "expireDate": "2025-01-01T00:00:00Z",
          "createdAt": "2024-01-01T00:00:00Z",
          "updatedAt": "2024-01-01T00:00:00Z"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 5.2 会员详情
- **接口路径**: `/api/admin/members/:id`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 1,
      "userId": 1,
      "userName": "用户1",
      "phone": "13800138000",
      "vipLevel": 2,
      "points": 1000,
      "expireDate": "2025-01-01T00:00:00Z",
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-01T00:00:00Z",
      "rechargeRecords": [
        {
          "id": 1,
          "amount": "500",
          "points": 500,
          "createdAt": "2024-01-01T00:00:00Z"
        }
      ]
    }
  }
  ```

### 5.3 更新会员信息
- **接口路径**: `/api/admin/members/:id`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "vipLevel": 3,
    "points": 2000,
    "expireDate": "2025-01-01T00:00:00Z"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 5.4 会员充值
- **接口路径**: `/api/admin/members/recharge`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "userId": 1,
    "amount": "500",
    "points": 500
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 6. 数据统计 API

### 6.1 销售统计
- **接口路径**: `/api/admin/statistics/sales`
- **请求方法**: `GET`
- **请求参数**:
  - `startDate`: 开始日期，可选
  - `endDate`: 结束日期，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalSales": "100000",
      "totalOrders": 500,
      "totalProducts": 100,
      "totalUsers": 200,
      "dailySales": [
        { "date": "2024-01-01", "sales": "1000" },
        { "date": "2024-01-02", "sales": "1200" }
      ],
      "categorySales": [
        { "category": "奶粉", "sales": "50000" },
        { "category": "尿裤", "sales": "30000" }
      ]
    }
  }
  ```

### 6.2 用户统计
- **接口路径**: `/api/admin/statistics/users`
- **请求方法**: `GET`
- **请求参数**:
  - `startDate`: 开始日期，可选
  - `endDate`: 结束日期，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalUsers": 200,
      "newUsers": 50,
      "vipUsers": 100,
      "dailyUsers": [
        { "date": "2024-01-01", "count": 10 },
        { "date": "2024-01-02", "count": 15 }
      ]
    }
  }
  ```

### 6.3 服务统计
- **接口路径**: `/api/admin/statistics/services`
- **请求方法**: `GET`
- **请求参数**:
  - `startDate`: 开始日期，可选
  - `endDate`: 结束日期，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalBookings": 300,
      "totalRevenue": "10000",
      "serviceBookings": [
        { "service": "婴儿洗澡", "count": 150 },
        { "service": "婴儿理发", "count": 100 }
      ]
    }
  }
  ```

## 7. 系统管理 API

### 7.1 登录
- **接口路径**: `/api/admin/login`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "username": "admin",
    "password": "123456"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
      "user": {
        "id": 1,
        "username": "admin",
        "nickname": "管理员",
        "avatar": "/static/admin.png"
      }
    }
  }
  ```

### 7.2 退出登录
- **接口路径**: `/api/admin/logout`
- **请求方法**: `POST`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 7.3 修改密码
- **接口路径**: `/api/admin/change-password`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "oldPassword": "123456",
    "newPassword": "654321"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 8. 通用 API

### 8.1 上传图片
- **接口路径**: `/api/admin/upload/image`
- **请求方法**: `POST`
- **请求参数**: 表单数据，字段名为 `file`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "url": "/static/upload/20240101/123456.png"
    }
  }
  ```

### 8.2 获取系统配置
- **接口路径**: `/api/admin/config`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "siteName": "爱宝贝儿孕婴生活馆",
      "logo": "/static/logo.png",
      "contactPhone": "400-123-4567",
      "address": "北京市朝阳区",
      "workingHours": "周一至周日 9:00-21:00"
    }
  }
  ```

### 8.3 更新系统配置
- **接口路径**: `/api/admin/config`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "siteName": "爱宝贝儿孕婴生活馆",
    "logo": "/static/logo.png",
    "contactPhone": "400-123-4567",
    "address": "北京市朝阳区",
    "workingHours": "周一至周日 9:00-21:00"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 9. 错误码说明

| 错误码 | 说明 |
| --- | --- |
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 10. 请求头说明

所有需要认证的接口都需要在请求头中添加 `Authorization` 字段，值为 `Bearer {token}`，其中 `{token}` 是登录接口返回的令牌。

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```