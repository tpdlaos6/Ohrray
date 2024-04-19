package com.ohrray.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    //회원정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void changeMember(Member member){
        this.member=member;
    }


    // 장바구니 생성
    public Cart createCart(Member member){
        Cart cart=new Cart();
        cart.changeMember(member);
        return cart;
    }


}