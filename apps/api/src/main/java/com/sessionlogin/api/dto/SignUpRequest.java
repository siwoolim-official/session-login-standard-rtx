package com.sessionlogin.api.dto;

import com.sessionlogin.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private String nickname;
    private String address;

//    public User toEntity()
}
