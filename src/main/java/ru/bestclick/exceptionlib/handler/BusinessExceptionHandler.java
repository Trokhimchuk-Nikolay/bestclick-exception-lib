package ru.bestclick.exceptionlib.handler;


import static ru.bestclick.exceptionlib.constant.ExceptionCodeConstants.MULTI_VALIDATION_ERROR_CODE;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.bestclick.exceptionlib.exception.ServerException;
import ru.bestclick.exceptionlib.exception.ValidationException;
import ru.bestclick.exceptionlib.helper.MessageAccessorHelper;
import ru.bestclick.exceptionlib.model.ApiError;
import ru.bestclick.exceptionlib.properties.BestclickExceptionProperties;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

  private final BestclickExceptionProperties properties;
  private final MessageAccessorHelper messageAccessorHelper;

  @PostConstruct
  public void init() {
    if (isDisableProperties(properties)) {
      log.info("Error lib is disable");
      return;
    }
    log.info("Error lib is enable");
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BusinessException.class)
  public ApiError handleBaseBusinessException(BusinessException ex) {
    logException(ex);
    return getBaseBusinessException(ex);
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ApiError handleValidationException(ValidationException ex) {
    logException(ex);
    return getValidationException(ex);
  }

  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServerException.class)
  public ApiError handleBaseInternalServerException(ServerException ex) {
    logException(ex);
    return getBaseInternalServerException(ex);
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public ApiError handleException(Exception ex) {
    logException(ex);
    return getUnknownException();
  }

  private boolean isDisableProperties(BestclickExceptionProperties properties) {
    return properties == null
        || !properties.isEnable();
  }

  private ApiError getUnknownException() {
    return ApiError.builder()
        .message("Unknown error")
        .build();
  }

  private ApiError getBaseBusinessException(BusinessException ex) {
    return ApiError.builder()
        .message(messageAccessorHelper.getMessage(ex.getCode(), ex.getArgs()))
        .build();
  }

  private ApiError getValidationException(ValidationException ex) {
    val details = ex.getValidationResults().stream()
        .map(result -> messageAccessorHelper.getMessage(result.getCode(), result.getArgs()))
        .toArray(String[]::new);
    return ApiError.builder()
        .message(messageAccessorHelper.getMessage(MULTI_VALIDATION_ERROR_CODE))
        .details(details)
        .build();
  }

  private ApiError getBaseInternalServerException(ServerException ex) {
    return ApiError.builder()
        .message(messageAccessorHelper.getMessage(ex.getCode(), ex.getArgs()))
        .build();
  }

  private void logException(Exception ex) {
    if (isDisableProperties(properties)) {
      return;
    }
    log.error("Error handled: ", ex);
  }
}
