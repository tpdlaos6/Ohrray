package com.ohrray.repository;

import com.ohrray.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}
