package com.sessionlogin.api.service.user;

import com.sessionlogin.api.dto.user.SignUpRequest;
import com.sessionlogin.api.dto.user.UserResponse;

public interface UserService {
    /**
     * 회원가입 메서드
     * @param request  회원가입 요청 DTO
     * @return 가입된 사용저 정보 응답 DTO
     */
    UserResponse signUp(SignUpRequest request);
}
