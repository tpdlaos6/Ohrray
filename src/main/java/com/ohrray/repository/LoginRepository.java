package com.ohrray.repository;

import com.ohrray.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Member, String> {


}
