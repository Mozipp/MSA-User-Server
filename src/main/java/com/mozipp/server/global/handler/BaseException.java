package com.mozipp.server.global.handler;

import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseResponseStatus status;
}
