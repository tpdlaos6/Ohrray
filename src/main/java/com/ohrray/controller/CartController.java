package com.ohrray.controller;

import com.ohrray.domain.CartDTO;
import com.ohrray.repository.CartRepository;
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
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;



    // 장바구니에 상품 담기
    @PostMapping(value = "/add")
    public @ResponseBody ResponseEntity<?> addCartProduct(@RequestBody CartDTO cartDTO, BindingResult bindingResult, Principal principal){

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
            cartItemId = cartService.addCart(cartDTO, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }


    // 장바구니 상품목록 조회
    @GetMapping("/cart")
    public String cartList(Model model) {

        List<CartDTO> cartLists=cartService.cartList();


//        // 테스트용 더미데이터
//        cartLists.add(new CartProductDTO(1L, "테스트1", 1000, 5, "이미지1"));
//        cartLists.add(new CartProductDTO(2L, "테스트2", 3000, 10, "이미지2"));
//        cartLists.add(new CartProductDTO(3L, "테스트3", 3000, 30, "이미지3"));

        // 상품별 총계
        Map<Long, Integer> productTotal=cartService.productTotal(cartLists);
        // 전체 총계
        int subTotal=cartService.subTotal(cartLists);

        model.addAttribute("cartLists", cartLists);
        model.addAttribute("productTotal", productTotal);
        model.addAttribute("subTotal", subTotal);

        return "cart/cart";
    }


    // 장바구니 수량 변경
    @PatchMapping(value = "/cartProduct/{cartProductId}")
    @ResponseBody
    public ResponseEntity<?> updateCartProduct(@PathVariable("cartProductId") Long cartProductId, int productCount){

        if(productCount <= 0){
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else{
            cartService.updateCartProductCount(cartProductId, productCount);
        }
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);
    }

    // 장바구니 상품 삭제
    @DeleteMapping(value = "/cartProduct/{cartProductId}")
    @ResponseBody
    public ResponseEntity<?> deleteCartProduct(@PathVariable ("cartProductId") Long cartProductId){
        cartService.deleteCartProduct(cartProductId);
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);
    }
}
