package com.rcplus.wanted.configs;

import org.springframework.http.HttpStatus;

import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BaseResponseStatus {
    

    SUCCESS(true,  "요청에 성공하였습니다.", OK),

    REQUEST_ERROR(false, "입력값을 확인해주세요.", BAD_REQUEST),

    EMPTY_JWT(false, "JWT를 입력해주세요.", UNAUTHORIZED),
    INVALID_JWT(false, "유효하지 않은 JWT입니다.", UNAUTHORIZED),
    INVALID_USER_JWT(false,"권한이 없는 유저의 접근입니다.", FORBIDDEN),

    USER_ID_EMPTY(false, "유저 아이디를 입력해주세요.", BAD_REQUEST),
    USER_NAME_EMPTY(false, "닉네임을 입력해주세요.", BAD_REQUEST),
    INVALID_USER_COUNTRY_CODE(false, "국가 코드를 확인해주세요.", BAD_REQUEST),
    INVALID_USER_PHONE_NUMBER(false, "전화번호를 확인해주세요.", BAD_REQUEST),
    INVALID_USER_MARKETING_EMAIL(false, "이메일 마케팅 정보 수신 필드를 확인해주세요.", BAD_REQUEST),
    INVALID_USER_MARKETING_PUSH(false, "푸시 알림 마케팅 정보 수신 필드를 확인해주세요.", BAD_REQUEST),
    INVALID_USER_MARKETING_SMS(false, "SMS 마케팅 정보 수신 필드를 확인해주세요.", BAD_REQUEST),
    DUPLICATE_BUSINESS_REG_CODE(false, "이미 존재하는 사업자등록번호입니다.", BAD_REQUEST),
    COMPANY_NOT_FOUND(false, "존재하지 않는 기업 아이디입니다.", BAD_REQUEST),

    POST_USERS_EMPTY_EMAIL(false, "이메일을 입력해주세요.", BAD_REQUEST),
    POST_USERS_INVALID_EMAIL(false, "이메일 형식을 확인해주세요.", BAD_REQUEST),
    POST_USERS_EXISTS_EMAIL(false, "중복된 이메일입니다.", BAD_REQUEST),
    RESPONSE_ERROR(false, "값을 불러오는데 실패하였습니다.", BAD_REQUEST),
    DUPLICATED_EMAIL(false, "중복된 이메일입니다.", BAD_REQUEST),
    FAILED_TO_LOGIN(false, "없는 아이디거나 비밀번호가 틀렸습니다.", BAD_REQUEST),
    DATABASE_ERROR(false, "데이터베이스 연결에 실패하였습니다.", BAD_REQUEST),
    SERVER_ERROR(false, "서버와의 연결에 실패하였습니다.", BAD_REQUEST),
    MODIFY_FAIL_USERNAME(false, "유저네임 수정 실패", BAD_REQUEST),
    PASSWORD_ENCRYPTION_ERROR(false, "비밀번호 암호화에 실패하였습니다.", BAD_REQUEST),
    PASSWORD_DECRYPTION_ERROR(false, "비밀번호 복호화에 실패하였습니다.", BAD_REQUEST);
  
    DUPLICATED_APPLICATION(false, "이미 지원하였습니다."),
    DUPLICATED_LIKE(false,"이미 좋아요 하셨습니다."),
    NO_EXISTS_LIKE(false,"좋아요를 하지 않으셨습니다."),
    NO_EXISTS_APPLICATION(false,"지원서가 존재하지 않습니다.")


    private final HttpStatus status;
    private final String result;
    private final String message;

    private BaseResponseStatus(boolean result, String message, HttpStatus status) {
        this.status = status;
        this.result = result ? "success" : "failure";
        this.message = message;
    }
}
