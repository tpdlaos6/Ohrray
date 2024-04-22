package com.ohrray.service;

import com.ohrray.domain.CartProductDTO;

public interface CartService{

    public Long addCart(CartProductDTO cartProductDTO, String email);

//    public List<CartDTO> getList();



}
