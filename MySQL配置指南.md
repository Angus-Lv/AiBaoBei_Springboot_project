# MySQL配置指南

## 1. 安装MySQL

### Windows系统
1. 下载MySQL：https://dev.mysql.com/downloads/mysql/
2. 选择Windows版本，下载MSI安装包
3. 运行安装程序，选择"Developer Default"
4. 设置root用户密码（请记住这个密码）
5. 完成安装后，MySQL会自动启动

### 验证安装
```powershell
mysql --version
```

## 2. 启动MySQL服务

```powershell
# 启动MySQL服务
net start mysql

# 或者
net start mysql80

# 停止MySQL服务
net stop mysql

# 重启MySQL服务
net stop mysql
net start mysql
```

## 3. 登录MySQL

```powershell
mysql -u root -p
```
输入您在安装时设置的root密码。

## 4. 创建数据库和表

### 方法一：在MySQL命令行中执行
```sql
source E:\BiYeSheJi\spring_xiaochengxu_houduan\database.sql
```

### 方法二：使用PowerShell执行
```powershell
Get-Content "E:\BiYeSheJi\spring_xiaochengxu_houduan\database.sql" | mysql -u root -p
```

### 方法三：使用MySQL Workbench
1. 打开MySQL Workbench
2. 连接到本地MySQL服务器
3. 打开SQL脚本文件：`database.sql`
4. 点击执行按钮

## 5. 验证数据库创建

```sql
-- 查看所有数据库
SHOW DATABASES;

-- 使用数据库
USE aibaobei;

-- 查看所有表
SHOW TABLES;

-- 查看管理员表
SELECT * FROM admin;
```

## 6. 更新项目配置

修改 `src\main\resources\application.yml` 文件：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/aibaobei?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 你的MySQL密码  # 改成你的实际密码
    driver-class-name: com.mysql.cj.jdbc.Driver
```

## 7. 运行项目测试

### 在IDEA中运行
1. 打开 `Application.java` 文件
2. 点击main方法旁边的绿色运行按钮
3. 查看控制台输出，应该显示：
   ```
   ====================================
   数据库连接成功！
   当前数据库: aibaobei
   数据库表数量: 20
   ====================================
   ```

### 使用Maven运行
```powershell
mvn spring-boot:run
```

## 8. 常见问题解决

### 问题1：MySQL服务无法启动
```powershell
# 检查MySQL服务状态
Get-Service mysql*

# 如果服务不存在，尝试重新安装
```

### 问题2：连接被拒绝
```sql
-- 在MySQL中执行
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '你的密码';
FLUSH PRIVILEGES;
```

### 问题3：字符编码问题
```sql
-- 查看数据库编码
SHOW VARIABLES LIKE 'character%';

-- 确保数据库使用utf8mb4
ALTER DATABASE aibaobei CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 问题4：端口被占用
```powershell
# 检查3306端口是否被占用
netstat -ano | findstr :3306

# 如果被占用，可以修改MySQL端口或停止占用端口的服务
```

## 9. 数据库管理工具推荐

- **MySQL Workbench**：官方工具，功能全面
- **Navicat for MySQL**：商业软件，界面友好
- **DBeaver**：免费开源，支持多种数据库
- **phpMyAdmin**：Web界面管理工具

## 10. 默认账号信息

- **数据库用户名**：root
- **数据库密码**：您设置的密码
- **数据库名**：aibaobei
- **管理员账号**：admin
- **管理员密码**：admin123（需要加密存储）

## 11. 下一步

1. 确保MySQL服务正在运行
2. 执行 `database.sql` 创建数据库和表
3. 更新 `application.yml` 中的数据库密码
4. 运行项目测试数据库连接
5. 开始开发业务功能

## 12. 注意事项

1. 生产环境中请修改默认管理员密码
2. 定期备份数据库
3. 使用强密码保护数据库
4. 限制数据库远程访问
5. 定期更新MySQL版本以修复安全漏洞
