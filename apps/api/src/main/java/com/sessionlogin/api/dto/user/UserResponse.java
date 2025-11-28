package com.sessionlogin.api.dto.user;

import com.sessionlogin.api.domain.user.Role;
import com.sessionlogin.api.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final Long id;
    private final String email;
    private final String nickname;
    private final String address;
    private final Role role;
    private final int loginFailCnt;
    private final boolean isLocked;

    // Entity -> DTO 변환 정적 메서드
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getAddress(),
                user.getRole(),
                user.getLoginFailCnt(),
                user.isLocked()
        );
    }
}
