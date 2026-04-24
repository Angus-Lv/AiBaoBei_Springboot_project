# 爱宝贝儿后台管理系统API文档

## 1. 认证模块

### 1.1 管理员登录
- **接口路径**: `/api/admin/login`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | username | string | 是 | 用户名 |
  | password | string | 是 | 密码 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "登录成功",
    "data": {
      "token": "JWT token",
      "admin": {
        "id": 1,
        "username": "admin",
        "role": "admin"
      }
    }
  }
  ```

### 1.2 管理员登出
- **接口路径**: `/api/admin/logout`
- **请求方法**: POST
- **请求头**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | Authorization | string | 是 | Bearer token |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "登出成功"
  }
  ```

## 2. 会员管理模块

### 2.1 获取会员列表
- **接口路径**: `/api/admin/members`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | keyword | string | 否 | 搜索关键词 |
  | status | string | 否 | 会员状态 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "username": "user1",
          "phone": "13800138001",
          "points": 100,
          "balance": 500,
          "status": "active",
          "createTime": "2024-01-01 10:00:00"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 2.2 获取会员详情
- **接口路径**: `/api/admin/members/{id}`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 会员ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "username": "user1",
      "phone": "13800138001",
      "points": 100,
      "balance": 500,
      "status": "active",
      "createTime": "2024-01-01 10:00:00",
      "lastLoginTime": "2024-01-10 15:30:00"
    }
  }
  ```

### 2.3 更新会员信息
- **接口路径**: `/api/admin/members/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 会员ID |
  | points | number | 否 | 积分 |
  | balance | number | 否 | 余额 |
  | status | string | 否 | 状态 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "points": 200,
      "balance": 600,
      "status": "active"
    }
  }
  ```

### 2.4 禁用/启用会员
- **接口路径**: `/api/admin/members/{id}/status`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 会员ID |
  | status | string | 是 | 状态 (active/inactive) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": {
      "id": 1,
      "status": "inactive"
    }
  }
  ```

## 3. 充值管理模块

### 3.1 获取充值记录
- **接口路径**: `/api/admin/recharges`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | memberId | number | 否 | 会员ID |
  | status | string | 否 | 充值状态 |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "orderNo": "RE202401010001",
          "memberId": 1,
          "memberNickname": "张三",
          "amount": 500,
          "bonus": 50,
          "totalAmount": 550,
          "status": "success",
          "createTime": "2024-01-01 10:00:00",
          "completeTime": "2024-01-01 10:01:00"
        }
      ],
      "total": 50,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 3.2 获取充值档位
- **接口路径**: `/api/admin/recharge-tiers`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {
        "id": 1,
        "amount": 100,
        "bonus": 0,
        "sort": 1
      },
      {
        "id": 2,
        "amount": 500,
        "bonus": 50,
        "sort": 2
      }
    ]
  }
  ```

### 3.3 添加充值档位
- **接口路径**: `/api/admin/recharge-tiers`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | amount | number | 是 | 充值金额 |
  | bonus | number | 是 | 赠送金额 |
  | sort | number | 是 | 排序 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 3,
      "amount": 1000,
      "bonus": 120,
      "sort": 3
    }
  }
  ```

### 3.4 更新充值档位
- **接口路径**: `/api/admin/recharge-tiers/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 档位ID |
  | amount | number | 否 | 充值金额 |
  | bonus | number | 否 | 赠送金额 |
  | sort | number | 否 | 排序 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "amount": 100,
      "bonus": 10,
      "sort": 1
    }
  }
  ```

### 3.5 删除充值档位
- **接口路径**: `/api/admin/recharge-tiers/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 档位ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

## 4. 签到管理模块

### 4.1 获取签到设置
- **接口路径**: `/api/admin/signin/settings`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "dailyPoints": 10,
      "连续签到奖励": [
        { "days": 3, "points": 50 },
        { "days": 7, "points": 100 }
      ],
      "status": "enabled"
    }
  }
  ```

