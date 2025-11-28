package com.sessionlogin.api.dto.common;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * API 응답 표준화 클래스
 * @param <T> 응답 본문 타입
 */
@Getter
public class ApiResponse<T> {
    private final boolean success;  // 비즈니스 로직 성공 여부
    private final String message;   // 전달 메시지
    private final T data;           // 실제 응답 데이터
    private final ErrorResponse error; // 에러 정보 전달

    @Builder
    private ApiResponse(boolean success, String message, T data, ErrorResponse error) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    // 응답 팩토리 메서드

    /**
     * 데이터가 있는 성공 응답
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .error(null)
                .build();
    }

    /**
     * 데이터가 없는 성공 응답
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(null)
                .message(message)
                .error(null)
                .build();
    }

    /**
     * 유효성 검사가 없는 실패 응답, GlobalExceptionHandler에서 호출
     */
    public static <T> ApiResponse<T> failure(String code, String ErrMessage) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(null)
                .message(null)
                .error(new ErrorResponse(code, ErrMessage, null))
                .build();
    }

    /**
     * 유효성 검사가 결과가 있는 실패 응답, GlobalExceptionHandler에서 호출
     */
    public static <T> ApiResponse<T> failure(String code, String ErrMessage, List<ValidationError> validationErrors) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(null)
                .message(null)
                .error(new ErrorResponse(code, ErrMessage, validationErrors))
                .build();
    }

    /**
     * 오류 상세 정보를 담는 내부 클래스
     */
    @Getter
    private static class ErrorResponse {
        private final String code; // 오류 코드
        private final String message; // 오류 메시지
        private final List<ValidationError> validationErrors;


        protected ErrorResponse(String code, String message, List<ValidationError> validationErrors) {
            this.code = code;
            this.message = message;
            this.validationErrors = validationErrors;
        }
    }

    @Getter
    @Builder
    public static class ValidationError {
        private final String field; // 오류 필드 이름
        private final String message; // 필드 상세 오류 메시지
    }
}
