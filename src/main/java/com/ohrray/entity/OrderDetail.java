package com.ohrray.entity;

import com.ohrray.enums.DetailStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetail {
    //주문 상품 세부정보 pk
    @Id @GeneratedValue
    @Column(name = "ORDER_DETAIL_ID")
    private Long id;

    //주문(order) 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    //상품게시글(product) 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    //상품갯수
    private int productCount;
    //배송 상태
    @Enumerated(EnumType.STRING)
    private DetailStatus detailStatus;

    //등록 시간
    //수정 시간
}
