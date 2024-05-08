package com.ohrray;

import com.ohrray.entity.Cart;
import com.ohrray.entity.Member;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class OhrrayApplicationTests {
	@Autowired LoginRepository loginRepository;
	@Autowired ProductRepository productRepository;
	@Autowired CartService cartService;
	@Autowired CartRepository cartRepository;
    @Autowired MemberRepository memberRepository;

	@Autowired private MockMvc mockMvc;

//	public Product saveProduct(){
//		List<Member> members=new ArrayList<>();
//
//		for(int i=1;i<10;i++){
//			Product product=new Product();
//			product.setId((long)i);
//			product.setProductName(i+"만원짜리 상품");
//			product.setProductPrice(i*10000);
//			product.setMember(members.get(i));
//		}
//
//		return productRepository.save(product);
//	}



	public void saveMember() {
		List<Member> members = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			Member member = new Member();
			member.setEmail("test" + i + "@test.com");
			members.add(memberRepository.save(member));
		}
	}

	@Test
	public void saveCart(){
		List<Member> members=new ArrayList<>();

		for(int i=1;i<=10;i++){
			Cart cart=new Cart();
			cart.setProductCount(1);
			cart.setMember(members.get(i));
//			cart.setMember(members.get(i));
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
