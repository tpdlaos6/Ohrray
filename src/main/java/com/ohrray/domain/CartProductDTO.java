package com.ohrray.domain;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {

    private Long productId;
    private int count;
    private String color;
    private int size;

}
