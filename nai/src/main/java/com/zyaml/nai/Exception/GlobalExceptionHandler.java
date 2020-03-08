package com.zyaml.nai.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/**
 * 全局捕获rest controller抛出的异常。
 *
 * @author zzh
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 抓住所有的未知异常，并转出json格式。未知异常都认为是服务器内部错误。
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("handling exception ==> " + request.getDescription(true));
        log.error("RuntimeException：", ex);
        RestException rex = new RestException(ErrorCode.SYSTEM_FAIL);
        ErrorResponse res = new ErrorResponse(rex);
        return new ResponseEntity<>(res, rex.getHttpStatus());
    }

    /**
     * 参数绑定异常
     *
     * @param ex 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex, WebRequest request) {
        log.warn("handling BindException ==> " + request.getDescription(true));
        return wrapperBindingResult(ex.getBindingResult());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param ex 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("handling ValidException ==> " + request.getDescription(true));
        return wrapperBindingResult(ex.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ResponseEntity<ErrorResponse> wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());

        }

        RestException rex = new RestException(ErrorCode.PARAM_INVALID, msg.substring(2));
        ErrorResponse res = new ErrorResponse(rex);

        return new ResponseEntity<>(res, rex.getHttpStatus());
    }


    /**
     * 处理已知的错误。
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(RestException ex, WebRequest request) {
        log.warn("handling exception ==> " + request.getDescription(true));
        log.warn("RestException ==> {}, HttpStatus:{}, Reason: {}", ex.getErrorCode().name(), ex.getErrorCode().getHttpStatus().value(), ex.getErrorCode().getReasonPhrase());
        return new ResponseEntity<>(new ErrorResponse(ex), ex.getHttpStatus());
    }

}
