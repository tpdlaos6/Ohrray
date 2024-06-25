package com.ohrray.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductImg {
    //상품게시물 ok
    @Id @GeneratedValue
    @Column(name = "PRODUCT_IMG_ID")
    private Long id;

    //메인이미지
    private String mainImg;

    //서브이미지1~3
    private String subImg1;
    private String subImg2;
    private String subImg3;

    //상세정보 이미지
    private String detailImg;

    //경로
    private String path;
}
