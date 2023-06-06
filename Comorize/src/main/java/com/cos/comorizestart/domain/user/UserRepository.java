package com.cos.comorizestart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션 없어도 IoC 자동 등록 (JpaRepository 상속 덕분에)
public interface UserRepository extends JpaRepository<User, Integer>{

}

