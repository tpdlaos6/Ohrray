package com.ohrray.entity;

import com.ohrray.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @Setter
    private String email;
    //비밀번호

    private String password;
    //닉네임

    private String nickName;
    //주소

    private String address;

    //회원 권한 -> 일반유저, 판매자, 관리자
    private Role role;

    //상속으로 가입시간 받기.
    //변경 시간 받기
}
