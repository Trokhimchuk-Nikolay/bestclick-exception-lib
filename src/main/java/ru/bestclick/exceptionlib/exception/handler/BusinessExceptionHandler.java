package ru.bestclick.exceptionlib.exception.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bestclick.exceptionlib.exception.ApiError;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.bestclick.exceptionlib.exception.InternalServerException;

import java.time.OffsetDateTime;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiError handleBaseBusinessException(BusinessException ex) {
        log.debug("BusinessException : {}", ex.toString());
        return getBaseBusinessException(ex);
    }

    @ExceptionHandler(InternalServerException.class)
    public ApiError handleBaseInternalServerException(InternalServerException ex) {
        log.debug("InternalServerException : {}", ex.toString());
        return getBaseInternalServerException(ex);
    }

    private ApiError getBaseBusinessException(BusinessException ex) {
        return ApiError.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(ex.getDetails())
                .createDate(checkTime(ex.getCreateDate()))
                .build();
    }

    private ApiError getBaseInternalServerException(InternalServerException ex) {
        return ApiError.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(ex.getDetails())
                .createDate(checkTime(ex.getCreateDate()))
                .build();
    }

    private OffsetDateTime checkTime(OffsetDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return OffsetDateTime.now();
        }
        return dateTime;
    }
}
