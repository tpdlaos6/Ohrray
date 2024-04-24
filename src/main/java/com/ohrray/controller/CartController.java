package com.ohrray.controller;

import com.ohrray.domain.CartProductDTO;
import com.ohrray.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;


    // 장바구니에 상품 담기
    @PostMapping(value = "/add")
    public @ResponseBody ResponseEntity<?> addCartProduct(@RequestBody CartProductDTO cartProductDTO, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartProductDTO, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }


    // 장바구니 상품목록 조회
    @GetMapping("/list")
    public String cartList(Model model) {

        List<CartProductDTO> cartLists=new ArrayList<>();

        // 테스트용 더미데이터
        cartLists.add(new CartProductDTO(1L, "테스트1", 1000, 5, "이미지1"));
        cartLists.add(new CartProductDTO(2L, "테스트2", 3000, 10, "이미지2"));
        cartLists.add(new CartProductDTO(3L, "테스트3", 3000, 30, "이미지3"));

        // 상품별 총계
        int productTotal=0;
        for(CartProductDTO cartProductDTO : cartLists){
            productTotal=cartProductDTO.getProductPrice()*cartProductDTO.getCount();
        }

        // 전체 총계
        int subTotal=0;
        for(CartProductDTO cartProductDTO : cartLists){
            subTotal+= cartProductDTO.getCount()*cartProductDTO.getProductPrice();
        }


        model.addAttribute("cartList", cartService.cartList());
        model.addAttribute("cartLists", cartLists);
        model.addAttribute("productTotal", productTotal);
        model.addAttribute("subTotal", subTotal);

        return "cart/list";
    }


    // 장바구니 수량 변경
    @PatchMapping(value = "/{cartProductId}")
    @ResponseBody
    public ResponseEntity<?> updateCartProduct(@PathVariable("cartProductId") Long cartProductId, int productCount){

        if(productCount <= 0){
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else{
            cartService.updateCartProductCount(cartProductId, productCount);
        }
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String deleteCartProduct(CartProductDTO cartProductDTO){
        cartService.deleteCartProduct(cartProductDTO);
        return "redirect: cart/list";
    }


}
