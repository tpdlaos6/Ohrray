package com.ohrray.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Options {

    //상품게시물 ok
    @Id @GeneratedValue
    @Column(name = "OPTIONS_ID")
    private Long id;

    //상품게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    //색상
    private String color;
    //사이즈
    private int size;
}
