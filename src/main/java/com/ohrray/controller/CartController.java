package com.ohrray.controller;

import com.ohrray.domain.CartDTO;
import com.ohrray.repository.CartRepository;
import com.ohrray.repository.OptionsRepository;
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
    private final OptionsRepository optionsRepository;

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

    // 상품 사이즈 정보 요청 처리
    @GetMapping("/api/products/{productId}/sizes")
    public ResponseEntity<List<Integer>> getProductSizes(@PathVariable("productId") Long productId) {
        List<Integer> sizes = optionsRepository.findSizesByProductId(productId);
        return ResponseEntity.ok(sizes);
    }

    // 상품 색상 정보 요청 처리
    @GetMapping("/api/products/{productId}/colors")
    public ResponseEntity<List<String>> getProductColors(@PathVariable("productId") Long productId) {
        List<String> colors = optionsRepository.findColorsByProductId(productId);
        return ResponseEntity.ok(colors);
    }

    // 장바구니 옵션 변경 (수정 중) (수정 중) (수정 중) (수정 중) (수정 중) (수정 중) (수정 중) (수정 중)
//    @GetMapping("/{cartId}/{productId}/options")
//    public ResponseEntity<List<Options>> getProductOptions(@PathVariable Long cartId, @PathVariable Long productId) {
//        List<Options> options = cartService.findOptionsByCartIdAndProductId(cartId, productId);
//        return ResponseEntity.ok().body(options);
//    }

//    @PostMapping("/{cartId}/changeCartOptions")
//    public ResponseEntity<?> changeCartOptions(@PathVariable Long cartId, @RequestParam int size, @RequestParam String color) {
//        try {
//            cartService.changeOptions(cartId, size, color);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
