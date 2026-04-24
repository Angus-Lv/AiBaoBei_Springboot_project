# 后台管理系统API文档

## 1. 商品管理 API

### 1.1 商品列表

- **接口路径**: `/api/admin/goods/list`
- **请求方法**: `GET`
- **请求参数**:
  - `keyword`: 搜索关键词，可选
  - `category`: 分类ID，可选
  - `status`: 状态，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "商品名称",
          "image": "商品图片",
          "price": "价格",
          "stock": 库存,
          "status": "状态",
          "categoryId": 分类ID,
          "categoryName": "分类名称",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 1.2 添加商品

- **接口路径**: `/api/admin/goods`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "商品名称",
    "image": "商品图片",
    "price": "价格",
    "stock": 库存,
    "categoryId": 分类ID,
    "status": "状态",
    "description": "商品描述",
    "packages": [
      { "name": "套餐名称", "price": "套餐价格" }
    ]
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 商品ID
    }
  }
  ```

### 1.3 编辑商品

- **接口路径**: `/api/admin/goods/{id}`
- **请求方法**: `PUT`
- **请求参数**: 同添加商品
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 1.4 删除商品

- **接口路径**: `/api/admin/goods/{id}`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 1.5 更新商品状态

- **接口路径**: `/api/admin/goods/{id}/status`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "status": "状态"
  }
  ```
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
      { "id": 1, "name": "分类名称" }
    ]
  }
  ```

### 1.7 添加商品分类

- **接口路径**: `/api/admin/categories`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "分类名称"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 1.8 编辑商品分类

- **接口路径**: `/api/admin/categories/{id}`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "name": "分类名称"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 1.9 删除商品分类

- **接口路径**: `/api/admin/categories/{id}`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 2. 订单管理 API

### 2.1 订单列表

- **接口路径**: `/api/admin/orders/list`
- **请求方法**: `GET`
- **请求参数**:
  - `keyword`: 搜索关键词，可选
  - `status`: 订单状态，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": "订单ID",
          "userId": 用户ID,
          "userName": "用户名",
          "totalPrice": "总价",
          "status": "状态",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 2.2 发货

- **接口路径**: `/api/admin/orders/{id}/ship`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "trackingNumber": "物流单号",
    "expressCompany": "快递公司"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 2.3 取消订单

- **接口路径**: `/api/admin/orders/{id}/cancel`
- **请求方法**: `PUT`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 2.4 完成订单

- **接口路径**: `/api/admin/orders/{id}/complete`
- **请求方法**: `PUT`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 2.5 销售统计

- **接口路径**: `/api/admin/statistics/sales`
- **请求方法**: `GET`
- **请求参数**:
  - `timeRange`: 时间范围，可选
  - `startDate`: 开始日期，可选
  - `endDate`: 结束日期，可选
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalOrders": 总订单数,
      "totalAmount": 总金额,
      "completedOrders": 已完成订单数,
      "refundOrders": 退款订单数,
      "orderTrend": 订单趋势,
      "amountTrend": 金额趋势,
      "completionRate": 完成率,
      "refundRate": 退款率
    }
  }
  ```

## 3. 服务管理 API

### 3.1 服务列表

- **接口路径**: `/api/admin/services/list`
- **请求方法**: `GET`
- **请求参数**:
  - `keyword`: 搜索关键词，可选
  - `category`: 分类ID，可选
  - `status`: 状态，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "服务名称",
          "price": "价格",
          "status": "状态",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 3.2 添加服务

- **接口路径**: `/api/admin/services`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "服务名称",
    "price": "价格",
    "description": "服务描述",
    "status": "状态"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 服务ID
    }
  }
  ```

### 3.3 编辑服务

- **接口路径**: `/api/admin/services/{id}`
- **请求方法**: `PUT`
- **请求参数**: 同添加服务
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 3.4 更新服务状态

- **接口路径**: `/api/admin/services/{id}/status`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "status": "状态"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 4. 会员管理 API

### 4.1 会员列表

- **接口路径**: `/api/admin/members/list`
- **请求方法**: `GET`
- **请求参数**:
  - `keyword`: 搜索关键词，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "userId": 用户ID,
          "userName": "用户名",
          "phone": "手机号",
          "points": 积分,
          "vipLevel": 会员等级,
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 4.2 更新会员信息

- **接口路径**: `/api/admin/members/{id}`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "vipLevel": 会员等级,
    "points": 积分
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.3 积分统计

- **接口路径**: `/api/admin/points/stats`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalPoints": 总积分,
      "usedPoints": 已使用积分,
      "availablePoints": 可用积分
    }
  }
  ```

### 4.4 积分设置

- **接口路径**: `/api/admin/points/settings`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "signInPoints": 签到积分,
      "purchasePointsRate": 消费积分比例
    }
  }
  ```

### 4.5 更新积分设置

- **接口路径**: `/api/admin/points/settings`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "signInPoints": 签到积分,
    "purchasePointsRate": 消费积分比例
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.6 积分记录

- **接口路径**: `/api/admin/points/records`
- **请求方法**: `GET`
- **请求参数**:
  - `userId`: 用户ID，可选
  - `type`: 记录类型，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "userId": 用户ID,
          "userName": "用户名",
          "points": 积分数量,
          "type": "记录类型",
          "description": "描述",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 4.7 签到统计

- **接口路径**: `/api/admin/signin/stats`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalSignIn": 总签到次数,
      "activeUsers": 活跃用户数
    }
  }
  ```

### 4.8 签到设置

