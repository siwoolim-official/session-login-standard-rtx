package com.sessionlogin.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // 엔터티임을 JPA에게 알림
@Table(name = "users") // 테이블명을 users로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 사용을 위한 디폴트 생성자 및 접근 제어
@AllArgsConstructor
@Getter
@Builder // Builder 패턴 사용
public class User {
    @Id // Pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값 자동 증가
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String nickname;

    @Column
    private String address;

    @Enumerated(EnumType.STRING) // Enum 타입임을 DB에 알림
    @Column(nullable = false)
    private Role role;
}
