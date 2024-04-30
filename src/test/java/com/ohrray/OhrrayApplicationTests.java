package com.ohrray;

import com.ohrray.entity.Cart;
import com.ohrray.entity.Member;
import com.ohrray.entity.Product;
import com.ohrray.repository.CartRepository;
import com.ohrray.repository.LoginRepository;
import com.ohrray.repository.MemberRepository;
import com.ohrray.repository.ProductRepository;
import com.ohrray.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OhrrayApplicationTests {
	@Autowired LoginRepository loginRepository;
	@Autowired ProductRepository productRepository;
	@Autowired CartService cartService;
	@Autowired CartRepository cartRepository;
    @Autowired MemberRepository memberRepository;

	@Autowired private MockMvc mockMvc;

	public Product saveProduct(){
		Product product=new Product();

		product.setProductName("상품이름");
		product.setProductPrice(10000);
		product.setReadCount(5);

		return productRepository.save(product);
	}

	@Test
	public void saveMember(){

		for(int i=21;i<=30;i++){
		Member member=new Member();
		member.setEmail("test"+i+"@test.com");
		memberRepository.save(member);
		}
	}


	public void saveCart(){

		for(int i=21;i<=30;i++){
			Cart cart=new Cart();
			cart.setProductCount(1);
			cart.setId((long) i);
			cartRepository.save(cart);
		}
	}


//	@DisplayName("장바구니 담기 테스트")
//	public void addCart(){
//		Product product=saveProduct();
//		Member member=saveMember();
//
//		CartDTO cartDTO =new CartDTO();
//		cartDTO.setProductId(product.getId());
//		cartDTO.setCount(5);
//
//		Long cartProductId= cartService.addCart(cartDTO, member.getEmail());
//		Cart cartProduct=cartRepository.findById(cartProductId)
//				.orElseThrow(EntityNotFoundException::new);
//
//		assertEquals(product.getId(), cartProduct.getProduct().getId());
//		assertEquals(cartDTO.getCount(), cartProduct.getProductCount());
//	}

//	@Test
//	@DisplayName("장바구니 리스트 테스트")
//	public void cartList(){
//		List<CartProduct> cartProductList=cartProductRepository.findAll();
//		assertThat(cartProductList).isNotEmpty();
//		assertThat(cartProductList.get(0).getProduct().)
//	}

}
