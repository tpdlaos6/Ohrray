package com.ohrray.domain;

import lombok.*;

@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long productId;
    private int count;
    private String color;
    private int size;

    private Long cartProductId;
    private String productName;
    private int productPrice;
    private String mainImg;

    private OptionDTO option;

    public CartDTO(Long cartProductId, String productName, int productPrice, int count, String mainImg, OptionDTO option){
        this.cartProductId=cartProductId;
        this.productName=productName;
        this.productPrice=productPrice;
        this.count=count;
        this.mainImg=mainImg;
        this.option=option;
    }
}
