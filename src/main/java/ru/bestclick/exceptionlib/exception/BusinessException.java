package ru.bestclick.exceptionlib.exception;

import static ru.bestclick.exceptionlib.constant.ExceptionCodeConstants.UNKNOWN_SERVICE_ERROR_CODE;

import java.io.Serial;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -3332598979055089070L;

  private final String code;
  private final String[] args;

  public BusinessException(String code, String[] args) {
    this.code = code;
    this.args = args;
  }

  public BusinessException(String code) {
    this.code = code;
    this.args = null;
  }

  public BusinessException() {
    this.code = UNKNOWN_SERVICE_ERROR_CODE;
    this.args = null;
  }
}