- **接口路径**: `/api/admin/signin/settings`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "signInPoints": 签到积分,
      "continuousSignInBonus": 连续签到奖励
    }
  }
  ```

### 4.9 更新签到设置

- **接口路径**: `/api/admin/signin/settings`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "signInPoints": 签到积分,
    "continuousSignInBonus": 连续签到奖励
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.10 签到记录

- **接口路径**: `/api/admin/signin/records`
- **请求方法**: `GET`
- **请求参数**:
  - `userId`: 用户ID，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "userId": 用户ID,
          "userName": "用户名",
          "points": 签到积分,
          "createdAt": "签到时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 4.11 充值统计

- **接口路径**: `/api/admin/recharge/stats`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalRecharge": 总充值金额,
      "totalUsers": 充值用户数
    }
  }
  ```

### 4.12 充值记录

- **接口路径**: `/api/admin/recharge/records`
- **请求方法**: `GET`
- **请求参数**:
  - `userId`: 用户ID，可选
  - `status`: 状态，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "userId": 用户ID,
          "userName": "用户名",
          "amount": "充值金额",
          "status": "状态",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 4.13 取消充值

- **接口路径**: `/api/admin/recharge/records/{id}/cancel`
- **请求方法**: `PUT`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.14 积分兑换统计

- **接口路径**: `/api/admin/point-exchange/stats`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "totalExchange": 总兑换次数,
      "totalPoints": 总消耗积分
    }
  }
  ```

### 4.15 礼品列表

- **接口路径**: `/api/admin/gifts`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": [
      {
        "id": 1,
        "name": "礼品名称",
        "points": 所需积分,
        "stock": 库存,
        "status": "状态"
      }
    ]
  }
  ```

### 4.16 积分兑换记录

- **接口路径**: `/api/admin/point-exchange/records`
- **请求方法**: `GET`
- **请求参数**:
  - `userId`: 用户ID，可选
  - `status`: 状态，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "userId": 用户ID,
          "userName": "用户名",
          "giftId": 礼品ID,
          "giftName": "礼品名称",
          "points": 消耗积分,
          "status": "状态",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 4.17 完成积分兑换

- **接口路径**: `/api/admin/point-exchange/records/{id}/complete`
- **请求方法**: `PUT`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.18 添加礼品

- **接口路径**: `/api/admin/gifts`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "礼品名称",
    "points": 所需积分,
    "stock": 库存,
    "status": "状态"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 礼品ID
    }
  }
  ```

### 4.19 编辑礼品

- **接口路径**: `/api/admin/gifts/{id}`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "name": "礼品名称",
    "points": 所需积分,
    "stock": 库存,
    "status": "状态"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 4.20 删除礼品

- **接口路径**: `/api/admin/gifts/{id}`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 5. 营销管理 API

### 5.1 积分礼品列表

- **接口路径**: `/api/admin/points-gifts/list`
- **请求方法**: `GET`
- **请求参数**:
  - `keyword`: 搜索关键词，可选
  - `page`: 页码，默认1
  - `pageSize`: 每页数量，默认10
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "礼品名称",
          "points": 所需积分,
          "stock": 库存,
          "status": "状态",
          "createdAt": "创建时间"
        }
      ],
      "total": 总条数
    }
  }
  ```

### 5.2 添加积分礼品

- **接口路径**: `/api/admin/points-gifts`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "name": "礼品名称",
    "points": 所需积分,
    "stock": 库存,
    "status": "状态"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "id": 礼品ID
    }
  }
  ```

### 5.3 编辑积分礼品

- **接口路径**: `/api/admin/points-gifts/{id}`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "name": "礼品名称",
    "points": 所需积分,
    "stock": 库存,
    "status": "状态"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 5.4 删除积分礼品

- **接口路径**: `/api/admin/points-gifts/{id}`
- **请求方法**: `DELETE`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 6. 系统管理 API

### 6.1 获取系统配置

- **接口路径**: `/api/admin/config`
- **请求方法**: `GET`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "siteName": "网站名称",
      "logo": "网站Logo",
      "contactPhone": "联系电话",
      "address": "地址",
      "workingHours": "营业时间"
    }
  }
  ```

### 6.2 更新系统配置

- **接口路径**: `/api/admin/config`
- **请求方法**: `PUT`
- **请求参数**:
  ```json
  {
    "siteName": "网站名称",
    "logo": "网站Logo",
    "contactPhone": "联系电话",
    "address": "地址",
    "workingHours": "营业时间"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

### 6.3 上传图片

- **接口路径**: `/api/admin/upload/image`
- **请求方法**: `POST`
- **请求参数**: 表单数据，字段名为 `file`
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success",
    "data": {
      "url": "图片URL"
    }
  }
  ```

### 6.4 修改密码

- **接口路径**: `/api/admin/change-password`
- **请求方法**: `POST`
- **请求参数**:
  ```json
  {
    "oldPassword": "旧密码",
    "newPassword": "新密码"
  }
  ```
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "success"
  }
  ```

## 7. 错误码说明

| 错误码 | 说明      |
| --- | ------- |
| 200 | 成功      |
| 400 | 请求参数错误  |
| 401 | 未授权     |
| 403 | 禁止访问    |
| 404 | 资源不存在   |
| 500 | 服务器内部错误 |

## 8. 请求头说明

所有需要认证的接口都需要在请求头中添加 `Authorization` 字段，值为 `Bearer {token}`，其中 `{token}` 是登录接口返回的令牌。

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

