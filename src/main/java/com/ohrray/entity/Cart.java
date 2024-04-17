package com.ohrray.entity;

import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    //회원정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //게시글 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    //상품갯수
    private int productCount;
}