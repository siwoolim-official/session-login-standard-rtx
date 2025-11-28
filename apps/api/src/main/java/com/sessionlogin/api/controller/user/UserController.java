package com.sessionlogin.api.controller.user;

import com.sessionlogin.api.dto.common.ApiResponse;
import com.sessionlogin.api.dto.user.SignUpRequest;
import com.sessionlogin.api.dto.user.UserResponse;
import com.sessionlogin.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     *
     * @param request 회원가입 요청 DTO
     * @return 성공시 HTTP 201 Create 상태 코드와 사용자 정보가 담긴 ApiResponse 반환
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponse>> signUp(@RequestBody @Validated SignUpRequest request) {

        UserResponse userResponse = userService.signUp(request);

        ApiResponse<UserResponse> responseBody = ApiResponse.success(
                userResponse,
                "회원가입이 성공적으로 처리되었습니다.");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }
}
