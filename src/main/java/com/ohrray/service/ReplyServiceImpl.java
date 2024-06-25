package com.ohrray.service;

import com.ohrray.domain.CommunityReplyDTO;
import com.ohrray.domain.ProductQnADTO;
import com.ohrray.domain.ProductReviewDTO;
import com.ohrray.entity.*;
import com.ohrray.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final CommunityBoardRepository boardRepository;
    private final ProductRepository productRepository;
    private final ProductReviewRepository reviewRepository;
    private final ProductQnARepository productQnARepository;
    private final OrderDetailRepository orderDetailRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public CommunityReplyDTO addReply(CommunityReplyDTO communityReplyDTO,String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();
        CommunityBoard findBoard = boardRepository.findById(communityReplyDTO.getBid()).orElseThrow(EntityNotFoundException::new);
        CommunityReply communityReply = communityReplyDTO.addEntity(communityReplyDTO,findBoard, member);
        CommunityReply save = replyRepository.save(communityReply);
        CommunityReplyDTO savedDTO = communityReplyDTO.entityToDto(save);
        return savedDTO;
    }

    @Override
    public CommunityReplyDTO get(CommunityReplyDTO communityReplyDTO) {
        CommunityReply find = replyRepository.findByIds(communityReplyDTO.getId()).orElseThrow(EntityNotFoundException::new);
        return communityReplyDTO.entityToDto(find);
    }

    @Transactional
    @Override
    public CommunityReplyDTO updateReply(CommunityReplyDTO communityReplyDTO) {
        System.out.println("communityReplyDTO.getId() = " + communityReplyDTO.getId());
        CommunityReply find = replyRepository.findByIds(communityReplyDTO.getId()).orElseThrow(EntityNotFoundException::new);
        System.out.println("find.getContent() = " + find.getContent());
        System.out.println("find.getId() = " + find.getId());
        
        find.setContent(communityReplyDTO.getContent());

        return communityReplyDTO.entityToDto(find);
    }

    @Transactional
    @Override
    public void deleteReply(CommunityReplyDTO communityReplyDTO) {
        replyRepository.deleteById(communityReplyDTO.getId());
        System.out.println("이상이 없어야하는데?");
    }

    //목록가져와
    @Override
    public List<CommunityReplyDTO> getList(Long bid) {
        List<CommunityReply> getListReply = replyRepository.findByBoard(bid);
        List<CommunityReplyDTO> list = getListReply.stream().map(getList -> CommunityReplyDTO.builder()
                .id(getList.getId())
                .bid(getList.getCommunityBoard().getId())
                .member(getList.getMember())
                .content(getList.getContent())
                .modDate(getList.getModDate())
                .regDate(getList.getRegDate())
                .build()
        ).toList();
        return list;
    }

    @Override
    public List<ProductReviewDTO> getReviewList(Long pno) {
        List<ProductReview> getListReview = reviewRepository.findByProduct(pno);
        List<ProductReviewDTO> list = getListReview.stream().map(getList -> ProductReviewDTO.builder()
                .id(getList.getId())
                .pno(getList.getProduct().getId())
                .mid(1L)
                .review(getList.getReview())
                .answer(getList.getAnswer())
                .rating(getList.getRating())
                .modDate(getList.getModDate())
                .regDate(getList.getRegDate())
                .build()
        ).toList();
        return list;
    }

    @Override
    public ProductReviewDTO get(ProductReviewDTO productReviewDTO) {
        ProductReview find = reviewRepository.findById(productReviewDTO.getId()).orElseThrow(EntityNotFoundException::new);
        return productReviewDTO.changeDTO(find);
    }

    //리뷰 등록
    @Transactional
    @Override
    public ProductReviewDTO addReview(ProductReviewDTO productReviewDTO ,Long orderDetailId) {
        System.out.println("productReviewDTO.getReview() = " + productReviewDTO.getReview());
        Product product = productRepository.findById(productReviewDTO.getPno()).orElseThrow(EntityNotFoundException::new);
        ProductReview productReview = productReviewDTO.changeEntity(productReviewDTO, product);
        ProductReview savedproduct = reviewRepository.save(productReview);

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow();
        orderDetail.setReview(true);
        return  productReviewDTO.changeDTO(savedproduct);

    }

    @Override
    public ProductReviewDTO updateReview(ProductReviewDTO productReviewDTO) {
        ProductReview find = reviewRepository.findById(productReviewDTO.getId()).orElseThrow(EntityNotFoundException::new);
        find.setReview(productReviewDTO.getReview());
        find.setAnswer(productReviewDTO.getAnswer());
        find.setRating(productReviewDTO.getRating());

        return productReviewDTO.changeDTO(find);
    }

    @Override
    public void deleteReview(ProductReviewDTO productReviewDTO) {
        reviewRepository.deleteById(productReviewDTO.getId());
    }

    @Override
    public List<ProductQnADTO> getQNAList(Long pno) {
        List<ProductQnA> getListQnA = productQnARepository.findByProduct(pno);
        List<ProductQnADTO> list = getListQnA.stream().map(getList -> ProductQnADTO.builder()
                .id(getList.getId())
                .pid(getList.getProduct().getId())
                .mid(1L)
                .question(getList.getQuestion())
                .answer(getList.getAnswer())
                .modDate(getList.getModDate())
                .regDate(getList.getRegDate())
                .build()
        ).toList();
        return list;
    }

    @Override
    public ProductQnADTO getQNA(ProductQnADTO productQnADTO) {
        ProductQnA find = productQnARepository.findById(productQnADTO.getId()).orElseThrow(EntityNotFoundException::new);
        return productQnADTO.changeDTO(find);
    }

    @Override
    public ProductQnADTO addQNA(ProductQnADTO productQnADTO) {
        System.out.println("productReviewDTO.getReview() = " + productQnADTO.getQuestion());
        Product product = productRepository.findById(productQnADTO.getPid()).orElseThrow(EntityNotFoundException::new);
        ProductQnA productQnA = productQnADTO.changeEntity(productQnADTO, product);
        ProductQnA savedproduct = productQnARepository.save(productQnA);
        return  productQnADTO.changeDTO(savedproduct);
    }

    @Override
    public ProductQnADTO updateQNA(ProductQnADTO productQnADTO) {
        ProductQnA find = productQnARepository.findById(productQnADTO.getId()).orElseThrow(EntityNotFoundException::new);
        find.setQuestion(productQnADTO.getQuestion());
        find.setAnswer(productQnADTO.getAnswer());

        return productQnADTO.changeDTO(find);
    }

    @Override
    public void deleteQNA(ProductQnADTO productQnADTO) {
        productQnARepository.deleteById(productQnADTO.getId());
    }


}
