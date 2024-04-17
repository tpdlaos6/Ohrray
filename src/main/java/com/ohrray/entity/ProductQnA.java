package com.ohrray.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductQnA {
    //상품문의 pk
    @Id @GeneratedValue
    @Column(name = "PRODUCT_QNA_ID")
    private Long id;

    //상품게시글 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //질문
    private String question;

    //답변(null가능)
    @Column(nullable = true)
    private String answer;

    //등록시간
    //수정시간
}
