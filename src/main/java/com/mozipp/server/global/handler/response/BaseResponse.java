package com.mozipp.server.global.handler.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

    private final Boolean isSuccess;
    private final int code;
    private final String message;
    private final T result;

    private BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.result = null;
    }

    private BaseResponse(BaseResponseStatus status, T result) {
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.result = result;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    public static <T> BaseResponse<T> success(T result) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, result);
    }

    public static <T> BaseResponse<T> fail(BaseResponseStatus status) {
        return new BaseResponse<>(status);
    }

}