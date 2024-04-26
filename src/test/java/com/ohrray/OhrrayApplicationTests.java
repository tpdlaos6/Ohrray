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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OhrrayApplicationTests {
	@Autowired LoginRepository loginRepository;
	@Autowired ProductRepository productRepository;
	@Autowired CartService cartService;
	@Autowired CartProductRepository cartProductRepository;
    @Autowired MemberRepository memberRepository;

	@Autowired private MockMvc mockMvc;

	public Product saveProduct(){
		Product product=new Product();

		product.setProductName("상품이름");
		product.setProductPrice(10000);
		product.setReadCount(5);

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

//	@Test
//	@DisplayName("장바구니 리스트 테스트")
//	public void cartList(){
//		List<CartProduct> cartProductList=cartProductRepository.findAll();
//		assertThat(cartProductList).isNotEmpty();
//		assertThat(cartProductList.get(0).getProduct().)
//	}

	@Test
	public void deleteCartProductTest() throws Exception{
		// 장바구니 상품 ID. 실제 테스트 환경에서는 적절한 ID로 변경하십시오.
		Long cartProductId = 1L;

		// delete 요청을 수행하고, 상태 코드가 OK인지 검증합니다.
		mockMvc.perform(delete("/cartProduct/{cartProductId}", cartProductId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
