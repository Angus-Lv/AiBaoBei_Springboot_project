package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.dto.ProductQueryDto;
import com.w1101.entity.Product;
import com.w1101.service.ProductService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 产品控制器，用于处理产品相关的 API 接口
 * 提供产品管理的 CRUD（增删改查）操作
 */
@RestController
@RequestMapping("/api/admin/goods")
public class ProductController {

    /**
     * ProductService 实例，用于处理业务逻辑操作
     * 通过 Spring 的依赖注入自动装配
     */
    @Autowired
    private ProductService productService;

    /**
     * 根据查询参数获取产品分页列表
     * @param queryDto 包含过滤和分页查询参数的数据传输对象 (DTO)
     * @return 包含产品列表、总数和分页信息的响应
     */
    @GetMapping("/list")
    public Map<String, Object> getProductList(ProductQueryDto queryDto) {
        IPage<Product> page = productService.getProductList(queryDto);

        Map<String, Object> data = new HashMap<>();
        data.put("list", page.getRecords());
        data.put("total", page.getTotal());

        return ResponseUtil.success("success", data);
    }

    /**
     * 根据 ID 获取产品详情
     * @param id 产品的唯一标识符
     * @return 包含产品数据的响应，如果未找到则返回错误信息
     */
    @GetMapping("/{id}")
    public Map<String, Object> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseUtil.notFound();
        }
        return ResponseUtil.success("获取成功", product);
    }

    /**
     * 添加新产品
     * @param product 待添加的产品实体
     * @return 包含已添加产品的响应或错误信息
     */
    @PostMapping
    public Map<String, Object> addProduct(@RequestBody Product product) {
        boolean success = productService.addProduct(product);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", product.getId());
            return ResponseUtil.success("success", data);
        }
        return ResponseUtil.error("添加失败");
    }

    /**
     * 更新现有产品
     * @param id 待更新产品的唯一标识符
     * @param product 更新后的产品数据
     * @return 包含更新后产品的响应或错误信息
     */
    @PutMapping("/{id}")
    public Map<String, Object> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        boolean success = productService.updateProduct(product);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }

    /**
     * 根据 ID 删除产品
     * @param id 待删除产品的唯一标识符
     * @return 包含成功消息或错误的响应
     */
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteProduct(@PathVariable Integer id) {
        boolean success = productService.deleteProduct(id);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("删除失败");
    }

    /**
     * 更新产品状态
     * @param id 产品的唯一标识符
     * @param statusData 包含状态的请求体
     * @return 包含成功消息或错误的响应
     */
    @PutMapping("/{id}/status")
    public Map<String, Object> updateProductStatus(@PathVariable Integer id, @RequestBody Map<String, String> statusData) {
        String status = statusData.get("status");
        if (status == null) {
            return ResponseUtil.error("状态参数不能为空");
        }

        boolean success = productService.updateProductStatus(id, status);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }
}
