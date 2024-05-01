package com.ohrray.service;

import com.ohrray.domain.CartDTO;

import java.util.List;
import java.util.Map;

public interface CartService{

    public Long addCart(CartDTO cartDTO, String email);

    public List<CartDTO> cartList();

    public void updateCartProductCount(Long cartProductId, int productCount);

    public void deleteCartProduct(Long cartProductId);

    // 상품별 합계
    public Map<Long, Integer> productTotal(List<CartDTO> cartLists);

    // 전체 총계
    public int subTotal(List<CartDTO> cartLists);

    // 장바구니 상품 색상, 사이즈 변경
    public void changeOptions(Long cartId, int size, String color);


}
