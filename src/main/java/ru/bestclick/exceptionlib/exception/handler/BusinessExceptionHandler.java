package ru.bestclick.exceptionlib.exception.handler;


import jakarta.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bestclick.exceptionlib.exception.ApiError;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.bestclick.exceptionlib.exception.ServerException;
import ru.bestclick.exceptionlib.properties.BestclickExceptionProperties;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

  private final BestclickExceptionProperties properties;

  @PostConstruct
  public void init() {
    if (isDisableProperties(properties)) {
      log.info("Error log is disable");
      return;
    }
    log.info("Error log is enable. Business log: {}. Server log: {} ",
        properties.getLogging().getException().isBusiness(),
        properties.getLogging().getException().isServer());
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BusinessException.class)
  public ApiError handleBaseBusinessException(BusinessException ex) {
    logException(ex);
    return getBaseBusinessException(ex);
  }

  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServerException.class)
  public ApiError handleBaseInternalServerException(ServerException ex) {
    logException(ex);
    return getBaseInternalServerException(ex);
  }

  private boolean isDisableProperties(BestclickExceptionProperties properties) {
    return Objects.isNull(properties)
        || Objects.isNull(properties.getLogging())
        || Objects.isNull(properties.getLogging().getException());
  }

  private ApiError getBaseBusinessException(BusinessException ex) {
    return ApiError.builder()
        .code(ex.getCode())
        .message(ex.getMessage())
        .details(ex.getDetails())
        .createDate(validateTime(ex.getCreateDate()))
        .build();
  }

  private ApiError getBaseInternalServerException(ServerException ex) {
    return ApiError.builder()
        .code(ex.getCode())
        .message(ex.getMessage())
        .details(ex.getDetails())
        .createDate(validateTime(ex.getCreateDate()))
        .build();
  }

  private OffsetDateTime validateTime(OffsetDateTime dateTime) {
    if (Objects.isNull(dateTime)) {
      return OffsetDateTime.now();
    }
    return dateTime;
  }

  private void logException(Exception ex) {
    if (Objects.isNull(ex) || isDisableProperties(properties)) {
      return;
    }

    if (ex instanceof BusinessException && (properties.getLogging().getException().isBusiness())) {
      log.error("In BusinessExceptionHandler: ", ex);
    }

    if (ex instanceof ServerException && (properties.getLogging().getException().isServer())) {
      log.error("In BusinessExceptionHandler: ", ex);
    }
  }
}
