package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.dto.ProductQueryDto;
import com.w1101.entity.Product;

public interface ProductService {
    
    IPage<Product> getProductList(ProductQueryDto queryDto);
    
    Product getProductById(Integer id);
    
    boolean addProduct(Product product);
    
    boolean updateProduct(Product product);
    
    boolean deleteProduct(Integer id);
    
    boolean updateProductStatus(Integer id, String status);
}
