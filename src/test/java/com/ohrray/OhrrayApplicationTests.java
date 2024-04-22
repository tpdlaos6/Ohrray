package com.ohrray;

import com.ohrray.domain.CartProductDTO;
import com.ohrray.entity.CartProduct;
import com.ohrray.entity.Member;
import com.ohrray.entity.Product;
import com.ohrray.repository.CartProductRepository;
import com.ohrray.repository.LoginRepository;
import com.ohrray.repository.MemberRepository;
import com.ohrray.repository.ProductRepository;
import com.ohrray.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OhrrayApplicationTests {
	@Autowired LoginRepository loginRepository;
	@Autowired ProductRepository productRepository;
	@Autowired CartService cartService;
	@Autowired CartProductRepository cartProductRepository;
    @Autowired MemberRepository memberRepository;

	public Product saveProduct(){
		Product product=new Product();

		product.setProductName("상품이름");
		product.setProductPrice(10000);

		return productRepository.save(product);
	}

	public Member saveMember(){
		Member member=new Member();

		member.setEmail("test@test.com");
		return memberRepository.save(member);
	}

	@Test
	@DisplayName("장바구니 담기 테스트")
	public void addCart(){
		Product product=saveProduct();
		Member member=saveMember();

		CartProductDTO cartProductDTO=new CartProductDTO();
		cartProductDTO.setProductId(product.getId());
		cartProductDTO.setCount(5);

		Long cartProductId= cartService.addCart(cartProductDTO, member.getEmail());
		CartProduct cartProduct=cartProductRepository.findById(cartProductId)
				.orElseThrow(EntityNotFoundException::new);

		assertEquals(product.getId(), cartProduct.getProduct().getId());
		assertEquals(cartProductDTO.getCount(), cartProduct.getProductCount());
	}

}