### 4.2 更新签到设置
- **接口路径**: `/api/admin/signin/settings`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | dailyPoints | number | 是 | 每日签到积分 |
  | continuousRewards | array | 是 | 连续签到奖励 |
  | status | string | 是 | 状态 (enabled/disabled) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "dailyPoints": 15,
      "连续签到奖励": [
        { "days": 3, "points": 50 },
        { "days": 7, "points": 100 },
        { "days": 14, "points": 200 }
      ],
      "status": "enabled"
    }
  }
  ```

### 4.3 获取签到记录
- **接口路径**: `/api/admin/signin/records`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | memberId | number | 否 | 会员ID |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "memberId": 1,
          "memberNickname": "张三",
          "points": 10,
          "signinDate": "2024-01-01",
          "createTime": "2024-01-01 08:00:00"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

## 5. 积分管理模块

### 5.1 获取积分设置
- **接口路径**: `/api/admin/points/settings`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "pointsPerYuan": 1,
      "signinPoints": 10,
      "registerPoints": 100,
      "pointExpiryDays": 365,
      "enablePoints": true
    }
  }
  ```

### 5.2 更新积分设置
- **接口路径**: `/api/admin/points/settings`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | pointsPerYuan | number | 是 | 消费1元获得积分 |
  | signinPoints | number | 是 | 每日签到积分 |
  | registerPoints | number | 是 | 首次注册积分 |
  | pointExpiryDays | number | 是 | 积分有效期(天) |
  | enablePoints | boolean | 是 | 是否开启积分 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "pointsPerYuan": 1.5,
      "signinPoints": 15,
      "registerPoints": 100,
      "pointExpiryDays": 365,
      "enablePoints": true
    }
  }
  ```

### 5.3 获取积分记录
- **接口路径**: `/api/admin/points/records`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | memberId | number | 否 | 会员ID |
  | type | string | 否 | 操作类型 |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "memberId": 1,
          "memberNickname": "张三",
          "points": 100,
          "type": "increase",
          "typeDetail": "register",
          "balance": 100,
          "createTime": "2024-01-01 10:00:00",
          "remark": "首次注册获得积分"
        }
      ],
      "total": 200,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 5.4 手动调整积分
- **接口路径**: `/api/admin/points/adjust`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | memberId | number | 是 | 会员ID |
  | points | number | 是 | 调整积分(正数增加，负数减少) |
  | remark | string | 是 | 调整原因 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "调整成功",
    "data": {
      "memberId": 1,
      "points": 50,
      "balance": 150,
      "remark": "活动奖励"
    }
  }
  ```

## 6. 积分兑换模块

### 6.1 获取礼品列表
- **接口路径**: `/api/admin/gifts`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | status | string | 否 | 礼品状态 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "精美笔记本",
          "points": 100,
          "stock": 50,
          "exchangedCount": 12,
          "image": "gift1.jpg",
          "description": "高品质纸质笔记本",
          "status": "active",
          "createTime": "2024-01-01 10:00:00"
        }
      ],
      "total": 20,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 6.2 添加礼品
- **接口路径**: `/api/admin/gifts`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | name | string | 是 | 礼品名称 |
  | points | number | 是 | 所需积分 |
  | stock | number | 是 | 库存数量 |
  | image | string | 是 | 礼品图片 |
  | description | string | 是 | 礼品描述 |
  | status | string | 是 | 状态 (active/inactive) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 2,
      "name": "保温杯",
      "points": 300,
      "stock": 30,
      "exchangedCount": 0,
      "image": "gift2.jpg",
      "description": "304不锈钢保温杯",
      "status": "active",
      "createTime": "2024-01-02 10:00:00"
    }
  }
  ```

### 6.3 更新礼品
- **接口路径**: `/api/admin/gifts/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 礼品ID |
  | name | string | 否 | 礼品名称 |
  | points | number | 否 | 所需积分 |
  | stock | number | 否 | 库存数量 |
  | image | string | 否 | 礼品图片 |
  | description | string | 否 | 礼品描述 |
  | status | string | 否 | 状态 (active/inactive) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "name": "精美笔记本",
      "points": 120,
      "stock": 45,
      "exchangedCount": 12,
      "image": "gift1.jpg",
      "description": "高品质纸质笔记本",
      "status": "active",
      "createTime": "2024-01-01 10:00:00"
    }
  }
  ```

### 6.4 删除礼品
- **接口路径**: `/api/admin/gifts/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 礼品ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

