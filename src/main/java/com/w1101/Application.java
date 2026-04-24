package com.w1101;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.w1101.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
class DatabaseConnectionTest implements CommandLineRunner {
    
    private final JdbcTemplate jdbcTemplate;
    
    public DatabaseConnectionTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void run(String... args) throws Exception {
        try {
            String databaseName = jdbcTemplate.queryForObject(
                "SELECT DATABASE()", String.class);
            System.out.println("====================================");
            System.out.println("数据库连接成功！");
            System.out.println("当前数据库: " + databaseName);
            
            Integer tableCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'aibaobei'", 
                Integer.class);
            System.out.println("数据库表数量: " + tableCount);
            System.out.println("====================================");
        } catch (Exception e) {
            System.err.println("数据库连接失败: " + e.getMessage());
        }
    }
}
