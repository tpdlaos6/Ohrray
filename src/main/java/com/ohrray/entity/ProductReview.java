package com.ohrray.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductReview {
    //상품리뷰 pk
    @Id @GeneratedValue
    @Column(name = "PRODUCT_REVIEW_ID")
    private Long id;

    //상품게시글 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //리뷰
    private String review;

    //답변(null가능)
    @Column(nullable = true)
    private String answer;
    
    //등록시간
    //수정시간
}
