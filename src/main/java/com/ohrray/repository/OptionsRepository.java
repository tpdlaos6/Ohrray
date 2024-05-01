package com.ohrray.repository;

import com.ohrray.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionsRepository extends JpaRepository<Options, Long> {

    List<Options> findByCartIdAndProductId(Long cartId, Long productId);


}
