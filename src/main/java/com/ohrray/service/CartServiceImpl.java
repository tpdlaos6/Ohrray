package com.ohrray.service;

import com.ohrray.repository.CartProductRepository;
import com.ohrray.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

//    @Override
//    public List<CartDTO> getList() {
//
//        return null;
//    }
}
