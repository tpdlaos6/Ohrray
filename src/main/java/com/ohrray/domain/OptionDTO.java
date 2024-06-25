package com.ohrray.domain;

import lombok.*;

@Setter @Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
    private String color;
    private Integer size;
}
