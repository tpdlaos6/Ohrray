package com.ohrray.repository;

import com.ohrray.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionsRepository extends JpaRepository<Options, Long> {

//    List<Options> findByCartIdAndProductId(Long cartId, Long productId);

    List<Options> findByProductId(Long productId);


}
