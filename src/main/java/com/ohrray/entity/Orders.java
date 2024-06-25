package com.ohrray.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Orders {
    //주문정보 pk
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    //주문자 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //주문 갯수
    private int count;
    //주문주소
    private String orderAddress;
    //수령인
    private String recipient;
    //송장번호
    private String trackingNumber;

    //등록 시간(주문 날짜)
    //수정 시간
}
