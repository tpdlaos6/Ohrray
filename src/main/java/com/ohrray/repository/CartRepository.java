package com.ohrray.repository;

import com.ohrray.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 회원의 카트를 찾기 위한 쿼리메서드
    Cart findByMemberId(Long memberId);

}

