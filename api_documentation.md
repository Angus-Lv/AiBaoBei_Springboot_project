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
      "token": "mock_token_1773393101433",
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

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "登出成功"
  }
  ```

## 2. 商品管理模块

### 2.1 获取商品列表
- **接口路径**: `/api/admin/products`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | page | number | 否 | 页码，默认1 |
  | pageSize | number | 否 | 每页数量，默认20 |
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
          "image": "https://example.com/images/milk1.jpg",
          "price": 199.00,
          "originalPrice": 299.00,
          "stock": 200,
          "sales": 1258,
          "status": "active",
          "isSeckill": true,
          "createTime": "2026-03-13T17:09:32"
        }
      ],
      "total": 16,
      "page": 1,
      "pageSize": 10
    }
  }
  ```

### 2.2 获取商品详情
- **接口路径**: `/api/admin/products/{id}`
- **请求方法**: GET
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 商品ID |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "id": 1,
      "name": "爱他美白金版奶粉",
      "category": "milk",
      "image": "https://example.com/images/milk1.jpg",
      "images": "[\"https://example.com/images/milk1-1.jpg\", \"https://example.com/images/milk1-2.jpg\"]",
      "price": 199.00,
      "originalPrice": 299.00,
      "stock": 200,
      "sales": 1258,
      "status": "active",
      "isSeckill": true,
      "seckillPrice": 159.00,
      "seckillStock": 50,
      "spec": "800g/罐",
      "description": "德国进口，含有DHA和ARA，促进大脑和视力发育",
      "detailImages": "[\"https://example.com/images/milk1-detail1.jpg\", \"https://example.com/images/milk1-detail2.jpg\"]",
      "packages": "[{\"name\": \"单罐\", \"price\": 199.00, \"stock\": 200}, {\"name\": \"三罐套装\", \"price\": 569.00, \"stock\": 50}]",
      "createTime": "2026-03-13T17:09:32",
      "updateTime": "2026-03-13T17:09:32"
    }
  }
  ```

### 2.3 添加商品
- **接口路径**: `/api/admin/products`
- **请求方法**: POST
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | name | string | 是 | 商品名称 |
  | category | string | 是 | 商品分类 |
  | image | string | 是 | 商品图片 |
  | images | string | 是 | 商品图片列表（JSON字符串） |
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
  | detailImages | string | 否 | 详情图片（JSON字符串） |
  | packages | string | 否 | 商品套餐（JSON字符串） |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 17,
      "name": "新商品",
      "category": "milk",
      "image": "https://example.com/images/new.jpg",
      "price": 199.00,
      "originalPrice": 299.00,
      "stock": 200,
      "sales": 0,
      "status": "active",
      "isSeckill": false,
      "createTime": "2026-03-13T17:09:32"
    }
  }
  ```

### 2.4 更新商品
- **接口路径**: `/api/admin/products/{id}`
- **请求方法**: PUT
- **请求参数**:
  | 参数名 | 类型 | 必填 | 描述 |
  | --- | --- | --- | --- |
  | id | number | 是 | 商品ID |
  | name | string | 否 | 商品名称 |
  | category | string | 否 | 商品分类 |
  | image | string | 否 | 商品图片 |
  | images | string | 否 | 商品图片列表（JSON字符串） |
  | price | number | 否 | 商品价格 |
  | originalPrice | number | 否 | 原价 |
  | stock | number | 否 | 库存 |
  | status | string | 否 | 状态 (active/inactive) |
  | isSeckill | boolean | 否 | 是否秒杀 |
  | seckillPrice | number | 否 | 秒杀价格 |
  | seckillStock | number | 否 | 秒杀库存 |
  | spec | string | 否 | 商品规格 |
  | description | string | 否 | 商品描述 |
  | detailImages | string | 否 | 详情图片（JSON字符串） |
  | packages | string | 否 | 商品套餐（JSON字符串） |

- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 1,
      "name": "爱他美白金版奶粉（更新）",
      "category": "milk",
      "image": "https://example.com/images/milk1.jpg",
      "price": 189.00,
      "originalPrice": 299.00,
      "stock": 180,
      "status": "active",
      "isSeckill": true,
      "seckillPrice": 159.00,
      "seckillStock": 50
    }
  }
  ```

### 2.5 删除商品
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

## 3. 分类管理模块

### 3.1 获取分类列表
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
        "name": "全部",
        "value": "all",
        "sort": 0,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 2,
        "name": "奶粉",
        "value": "milk",
        "sort": 1,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 3,
        "name": "尿裤",
        "value": "diaper",
        "sort": 2,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 4,
        "name": "服装",
        "value": "clothes",
        "sort": 3,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 5,
        "name": "玩具",
        "value": "toys",
        "sort": 4,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 6,
        "name": "喂养",
        "value": "feeding",
        "sort": 5,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 7,
        "name": "护理",
        "value": "care",
        "sort": 6,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 8,
        "name": "营养",
        "value": "nutrition",
        "sort": 7,
        "createTime": "2026-03-13T17:09:32"
      },
      {
        "id": 9,
        "name": "出行",
        "value": "travel",
        "sort": 8,
        "createTime": "2026-03-13T17:09:32"
      }
    ]
  }
  ```

### 3.2 添加分类
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
      "id": 10,
      "name": "新分类",
      "value": "new",
      "sort": 9,
      "createTime": "2026-03-13T17:09:32"
    }
  }
  ```

### 3.3 更新分类
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
      "name": "全部商品",
      "value": "all",
      "sort": 0,
      "createTime": "2026-03-13T17:09:32"
    }
  }
  ```

### 3.4 删除分类
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

## 4. 系统信息

### 4.1 服务地址
- **基础URL**: `http://localhost:8080`
- **API前缀**: `/api/admin`

### 4.2 认证信息
- **登录账号**: admin
- **登录密码**: admin123
- **认证方式**: 预留JWT但使用模拟token
- **请求头**: 不需要Authorization头（模拟token，方便调试）

### 4.3 数据库信息
- **数据库名称**: aibaobei
- **字符集**: utf8mb4_unicode_ci
- **表数量**: 20

### 4.4 测试数据
- **分类数据**: 9个分类（全部、奶粉、尿裤、服装、玩具、喂养、护理、营养、出行）
- **商品数据**: 16个商品，覆盖所有分类

## 5. 错误码说明

| 错误码 | 描述 |
| --- | --- |
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 6. 注意事项

1. 所有接口返回格式统一为JSON格式
2. 分页接口默认返回20条数据
3. 时间格式统一为`YYYY-MM-DD HH:mm:ss`
4. 金额单位为人民币元
5. 接口调用频率限制：每个IP每分钟最多调用60次
6. 图片路径为示例路径，实际项目中需要替换为真实路径
7. 商品的images、detailImages、packages字段为JSON字符串格式
