package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w1101.entity.CartItem;
import com.w1101.entity.Product;
import com.w1101.entity.ProductPackage;
import com.w1101.mapper.CartItemMapper;
import com.w1101.mapper.ProductMapper;
import com.w1101.mapper.ProductPackageMapper;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPackageMapper packageMapper;

    @GetMapping
    public Map<String, Object> getCartList(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<CartItem> cartItems = cartItemMapper.selectList(wrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        int totalQuantity = 0;

        for (CartItem cartItem : cartItems) {
            Product product = productMapper.selectById(cartItem.getProductId());
            if (product == null) {
                continue;
            }

            Map<String, Object> item = new HashMap<>();
            item.put("id", cartItem.getId());
            item.put("productId", product.getId());
            item.put("productName", product.getName());
            item.put("productImage", product.getImage());

            BigDecimal price = product.getPrice();
            String packageName = null;

            if (cartItem.getPackageId() != null) {
                ProductPackage productPackage = packageMapper.selectById(cartItem.getPackageId());
                if (productPackage != null) {
                    price = productPackage.getPrice();
                    packageName = productPackage.getName();
                    item.put("packageId", productPackage.getId());
                    item.put("packageName", packageName);
                }
            }

            item.put("price", price);
            item.put("quantity", cartItem.getQuantity());
            item.put("stock", product.getStock());

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            item.put("itemTotal", itemTotal);

            totalPrice = totalPrice.add(itemTotal);
            totalQuantity += cartItem.getQuantity();

            list.add(item);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("totalPrice", totalPrice);
        data.put("totalQuantity", totalQuantity);

        return ResponseUtil.success(data);
    }

    @PostMapping("/add")
    public Map<String, Object> addToCart(HttpServletRequest request,
                                          @RequestBody Map<String, Object> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Long productId = Long.valueOf(params.get("productId").toString());
        Product product = productMapper.selectById(productId);
        if (product == null) {
            return ResponseUtil.fail("商品不存在");
        }

        Integer quantity = Integer.valueOf(params.get("quantity").toString());
        Long packageId = params.containsKey("packageId") ?
                Long.valueOf(params.get("packageId").toString()) : null;

        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("product_id", productId);
        if (packageId != null) {
            wrapper.eq("package_id", packageId);
        } else {
            wrapper.and(w -> w.eq("package_id", null).or().isNull("package_id"));
        }

        CartItem existItem = cartItemMapper.selectOne(wrapper);

        if (existItem != null) {
            existItem.setQuantity(existItem.getQuantity() + quantity);
            cartItemMapper.updateById(existItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(Long.valueOf(userId));
            cartItem.setProductId(productId);
            cartItem.setPackageId(packageId);
            cartItem.setQuantity(quantity);
            cartItemMapper.insert(cartItem);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("cartId", existItem != null ? existItem.getId() :
                cartItemMapper.selectOne(wrapper).getId());

        return ResponseUtil.success(data);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateCartItem(HttpServletRequest request,
                                                @PathVariable Long id,
                                                @RequestBody Map<String, Object> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        CartItem cartItem = cartItemMapper.selectById(id);
        if (cartItem == null || !cartItem.getUserId().equals(Long.valueOf(userId))) {
            return ResponseUtil.fail("购物车商品不存在");
        }

        if (params.containsKey("quantity")) {
            Integer quantity = Integer.valueOf(params.get("quantity").toString());
            if (quantity <= 0) {
                cartItemMapper.deleteById(id);
            } else {
                cartItem.setQuantity(quantity);
                cartItemMapper.updateById(cartItem);
            }
        }

        return ResponseUtil.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteCartItem(HttpServletRequest request,
                                                @PathVariable Long id) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        CartItem cartItem = cartItemMapper.selectById(id);
        if (cartItem == null || !cartItem.getUserId().equals(Long.valueOf(userId))) {
            return ResponseUtil.fail("购物车商品不存在");
        }

        cartItemMapper.deleteById(id);

        return ResponseUtil.success("删除成功");
    }

    @DeleteMapping("/clear")
    public Map<String, Object> clearCart(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        cartItemMapper.delete(wrapper);

        return ResponseUtil.success("清空成功");
    }
}
