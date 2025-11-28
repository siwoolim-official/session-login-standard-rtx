package com.sessionlogin.api.handler;

import com.sessionlogin.api.dto.common.ApiResponse;
import com.sessionlogin.api.exception.user.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 유표성 검사 실패 예외 처리
     * HTTP Status: 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 필드별 에러 정보 추출
        List<ApiResponse.ValidationError> validationErrors = bindingResult.getFieldErrors().stream()
                .map(fieldError -> ApiResponse.ValidationError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());


        // 오류 응답 생성
        ApiResponse<?> errorResponse = ApiResponse.failure(
                String.valueOf(httpStatus.value()),
                "입력 데이터의 유효성 검사에 실패했습니다.",
                 validationErrors
        );

        return ResponseEntity
                .status(httpStatus)
                .body(errorResponse);
    }

    @ExceptionHandler({
            EmailAlreadyExistsException.class
    })
    public ResponseEntity<ApiResponse<?>> handleCustomExceptions(RuntimeException ex) {
        HttpStatus httpStatus;

        // 예외 타입에 따라 HTTP 상태 코드 매핑
        if (ex instanceof EmailAlreadyExistsException) {
            httpStatus = HttpStatus.CONFLICT; // 409
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        }

        // 오류 응답 생성
        ApiResponse<?> errorResponse = ApiResponse.failure(
                String.valueOf(httpStatus.value()),
                ex.getMessage()
        );

        return ResponseEntity
                .status(httpStatus)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // 오류 응답 생성
        ApiResponse<?> errorResponse = ApiResponse.failure(
                String.valueOf(httpStatus.value()),
                "예상치 못한 서버 오류가 발생했습니다. 잠시 후 다시 시도해 주세요."
        );

        return ResponseEntity
                .status(httpStatus)
                .body(errorResponse);
    }
}