### 6.5 获取兑换记录
- **接口路径**: `/api/admin/exchanges`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | memberId | number | 否 | 会员ID |
  | status | string | 否 | 兑换状态 |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "orderNo": "EX202401010001",
          "memberId": 1,
          "memberNickname": "张三",
          "giftId": 1,
          "giftName": "精美笔记本",
          "giftPoints": 100,
          "receiverName": "张三",
          "receiverPhone": "13800138001",
          "receiverAddress": "北京市朝阳区",
          "status": "completed",
          "exchangeTime": "2024-01-01 14:30:00",
          "completeTime": "2024-01-02 10:00:00"
        }
      ],
      "total": 50,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 6.6 处理兑换订单
- **接口路径**: `/api/admin/exchanges/{id}/process`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 兑换订单ID |
  | status | string | 是 | 状态 (completed/cancelled) |
  | remark | string | 否 | 处理备注 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "处理成功",
    "data": {
      "id": 1,
      "status": "completed",
      "completeTime": "2024-01-02 10:00:00"
    }
  }
  ```

## 7. 商品管理模块

### 7.1 获取商品列表
- **接口路径**: `/api/admin/products`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | category | string | 否 | 商品分类 |
  | keyword | string | 否 | 搜索关键词 |
  | status | string | 否 | 商品状态 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "爱他美白金版奶粉",
          "category": "milk",
          "image": "product1.jpg",
          "price": 199,
          "originalPrice": 299,
          "stock": 200,
          "sales": 1258,
          "status": "active",
          "isSeckill": true,
          "createTime": "2024-01-01 10:00:00"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 7.2 添加商品
- **接口路径**: `/api/admin/products`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | name | string | 是 | 商品名称 |
  | category | string | 是 | 商品分类 |
  | image | string | 是 | 商品图片 |
  | images | array | 是 | 商品图片列表 |
  | price | number | 是 | 商品价格 |
  | originalPrice | number | 是 | 原价 |
  | stock | number | 是 | 库存 |
  | sales | number | 是 | 销量 |
  | status | string | 是 | 状态 (active/inactive) |
  | isSeckill | boolean | 是 | 是否秒杀 |
  | seckillPrice | number | 否 | 秒杀价格 |
  | seckillStock | number | 否 | 秒杀库存 |
  | spec | string | 否 | 商品规格 |
  | description | string | 否 | 商品描述 |
  | detailImages | array | 否 | 详情图片 |
  | packages | array | 否 | 商品套餐 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 2,
      "name": "爱他美白金版奶粉",
      "category": "milk",
      "image": "product1.jpg",
      "price": 199,
      "originalPrice": 299,
      "stock": 200,
      "sales": 0,
      "status": "active",
      "isSeckill": false,
      "createTime": "2024-01-02 10:00:00"
    }
  }
  ```

### 7.3 更新商品
- **接口路径**: `/api/admin/products/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 商品ID |
  | name | string | 否 | 商品名称 |
  | category | string | 否 | 商品分类 |
  | image | string | 否 | 商品图片 |
  | images | array | 否 | 商品图片列表 |
  | price | number | 否 | 商品价格 |
  | originalPrice | number | 否 | 原价 |
  | stock | number | 否 | 库存 |
  | status | string | 否 | 状态 (active/inactive) |
  | isSeckill | boolean | 否 | 是否秒杀 |
  | seckillPrice | number | 否 | 秒杀价格 |
  | seckillStock | number | 否 | 秒杀库存 |
  | spec | string | 否 | 商品规格 |
  | description | string | 否 | 商品描述 |
  | detailImages | array | 否 | 详情图片 |
  | packages | array | 否 | 商品套餐 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "name": "爱他美白金版奶粉",
      "category": "milk",
      "image": "product1.jpg",
      "price": 189,
      "originalPrice": 299,
      "stock": 180,
      "status": "active",
      "isSeckill": true,
      "seckillPrice": 159,
      "seckillStock": 50
    }
  }
  ```

