package com.ohrray.service;

import com.ohrray.domain.CartProductDTO;

import java.util.List;

public interface CartService{

    public Long addCart(CartProductDTO cartProductDTO, String email);

    public List<CartProductDTO> cartList();

    public void updateCartProductCount(Long cartProductId, int productCount);


}
