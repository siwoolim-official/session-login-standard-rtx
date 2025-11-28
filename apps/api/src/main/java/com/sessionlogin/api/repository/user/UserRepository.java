package com.sessionlogin.api.repository.user;

import com.sessionlogin.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 필수는 아님 JpaRepository를 상속 받으면 자동으로 빈에 등록됨
public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인 용도
    Optional<User> findByEmail(String email);

    // 이메일 중복 확인용
    boolean existsByEmail(String email);

    // 닉네임 중복 확인
    boolean existsByNickname(String nickname);
}