### 7.4 删除商品
- **接口路径**: `/api/admin/products/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 商品ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

### 7.5 获取商品分类
- **接口路径**: `/api/admin/categories`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {
        "id": 1,
        "name": "奶粉",
        "value": "milk",
        "sort": 1
      },
      {
        "id": 2,
        "name": "尿裤",
        "value": "diaper",
        "sort": 2
      }
    ]
  }
  ```

### 7.6 添加商品分类
- **接口路径**: `/api/admin/categories`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | name | string | 是 | 分类名称 |
  | value | string | 是 | 分类值 |
  | sort | number | 是 | 排序 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 3,
      "name": "辅食",
      "value": "food",
      "sort": 3
    }
  }
  ```

### 7.7 更新商品分类
- **接口路径**: `/api/admin/categories/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 分类ID |
  | name | string | 否 | 分类名称 |
  | value | string | 否 | 分类值 |
  | sort | number | 否 | 排序 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "name": "奶粉",
      "value": "milk",
      "sort": 1
    }
  }
  ```

### 7.8 删除商品分类
- **接口路径**: `/api/admin/categories/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 分类ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

## 8. 订单管理模块

### 8.1 获取订单列表
- **接口路径**: `/api/admin/orders`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | orderId | string | 否 | 订单ID |
  | memberId | number | 否 | 会员ID |
  | status | string | 否 | 订单状态 |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "orderId": "20240129001",
          "memberId": 1,
          "memberNickname": "张三",
          "totalPrice": 288,
          "totalQuantity": 2,
          "status": "completed",
          "createTime": "2024-01-29 12:00:00",
          "payTime": "2024-01-29 12:30:00",
          "products": [
            { "id": 1, "name": "爱他美白金版奶粉", "price": "199", "quantity": 1, "image": "product1.jpg" }
          ]
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 8.2 获取订单详情
- **接口路径**: `/api/admin/orders/{id}`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 订单ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "orderId": "20240129001",
      "memberId": 1,
      "memberNickname": "张三",
      "memberPhone": "13800138001",
      "totalPrice": 288,
      "totalQuantity": 2,
      "status": "completed",
      "createTime": "2024-01-29 12:00:00",
      "payTime": "2024-01-29 12:30:00",
      "address": {
        "name": "张三",
        "phone": "13800138001",
        "address": "北京市朝阳区"
      },
      "products": [
        {
          "id": 1,
          "name": "爱他美白金版奶粉",
          "price": "199",
          "quantity": 1,
          "image": "product1.jpg",
          "spec": "800g/罐"
        }
      ],
      "payMethod": "微信支付",
      "remark": "尽快发货"
    }
  }
  ```

### 8.3 更新订单状态
- **接口路径**: `/api/admin/orders/{id}/status`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 订单ID |
  | status | string | 是 | 订单状态 |
  | remark | string | 否 | 备注 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "status": "shipped",
      "remark": "已发货"
    }
  }
  ```

### 8.4 获取退款列表
- **接口路径**: `/api/admin/refunds`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | orderId | string | 否 | 订单ID |
  | memberId | number | 否 | 会员ID |
  | status | string | 否 | 退款状态 |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "orderId": "20240129001",
          "memberId": 1,
          "memberNickname": "张三",
          "refundAmount": 199,
          "reason": "商品质量问题",
          "status": "approved",
          "createTime": "2024-01-30 10:00:00",
          "processTime": "2024-01-30 14:00:00"
        }
      ],
      "total": 20,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 8.5 处理退款
- **接口路径**: `/api/admin/refunds/{id}/process`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 退款ID |
  | status | string | 是 | 退款状态 (approved/rejected) |
  | remark | string | 否 | 处理备注 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "处理成功",
    "data": {
      "id": 1,
      "status": "approved",
      "processTime": "2024-01-30 14:00:00",
      "remark": "同意退款"
    }
  }
  ```

## 9. 服务管理模块

### 9.1 获取服务列表
- **接口路径**: `/api/admin/services`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | type | string | 否 | 服务类型 |
  | status | string | 否 | 服务状态 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "name": "婴儿游泳",
          "type": "bath",
          "price": 58,
          "description": "专业婴儿游泳服务",
          "duration": 30,
          "status": "active",
          "createTime": "2024-01-01 10:00:00"
        }
      ],
      "total": 10,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 9.2 添加服务
- **接口路径**: `/api/admin/services`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | name | string | 是 | 服务名称 |
  | type | string | 是 | 服务类型 |
  | price | number | 是 | 服务价格 |
  | description | string | 是 | 服务描述 |
  | duration | number | 是 | 服务时长(分钟) |
  | status | string | 是 | 状态 (active/inactive) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 2,
      "name": "婴儿理发",
      "type": "hair",
      "price": 38,
      "description": "专业婴儿理发服务",
      "duration": 20,
      "status": "active",
      "createTime": "2024-01-02 10:00:00"
    }
  }
  ```

