package com.ohrray.service;

import com.ohrray.domain.CartDTO;

import java.util.List;

public interface CartService{

    public Long addCart(CartDTO cartDTO, String email);

    public List<CartDTO> cartList();

    public void updateCartProductCount(Long cartProductId, int productCount);

    public void deleteCartProduct(Long cartProductId);

}
