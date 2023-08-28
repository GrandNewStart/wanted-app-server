package com.rcplus.wanted.configs;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    
    SUCCESS(true,  "요청에 성공하였습니다."),
    REQUEST_ERROR(false, "입력값을 확인해주세요."),
    EMPTY_JWT(false, "JWT를 입력해주세요."),
    INVALID_JWT(false, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,"권한이 없는 유저의 접근입니다."),
    POST_USERS_EMPTY_EMAIL(false, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false, "중복된 이메일입니다."),
    RESPONSE_ERROR(false, "값을 불러오는데 실패하였습니다."),
    DUPLICATED_EMAIL(false, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false, "없는 아이디거나 비밀번호가 틀렸습니다."),
    DATABASE_ERROR(false, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, "서버와의 연결에 실패하였습니다."),
    MODIFY_FAIL_USERNAME(false, "유저네임 수정 실패"),
    PASSWORD_ENCRYPTION_ERROR(false, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, "비밀번호 복호화에 실패하였습니다.");

    private final String result;
    private final String message;

    private BaseResponseStatus(boolean result, String message) {
        this.result = result ? "success" : "failure";
        this.message = message;
    }
}