### 9.3 更新服务
- **接口路径**: `/api/admin/services/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 服务ID |
  | name | string | 否 | 服务名称 |
  | type | string | 否 | 服务类型 |
  | price | number | 否 | 服务价格 |
  | description | string | 否 | 服务描述 |
  | duration | number | 否 | 服务时长(分钟) |
  | status | string | 否 | 状态 (active/inactive) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "name": "婴儿游泳",
      "type": "bath",
      "price": 68,
      "description": "专业婴儿游泳服务",
      "duration": 30,
      "status": "active",
      "createTime": "2024-01-01 10:00:00"
    }
  }
  ```

### 9.4 删除服务
- **接口路径**: `/api/admin/services/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 服务ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

### 9.5 获取服务类型
- **接口路径**: `/api/admin/service-types`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {
        "id": 1,
        "name": "游泳",
        "value": "bath",
        "sort": 1
      },
      {
        "id": 2,
        "name": "理发",
        "value": "hair",
        "sort": 2
      }
    ]
  }
  ```

### 9.6 添加服务类型
- **接口路径**: `/api/admin/service-types`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | name | string | 是 | 类型名称 |
  | value | string | 是 | 类型值 |
  | sort | number | 是 | 排序 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 3,
      "name": "摄影",
      "value": "photo",
      "sort": 3
    }
  }
  ```

### 9.7 更新服务类型
- **接口路径**: `/api/admin/service-types/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 类型ID |
  | name | string | 否 | 类型名称 |
  | value | string | 否 | 类型值 |
  | sort | number | 否 | 排序 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "name": "游泳",
      "value": "bath",
      "sort": 1
    }
  }
  ```

### 9.8 删除服务类型
- **接口路径**: `/api/admin/service-types/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 类型ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

### 9.9 获取预约列表
- **接口路径**: `/api/admin/bookings`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | serviceId | number | 否 | 服务ID |
  | memberId | number | 否 | 会员ID |
  | status | string | 否 | 预约状态 |
  | date | string | 否 | 预约日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "serviceId": 1,
          "serviceName": "婴儿游泳",
          "memberId": 1,
          "memberNickname": "张三",
          "memberPhone": "13800138001",
          "babyName": "小明",
          "babyAge": "1岁",
          "date": "2024-02-01",
          "time": "10:00",
          "status": "confirmed",
          "createTime": "2024-01-30 10:00:00"
        }
      ],
      "total": 50,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 9.10 处理预约
- **接口路径**: `/api/admin/bookings/{id}/process`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 预约ID |
  | status | string | 是 | 预约状态 (confirmed/cancelled/completed) |
  | remark | string | 否 | 处理备注 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "处理成功",
    "data": {
      "id": 1,
      "status": "confirmed",
      "remark": "已确认"
    }
  }
  ```

## 10. 系统设置模块

### 10.1 获取基本设置
- **接口路径**: `/api/admin/settings/basic`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "siteName": "爱宝贝儿",
      "siteDescription": "专业母婴用品商城",
      "contactPhone": "400-123-4567",
      "contactEmail": "service@aibaobei.com",
      "address": "北京市朝阳区",
      "logo": "logo.png",
      "favicon": "favicon.ico"
    }
  }
  ```

