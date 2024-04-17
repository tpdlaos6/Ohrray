package com.ohrray.entity;

import com.ohrray.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MemberRole {
    //pk로 자동 1씩 증가 되는 수 설정.
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ROLE_ID")
    private Long id;

    //권한 - 일반유저, 판매자, 관리자
    @Enumerated(EnumType.STRING)
    private Role role;
}
