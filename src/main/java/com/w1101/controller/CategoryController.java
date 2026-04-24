package com.w1101.controller;

import com.w1101.entity.Category;
import com.w1101.service.CategoryService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Map<String, Object> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseUtil.success("获取成功", categories);
    }

    @PostMapping
    public Map<String, Object> addCategory(@RequestBody Category category) {
        boolean success = categoryService.addCategory(category);
        if (success) {
            return ResponseUtil.success("添加成功", category);
        }
        return ResponseUtil.error("添加失败");
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        boolean success = categoryService.updateCategory(category);
        if (success) {
            return ResponseUtil.success("更新成功", category);
        }
        return ResponseUtil.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteCategory(@PathVariable Integer id) {
        boolean success = categoryService.deleteCategory(id);
        if (success) {
            return ResponseUtil.success("删除成功");
        }
        return ResponseUtil.error("删除失败");
    }
}
