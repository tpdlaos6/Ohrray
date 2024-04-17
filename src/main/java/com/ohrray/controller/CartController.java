package com.ohrray.controller;
jpa auditing
import com.ohrray.entity.Cart;
import com.ohrray.service.CartService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Getter("/cart")
    Cart cart=new Cart();



}
