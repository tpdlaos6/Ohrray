package com.ohrray.domain;

import com.ohrray.entity.Product;
import com.ohrray.entity.ProductQnA;
import com.ohrray.entity.ProductReview;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQnADTO {

    private Long id;

    private Long pid;

    private Long mid;

    private String question;

    private String answer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    public ProductQnA changeEntity(ProductQnADTO productQnADTO, Product product){
        ProductQnA productQnA = new ProductQnA();
        productQnA.setProduct(product);
        productQnA.setQuestion(productQnADTO.getQuestion());
        productQnA.setAnswer(productQnADTO.getAnswer());
        return productQnA;
    }

    public ProductQnADTO changeDTO(ProductQnA productQnA){
        this.id =productQnA.getId();
        this.pid=productQnA.getProduct().getId();
        this.mid = 1L;
        this.question= productQnA.getQuestion();
        this.answer= productQnA.getAnswer();
        this.regDate=productQnA.getRegDate();
        this.modDate=productQnA.getModDate();
        return this;
    }

}
