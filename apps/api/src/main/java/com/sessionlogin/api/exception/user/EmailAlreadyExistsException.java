package com.sessionlogin.api.exception.user;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("이미 사용중인 이메일입니다. " + email);
    }
}
