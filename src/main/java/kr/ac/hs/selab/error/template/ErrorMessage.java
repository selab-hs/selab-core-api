package kr.ac.hs.selab.error.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    /**
     * CONFLICT ERROR RESPONSE MESSAGE
     **/
    CONFLICT_ERROR("E-C-0001", "예기치 못한 에러가 발생했습니다."),
    METHOD_ARGUMENT_NOT_VALID_ERROR("E-C-0002", "유효성 검사에서 문제가 발생했습니다."),

    /**
     * MEMBER ERROR RESPONSE MESSAGE
     **/
    MEMBER_EMAIL_INVALID_ARGUMENT_ERROR("E-M-0001",
            "회원 이메일 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NAME_INVALID_ARGUMENT_ERROR("E-M-0002",
            "회원 이름 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NICKNAME_INVALID_ARGUMENT_ERROR("E-M-0003",
            "회원 닉네임 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_STUDENT_ID_INVALID_ARGUMENT_ERROR("E-M-0004",
            "회원 학번 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR("E-M-0005",
            "회원 비밀번호 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_TERMS_INVALID_ARGUMENT_ERROR("E-M-0006",
            "회원 정책 규칙에서 문제가 발생했습니다."),
    MEMBER_EMAIL_DUPLICATION_ERROR("E-M-0007",
            "중복된 회원 이메일 입력입니다."),
    MEMBER_STUDENT_ID_DUPLICATION_ERROR("E-M-0008",
            "중복된 회원 학번 입력입니다."),
    MEMBER_NICKNAME_DUPLICATION_ERROR("E-M-0009",
            "중복된 회원 닉네임 입력입니다."),
    MEMBER_NOT_EXISTS_ERROR("E-M-0010", "존재하지 않는 회원 입니다."),

    /**
     * AUTH ERROR RESPONSE MESSAGE
     */
    AUTH_INTERNAL_AUTHENTICATION_SERVICE_ERROR("E-A-0001", "존재하지 않는 회원 입니다."),
    AUTH_BAD_CREDENTIALS_ERROR("E-A-0002", "자격 증명에 실패하였습니다."),
    AUTH_LOCKED_ERROR("E-A-0003", "잠긴 계정입니다."),
    AUTH_DISABLE_ERROR("E-A-0004", "비활성화된 계정입니다."),
    AUTH_ACCOUNT_EXPIRED_ERROR("E-A-0005", "만료된 계정입니다."),
    AUTH_CREDENTIAL_EXPIRED_ERROR("E-A-0006", "비밀번호가 만료되었습니다."),

    /**
     * BOARD ERROR RESPONSE MESSAGE
     **/
    BOARD_NOT_EXISTS_ERROR("E-B-0001", "존재하지 않는 게시판입니다."),

    /**
     * POST ERROR RESPONSE MESSAGE
     */
    POST_NOT_EXISTS_ERROR("E-P-0001", "존재하지 않는 게시글입니다."),

    /**
     * COMMENT ERROR RESPONSE MESSAGE
     */
    COMMENT_NOT_EXISTS_ERROR("E-C-0001", "존재하지 않는 댓글입니다."),

    /**
     * TERMS ERROR RESPONSE MESSAGE
     **/
    TERMS_NOT_EXISTS_ERROR("E-T-0001", "존재하지 않는 약관 카테코리입니다."),

    /**
     * NOTICE ERROR RESPONSE MESSAGE
     */
    NOTICE_NOT_EXISTS_ERROR("E-N-0001", "존재하지 않는 공지사항입니다."),

    /**
     * COMMENT ERROR RESPONSE MESSAGE
     */
    NOTICE_COMMENT_NOT_EXISTS_ERROR("E-NC-0001", "존재하지 않는 공지사항의 댓글입니다."),

    /**
     * FREE POST ERROR RESPONSE MESSAGE
     */
    FREE_POST_NOT_EXISTS_ERROR("E-F-0001", "존재하지 않는 자유게시글입니다."),

    /**
     * FREE POST COMMENT ERROR RESPONSE MESSAGE
     */
    FREE_POST_COMMENT_NOT_EXISTS_ERROR("E-FC-0001", "존재하지 않는 자유게시글의 댓글입니다."),

    /**
     * CORE QA ERROR RESPONSE MESSAGE
     */
    CORE_QA_NOT_EXISTS_ERROR("E-CQ-0001", "존재하지 않는 QA 항목입니다."),
    ;

    private final String code;
    private final String detail;
}