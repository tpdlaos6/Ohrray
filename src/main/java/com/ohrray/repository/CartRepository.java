package com.ohrray.repository;

import com.ohrray.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 회원의 카트를 찾기 위한 쿼리메서드
    Cart findByMemberEmail(String memberId);

    // 상품이 장바구니에 들어있는지 조회
    Cart findByIdAndProductId(Long cartId, Long productId);


}

