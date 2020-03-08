package com.zyaml.nai.Exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 客户端请求返回的错误码定义。此处系统事先定义的统一错误。
 * reasonCode三位数字是1000-9999之间，本处事先定义的错误在100-499之间。在validator自定义的错误，在500-999之间。
 * @author z994
 */
@ToString
public enum ErrorCode {
    /** 错误码及提示 */

    SUCCESS(HttpStatus.OK,200,"Success"),
    NULL(HttpStatus.BAD_REQUEST,1001,"not result"),
    PARAM_INVALID(HttpStatus.BAD_REQUEST, 1002, "This param is error."),
    SYSTEM_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "It's a unidentified error or a internal server fails. Please notice the administrator."),

    /**elastic**/
    ELASTIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,5001,"Elasticsearch 发生错误")
    ;

    @Getter
    private HttpStatus httpStatus;
    @Getter
    private int reasonCode;
    @Getter
    private String reasonPhrase;

    /**
     * 错误码定义
     *
     * @param httpStatus   http状态码
     * @param reasonCode
     * @param reasonPhrase
     */
    ErrorCode(HttpStatus httpStatus, int reasonCode, String reasonPhrase) {
        this.httpStatus = httpStatus;
        this.reasonCode = reasonCode;
        this.reasonPhrase = reasonPhrase;
    }
}
