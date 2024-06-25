package com.ohrray.repository;

import com.ohrray.entity.CommunityReply;
import com.ohrray.entity.ProductQnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductQnARepository extends JpaRepository<ProductQnA,Long> {
    @Query(" select r " +
            " from ProductQnA r  " +
            " where r.product.id= :pno")
    List<ProductQnA> findByProduct(@Param("pno") Long pno);
}
