package com.ohrray.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDTO {
    private String email;
    private String password;
}
