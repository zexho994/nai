package com.zyaml.nai.Exception;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 返回数据格式
 * @Author: 994
 * @Date: 2020-03-08 14:45
 */
@Data
public class Resp {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "data")
    private Object data;

    @JsonProperty(value = "status")
    private HttpStatus httpStatus;

    public Resp(int code,String message){
        this.code = code;
        this. message = message;
    }

    public Resp(ErrorCode errorCode, Object data){
        this.code = errorCode.getReasonCode();
        this.message = errorCode.getReasonPhrase();
        this.httpStatus = errorCode.getHttpStatus();
        this.data = data;
    }

    /**
     * 默认为code：200 成功格式
     * @param data
     */
    public Resp(Object data){
        this.code = ErrorCode.SUCCESS.getReasonCode();
        this.message = ErrorCode.SUCCESS.getReasonPhrase();
        this.httpStatus = ErrorCode.SUCCESS.getHttpStatus();
        this.data = data;
    }

}
