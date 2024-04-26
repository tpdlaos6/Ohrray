package com.ohrray.service;

import com.ohrray.domain.CartProductDTO;
import com.ohrray.entity.Cart;
import com.ohrray.entity.CartProduct;
import com.ohrray.entity.Member;
import com.ohrray.entity.Product;
import com.ohrray.repository.CartProductRepository;
import com.ohrray.repository.CartRepository;
import com.ohrray.repository.MemberRepository;
import com.ohrray.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addCart(CartProductDTO cartProductDTO, String email) {

        // 상품 조회. 상품이 없으면 예외처리
        Product product = productRepository.findById(cartProductDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("상품 없음"));

        // 회원 조회. 계정 없으면 예외처리
        Optional<Member> findId = memberRepository.findById(email);
        Member member = findId.orElseThrow(()->new EntityNotFoundException("계정 없음"));

        // 장바구니 조회. 없으면 새로 만들고, cartRepository에 저장
        Cart cart=cartRepository.findByMemberEmail(member.getEmail());
        if(cart==null){
            cart=Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 카트 상품 조회. 카트 안에 동일 상품이 있으면 갯수만 추가. 없으면 카트 상품 생성
        CartProduct cartProduct=cartProductRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        if(cartProduct!=null){
            cartProduct.addCount(cartProductDTO.getCount());
            return cartProduct.getId();
        } else{
            CartProduct cartProduct1=CartProduct.createCartProduct(cart,product,cartProductDTO.getCount());
            cartProductRepository.save(cartProduct1);
            return cartProduct1.getId();
        }
    }

    @Override
    public List<CartProductDTO> cartList() {
        List<CartProductDTO> list = cartProductRepository.findAll()
                .stream()
                .map(cartProduct ->
                        CartProductDTO.builder()
                                .cartProductId(cartProduct.getId())
                                .productName(cartProduct.getProduct().getProductName())
                                .productPrice(cartProduct.getProduct().getProductPrice())
                                .count(cartProduct.getProductCount())
                                .build()
                        )
                .collect(Collectors.toList());
        return list;
    }

    // 장바구니 상품 수량 수정
    @Override
    public void updateCartProductCount(Long cartProductId, int productCount) {
        CartProduct cartProduct=cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new);
        cartProduct.updateCount(productCount);
    }

    @Override
    public void deleteCartProduct(Long cartProductId) {
        cartProductRepository.deleteById(cartProductId);
    }

}
