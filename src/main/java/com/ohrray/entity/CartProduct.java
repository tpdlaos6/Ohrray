package com.ohrray.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "CARTPRODUCT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="CART_ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    // 장바구니에 담을 갯수
    private int productCount;

    public void changeCart(Cart cart){
        this.cart=cart;
    }

    public void changeProduct(Product product){
        this.product=product;
    }

    public void changeProductCount(int productCount){
        this.productCount=productCount;
    }

    // 기존 상품을 추가로 담을 때
    public void addCount(int count){
        this.productCount += count;
    }

}
