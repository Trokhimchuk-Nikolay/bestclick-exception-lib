package ru.bestclick.exceptionlib.exception;

import static ru.bestclick.exceptionlib.constant.ExceptionCodeConstants.UNKNOWN_SERVICE_ERROR_CODE;

import java.io.Serial;
import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6572598947855086802L;

  private final String code;
  private final String[] args;


  public ServerException(String code, String[] args) {
    this.code = code;
    this.args = args;
  }

  public ServerException(String code) {
    this.code = code;
    this.args = null;
  }

  public ServerException() {
    this.code = UNKNOWN_SERVICE_ERROR_CODE;
    this.args = null;
  }
}
