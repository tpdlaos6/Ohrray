package com.ohrray.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.ohrray.entity.Member;
import com.ohrray.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long sbno;
    private Member member;
    private Product product;
    private int productCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
