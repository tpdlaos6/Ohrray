package com.ohrray.service;

import com.ohrray.domain.CartProductDTO;
import com.ohrray.entity.Product;
import com.ohrray.repository.CartProductRepository;
import com.ohrray.repository.CartRepository;
import com.ohrray.repository.MemberRepository;
import com.ohrray.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addCart(CartProductDTO cartProductDTO) {
        Product product = productRepository.findById(cartProductDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다."));

        return null;
    }



//    @Override
//    public List<CartDTO> getList() {
//
//        return null;
//    }
}