### 10.2 更新基本设置
- **接口路径**: `/api/admin/settings/basic`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | siteName | string | 否 | 网站名称 |
  | siteDescription | string | 否 | 网站描述 |
  | contactPhone | string | 否 | 联系电话 |
  | contactEmail | string | 否 | 联系邮箱 |
  | address | string | 否 | 地址 |
  | logo | string | 否 | 网站Logo |
  | favicon | string | 否 | 网站图标 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "siteName": "爱宝贝儿",
      "siteDescription": "专业母婴用品商城",
      "contactPhone": "400-123-4567",
      "contactEmail": "service@aibaobei.com",
      "address": "北京市朝阳区",
      "logo": "logo.png",
      "favicon": "favicon.ico"
    }
  }
  ```

### 10.3 获取用户管理设置
- **接口路径**: `/api/admin/settings/user`
- **请求方法**: GET

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "registerEnabled": true,
      "loginAttempts": 5,
      "lockoutDuration": 30,
      "passwordMinLength": 6
    }
  }
  ```

### 10.4 更新用户管理设置
- **接口路径**: `/api/admin/settings/user`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | registerEnabled | boolean | 否 | 是否允许注册 |
  | loginAttempts | number | 否 | 登录尝试次数 |
  | lockoutDuration | number | 否 | 锁定时长(分钟) |
  | passwordMinLength | number | 否 | 密码最小长度 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "registerEnabled": true,
      "loginAttempts": 5,
      "lockoutDuration": 30,
      "passwordMinLength": 6
    }
  }
  ```

### 10.5 获取管理员列表
- **接口路径**: `/api/admin/admins`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "username": "admin",
          "role": "admin",
          "status": "active",
          "createTime": "2024-01-01 10:00:00",
          "lastLoginTime": "2024-01-10 15:30:00"
        }
      ],
      "total": 5,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

### 10.6 添加管理员
- **接口路径**: `/api/admin/admins`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | username | string | 是 | 用户名 |
  | password | string | 是 | 密码 |
  | role | string | 是 | 角色 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 2,
      "username": "editor",
      "role": "editor",
      "status": "active",
      "createTime": "2024-01-10 10:00:00"
    }
  }
  ```

### 10.7 更新管理员
- **接口路径**: `/api/admin/admins/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 管理员ID |
  | password | string | 否 | 密码 |
  | role | string | 否 | 角色 |
  | status | string | 否 | 状态 (active/inactive) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 2,
      "username": "editor",
      "role": "editor",
      "status": "active"
    }
  }
  ```

### 10.8 删除管理员
- **接口路径**: `/api/admin/admins/{id}`
- **请求方法**: DELETE
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 管理员ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

## 11. 操作日志模块

### 11.1 获取操作日志
- **接口路径**: `/api/admin/logs`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码 |
  | pageSize | number | 否 | 每页数量 |
  | adminId | number | 否 | 管理员ID |
  | action | string | 否 | 操作类型 |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "list": [
        {
          "id": 1,
          "adminId": 1,
          "adminName": "admin",
          "action": "login",
          "description": "管理员登录",
          "ip": "192.168.1.100",
          "createTime": "2024-01-10 15:30:00"
        }
      ],
      "total": 100,
      "page": 1,
      "pageSize": 20
    }
  }
  ```

## 12. 通用接口

### 12.1 上传文件
- **接口路径**: `/api/admin/upload`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | file | file | 是 | 文件 |
  | type | string | 否 | 文件类型 (image/file) |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "上传成功",
    "data": {
      "url": "https://example.com/uploads/file.jpg"
    }
  }
  ```

### 12.2 获取统计数据
- **接口路径**: `/api/admin/statistics`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | type | string | 是 | 统计类型 (sales/users/orders) |
  | startDate | string | 否 | 开始日期 |
  | endDate | string | 否 | 结束日期 |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "total": 10000,
      "data": [
        { "date": "2024-01-01", "value": 1000 },
        { "date": "2024-01-02", "value": 1200 }
      ]
    }
  }
  ```

## 13. 错误码说明

| 错误码 | 描述 |
| --- | --- |
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 14. 注意事项

1. 所有需要认证的接口都需要在请求头中携带 `Authorization: Bearer token`
2. 接口返回格式统一为 JSON 格式
3. 分页接口默认返回 20 条数据
4. 时间格式统一为 `YYYY-MM-DD HH:mm:ss`
5. 金额单位为人民币元
6. 所有接口都需要进行权限验证，确保只有具有相应权限的管理员才能访问
7. 接口调用频率限制：每个IP每分钟最多调用60次