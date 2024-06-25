package com.ohrray.repository;

import com.ohrray.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionsRepository extends JpaRepository<Options, Long> {

    List<Options> findByProductId(Long productId);


    @Query("SELECT o.size FROM Options o WHERE o.product.id = :productId")
    List<Integer> findSizesByProductId(@Param("productId") Long productId);

    @Query("SELECT o.color FROM Options o WHERE o.product.id = :productId")
    List<String> findColorsByProductId(@Param("productId") Long productId);

}
