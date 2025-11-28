package com.sessionlogin.api.service.user.impl;

import com.sessionlogin.api.domain.user.User;
import com.sessionlogin.api.dto.user.SignUpRequest;
import com.sessionlogin.api.dto.user.UserResponse;
import com.sessionlogin.api.exception.user.EmailAlreadyExistsException;
import com.sessionlogin.api.repository.user.UserRepository;
import com.sessionlogin.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Override
    @Transactional // 트랜젝션 경계 설정
    public UserResponse signUp(SignUpRequest request) {
        // Eamil 중복 검사
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException(request.getEmail());
                });

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // User 엔터티 생성
        User newUser = request.toEntity(encodedPassword);

        // User DB 저장
        User saveedUser = userRepository.save(newUser);

        // saveedUser를 응답 DTO로 변환하여 반환
        return UserResponse.from(saveedUser);
    }
}
