package com.sessionlogin.api.dto.user;

import com.sessionlogin.api.domain.user.Role;
import com.sessionlogin.api.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 입력값입니다.") // null, "", " " 등 불가
    @Email(message = "올바른 이메일 형식이 아닙니다.") // 기본적인 이메일 형식 검사
    private String email;

    @NotBlank(message = "비밀전호는 필수 입력값입니다.")
    @Size(min=10, max=20, message = "비밀번호는 10자 이상 20자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*?])[A-Za-z\\d!@#$%^&*?]+$",
            message = "비밀번호는 영문 대/소문자, 특수문자를 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    private String address;

    // DTO -> Entity 변환 메서드
    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword) // 암호화된 비밀번호 주입
                .nickname(this.nickname)
                .address(this.address)
                .role(Role.USER)
                .loginFailCnt(0)
                .isLocked(false)
                .build();
    }
}
