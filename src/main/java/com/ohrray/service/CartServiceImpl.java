package com.ohrray.service;

import com.ohrray.domain.CartDTO;
import com.ohrray.domain.OptionDTO;
import com.ohrray.entity.Cart;
import com.ohrray.entity.Member;
import com.ohrray.entity.Options;
import com.ohrray.entity.Product;
import com.ohrray.repository.CartRepository;
import com.ohrray.repository.MemberRepository;
import com.ohrray.repository.OptionsRepository;
import com.ohrray.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OptionsRepository optionsRepository;

    @Transactional
    @Override
    public Long addCart(CartDTO cartDTO, String email) {

        // 상품 조회. 상품이 없으면 예외처리
        Product product = productRepository.findById(cartDTO.getProductId())
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
        Cart cartProduct=cartRepository.findByIdAndProductId(cart.getId(), product.getId());
        if(cartProduct!=null){
            cartProduct.addCount(cartDTO.getCount());
            return cartProduct.getId();
        } else{
            Cart cartProduct1=Cart.createCartProduct(cart,product, cartDTO.getCount());
            cartRepository.save(cartProduct1);
            return cartProduct1.getId();
        }
    }


    @Override
    public List<CartDTO> cartList() {
        List<CartDTO> list = cartRepository.findAll()
                .stream()
                .map(cart ->{

                    List<Options> optionsList=optionsRepository.findByProductId(cart.getProduct().getId());

                    List<OptionDTO> optionDTOs = optionsList.stream()
                            .map(option -> new OptionDTO(option.getColor(), option.getSize()))
                            .collect(Collectors.toList());

                    return CartDTO.builder()
                            .cartProductId(cart.getProduct().getId())
                            .productName(cart.getProduct().getProductName())
                            .productPrice(cart.getProduct().getProductPrice())
                            .count(cart.getProductCount())
                            .option(optionDTOs)
                            .build();
                })
                .collect(Collectors.toList());
        return list;
    }


    // 장바구니 상품 수량 수정
    @Transactional
    @Override
    public void updateCartProductCount(Long cartProductId, int productCount) {
        Cart cart=cartRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new);
        cart.updateCount(productCount);
    }

    // 장바구니 상품 삭제
    @Transactional
    @Override
    public void deleteCartProduct(Long cartProductId) {
        cartRepository.deleteById(cartProductId);
    }

    // 상품별 합계
    @Transactional
    @Override
    public Map<Long, Integer> productTotal(List<CartDTO> cartLists) {
        Map<Long, Integer> productTotal = new HashMap<>();
        for(CartDTO cartDTO : cartLists) {
            int total = cartDTO.getProductPrice() * cartDTO.getCount();
            productTotal.put(cartDTO.getCartProductId(), total);
        }
        return productTotal;
    }

    // 전체 총계
    @Transactional
    @Override
    public int subTotal(List<CartDTO> cartLists) {
        int subTotal=0;
        for(CartDTO cartDTO : cartLists){
            subTotal+= cartDTO.getCount()* cartDTO.getProductPrice();
        }
        return subTotal;
    }


//    // 장바구니 상품 옵션 변경 (수정 중) (수정 중) (수정 중) (수정 중) (수정 중) (수정 중) (수정 중)
//    @Override
//    @Transactional
//    public List<Options> findOptionsByCartIdAndProductId(Long cartId, Long productId) {
//        // 우선, 장바구니 ID를 통해서 장바구니 엔티티 조회
//        Cart cart = cartRepository.findById(cartId).orElseThrow(
//                () -> new NoSuchElementException("해당 ID의 장바구니가 없음"));
//
//        // 장바구니와 연관된 상품이 입력된 상품 ID와 일치하는 경우, 해당 상품의 옵션들을 조회
//        if (cart.getProduct().getId().equals(productId)) {
//            return optionsRepository.findByProductId(productId);
//        } else {
//            throw new IllegalStateException("상품이 장바구니에 없음");
//        }
//    }
}
