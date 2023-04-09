package ru.bestclick.exceptionlib.exception;

import static ru.bestclick.exceptionlib.constant.ExceptionConstants.UNKNOWN_SERVICE_ERROR_CODE;

import java.io.Serial;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.bestclick.exceptionlib.util.MessageUtils;

@Data
@EqualsAndHashCode(callSuper = true)

public class ServerException extends RuntimeException {

  @Serial
  static final long serialVersionUID = -6572598947855086802L;

  private final String code;
  private final OffsetDateTime createDate;
  private String[] details;

  public ServerException(String code, OffsetDateTime createDate, String[] details) {
    super(MessageUtils.getMessage(code));
    this.code = code;
    this.createDate = createDate;
    this.details = details;
  }

  public ServerException(String code, String[] details) {
    super(MessageUtils.getMessage(code));
    this.code = code;
    this.createDate = OffsetDateTime.now();
    this.details = details;
  }

  public ServerException(String code) {
    super(MessageUtils.getMessage(code));
    this.code = code;
    this.createDate = OffsetDateTime.now();
  }

  public ServerException() {
    super(MessageUtils.getMessage(UNKNOWN_SERVICE_ERROR_CODE));
    this.code = UNKNOWN_SERVICE_ERROR_CODE;
    this.createDate = OffsetDateTime.now();
  }

  public ServerException(String code, String message) {
    super(message);
    this.code = code;
    this.createDate = OffsetDateTime.now();
  }
}
