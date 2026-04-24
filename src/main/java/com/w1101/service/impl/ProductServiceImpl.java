package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.dto.ProductQueryDto;
import com.w1101.entity.Product;
import com.w1101.mapper.ProductMapper;
import com.w1101.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 * 服务实现类：负责产品相关的业务逻辑。
 *
 * 说明（契约）：
 * - 输入：来自 Controller 的请求参数或实体（通常是 Product 或 ProductQueryDto）。
 * - 输出：通常为 MyBatis-Plus 封装的结果（IPage<Product>、Product 或 boolean 成功标志）。
 * - 错误模式：对于单表的简单 CRUD 操作，方法内部不捕获异常（交由上层统一异常处理/事务处理）；复杂操作应在调用处使用 @Transactional。
 *
 * 设计要点：
 * - 继承自 ServiceImpl<ProductMapper, Product>，可直接使用 this.save / this.getById / this.page / this.updateById 等方法。
 * - 查询使用 LambdaQueryWrapper 来构建类型安全的查询条件，避免拼 SQL 字符串错误并支持动态条件。
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    
    /**
     * 根据查询 DTO 返回分页的商品列表。
     *
     * Contract:
     * - Input: ProductQueryDto 包含 page, pageSize, category, keyword, status, isHot, isSeckill 等可选筛选项。
     * - Output: 包含分页信息的 IPage<Product>，records 为查询到的记录，total 为满足条件的总数。
     * - Errors: 如果数据库错误或连接异常，会抛出运行时异常（由上层处理）；参数范围（如 page<=0）应由调用者或 DTO 默认值保证。
     *
     * 典型边界情况与处理：
     * - page/pageSize 为 null：在 DTO 中已设置默认值，保证 Page 构造器有效。
     * - 所有筛选条件均为空：返回全部商品的分页结果（按照 createTime 倒序）。
     * - keyword 为特殊字符或 SQL 片段：LambdaQueryWrapper.like 会以参数化方式传递，避免 SQL 注入。
     */
    @Override
    public IPage<Product> getProductList(ProductQueryDto queryDto) {
        // 构造分页对象（MyBatis-Plus 的 Page）
        Page<Product> page = new Page<>(queryDto.getPage(), queryDto.getPageSize());
        
        // 使用 LambdaQueryWrapper 可以直接使用实体的 getter 引用，避免手写列名引起错误
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        
        // 分类筛选（category 不为空时生效）
        if (StringUtils.isNotBlank(queryDto.getCategory())) {
            wrapper.eq(Product::getCategory, queryDto.getCategory());
        }
        
        // 关键词搜索（对商品名称做模糊匹配）
        if (StringUtils.isNotBlank(queryDto.getKeyword())) {
            // 使用 like 会生成类似 WHERE name LIKE %keyword% 的语句
            wrapper.like(Product::getName, queryDto.getKeyword());
        }
        
        // 状态筛选（例如 active/inactive 等）
        if (StringUtils.isNotBlank(queryDto.getStatus())) {
            wrapper.eq(Product::getStatus, queryDto.getStatus());
        }
        
        // 爆款筛选（isHot 为 Boolean，可能为 true/false/null）
        if (queryDto.getIsHot() != null) {
            wrapper.eq(Product::getIsHot, queryDto.getIsHot());
        }
        
        // 秒杀筛选（isSeckill）
        if (queryDto.getIsSeckill() != null) {
            wrapper.eq(Product::getIsSeckill, queryDto.getIsSeckill());
        }
        
        // 按创建时间倒序排序，确保最新创建的商品排在前面
        wrapper.orderByDesc(Product::getCreateTime);
        
        // 返回分页查询结果（page 中包含 records 与 total）
        return this.page(page, wrapper);
    }
    
    /**
     * 根据 id 查询单个商品。
     *
     * Input: Integer id（可能为 null，如果为 null 应在调用处处理或会抛出异常）
     * Output: 找到返回 Product 实例，未找到返回 null。
     */
    @Override
    public Product getProductById(Integer id) {
        return this.getById(id);
    }
    
    /**
     * 添加新商品。
     *
     * Input: Product 实体（必要字段应由 Controller/DTO 校验）。
     * Output: boolean 表示保存成功与否；保存成功后，传入的 product 会被填充主键 id（如果数据库返回了自增 id）。
     *
     * 注意事项：如果 product 中包含 null 值，且数据库列不允许为空，则会抛出异常；建议在 Controller 层进行校验。
     */
    @Override
    public boolean addProduct(Product product) {
        return this.save(product);
    }
    
    /**
     * 更新商品（整个实体按 id 更新）。
     *
     * Input: 包含 id 的 Product 实体。
     * Output: boolean 表示更新成功与否。
     *
     * 警告：调用 this.updateById(product) 时，如果 product 中有字段为 null，则默认会把该列更新为 null（覆盖原值），这可能不是期望行为。
     * 如果希望只更新非空字段，可以在 Controller 中使用专用 DTO 或在这里使用 UpdateWrapper/lambdaUpdate 来仅设置要更新的字段。
     */
    @Override
    public boolean updateProduct(Product product) {
        return this.updateById(product);
    }
    
    /**
     * 根据 id 删除商品。
     *
     * Input: 商品 id
     * Output: boolean 表示删除是否成功（不存在的 id 也可能返回 false）。
     */
    @Override
    public boolean deleteProduct(Integer id) {
        return this.removeById(id);
    }

    /**
     * 更新商品状态（只修改 status 字段）。
     *
     * 当前实现：构造一个只包含 id 和 status 的 Product 对象并调用 updateById。
     * 这是一个常见的做法，但请注意：
     * - 如果 MyBatis-Plus 的全局配置或实体映射策略会将 null 字段写入数据库，上述构造不会把其它字段设为 null（因为未赋值的引用字段默认为 null），但 updateById 的行为依赖于具体版本/配置；因此在某些配置下，未赋值字段可能被覆盖为 null。为避免此风险，可以改为使用基于 wrapper 的更新：
     *   this.lambdaUpdate().set(Product::getStatus, status).eq(Product::getId, id).update();
     * - 如果需要返回更新后的完整实体，应在更新后再次调用 this.getById(id)。
     *
     * Input: id、status
     * Output: boolean 表示更新是否成功
     */
    @Override
    public boolean updateProductStatus(Integer id, String status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        return this.updateById(product);
    }

    @Override
    public boolean updateProductHotStatus(Integer id, Boolean isHot) {
        Product product = new Product();
        product.setId(id);
        product.setIsHot(isHot);
        return this.updateById(product);
    }
}
