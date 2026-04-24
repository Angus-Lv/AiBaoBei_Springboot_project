package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.Category;
import com.w1101.mapper.CategoryMapper;
import com.w1101.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> getCategories() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        return this.list(wrapper);
    }

    /**
     * 添加分类方法
     * @param category 要添加的分类对象
     * @return 返回添加是否成功
     */
    @Override
    public boolean addCategory(Category category) {
        //实现了排序值的自动调整，当用户指定排序值时，会将大于等于该排序值的分类排序值加1；当用户未指定排序值时，默认添加到最后。
        // 如果用户指定了排序值，需要调整其他分类的排序值
        if (category.getSort() != null) {
            // 将大于等于指定排序值的分类排序值加1，为新分类腾出位置
            LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
            wrapper.ge(Category::getSort, category.getSort());
            List<Category> categories = this.list(wrapper);
            for (Category c : categories) {
                c.setSort(c.getSort() + 1);
                this.updateById(c);
            }
        } else {
            // 如果用户没有指定排序值，默认添加到最后
            LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByDesc(Category::getSort);
            List<Category> categories = this.list(wrapper);
            if (categories != null && !categories.isEmpty()) {
                // 获取当前排序值最大的分类，新分类排序值设为最大值+1
                Category lastCategory = categories.get(0);
                category.setSort(lastCategory.getSort() + 1);
            } else {
                // 如果没有分类，默认排序值为1
                category.setSort(1);
            }
        }
        return this.save(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        // 获取原始分类信息
        Category oldCategory = this.getById(category.getId());
        if (oldCategory == null) {
            return false;
        }
        
        // 如果排序值发生了变化，需要调整其他分类的排序值
        // 实现了排序值的自动调整，当用户指定排序值时，会将大于等于该排序值的分类排序值加1；当用户未指定排序值时，默认添加到最后。
        if (category.getSort() != null && !category.getSort().equals(oldCategory.getSort())) {
            if (category.getSort() > oldCategory.getSort()) {
                // 排序值增大，将中间的分类排序值减1
                LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
                wrapper.gt(Category::getSort, oldCategory.getSort()).le(Category::getSort, category.getSort());
                List<Category> categories = this.list(wrapper);
                for (Category c : categories) {
                    c.setSort(c.getSort() - 1);
                    this.updateById(c);
                }
            } else {
                // 排序值减小，将中间的分类排序值加1
                LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
                wrapper.ge(Category::getSort, category.getSort()).lt(Category::getSort, oldCategory.getSort());
                List<Category> categories = this.list(wrapper);
                for (Category c : categories) {
                    c.setSort(c.getSort() + 1);
                    this.updateById(c);
                }
            }
        }
        return this.updateById(category);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        // 获取要删除的分类信息
        Category category = this.getById(id);
        if (category == null) {
            return false;
        }
        
        // 记录要删除的分类的排序值
        Integer sort = category.getSort();
        
        // 删除分类
        boolean success = this.removeById(id);
        
        if (success) {
            // 将大于删除分类排序值的分类排序值减1
            LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
            wrapper.gt(Category::getSort, sort);
            List<Category> categories = this.list(wrapper);
            for (Category c : categories) {
                c.setSort(c.getSort() - 1);
                this.updateById(c);
            }
        }
        
        return success;
    }
}
